package com.vstock.admin.data.service;

import com.vstock.db.dao.ICustomJob;
import com.vstock.db.entity.CustomJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Created by administor on 2016/5/30.
 */
@Service
public class CustomJobService {

    @Autowired
    ICustomJob customJob;

    /** 查询数据所有数据 */
    public List<CustomJob> getAllJob(){
        List<CustomJob> customJobList = null;
        try {
            customJobList = customJob.findCustomjob();
        }catch (Exception e)
        {
            System.out.print(e.getMessage());
        }
        return customJobList;
    }
}
