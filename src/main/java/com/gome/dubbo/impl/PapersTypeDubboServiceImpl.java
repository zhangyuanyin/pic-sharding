package com.gome.dubbo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.gome.dao.PapersTypeDao;
import com.gome.domain.PapersType;
import com.gome.domain.PapersTypeKey;
import com.gome.dubbo.PapersTypeDubboService;
import com.gome.util.GomeCommonUtil;

/**
 * <dt>图片类型服务处理类</dt>
 * @author yyzhang
 * @since 2018年2月1日18:22:44
 */
@Service(version = "1.0.0")
public class PapersTypeDubboServiceImpl implements PapersTypeDubboService {
	@Autowired
	private PapersTypeDao papersTypeDao;

	@Override
	public List<PapersType> findPapersTypeBySysNo(String sysNo) {
		GomeCommonUtil.checkValue("sysNo", sysNo);
		return papersTypeDao.findPapersTypeBySysNo(sysNo);
	}

	@Override
	public PapersType findByPrimaryKey(String sysNo, String itemCode) {
		GomeCommonUtil.checkValue("sysNo", sysNo);
		GomeCommonUtil.checkValue("itemCode", itemCode);
		PapersTypeKey typeKey = new PapersTypeKey();
		typeKey.setItemCode(itemCode);
		typeKey.setSysNo(sysNo);
		return papersTypeDao.selectByPrimaryKey(typeKey);
	}

	@Override
	public boolean addPapersType(PapersType papersType) {
		GomeCommonUtil.checkObject(papersType);
		return papersTypeDao.insert(papersType) > 0 ? true : false;
	}

	/**
	 * 修改状态即可，非真实删除
	 */
	@Override
	public boolean delPapersType(PapersType papersType) {
		GomeCommonUtil.checkObject(papersType);
		return papersTypeDao.updateByPrimaryKey(papersType) > 0 ? true : false;
	}

	@Override
	public boolean editPapersType(PapersType papersType) {
		GomeCommonUtil.checkObject(papersType);
		return papersTypeDao.updateByPrimaryKey(papersType) > 0 ? true : false;
	}

	@Override
	public boolean isSortNo(String sortNo) {
		return false;
	}

}
