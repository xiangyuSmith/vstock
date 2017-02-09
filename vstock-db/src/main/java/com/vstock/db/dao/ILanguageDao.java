package com.vstock.db.dao;

import com.vstock.db.entity.LanguageControl;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ILanguageDao {

    List<LanguageControl> findLanguage(@Param("obj") LanguageControl record);

}
