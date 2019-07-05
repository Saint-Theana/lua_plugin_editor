package com.Tick_Tock.ANDROIDQQ.Java2LuaBridge;

import java.io.*;

public class fileentry
{
	private String curruntpath = new File("").getAbsolutePath();


	public String getcurrentpath()
	{

		return this.curruntpath;
	}

	public String[] getfilelist(String path)
	{
		return new File(path).list();
	}

	public boolean isfileexist(String path)
	{
		return new File(path).exists();
	}

	public String readfromfile(String path)
	{
		BufferedReader br = null;
		StringBuffer sb = null;
		try
		{
			br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8")); //这里可以控制编码
			sb = new StringBuffer();
			String line = null;
			while ((line = br.readLine()) != null)
			{
				sb.append(line+"\n");
			}
			br.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		

		return new String(sb);
	}


	public void writetofile(String text, String path)
	{
		try
		{
			File writeName = new File(path); // 相对路径，如果没有则要建立一个新的output.txt文件
			writeName.createNewFile(); // 创建新文件,有同名的文件的话直接覆盖
			FileWriter writer = new FileWriter(writeName);
			BufferedWriter out = new BufferedWriter(writer);
			out.write(text); // \r\n即为换行
			out.flush(); // 把缓存区内容压入文件
			out.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

}


