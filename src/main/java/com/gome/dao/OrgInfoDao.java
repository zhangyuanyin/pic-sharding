package com.gome.dao;

import com.gome.domain.OrgInfo;

public interface OrgInfoDao {
    int deleteByPrimaryKey(String orgNo);

    int insert(OrgInfo record);

    int insertSelective(OrgInfo record);

    OrgInfo selectByPrimaryKey(String orgNo);

    int updateByPrimaryKeySelective(OrgInfo record);

    int updateByPrimaryKey(OrgInfo record);
}