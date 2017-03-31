<%
/* *
 *功能：即时到账批量退款有密接口接入页
 *版本：3.4
 *修改日期：2016-03-08
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。

 *************************注意*****************
 *如果您在接口集成过程中遇到问题，可以按照下面的途径来解决
 *1、开发文档中心（https://doc.open.alipay.com/doc2/detail.htm?spm=a219a.7629140.0.0.oxen1k&treeId=66&articleId=103600&docType=1）
 *2、商户帮助中心（https://cshall.alipay.com/enterprise/help_detail.htm?help_id=473888）
 *3、支持中心（https://support.open.alipay.com/alipay/support/index.htm）
 *如果不想使用扩展功能请把扩展功能参数赋空值。
 **********************************************
 */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.vstock.server.alipay.config.*"%>
<%@ page import="com.vstock.server.alipay.util.*"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Map"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>支付宝即时到账批量退款有密接口</title>
	</head>
	<%
		////////////////////////////////////请求参数//////////////////////////////////////

        //批次号，必填，格式：当天日期[8位]+序列号[3至24位]，如：201603081000001

//        String batch_no = new String(request.getParameter("WIDbatch_no").getBytes("ISO-8859-1"),"UTF-8");
//
//        //退款笔数，必填，参数detail_data的值中，“#”字符出现的数量加1，最大支持1000笔（即“#”字符出现的数量999个）
//
//        String batch_num = new String(request.getParameter("WIDbatch_num").getBytes("ISO-8859-1"),"UTF-8");
//
//        //退款详细数据，必填，格式（支付宝交易号^退款金额^备注），多笔请用#隔开
//        String detail_data = new String(request.getParameter("WIDdetail_data").getBytes("ISO-8859-1"),"UTF-8");
//
//
//
//		//////////////////////////////////////////////////////////////////////////////////
//
//		//把请求参数打包成数组
//		Map<String, String> sParaTemp = new HashMap<String, String>();
//		sParaTemp.put("service", AlipayConfig.service);
//        sParaTemp.put("partner", AlipayConfig.partner);
//        sParaTemp.put("_input_charset", AlipayConfig.input_charset);
//		sParaTemp.put("notify_url", AlipayConfig.notify_url);
//		sParaTemp.put("seller_user_id", AlipayConfig.seller_user_id);
//		sParaTemp.put("refund_date", AlipayConfig.refund_date);
//		sParaTemp.put("batch_no", batch_no);
//		sParaTemp.put("batch_num", batch_num);
//		sParaTemp.put("detail_data", detail_data);

		Map<String,String> sParaTemp = (Map<String,String>)request.getAttribute("sParaTemp");
		
		//建立请求
		String sHtmlText = AlipaySubmit.buildRequest(sParaTemp,"get","确认");
		out.println(sHtmlText);
	%>
	<body>
	</body>
</html>
