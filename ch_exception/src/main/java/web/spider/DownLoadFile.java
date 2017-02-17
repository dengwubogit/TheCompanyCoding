package web.spider;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

public class DownLoadFile {
	/**
	 * 根据URL的网页类型生成需要保存的网页的文件名,去除URL中的非文件名字符
	 */
	public String getFileNameByUrl(String url,String contentType){
		//移除HTTP
		url = url.substring(7);
		//text/html类型
		if(contentType.indexOf("html")!=-1){
			url=url.replaceAll("[\\?/:*|<>\"]", "_")+".html";
			return url;
		}
		// 如application/pdf类型
		else{
			return url.replaceAll("[\\?/:*|<>\"]","_")+"."+contentType.substring(contentType.lastIndexOf("/")+1);
		}
	}
	/**
	 * 保存网页字节数组到本地文件,filePath为要保存文件的相对地址
	 * 
	 */
	private void saveToLocal ( byte[] data ,String filePath){
		try {
			DataOutputStream out = new DataOutputStream(new FileOutputStream(new File(filePath)));
			for(int i=0;i<data.length;i++){
				
				out.write(data[i]);
				out.flush();
				out.close();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//下载URL指向的网页
	public String downloadFile(String url){
		String filePath=null;
		//1.生成httlClient对象并设置参数
		HttpClient httpClient = new HttpClient();
		//设置Http连接超时5s
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
		//2.生成GetMethod对象并设置参数
		GetMethod getMethod= new GetMethod(url);
		//设置Get请求超时5S
		getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 5000);
		//设置请求重试处理
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
		//3. 执行Http Get请求
		try {
			int statusCode = httpClient.executeMethod(getMethod);
			//判断访问的状态码
			if(statusCode!=HttpStatus.SC_OK){
				System.err.println("Method failed:"+getMethod.getStatusLine());
				filePath=null;
			}
			//处理HTTP响应内容
			byte [] responseBody= getMethod.getResponseBody();//读取为字节数组
			//根据网页URL生成保存时的文件名
			filePath="temp\\"+getFileNameByUrl(url, getMethod.getResponseHeader("Content-Type").getValue());
			saveToLocal(responseBody,filePath);
			
		} catch (HttpException e) {
			//发生致命的异常,可能是协议不对或者返回的内容有问题
			System.out.println("Please check your provided http address!!");
			e.printStackTrace();
		} catch (IOException e) {
			//发生网络异常
			e.printStackTrace();
		}finally {
			//释放连接
			getMethod.releaseConnection();
		}
		return filePath;
	}
	
}
