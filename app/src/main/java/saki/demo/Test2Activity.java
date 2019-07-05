package saki.demo;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;




public class Test2Activity extends AppCompatActivity {
	
	private Button button1 = null;
	private EditText edittextbody = null;
	private EditText edittextresult = null;

    private ListView listview;
    
    
    private LuaRobot robot;

    private Test2Activity.MyAdapter adapter;

    private List<String> list;

    private String filename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = this.getIntent();
        this.filename = intent.getStringExtra("filename");
        
		
        
        initactivity();
		try
		{
			this.robot = new LuaRobot(this.filename);
		}
		catch (Exception e)
		{
			Toast.makeText(this,"加载失败，请检查代码",Toast.LENGTH_SHORT).show();
			this.onBackPressed();
		}

        Util.robot = robot;
		
	}
    
    
    
    private void initactivity()
    {
        
        this.updatefilelist();
        this.listview=(ListView) this.findViewById(R.id.contenttest2ListView1);
        this.adapter = new MyAdapter();
        listview.setAdapter(this.adapter);
        listview.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int position,long arg3) {
                    Intent intent = new Intent(Test2Activity.this,Test6Activity.class);
                   
                    intent.putExtra("chattype",position);
                    
                    
                    startActivity(intent);
                }
            });
    }

    private void updatefilelist()
    {
        this.list =new ArrayList<String>();
        this.list.add("群组消息测试");
        this.list.add("好友消息测试");
        this.list.add("临时消息测试");
    }


 






    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view;
            ViewHolder mHolder;
            if(convertView==null){
                view= LayoutInflater.from(Test2Activity.this).inflate(R.layout.itemcards,null);
                mHolder=new ViewHolder();
                mHolder.card_title=(TextView) view.findViewById(R.id.cardTitle);
                view.setTag(mHolder);  //将ViewHolder存储在View中
            }else {
                view=convertView;
                mHolder=(ViewHolder)view.getTag();  //重新获得ViewHolder
            }
            mHolder.card_title.setText(list.get(position).toString());
            return view;
        }
    }

    class ViewHolder{
        TextView card_title;
        ImageView card_content;
    }
}
