package com.Tick_Tock.ANDROIDQQ.sdk;
import java.util.*;

public interface API {
	public void SendGroupMessage(MessageFactory factory);
	public void SendGroupImage(MessageFactory factory);
	public void SendGroupLongMessage(MessageFactory factory);
	public void SendGroupPtt(MessageFactory factory);
	public void SendFriendMessage(MessageFactory factory);
	public void SendFriendImage(MessageFactory factory);
	public void SendTempolaryMessage(MessageFactory factory);
	public void SendTempolaryImage(MessageFactory factory);
	public void SendTempolaryPtt(MessageFactory factory);
	public void DealGroupRequest(MessageFactory factory);
	public void GroupMemberShutUp(MessageFactory factory);
	public void GroupMemberDelete(MessageFactory factory);
	public void GroupShutUp(MessageFactory factory);
	public long GetInitiateTime();
	public long GetLastLoginTime();
	public String GetPluginConfigPath();
	public void UpdateGroupList();
	public List<QQGroup> GetGroupList();
}

