package com.gome.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.alibaba.fastjson.JSON;
import com.gome.domain.BodyModel;
import com.gome.domain.FileModel;
import com.gome.domain.PictureInfo;
import com.gome.service.HttpFileService;
import com.gome.util.GomeCommonUtil;

/**
 * <dt>图片上传、下载服务处理类</dt>
 * 
 * @author yyzhang
 * @since 2018年2月1日17:31:37
 */
@Service
@ConfigurationProperties(prefix = "config")	//前缀  
@PropertySource(value = "classpath:config/picture.properties")	//配置文件路径 
public class HttpFileServiceImpl implements HttpFileService {

	@Override
	public Map<String, String> upload(List<File> files, List<String> fileNames, String sysNo) {
		this.logger.info("----------【UPLOAD】调用文件服务系统上传图片开始----------");
		this.logger.info("【文件名称】：" + fileNames);

		Map<String, String> dataMap = Collections.emptyMap();
		String requestUrl = "";
		try {
			if (SYS_NO_MJ.equals(sysNo))
				requestUrl = mjRequestUrl.get();
			else
				requestUrl = mfRequestUrl.get();

			HttpPost httpPost = new HttpPost(requestUrl);
			MultipartEntity partEntity = new MultipartEntity();
			this.logger.info("【请求服务地址】：{}" + fileNames);

			StringBody isStatic = new StringBody("1", Charset.forName("UTF-8"));
			StringBody sysName = new StringBody(SYS_NO_MJ.equals(sysNo) ? mjSysNo : mfSysNo, Charset.forName("UTF-8"));

			for (int i = 0; i < fileNames.size(); i++) {
				String fileName = fileNames.get(i);
				if (GomeCommonUtil.isChinese(fileNames.get(i).toCharArray())) {
					this.fileName = MD5Encoder.encode(fileName.getBytes());
					dataMap.put(this.fileName, fileName);
					ContentBody contentBody = new ByteArrayBody(GomeCommonUtil.toFileByte(files.get(i)), this.fileName);
					partEntity.addPart(this.fileName, contentBody);
				} else {
					ContentBody contentBody = new ByteArrayBody(GomeCommonUtil.toFileByte(files.get(i)), fileName);
					partEntity.addPart(fileName, contentBody);
				}
			}
			partEntity.addPart("sysName", sysName);
			partEntity.addPart("isStatic", isStatic);
			httpPost.setEntity(partEntity);
			this.logger.info("【请求服务参数】：" + partEntity.getContent());

			HttpEntity resEntity = this.execute(httpPost);
			if(null != resEntity){
                String result = EntityUtils.toString(resEntity, Charset.forName("UTF-8"));
                logger.info("【响应结果】：" + result);
                dataMap = this.parseValue(result, sysNo, requestUrl);
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.logger.info("----------【UPLOAD】调用文件服务系统上传图片结束----------");
		
		return dataMap;
	}

	@Override
	public List<String> download(PictureInfo pictureInfo, String filePath) {
		this.logger.info("----------【DOWNLOAD】调用文件服务系统下载图片开始----------");
		List<String> fileNameList = new ArrayList<String>();
    	InputStream in = null;
    	FileOutputStream out = null;
    	String requestUrl = "", curSysNo = "";
    	
    	if (SYS_NO_MJ.equals(pictureInfo.getSysNo())) {
    		requestUrl = mjRequestUrl.get();
    		curSysNo = this.mjCurSysNo;
    	} else {
    		requestUrl = mfRequestUrl.get();
    		curSysNo = this.mfCurSysNo;
    	}
    	
    	StringBuffer url = new StringBuffer(requestUrl);
    	url.append(pictureInfo.getSysNo()).append("/download")		// 请求文件下载服务的系统名称
    	   .append("?").append("curSysNo=").append(curSysNo)		// 请求文件下载服务的系统编码
    	   .append("&").append("key=").append(pictureInfo.getkId())	// 上传文件时返回的文件唯一标识key
    	   .append("&").append("type=").append(1);					// 操作类型 1:下载, 0:查看
        this.logger.info("【影像系统-下载】新文件服务接口地址：" + url);
        
        try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpGet get = new HttpGet(url.toString());
			HttpResponse response = httpClient.execute(get);
			
			// 获取并验证执行结果
			int code = response.getStatusLine().getStatusCode();
			if(code == HttpStatus.SC_OK){
				String fileName = "";
				in = response.getEntity().getContent();
				Header[] headers = response.getAllHeaders();
				for (Header header : headers) {
					if(FILE_NAME.equals(header.getName())){
						fileName = URLDecoder.decode(header.getValue(), "UTF-8");
						break;
					}
				}
				fileNameList.add(fileName);
				this.logger.info("获取参数信息："+fileName+" -- "+in);
				
				// 判断文件路径是否存在，不存在创建对应路径
				filePath += pictureInfo.getAppNo() + "/";
				File file = new File(filePath);
				if(!file.isDirectory()){
					file.mkdir();
				}
				
				// 判断文件是否存在，不存在创建新文件
				file = new File(filePath + fileName);
				if(!file.exists()){
					file.createNewFile();
				}
				
				// 将输入流写入到新文件中
				out = new FileOutputStream(file);
				int i = 0;
				while(!((i = in.read()) == -1)){
					out.write(i);
				}
				out.flush();
			}else {
				this.logger.info("【影像系统-下载】失败！！！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
        this.logger.info("----------【DOWNLOAD】调用文件服务系统下载图片结束----------");
        
        return fileNameList;
	}

	/**
	 * 执行请求统一入口
	 * @param httpPost
	 */
	private HttpEntity execute(Object reqType) {
		HttpClient httpClient = new DefaultHttpClient();
		HttpResponse response = null;
		HttpEntity resEntity = null;
		try {
			if (reqType instanceof HttpPost) {
				response = httpClient.execute((HttpPost) reqType);
			} else {
				response = httpClient.execute((HttpGet) reqType);
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		int statusCode = response.getStatusLine().getStatusCode();
		this.logger.info("【响应结果】：" + statusCode);

		// 获取响应对象
		if (statusCode == HttpStatus.SC_OK) {
			resEntity = response.getEntity();
		}
		return resEntity;
	}
	
	/**
     * 解析json
     * @param result json
     * @return
     */
    private Map<String, String> parseValue(String result, String sysNo, String requestUrl){
    	Map<String, String> dataMap = new HashMap<String, String>();
        StringBuilder picPaths = new StringBuilder();
        //拼装地址
        String downloadUrl = requestUrl.replaceAll("upload", "download");
        String curSysNo = SYS_NO_MJ.equals(sysNo) ? this.mjCurSysNo : this.mfCurSysNo;
        //我知道GSON可以直接转为MAP
        FileModel fileModel = JSON.parseObject(result, FileModel.class);
        
        if(null != fileModel.getBody()){
            for(BodyModel bodyModel : fileModel.getBody()){
            	if(dataMap.containsKey(bodyModel.getFieldName())) {
            		dataMap.put(dataMap.get(bodyModel.getFieldName()), bodyModel.getKey());
            	}else {
            		dataMap.put(bodyModel.getFieldName(), bodyModel.getKey());
            	}
                picPaths.append(",").append(downloadUrl).append("?")
                		.append(curSysNo).append("&key=").append(bodyModel.getKey());
            }
            dataMap.put("picPaths", picPaths.substring(1));
        }
        dataMap.put("state", fileModel.getState());
        dataMap.put("status", fileModel.getStatus());
        dataMap.put("showMessage", fileModel.getShowMessage());
        
        return dataMap;
    }

	public void setMjSysNo(String mjSysNo) {
		this.mjSysNo = mjSysNo;
	}

	public void setMjCurSysNo(String mjCurSysNo) {
		this.mjCurSysNo = mjCurSysNo;
	}

	public void setMjUrl(String mjUrl) {
		this.mjUrl = mjUrl;
	}

	public void setMfSysNo(String mfSysNo) {
		this.mfSysNo = mfSysNo;
	}

	public void setMfCurSysNo(String mfCurSysNo) {
		this.mfCurSysNo = mfCurSysNo;
	}

	public void setMfUrl(String mfUrl) {
		this.mfUrl = mfUrl;
	}

	// 定义美借请求地址
	private final ThreadLocal<String> mjRequestUrl = new ThreadLocal<String>() {
		@Override
		protected String initialValue() {
			return mjUrl + mjSysNo + "/upload";
		}
	};
	// 定义非美借请求地址
	private final ThreadLocal<String> mfRequestUrl = new ThreadLocal<String>() {
		@Override
		protected String initialValue() {
			return mfUrl + mfSysNo + "/upload";
		}
	};

	private Logger logger = LoggerFactory.getLogger(getClass());
	private static final String FILE_NAME = "file_name"; // 文件名称
	private static final String SYS_NO_MJ = "mj"; // 美借系统标识符
	private String fileName;

	// 美借配置信息
	@Value("${pic.mj.sysNo}")
	private String mjSysNo;
	@Value("${pic.mj.curSysNo}")
	private String mjCurSysNo;
	@Value("${pic.mj.url}")
	private String mjUrl;

	// 美分配置信息
	@Value("${pic.mf.sysNo}")
	private String mfSysNo;
	@Value("${pic.mf.curSysNo}")
	private String mfCurSysNo;
	@Value("${pic.mf.url}")
	private String mfUrl;
}
