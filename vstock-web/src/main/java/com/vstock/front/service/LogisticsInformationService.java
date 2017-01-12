package com.vstock.front.service;

import com.vstock.db.dao.ILogisticsInformationDao;
import com.vstock.db.entity.LogisticsInformation;
import com.vstock.ext.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogisticsInformationService {
    @Autowired
    ILogisticsInformationDao logisticsInformationDao;

    /**
     * 查询所有记录
     * @param record
     * @return
     */
    public List<LogisticsInformation> findAll(LogisticsInformation record){
        return logisticsInformationDao.findAll(record);
    }

    /**
     * 查询所有总数
     * @param record
     * @return
     */
    public int findCount(LogisticsInformation record){return logisticsInformationDao.findCount(record);}

    /**
     * 新增
     * @param record
     * @return
     */
    public int insert(LogisticsInformation record){return logisticsInformationDao.insert(record);}

    /**
     * 修改
     * @param record
     * @return
     */
    public int update(LogisticsInformation record){return logisticsInformationDao.update(record);}

    /**
     * 保存方法
     * @param record
     * @return
     */
    public int save(LogisticsInformation record){
        if (record.getId() != null){
            return this.update(record);
        }else {
            if (record.getCreateDate() == null || "".equals(record.getCreateDate())){
                record.setCreateDate(DateUtils.getCurrentTimeAsString());
            }
            if (record.getStatus() == null || "".equals(record.getStatus())){
                record.setStatus("0");
            }
            return this.insert(record);
        }
    }

}
