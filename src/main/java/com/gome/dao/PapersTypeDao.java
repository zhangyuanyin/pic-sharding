package com.gome.dao;

import java.util.List;

import com.gome.domain.PapersType;
import com.gome.domain.PapersTypeKey;

public interface PapersTypeDao {
    int deleteByPrimaryKey(PapersTypeKey key);

    int insert(PapersType record);

    int insertSelective(PapersType record);

    PapersType selectByPrimaryKey(PapersTypeKey key);

    int updateByPrimaryKeySelective(PapersType record);

    int updateByPrimaryKey(PapersType record);
    
    List<PapersType> findPapersTypeBySysNo(String sysNo);
}