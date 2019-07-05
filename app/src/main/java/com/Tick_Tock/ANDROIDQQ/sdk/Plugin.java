package com.Tick_Tock.ANDROIDQQ.sdk;

public interface Plugin
{

	String author();
	String Version();
	String name();

	void onLoad(API api);

	void onMessageHandler(GroupMessage message);
	
	void onMessageHandler(FriendMessage message);
	
	void onMessageHandler(TempolaryMessage message);
	
	void onMessageHandler(RequestMessage message);
	
	void onMenu(GroupMessage qqmessage);
	
}



