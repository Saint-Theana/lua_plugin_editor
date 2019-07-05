package com.Tick_Tock.ANDROIDQQ.sdk;
import java.util.*;

public class MessageFactory
{
	public int message_type;
	public long group_uin;//群号 好友消息无效
	public String message;//消息内容
    public long friend_uin;//好友qq 群消息无效
	public long group_code;
	public long target_uin;
	public boolean isshutup =true;
	public long shuttime;
	public boolean ispass = true;
	public long requestid;
	public List<AtStore> at_list= new ArrayList<AtStore>();
	public MessageFactory(){

	}
}
