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

public class Test3Activity extends AppCompatActivity  {

    List<String> list=new ArrayList<String>();

    private static String filepath = "/sdcard/zzluascripts/";

    private ListView listview;

    private Test3Activity.MyAdapter adapter;

    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test3);
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
        this.listview=(ListView) this.findViewById(R.id.contenttest3ListView1);
        this.adapter = new MyAdapter();
        listview.setAdapter(this.adapter);
        listview.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int position,long arg3) {
                    Intent intent = new Intent(Test3Activity.this,Test2Activity.class);
                    intent.putExtra("filename",filepath+Test3Activity.this.list.get(position));
                    startActivity(intent);
                }
            });
        
    }

    private void updatefilelist()
    {
        this.list = Arrays.asList(Util.getfilelist(filepath));

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
                view= LayoutInflater.from(Test3Activity.this).inflate(R.layout.itemcards,null);
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
