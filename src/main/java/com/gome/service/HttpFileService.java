package com.gome.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.gome.domain.PictureInfo;

/**
 * <dt>HTTP 服务处理接口</dt>
 * @author yyzhang
 * @since 2018年2月1日16:59:09
 */
public interface HttpFileService {
	
	/**
	 * 图片上传接口
	 * @param files
	 * @param fileNames
	 * @param sysNo 
	 * @return
	 */
	Map<String, String> upload(List<File> files, List<String> fileNames, String sysNo);
	
	/**
	 * 图片下载接口
	 * @param pictureInfo
	 * @param filePath
	 * @return
	 */
	List<String> download(PictureInfo pictureInfo, String filePath);
}
