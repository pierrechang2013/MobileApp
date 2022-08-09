package canada.montreal.pierre.android2_exam2;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.sql.Connection;
import java.util.List;
import java.util.Objects;

import canada.montreal.pierre.android2_exam2.mysql.DbUtil;

public class ListActivity extends AppCompatActivity {

    RecyclerView rv;
    RecyclerView.Adapter rva;
    FloatingActionButton fab;
    final String  TAG = this.getClass().getSimpleName();


    List<Client> list;

//    long gymId;
//    String lastName;
//    String firstName;
//    int age;
//    String sex;

    Handler handler;
    final int GET_LIST = 0;
    final int Add_GE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d(TAG,"creattttttttttttttt");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        //Intent intent = getIntent();
        //gymTitle
        //gymTitle = intent.getStringExtra("gymTitle");
        toolbar.setTitle("Lister");
        setSupportActionBar(toolbar);
        //必须这样写
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);



        //gymId = intent.getLongExtra( "gymId",0);
//        Log.d(TAG+"gymId",String.valueOf(gymId) );
        this.lister();

        handler = new Handler(Looper.myLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                if (msg.what == GET_LIST) {
                    rv = findViewById(R.id.rv1);
                    rv.setLayoutManager(new LinearLayoutManager(ListActivity.this));

                    rva = new ListAdapter2(list);

                    rv.setAdapter(rva);
                }
//                else if(msg.what == Add_GE){
//                     listerGeExcercies(gymId);
//                }
            }
        };





    }

    @Override
    protected void onStart() {//按了后退键，onCreat不执行，从这里开始//当然Resume也会执行
        super.onStart();
        Log.d(TAG,"Startttttttttttttttttttt");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"Resumeeeeeeeeeeeeeeee");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        //getMenuInflater().inflate(R.id);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case android.R.id.home:{
                Intent intern = new Intent(ListActivity.this, MainActivity.class);
                startActivity(intern);
                this.finish();
            }

            default:{
                break;
            }

        }
        return super.onOptionsItemSelected(item);
    }

    //------------------------获取列表--------------------------

        void lister() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                Connection con;
                con = DbUtil.getConn();

                list = DbUtil.getClientList(con);
                //创建message对象
                Message message = new Message();
                //
                message.what = GET_LIST;
                handler.sendMessage(message);
            }
        }).start();

    }







}