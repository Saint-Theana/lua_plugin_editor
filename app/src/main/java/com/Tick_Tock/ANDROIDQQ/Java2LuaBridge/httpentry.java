package com.Tick_Tock.ANDROIDQQ.Java2LuaBridge;
import java.net.*;
import java.io.*;

public class httpentry
{
	private HttpURLConnection conn;
	private String data="";
	public httpentry(String url)
	{

		try
		{
			conn =  (HttpURLConnection) new URL(url).openConnection();
			this.conn.setRequestMethod("GET");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	public httpentry set_method(String method)
	{
		if (method.equals("POST"))
		{
			this.conn.setDoOutput(true);
		}
		try
		{
			this.conn.setRequestMethod(method);
		}
		catch (java.net.ProtocolException e)
		{
			e.printStackTrace();
		}
		return this;
	}

	public httpentry set_timeout(long v)
	{
		this.conn.setConnectTimeout((int)v);
		return this;
	}

	public httpentry set_header(String header, String content)
	{
		this.conn.setRequestProperty(header, content);
		return this;
	}

	public httpentry set_data(String _data)
	{
		this.data = _data;
		return this;
	}
	public String request()
	{
		try
		{
			this.conn.connect();
			if (this.conn.getRequestMethod().equals("POST") && !this.data.equals(""))
			{
				PrintWriter writer = new PrintWriter(this.conn.getOutputStream());
				writer.print(data);                                    
				writer.flush();
				writer.close();
			}
			// 连接会话  
			// 获取输入流  
			BufferedReader br= new BufferedReader(new InputStreamReader(this.conn.getInputStream(), "UTF-8"));  
			String line;  
			StringBuilder sb = new StringBuilder();  
			while ((line = br.readLine()) != null)
			{// 循环读取流  
				sb.append(line);  
			}  
			br.close();// 关闭流
			this.conn.disconnect();// 断开连接  
			String hosts = sb.toString();
			return hosts;
		}
		catch (IOException e)
		{e.printStackTrace();
			return e.getMessage();}
	}


}
	

