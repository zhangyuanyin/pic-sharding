package com.gome.dubbo.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.gome.dao.PictureInfoDao;
import com.gome.domain.PictureInfo;
import com.gome.dubbo.PictureDubboService;
import com.gome.service.HttpFileService;
import com.gome.util.GomeCommonUtil;

/**
 * <dt>图片服务处理类</dt>
 * @author yyzhang
 * @since 2018年2月1日18:20:23
 */
@Service(version = "1.0.0")
public class PictureDubboServiceImpl implements PictureDubboService {
	@Autowired
	private PictureInfoDao pictureDao;
	@Autowired
	private HttpFileService httpFileService;

	@Override
	public boolean delPicture(PictureInfo pictureInfo) {
		GomeCommonUtil.checkObject(pictureInfo);
		pictureInfo.setIsWaste("Y"); // 置为失效
		return pictureDao.updateByPrimaryKey(pictureInfo) > 0 ? true : false;
	}

	@Override
	public boolean addPicture(PictureInfo pictureInfo) {
		GomeCommonUtil.checkObject(pictureInfo);
		return pictureDao.insert(pictureInfo) > 0 ? true : false;
	}

	@Override
	public PictureInfo findPictureByPicId(String picId) {
		GomeCommonUtil.checkValue("picId", picId);
		return pictureDao.selectByPrimaryKey(picId);
	}

	@Override
	public List<PictureInfo> findPictureByAppNo(String appNo) {
		GomeCommonUtil.checkValue("appNo", appNo);
		return pictureDao.findPictureByAppNo(appNo);
	}

	@Override
	public List<PictureInfo> findPictures(List<String> appNos, List<String> paperTypes) {
		
		return null;
	}

	@Override
	public PictureInfo findPictureByAppNoAndSaveName(String appNo, String saveName) {
		GomeCommonUtil.checkValue("appNo", appNo);
		GomeCommonUtil.checkValue("saveName", saveName);
		return pictureDao.findPictureByAppNoAndSaveName(appNo, saveName);
	}

	@Override
	public PictureInfo findPictureByAppNoAndSaveName(String appNo, List<String> saveNames) {
		GomeCommonUtil.checkValue("appNo", appNo);
		GomeCommonUtil.checkObject(saveNames);
		return null;
	}

	/**
	 * 修改状态即可，非真实删除
	 */
	@Override
	public boolean updatePicture(PictureInfo pictureInfo) {
		GomeCommonUtil.checkObject(pictureInfo);
		return pictureDao.updateByPrimaryKey(pictureInfo) > 0 ? true : false;
	}

	@Override
	public List<PictureInfo> getPictureList(String sysNo, String appNo, String itemCode) {
		GomeCommonUtil.checkValue("appNo", appNo);
		GomeCommonUtil.checkValue("sysNo", sysNo);
		GomeCommonUtil.checkValue("itemCode", itemCode);
		return pictureDao.getPictureList(sysNo, appNo, itemCode);
	}

	@Override
	public Map<String, String> upload(List<File> files, List<String> fileNames, String... params) {
		return httpFileService.upload(files, fileNames, params[0]);
	}

	@Override
	public List<String> download(List<PictureInfo> dataList, String filePath) {
		List<String> resultList = new ArrayList<String>();
		for (PictureInfo pictureInfo : dataList) {
			resultList.addAll(httpFileService.download(pictureInfo, filePath));
		}
		return resultList;
	}

}
