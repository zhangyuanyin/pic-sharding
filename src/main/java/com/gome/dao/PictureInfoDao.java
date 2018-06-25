package com.gome.dao;

import java.util.List;

import com.gome.domain.PictureInfo;

public interface PictureInfoDao {
    int deleteByPrimaryKey(String picId);

    int insert(PictureInfo record);

    int insertSelective(PictureInfo record);

    PictureInfo selectByPrimaryKey(String picId);

    int updateByPrimaryKeySelective(PictureInfo record);

    int updateByPrimaryKey(PictureInfo record);
    
    List<PictureInfo> findPictureByAppNo(String appNo);
    
    PictureInfo findPictureByAppNoAndSaveName(String appNo, String saveName);
    
    List<PictureInfo> getPictureList(String sysNo, String appNo, String itemCode);
}