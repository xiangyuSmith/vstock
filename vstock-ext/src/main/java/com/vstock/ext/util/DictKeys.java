package com.vstock.ext.util;

/**
 * 常量数据字典
 * @author daniel
 *
 */
public class DictKeys {
	/**
	 * URL缓存Key
	 */
	public static final String cache_name_page = "SimplePageCachingFilter";

	/**
	 * 登陆注册盐
	 */
	public static final String reg_login_miyan = "9a1a3b753eadd0d46e31e6b2859f66e8";

	/**
	 * 用户登录状态码
	 */
	public static final int login_info_0 = 0;// 用户不存在
	public static final int login_info_1 = 1;// 未验证
	public static final int login_info_2 = 2;// 密码错误次数超限
	public static final int login_info_3 = 3;// 密码验证成功
	public static final int login_info_4 = 4;// 密码验证失败
	
}
