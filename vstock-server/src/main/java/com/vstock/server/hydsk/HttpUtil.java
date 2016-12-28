package com.vstock.server.hydsk;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.httpclient.methods.multipart.*;
import org.apache.commons.httpclient.params.HttpMethodParams;

/**
 * 工具类
 * @author Administrator
 *
 */
public class HttpUtil {

	
	/**
	 * Json编码
	 * 
	 * @param obj
	 * @return
	 */
	public static String jsonEncode(Object obj) {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
		try {
			return objectMapper.writeValueAsString(obj);
		} catch (Exception e) {
			System.out.println("json编码发生错误:" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Json解码
	 * 
	 * @param <T>
	 * @param value
	 * @param clazz
	 * @return
	 */
	public static <T> T jsonDecode(String value, Class<T> clazz) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			return objectMapper.readValue(value, clazz);
		} catch (Exception e) {
			System.out.println("json解码发生错误:" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * POST请求
	 * @param targetEndPoint 请求地址
	 * @return
	 * @throws Exception
	 */
	public static String callByPostStream(String targetEndPoint,Map<String,String> maps) throws Exception{
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod(targetEndPoint);
		client.getParams().setContentCharset("UTF-8");
		method.setRequestHeader("ContentType","application/x-www-form-urlencoded;charset=UTF-8");
		NameValuePair[] data = new NameValuePair[maps.size()];
		java.util.Iterator<String> keys=maps.keySet().iterator();
		int i=0;
		while(keys.hasNext()){
			String key=keys.next();
			data[i]=new NameValuePair(key, maps.get(key));
			i++;
		}
		method.setRequestBody(data);
		client.executeMethod(method);
		BufferedReader reader = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream(),"utf-8"));  
		StringBuffer stringBuffer = new StringBuffer();  
		String str = "";  
		while((str = reader.readLine())!=null){  
			stringBuffer.append(str);  
		}  
		return stringBuffer.toString();  
	}
	
	
	/**
	 * 
	 * @param targetEndPoint 请求地址
	 * @return
	 * @throws Exception
	 */
	public static String call(String targetEndPoint) throws Exception{
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod(targetEndPoint);
		client.getParams().setContentCharset("UTF-8");
		method.setRequestHeader("ContentType","application/x-www-form-urlencoded;charset=UTF-8");
		NameValuePair[] data = {};
		method.setRequestBody(data);
		client.executeMethod(method);
		return method.getResponseBodyAsString();
	}
	
	/**
	 *  post模式带上传文件
	 *  
	 *  获取StringPart参数：ServletRequestUtils.getStringParameter(request, "name")
	 *  获取Request URL中的参数： request.getParameter("name");
	 * @param targetEndPoint url带参数    
	 * @param fileParam  文件的参数名
	 * @param file  文件
	 * @return
	 * @throws Exception
	 */
	public static String callByPostWithFile(String targetEndPoint,Map<String,String> maps,String fileParam,File file) throws Exception{
		HttpClient client = new HttpClient();
		client.getHttpConnectionManager().getParams().setConnectionTimeout(15000);//设置连接超时
		client.getHttpConnectionManager().getParams().setSoTimeout(15000);//设置回应超时
		PostMethod method = new PostMethod(targetEndPoint);
		List<Part> parts=new ArrayList<Part>();
		if(null!=maps){
			java.util.Iterator<String> keys=maps.keySet().iterator();
			while(keys.hasNext()){
				String key=keys.next();
				parts.add(new StringPart(key,maps.get(key), "UTF-8"));
			}
		}
		if(null!=file && file.isFile()){
			parts.add(new FilePart(fileParam, new FilePartSource(file)));
		}
		method.setRequestEntity(new MultipartRequestEntity(parts.toArray(new Part[0]), new HttpMethodParams()));
		method.addRequestHeader("User-Agent", "Mozilla/4.0");
		client.executeMethod(method);
		return method.getResponseBodyAsString();	
	}
}
