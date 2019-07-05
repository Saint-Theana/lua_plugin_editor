package com.Tick_Tock.ANDROIDQQ.sdk;
import java.util.*;

public class GroupMessage
{
	public int message_type;//0 文本|1 图片|2 语音|3 xml|4 json
	public String font_name;
	public long sender_uin;
	public String sender_name;
	public long self_uin;
	public long group_uin;
	public String group_name;
	public String message;
	public long message_time;
	public List<Long> at_list=new ArrayList<Long>();
	public GroupMessage(){
		
	}
}
