package saki.demo;
import java.util.List;
import android.os.Handler;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.JsePlatform;
import org.luaj.vm2.Globals;
import android.os.Parcelable;
import android.os.Parcel;
import com.Tick_Tock.ANDROIDQQ.sdk.GroupMessage;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;
import java.io.Serializable;
import com.Tick_Tock.ANDROIDQQ.sdk.*;
import android.util.*;
import android.widget.*;

public class LuaRobot implements Serializable
{

	private LuaValue luaapi;

	private LuaValue luathis;

	public void call(FriendMessage message)
	{
		final LuaValue luamsg = CoerceJavaToLua.coerce(message);
        new Thread(){
            public void run()
            {
                LuaRobot.this.plugin.get(LuaValue.valueOf("onfriendmessage")).call(luamsg);
            }
        }.start();
	}

	public void call(TempolaryMessage message)
	{
		final LuaValue luamsg = CoerceJavaToLua.coerce(message);
        new Thread(){
            public void run()
            {
                LuaRobot.this.plugin.get(LuaValue.valueOf("ontempolarymessage")).call(luamsg);
            }
        }.start();
	}

    public void call(GroupMessage message)
    {
        final LuaValue luamsg = CoerceJavaToLua.coerce(message);
        new Thread(){
            public void run()
            {
                LuaRobot.this.plugin.get(LuaValue.valueOf("ongroupmessage")).call(luamsg);
            }
        }.start();
    }
    
    public void log(String t){
		Log.d("FUCK",t);
	}
    
    

    private RobotApi api;

    private String filename;

    private List<PersonChat> cats;

    private Handler handler;

    private ChatAdapter adapter;

    private LuaValue plugin;

    private Globals globals;

    public RobotApi getapi(){
        return this.api;
    }
    public void setapi(RobotApi _api){
        this.api=_api;
    }
    
    public String getfilename(){
        return this.filename;
    }
    public void setfilename(String _filename){
        this.filename=_filename;
    }
    
    public List<PersonChat> getcats(){
        return this.cats;
    }
    public void setcats(List<PersonChat> _filename){
        this.cats=_filename;
    }
    
    
    public Handler gethandler(){
        return this.handler;
    }
    public void sethandler(Handler _filename){
        this.handler=_filename;
    }
    
    
    public ChatAdapter getadapter(){
        return this.adapter;
    }
    public void setfilename(ChatAdapter _filename){
        this.adapter=_filename;
    }
    
    
    public LuaValue getplugin(){
        return this.plugin;
    }
    public void setfilename(LuaValue _filename){
        this.plugin=_filename;
    }
    
    
    public Globals getglobals(){
        return this.globals;
    }
    public void setfilename(Globals _filename){
        this.globals=_filename;
    }
	
	
	public MessageFactory getmessagefactory(){
		return new MessageFactory();
	}
   
    public LuaRobot(String _filename) throws Exception
    {
        this.filename = _filename;
        this.globals = JsePlatform.standardGlobals();
        
		
		this.luathis = CoerceJavaToLua.coerce(this);
		
        this.plugin = globals.loadfile(filename).call();
		
		
    }


    public void update(List<PersonChat> _cats, ChatAdapter _adapter, Handler _handler)
    {
        this.cats = _cats;
        this.adapter = _adapter;
        this.handler = _handler;
		this.api = new RobotApi(this.cats, this.adapter, this.handler);
		this.luaapi = CoerceJavaToLua.coerce(api);
		new Thread(){
			public void run()
			{
				LuaRobot.this.plugin.get(LuaValue.valueOf("load")).call(luaapi, luathis);
			}
		}.start();
    }


}
