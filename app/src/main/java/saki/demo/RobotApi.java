package saki.demo;
import com.Tick_Tock.ANDROIDQQ.sdk.API;
import com.Tick_Tock.ANDROIDQQ.sdk.MessageFactory;
import java.util.List;
import com.Tick_Tock.ANDROIDQQ.sdk.QQGroup;
import android.os.Handler;
import java.io.Serializable;
import android.util.*;

public class RobotApi implements API
{

    private ChatAdapter adapter;

    private Handler handler;

	private List<PersonChat> cats;
    public RobotApi (List<PersonChat> _cats,ChatAdapter _adapter,Handler _handler){
		this.cats=_cats;
        this.adapter=_adapter;
        this.handler=_handler;
        
    }

    @Override
    public void SendGroupMessage(MessageFactory factory)
    {
		
        PersonChat personChat = new PersonChat();
        personChat.setMeSend(false);

        personChat.setChatMessage(factory.message);
		
        this.cats.add(personChat);
        this.adapter.notifyDataSetChanged();
        this.handler.sendEmptyMessage(0);
    }

    @Override
    public void SendGroupImage(MessageFactory factory)
    {
        // TODO: Implement this method
    }

    @Override
    public void SendGroupLongMessage(MessageFactory factory)
    {
        // TODO: Implement this method
    }

    @Override
    public void SendGroupPtt(MessageFactory factory)
    {
        // TODO: Implement this method
    }

    @Override
    public void SendFriendMessage(MessageFactory factory)
    {
		PersonChat personChat = new PersonChat();
        personChat.setMeSend(false);

        personChat.setChatMessage(factory.message);

        this.cats.add(personChat);
        this.adapter.notifyDataSetChanged();
        this.handler.sendEmptyMessage(0);
    }

    @Override
    public void SendFriendImage(MessageFactory factory)
    {
        // TODO: Implement this method
    }

    @Override
    public void SendTempolaryMessage(MessageFactory factory)
    {
        PersonChat personChat = new PersonChat();
        personChat.setMeSend(false);

        personChat.setChatMessage(factory.message);

        this.cats.add(personChat);
        this.adapter.notifyDataSetChanged();
        this.handler.sendEmptyMessage(0);
    }

    @Override
    public void SendTempolaryImage(MessageFactory factory)
    {
        // TODO: Implement this method
    }

    @Override
    public void SendTempolaryPtt(MessageFactory factory)
    {
        // TODO: Implement this method
    }

    @Override
    public void DealGroupRequest(MessageFactory factory)
    {
        // TODO: Implement this method
    }

    @Override
    public void GroupMemberShutUp(MessageFactory factory)
    {
        // TODO: Implement this method
    }

    @Override
    public void GroupMemberDelete(MessageFactory factory)
    {
        // TODO: Implement this method
    }

    @Override
    public void GroupShutUp(MessageFactory factory)
    {
        // TODO: Implement this method
    }

    @Override
    public long GetInitiateTime()
    {
        // TODO: Implement this method
        return 0;
    }

    @Override
    public long GetLastLoginTime()
    {
        // TODO: Implement this method
        return 0;
    }

    @Override
    public String GetPluginConfigPath()
    {
        // TODO: Implement this method
        return null;
    }

    @Override
    public void UpdateGroupList()
    {
        // TODO: Implement this method
    }

    @Override
    public List<QQGroup> GetGroupList()
    {
        // TODO: Implement this method
        return null;
    }
    
}
