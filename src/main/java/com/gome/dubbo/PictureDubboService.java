package com.gome.dubbo;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.gome.domain.PictureInfo;

/**
 * <dt>图片dubbo服务处理接口</dt>
 * @author yyzhang
 * @since 2018年2月1日16:53:18
 */
public interface PictureDubboService {
	
	/**
	 * 删除图片
	 * @param pictureInfo
	 * @return
	 */
	boolean delPicture(PictureInfo pictureInfo);
	
	/**
	 * 增加图片
	 * @param pictureInfo
	 * @return
	 */
	boolean addPicture(PictureInfo pictureInfo);
	
	/**
	 * 通过图片ID，查找图片信息
	 * @param picId
	 * @return
	 */
	PictureInfo findPictureByPicId(String picId);
	
	/**
	 * 通过进件号，查找图片信息
	 * @param appNo
	 * @return
	 */
	List<PictureInfo> findPictureByAppNo(String appNo);
	
	/**
	 * 通过进件号、图片类型，批量查询图片信息
	 * @param appNos
	 * @param paperTypes
	 * @return
	 */
	List<PictureInfo> findPictures(List<String> appNos, List<String> paperTypes);
	
	/**
	 * 通过进件号、文件名称，查询图片信息
	 * @param appNo
	 * @param saveName
	 * @return
	 */
	PictureInfo findPictureByAppNoAndSaveName(String appNo, String saveName);
	
	/**
	 * 通过进件号、文件名称，查询图片信息
	 * @param appNo
	 * @param saveNames
	 * @return
	 */
	PictureInfo findPictureByAppNoAndSaveName(String appNo, List<String> saveNames);
	
	/**
	 * 更新图片信息
	 * @param pictureInfo
	 * @return
	 */
	boolean updatePicture(PictureInfo pictureInfo);
	
	/**
	 * 通过系统号、进件号、类型编号，获取文件信息
	 * @param sysName
	 * @param appNo
	 * @param itemCode
	 * @return
	 */
	List<PictureInfo> getPictureList(String sysNo, String appNo, String itemCode);
	
	/**
	 * 上传图片
	 * @param files
	 * @param fileNames
	 * @param params
	 * @return
	 */
	Map<String, String> upload(List<File> files, List<String> fileNames, String ...params);
	
	/**
	 * 下载图片
	 * @param dataList
	 * @param filePath
	 * @return
	 */
	List<String> download(List<PictureInfo> dataList, String filePath);
}
