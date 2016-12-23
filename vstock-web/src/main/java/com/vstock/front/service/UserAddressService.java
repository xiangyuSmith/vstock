package com.vstock.front.service;

import com.vstock.db.dao.IUserAddressDao;
import com.vstock.db.entity.UserAddress;
import com.vstock.ext.util.DateUtils;
import com.vstock.ext.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by administor on 2016/12/7.
 */
@Service
public class UserAddressService {
    @Autowired
    IUserAddressDao userAddressDao;

    /**
     * 分页查询所有记录
     * @param record
     * @param page
     * @return
     */
    public List<UserAddress> findAll(UserAddress record, Page page){
        return userAddressDao.findAll(record,page.getStartPos(),page.getPageSize());
    }

    /**
     * 查询所有总数
     * @param record
     * @return
     */
    public int findCount(UserAddress record){return userAddressDao.findCount(record);}

    /**
     * 新增
     * @param record
     * @return
     */
    public int insert(UserAddress record){return userAddressDao.insert(record);}

    /**
     * 修改
     * @param record
     * @return
     */
    public int update(UserAddress record){
        return userAddressDao.update(record.getStatus(),record.getInvalidDate(),record.getId());
    }

    /**
     * 查询对象
     * @param record
     * @return
     */
    public UserAddress findType(UserAddress record){return userAddressDao.findType(record);}

    public List<UserAddress> findAllUserAddress(UserAddress record){
        Page page = new Page(3,"1");
        List<UserAddress> userAddressList = new ArrayList<UserAddress>();
        List<UserAddress> adderList = this.findAll(record, page);
        record.setType(1);
        UserAddress userAddress = this.findType(record);
        userAddressList.add(userAddress);
        if (adderList.size() > 0){
            for (int i = 0; i < adderList.size(); i++){
                if (userAddressList.size() < 3) {
                    if (userAddress.getType() != adderList.get(i).getType()) {
                        userAddressList.add(adderList.get(i));
                    }
                }
            }
        }
        return userAddressList;
    }

    public int insertAdder(String localArea, String detailedAddress, String consigneeName,
                           String phoneNumber, String landlineNumber, String usid){
        UserAddress record = new UserAddress();
        record.setUserId(Integer.parseInt(usid));
        Page page = new Page(10,"1");
        List<UserAddress> userAddressList = this.findAll(record,page);
        if (userAddressList.size() > 0){
            record.setStatus(0);
        }else {
            record.setStatus(1);
        }
        if (localArea != null && !"".equals(localArea)){
            record.setLocalArea(localArea);
        }
        if (detailedAddress != null && !"".equals(detailedAddress)){
            record.setDetailedAddress(detailedAddress);
        }
        if (consigneeName != null && !"".equals(consigneeName)){
            record.setConsigneeName(consigneeName);
        }
        if (phoneNumber != null && !"".equals(phoneNumber)){
            record.setPhoneNumber(phoneNumber);
        }
        if (landlineNumber != null && !"".equals(landlineNumber)){
            record.setLandlineNumber(landlineNumber);
        }
        record.setCreateDate(DateUtils.dateToString(new Date()));
        return this.insert(record);
    }

}
