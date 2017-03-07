package com.vstock.server.alipay.config;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：3.4
 *修改日期：2016-03-08
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 合作身份者ID，签约账号，以2088开头由16位纯数字组成的字符串，查看地址：https://b.alipay.com/order/pidAndKey.htm
	public static String partner = "2088711791661586";
	
	// 收款支付宝账号，以2088开头由16位纯数字组成的字符串，一般情况下收款账号就是签约账号
	public static String seller_id = partner;

	//商户的私钥,需要PKCS8格式，RSA公私钥生成：https://doc.open.alipay.com/doc2/detail.htm?spm=a219a.7629140.0.0.nBDxfy&treeId=58&articleId=103242&docType=1
	public static String private_key = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBANM3vbGYq3rIVENH\n" +
			"K8c1piVDbnYXuL0kr/T3g6IIHta+WIyGMMkFlR9jFegatPqf5kWUphsqXGAmcOw2\n" +
			"TKWhONJYOy87lQrOu5gnqn61N+OxQDSw1ybUvv/LOWdow/gDTUIEk7FjGl/nRbt9\n" +
			"DCeQ0jI0bRwDXUyModieGq1zVPcVAgMBAAECgYBLCcuPs7CAP3mIKVnJPSsYo/sz\n" +
			"YzwTnlwQyHpwbzgdjKRC0E22rRAMhZQ70HkpbPNPFv9oOUV2N1H46VWTplJb6xgS\n" +
			"LsRCOvis8ugNgCk8rethrgxjvnAPRyWvdn2atcZHfIsDLWmI1i6uinmKDP1AOghp\n" +
			"sN0XGMPZXhhyqSFF4QJBAOo7xHxcI8biPOcrnN79gXOaK9DMvC6kDE5ou1my1edU\n" +
			"kFopAhHgk8sixq5ILtvHbWDHOY0XqKHW6H4rtTEGtJkCQQDm2HJ3o5ahsbV18WoF\n" +
			"NpTIpgcwLCyKBpz8iP/AgYBd0k6cxCqZ6CfVpBYZaSKEE83zGRsVfsKxtN6IejQ4\n" +
			"XefdAkEAmfEt8Y2c9dk6rbO8JdH2gkORCGmGRCEM9XtfplQiwSaSuK667YTrnVX9\n" +
			"vg0yvr8S48gLyu8BpVvY8RNIwi/mAQJAJEmWBNSEUzqaKcYfQl6IGNpwetQW1EOh\n" +
			"3dizS1GQGi97CA2WHMdvq5e1mZz7PgPW9gXWShS5CjJCYPXEy6+HSQJBAJwdyO64\n" +
			"ZD1u+MrCdJJjZ3O64BuEz4FiIThMCslVp0Pu4/L5WADFY+/pjScw+8wKFwo3Fobq\n" +
			"DHWGN/poz4D9GPU=";
	
	// 支付宝的公钥,查看地址：https://b.alipay.com/order/pidAndKey.htm
	public static String alipay_public_key  = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://116.228.89.158:1236/bid/createPay";

	public static String notify_trade_url = "http://116.228.89.158:1236/trade/createTradePay";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://116.228.89.158:1236/bid/createPay";

	public static String return_trade_url = "http://116.228.89.158:1236/trade/createTradePay";

	// 签名方式
	public static String sign_type = "RSA";
	
	// 调试用，创建TXT日志文件夹路径，见AlipayCore.java类中的logResult(String sWord)打印方法。
	public static String log_path = "C:\\";
		
	// 字符编码格式 目前支持 gbk 或 utf-8
	public static String input_charset = "utf-8";
		
	// 支付类型 ，无需修改
	public static String payment_type = "1";
		
	// 调用的接口名，无需修改
	public static String service = "create_direct_pay_by_user";

	//支付宝登录接口名称
	public static String loginservice = "alipay.auth.authorize";

	//支付宝登录需要授权执行的目标服务地址
	public static String target_service = "user.auth.quick.login";

	public static String login_return_url = "http://www.v-stock.com/login/alipayLogin";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
	
//↓↓↓↓↓↓↓↓↓↓ 请在这里配置防钓鱼信息，如果没开通防钓鱼功能，为空即可 ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	
	// 防钓鱼时间戳  若要使用请调用类文件submit中的query_timestamp函数
	public static String anti_phishing_key = "";
	
	// 客户端的IP地址 非局域网的外网IP地址，如：221.0.0.1
	public static String exter_invoke_ip = "";
		
//↑↑↑↑↑↑↑↑↑↑请在这里配置防钓鱼信息，如果没开通防钓鱼功能，为空即可 ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
	
}

