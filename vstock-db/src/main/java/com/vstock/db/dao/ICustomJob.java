package com.vstock.db.dao;

import com.vstock.db.entity.CustomJob;

import java.util.List;

/**
 * Created by administor on 2016/5/30.
 */
public interface ICustomJob {

    List<CustomJob> findCustomjob();
}
