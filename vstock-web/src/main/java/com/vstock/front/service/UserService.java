package com.vstock.front.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.response.AlipayUserInfoShareResponse;
import com.vstock.db.dao.IUserDao;
import com.vstock.db.dao.IUserInvitationDao;
import com.vstock.db.entity.Express;
import com.vstock.db.entity.User;
import com.vstock.db.entity.UserInvitation;
import com.vstock.ext.base.ResultModel;
import com.vstock.ext.util.DateUtils;
import com.vstock.ext.util.MD5Util;
import com.vstock.ext.util.StringUtil;
import com.vstock.server.express.ExpressLogistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    IUserDao userDao;
    @Autowired
    IUserInvitationDao userInvitationDao;

    public int insertUser(User user){
        return userDao.insertUser(user);
    }

    public User findById(String suid){
        User u = new User();
        u.setId(suid);
        return userDao.findUser(u);
    }

    public UserInvitation findInvitation(String yaoqingCode){
        UserInvitation record = new UserInvitation();
        record.setCode(yaoqingCode);
        return userInvitationDao.findByCode(record);
    }

    public int updateInvitation(UserInvitation record){
        return userInvitationDao.update(record);
    }

    public User findUser(String mobile){
        User u = new User();
        u.setMobile(mobile);
        return userDao.findUser(u);
    }

    /**
     * 修改方法
     * @param record
     * @return
     */
    public int update(User record){return userDao.update(record);}

    /**
     * 支付宝登录
     * @return
     */
    public ResultModel alipayLogin(HttpServletRequest request , AlipayUserInfoShareResponse response ){
        ResultModel resultModel = new ResultModel();
        //判断返回用户信息是否为空
        if (response != null){
            User user = new User();
            //获取用户支付宝ID
            user.setAlipayUserId(response.getUserId());
            //查询用户是否存在
            List<User> userList = userDao.findAll(user,0,1);
            if (userList.size() < 1){//不存在
                user.setPassword(MD5Util.getMD5String(user.getAlipayUserId() + User.REG_MD5_CODE));
                user.setStatus(1);
                user.setCreate_time(DateUtils.getCurrentTimeAsString());
                //判断用户昵称是否为空
                if (response.getNickName() != null && !"".equals(response.getNickName())) {
                    user.setNick(response.getNickName());
                }else {//为空根据ID自动生成昵称
                    user.setNick(user.getAlipayUserId().substring(user.getAlipayUserId().length()-4,user.getAlipayUserId().length())+"V");
                }
                user.setLast_login_time(DateUtils.getCurrentTimeAsString());
                user.setLast_login_ip(this.ipadder(request));
                //新增用户信息
                resultModel.setRetCode(this.insertUser(user));
                //保存sesion中
                WebUtils.setSessionAttribute(request, User.SESSION_USER_ID, user.getId());
            }else {//用户存在
                user.setLast_login_time(DateUtils.getCurrentTimeAsString());
                user.setLast_login_ip(this.ipadder(request));
                user.setId(userList.get(0).getId());
                //更新用户登录信息
                if (this.update(user) > 0) {
                    resultModel.setRetCode(ResultModel.RET_OK);
                    WebUtils.setSessionAttribute(request, User.SESSION_USER_ID, userList.get(0).getId());
                }else {
                    resultModel.setRetMsg("登录失败！请联系管理员");
                }
            }
        }else {
            resultModel.setRetMsg("登录失败！");
        }
        return resultModel;
    }

    public String ipadder(HttpServletRequest request){
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            if(ip.equals("127.0.0.1")){
                //根据网卡取本机配置的IP
                InetAddress inet=null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ip= inet.getHostAddress();
            }
        }
        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if(ip != null && ip.length() > 15){
            if(ip.indexOf(",")>0){
                ip = ip.substring(0,ip.indexOf(","));
            }
        }
        return ip;
    }

    public List<List<Express>> obtainLogistics(String expresName, String expresNum){
        List<List<Express>> expressListlist = new ArrayList<List<Express>>();
        List<Express> expressList = new ArrayList<Express>();
        //判断快递公司和单号不为空
        if (expresName != null && !"".equals(expresName) && expresNum != null && !"".equals(expresNum)){
            //传入快递公司名称，转化为快递100接口参数名
            expresName = StringUtil.expressName(expresName);
            //调用快递100接口获取物流信息
            JSONObject jsonObject = ExpressLogistics.getexpress(expresName,expresNum);
            Object data = jsonObject.get("data");
            JSONArray jsonArray = (JSONArray)data;
            String ifweek = "";
            //循环获取每天的详细信息
            for (int i = 0; i < jsonArray.size(); i++){
                Express express = new Express();
                JSONObject json = (JSONObject)jsonArray.get(i);
                Object ftime = json.get("ftime");
                Object context = json.get("context");
                Object time = json.get("time");
                String week = DateUtils.getweek(time.toString());
                express.setExpressName(context.toString());
                express.setCreateDate(ftime.toString());
                express.setStatus(week);
                //判断是否第一条信息
                if (i == 0) {
                    expressList.add(express);
                    ifweek = week;
                }else {
                    //判断是否与前一条信息是同一天
                    if (ifweek.equals(week)){
                        expressList.add(express);
                        if (i+1 == jsonArray.size()){
                            expressListlist.add(expressList);
                        }
                    }else {//把同一天的信息放在一个Listzhong
                        expressListlist.add(expressList);
                        expressList = new ArrayList<Express>();
                        expressList.add(express);
                        ifweek = week;
                    }
                }
            }
        }
        return expressListlist;
    }

}
