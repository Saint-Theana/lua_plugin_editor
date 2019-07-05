package saki.demo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.os.Handler;
import java.util.List;
import java.util.ArrayList;
import android.widget.Button;
import android.widget.EditText;
import android.view.View.OnClickListener;
import android.view.View;
import android.text.TextUtils;
import android.widget.Toast;
import android.content.Intent;
import com.Tick_Tock.ANDROIDQQ.sdk.GroupMessage;
import android.util.*;
import com.Tick_Tock.ANDROIDQQ.sdk.*;

public class Test6Activity extends AppCompatActivity 
{
    
    private ChatAdapter chatAdapter;
    
    
    /**
     * 声明ListView
     */
    private ListView lv_chat_dialog;
    /**
     * 集合
     */
    private List<PersonChat> personChats = new ArrayList<PersonChat>();
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            int what = msg.what;
            switch (what) {
                case 1:
                    /**
                     * ListView条目控制在最后一行
                     */
                    lv_chat_dialog.setSelection(personChats.size());
                    break;

                default:
                    break;
            }
        };
    };

    private String filename;

    private LuaRobot robot;

    public int chattype;

	public void addmsg(PersonChat personChat)
	{
		this.personChats.add(personChat);
		this.chatAdapter.notifyDataSetChanged();
		this.handler.sendEmptyMessage(0);
	}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test6);
        Intent intent = this.getIntent();
        this.robot = Util.robot;
        this.chattype=intent.getIntExtra("chattype",0);
        
         /**
         * 虚拟4条发送方的消息
         */
        
        lv_chat_dialog = (ListView) findViewById(R.id.lv_chat_dialog);
        Button btn_chat_message_send = (Button) findViewById(R.id.btn_chat_message_send);
        final EditText et_chat_message = (EditText) findViewById(R.id.et_chat_message);
        /**
         *setAdapter
         */
        chatAdapter = new ChatAdapter(this, personChats);
        lv_chat_dialog.setAdapter(chatAdapter);
        /**
         * 发送按钮的点击事件
         */
        btn_chat_message_send.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View arg0) {
                    // TODO Auto-generated method stub
                    if (TextUtils.isEmpty(et_chat_message.getText().toString())) {
                        Toast.makeText(Test6Activity.this, "发送内容不能为空", 0).show();
                        return;
                    }
                    PersonChat personChat = new PersonChat();
                    //代表自己发送
                    personChat.setMeSend(true);
                    //得到发送内容
                    personChat.setChatMessage(et_chat_message.getText().toString());
                    //加入集合
                    personChats.add(personChat);
                    //清空输入框
                    
                    //刷新ListView
                    chatAdapter.notifyDataSetChanged();
                    handler.sendEmptyMessage(1);
                    switch(Test6Activity.this.chattype){
                        case 0:{
                            GroupMessage message = new GroupMessage();
                            message.group_uin=809551523;
                            message.message_type=0;
                            message.sender_uin=1721115102;
                                message.message=et_chat_message.getText().toString();
								Log.d("FUCK",message.message);
                                message.sender_name="Tick Tock";
                                message.self_uin=195990382;
                                message.font_name="微软雅黑";
                                message.group_name="麻花疼后宫";
                        Test6Activity.this.robot.call(message);
                   }
                   break;
						case 1:{
								FriendMessage message = new FriendMessage();
								message.message_type=0;
								message.sender_uin=1721115102;
                                message.message=et_chat_message.getText().toString();
								Log.d("FUCK",message.message);
                                message.self_uin=195990382;
                                message.font_name="微软雅黑";
                                Test6Activity.this.robot.call(message);
							}
							break;
						case 2:{
								TempolaryMessage message = new TempolaryMessage();
								message.group_uin=809551523;
								message.message_type=0;
								message.sender_uin=1721115102;
                                message.message=et_chat_message.getText().toString();
								Log.d("FUCK",message.message);
                      
                                message.self_uin=195990382;
                                message.font_name="微软雅黑";
         
								Test6Activity.this.robot.call(message);
							}
							break;
                    
                    
                    
                    }
					et_chat_message.setText("");
					
					
                }
            });
			
			
		this.robot.update(personChats,chatAdapter,handler);
    }
    
    
}
