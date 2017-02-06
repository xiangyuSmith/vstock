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
     * 查询单个地址
     */
    public UserAddress findAddressById(UserAddress record){
        if(userAddressDao.findAddressById(record).size() > 0){
            return userAddressDao.findAddressById(record).get(0);
        }
        return null;
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
        return userAddressDao.update(record);
    }

    /**
     * 查询对象
     * @param record
     * @return
     */
    public UserAddress findType(UserAddress record){return userAddressDao.findType(record);}

    //界面查询
    public List<UserAddress> findAllUserAddress(UserAddress record,int startPos , String type){
        if (type != null && !"".equals(type)){
            startPos = 20;
        }
        Page page = new Page(startPos,"1");
        List<UserAddress> userAddressList = new ArrayList<UserAddress>();
        record.setStatus(0);
        List<UserAddress> adderList = this.findAll(record, page);
        record.setType(1);
        UserAddress userAddress = this.findType(record);
        if (userAddress != null) {
            userAddressList.add(userAddress);
            if (adderList.size() > 0){
                for (int i = 0; i < adderList.size(); i++){
                    if (userAddressList.size() < startPos) {
                        if (userAddress.getType() != adderList.get(i).getType()) {
                            userAddressList.add(adderList.get(i));
                        }
                    }
                }
            }
        }
        return userAddressList;
    }

    /**
     * 保存方法
     * @param localArea 所属地区
     * @param detailedAddress 详细地址
     * @param consigneeName  收货人姓名
     * @param phoneNumber  手机号码
     * @param landlineNumber  电话号码
     * @param usid  用户ID
     * @param type  是否为默认地址
     * @param status  状态
     * @param id  编号
     * @return
     */
    public UserAddress saveAdder(String localArea, String detailedAddress, String consigneeName,
                           String phoneNumber, String landlineNumber, String usid, String type, String status, String id){
        UserAddress record = new UserAddress();
        record.setUserId(Integer.parseInt(usid));
        //判断不为空，赋值
        if (localArea != null && !"".equals(localArea)){
            record.setLocalArea(localArea);
        }
        if (status != null && !"".equals(status)){
            record.setStatus(Integer.parseInt(status));
        }
        if (type != null && !"".equals(type)){
            record.setType(Integer.parseInt(type));
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
        //判断是否为新增
        if (id == null || "".equals(id)) {
            //查询是否有默认地址，没有设本地址为默认地址
            UserAddress whereUserAdress = new UserAddress();
            whereUserAdress.setType(1);
            whereUserAdress.setUserId(record.getUserId());
            whereUserAdress.setStatus(0);
            UserAddress userAddress = this.findType(whereUserAdress);
            if (userAddress != null){
                record.setType(0);
            }else {
                record.setType(1);
            }
            record.setCreateDate(DateUtils.dateToString(new Date()));
            record.setStatus(0);
            if(insert(record) != 1){
                return null;
            }
            return record;
        }else {//为修改
            record.setId(Integer.parseInt(id));
            //判断是否修改默认地址
            if (type == null || "".equals(type)) {
                if(update(record) == 0){
                    return null;
                }
                return record;
            }else {//修改默认地址
                UserAddress userAddress = new UserAddress();
                userAddress.setType(Integer.parseInt(type));
                userAddress.setUserId(Integer.parseInt(usid));
                userAddress.setStatus(0);
                userAddress = this.findType(userAddress);
                //查询是否有默认地址
                if (userAddress.getId() != null && !"".equals(userAddress.getId()) && !"1".equals(status)){
                    userAddress.setType(0);
                    int i = this.update(userAddress);
                    //有默认地址修改
                    if (i > 0) {
                        if(update(record) != 1){
                            return null;
                        }
                        return record;
                    }else {
                        return null;
                    }
                }else {
                    //判断是否删除
                    if (status != null && !"".equals(status)) {
                        int i = this.update(record);
                        //判断是否删除的是默认地址
                        if (record.getType() == 1){
                            if (i > 0){
                                //是默认地址，修改最近的一条地址为默认地址
                                UserAddress userAddre = new UserAddress();
                                userAddre.setUserId(record.getUserId());
                                userAddre.setStatus(0);
                                Page page = new Page(10,"1");
                                List<UserAddress> userAddressList = this.findAll(userAddre,page);
                                if (userAddressList.size() > 0){
                                    userAddressList.get(0).setType(1);
                                    if(update(userAddressList.get(0)) != 1){
                                        return null;
                                    }
                                    return userAddressList.get(0);
                                }else {
                                    return null;
                                }
                            }else {
                                return null;
                            }
                        }else {
                            return null;
                        }
                    }else {
                        if(update(record) != 1){
                            return null;
                        }
                        return record;
                    }
                }
            }
        }
    }
}
