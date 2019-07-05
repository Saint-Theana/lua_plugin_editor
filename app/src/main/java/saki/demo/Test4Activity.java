package saki.demo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import android.widget.BaseAdapter;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;
import java.util.Arrays;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.content.Intent;
import android.widget.EditText;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.widget.CardView;
import saki.demo.Test4Activity.MyAdapter;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import com.Tick_Tock.ANDROIDQQ.sdk.MessageFactory;

public class Test4Activity extends AppCompatActivity  {

    List<String> list=new ArrayList<String>();

    private static String filepath = "/sdcard/zzluascripts/";

    private ListView listview;

    private Test4Activity.MyAdapter adapter;

    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test4);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.initactivity();
        //创建toolbar工具栏
           

        //获得抽屉布局
        

    }

    
    private void initactivity()
    {
        if(!Util.isfileexist(filepath)){
            Util.createdir(filepath);
        }
        this.updatefilelist();
        this.listview=(ListView) this.findViewById(R.id.contenttest4ListView1);
        this.adapter = new MyAdapter();
        listview.setAdapter(this.adapter);
        listview.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int position,long arg3) {
                    Intent intent = new Intent(Test4Activity.this,Test5Activity.class);
                    intent.putExtra("filename",filepath+Test4Activity.this.list.get(position));
                    startActivity(intent);
                }
            });
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Test4Activity.this);
                    builder.setTitle("确定删除？")
                        .setNegativeButton("Cancel", null);
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                if(Util.deletefile(filepath+Test4Activity.this.list.get(position))){
                                    Toast.makeText(Test4Activity.this,"删除成功"+position,Toast.LENGTH_SHORT).show(); 
                                }else{
                                    Toast.makeText(Test4Activity.this,"删除失败"+position,Toast.LENGTH_SHORT).show(); 

                                }
                                Test4Activity.this.updatefilelist();
                                Test4Activity.this.adapter.notifyDataSetChanged();
                                
                            }
                        });
                    builder.show();
                    return true;
                }
            });
    }

    private void updatefilelist()
    {
        this.list = Arrays.asList(Util.getfilelist(filepath));
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.testmenu4, menu);
        return true;
    }



    @Override 
    public boolean onOptionsItemSelected(MenuItem item) 
    { 
        super.onOptionsItemSelected(item); 
        switch(item.getItemId()){ 
            case R.id.action_create: 
                final EditText inputname = new EditText(this);
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("输入脚本名，无需后缀").setView(inputname)
                    .setNegativeButton("Cancel", null);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            if(Util.isfileexist(filepath+inputname.getText()+".lua")){
                                Toast.makeText(Test4Activity.this,"文件已存在",Toast.LENGTH_SHORT).show(); 
                            }else{
                                if(Util.createfile(filepath+inputname.getText()+".lua",Test4Activity.this.getResources())){
                                    Toast.makeText(Test4Activity.this,"创建成功",Toast.LENGTH_SHORT).show();
                                    Test4Activity.this.updatefilelist();
                                    Test4Activity.this.adapter.notifyDataSetChanged();
                                }else{
                                    Toast.makeText(Test4Activity.this,"创建失败",Toast.LENGTH_SHORT).show();
                                    
                                }
                            }
                        }
                    });
                builder.show(); 
                break; 
            case 2: 
                //处理代码 
                break; 
            case 3: 
                //处理代码 
                break; 
            case 4: 
                finish(); 
                break; 
        } 
        return true; 
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
                view= LayoutInflater.from(Test4Activity.this).inflate(R.layout.itemcards,null);
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
