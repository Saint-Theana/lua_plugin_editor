package saki.demo;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.os.Handler;
import android.os.Message;
import tiiehenry.code.view.CodeEditor;
import android.text.Layout;
import android.view.View;
import android.widget.LinearLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.content.*;
import android.view.View.*;

public class Test5Activity extends AppCompatActivity {

    private CodeEditor editor;
	private Button button1;
	private Button button2;

    private String filename;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.filename=this.getIntent().getStringExtra("filename");
        setContentView(R.layout.activity_test5);
        this.editor=(CodeEditor) findViewById(R.id.contenttest5CodeEditor1);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		this.button1=(Button) findViewById(R.id.activitytest5Button1);
		this.button2=(Button) findViewById(R.id.activitytest5Button2);
        setSupportActionBar(toolbar);
        this.initcode();
        
        
    }

    private void initcode()
    {
        this.editor.setText(Util.readfrom(this.filename));
		this.button1.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					Toast.makeText(Test5Activity.this,"撤回",Toast.LENGTH_SHORT).show();
				    Test5Activity.this.editor.undo();
				}
		});
		
		this.button2.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					Toast.makeText(Test5Activity.this,"前进",Toast.LENGTH_SHORT).show();
				    Test5Activity.this.editor.redo();
				}


			});
    }
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.testmenu5, menu);
        return true;
    }
    
    
    
    @Override 
    public boolean onOptionsItemSelected(MenuItem item) 
    { 
        super.onOptionsItemSelected(item); 
        switch(item.getItemId()){ 
            case R.id.action_save:
                if(Util.savetofile(this.editor.getString(),this.filename)){
                    Toast.makeText(this,"保存成功",Toast.LENGTH_SHORT).show(); 
                }else{
                    Toast.makeText(this,"保存失败",Toast.LENGTH_SHORT).show(); 
                }
                break; 
            case R.id.action_test: 
				if(Util.savetofile(this.editor.getString(),this.filename)){
                    Toast.makeText(this,"保存成功",Toast.LENGTH_SHORT).show(); 
                }else{
                    Toast.makeText(this,"保存失败",Toast.LENGTH_SHORT).show(); 
                }
				Intent intent = new Intent(this,Test2Activity.class);
				intent.putExtra("filename",this.filename);
				startActivity(intent); 
                break; 

        } 
        return true; 
    }
    
}
