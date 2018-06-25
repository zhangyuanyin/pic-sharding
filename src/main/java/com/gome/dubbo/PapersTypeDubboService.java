package com.gome.dubbo;

import java.util.List;

import com.gome.domain.PapersType;

/**
 * <dt>图片类型dubbo服务处理接口</dt>
 * @author yyzhang
 * @since 2018年2月1日16:53:49
 */
public interface PapersTypeDubboService {
	
	/**
	 * 通过系统编号，查询文件类型
	 * @param sysNo
	 * @return
	 */
	List<PapersType> findPapersTypeBySysNo(String sysNo);
	
	/**
	 * 通过主键，查询文件类型
	 * @param sysNo
	 * @param itemCode
	 * @return
	 */
	PapersType findByPrimaryKey(String sysNo, String itemCode);
	
	/**
	 * 添加图片类型
	 * @param papersType
	 * @return
	 */
	boolean addPapersType(PapersType papersType);
	
	/**
	 * 删除图片类型
	 * @param papersType
	 * @return
	 */
	boolean delPapersType(PapersType papersType);
	
	/**
	 * 编辑图片类型
	 * @param papersType
	 * @return
	 */
	boolean editPapersType(PapersType papersType);
	
	/**
	 * 判断排序号是否存在
	 * @param sortNo
	 * @return
	 */
	boolean isSortNo(String sortNo);
}
