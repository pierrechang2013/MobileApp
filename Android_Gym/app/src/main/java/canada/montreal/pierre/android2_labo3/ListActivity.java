package canada.montreal.pierre.android2_labo3;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;

import android.graphics.Color;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import canada.montreal.pierre.android2_labo3.mysql.DbUtil;

public class ListActivity extends AppCompatActivity {

    RecyclerView rv;
    RecyclerView.Adapter rva;
    FloatingActionButton fab;
    final String  TAG = this.getClass().getSimpleName();


    List<GymExercice> list;

    long gymId;
    String gymTitle;

    Handler handler;
    final int GET_LIST = 0;
    final int Add_GE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d(TAG,"creattttttttttttttt");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        Intent intent = getIntent();
        //gymTitle
        gymTitle = intent.getStringExtra("gymTitle");
        toolbar.setTitle(gymTitle);
        setSupportActionBar(toolbar);
        //必须这样写
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);



        gymId = intent.getLongExtra( "gymId",0);
        Log.d(TAG+"gymId",String.valueOf(gymId) );
        this.listerGeExcercies(gymId);

        handler = new Handler(Looper.myLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                if (msg.what == GET_LIST) {
                    rv = findViewById(R.id.rv1);
                    rv.setLayoutManager(new LinearLayoutManager(ListActivity.this));

                    rva = new ListAdapter2(list);

                    rv.setAdapter(rva);
                }else if(msg.what == Add_GE){
                     listerGeExcercies(gymId);
                }
            }
        };



        fab = findViewById(R.id.floatingActionButton);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                showDialog();

            }
        });

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
                this.finish();
            }

            default:{
                break;
            }

        }
        return super.onOptionsItemSelected(item);
    }

    //------------------------获取列表--------------------------

    void listerGeExcercies(long gymId){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Connection con;
                con = DbUtil.getConn();
//                if (con == null) {
//                    con = DbUtil.getConn();
//                    Log.d("kkkkkkkkkkkkkkkkkk", "kkkk");
//                }
                list = DbUtil.getGeList(con,gymId);
                //创建message对象
                Message message = new Message();
                //赋给标识
                message.what = GET_LIST;
                //发送信息
                handler.sendMessage(message);
            }
        }).start();


    }

    //------------------------添加--------------------------
    void addGymExercice(GymExercice ge){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Connection con = DbUtil.getConn();
                DbUtil.addGymExercice(con,ge);
                Message message = new Message();
                message.what = Add_GE;

//                con = DbUtil.getConn();
//                list =  DbUtil.getGeList(con,ge.getGymId());
                handler.sendMessage(message);

            }
        }).start();

    }
//----------------------------------
    protected void showDialog( ) {

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.fragment_dialog);

        final EditText img =  dialog.findViewById(R.id.editTextTextPersonName);
        final EditText title =  dialog.findViewById(R.id.editTextTextPersonName2);
        final EditText disc =  dialog.findViewById(R.id.editTextTextPersonName3);


        final TextView imgTv =  dialog.findViewById(R.id.textView);
        final TextView titleTv =  dialog.findViewById(R.id.textView2);
        final TextView discTv =  dialog.findViewById(R.id.textView9);



        Button cancel = dialog.findViewById(R.id.button);
        Button save = dialog.findViewById(R.id.button2);

        save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String imgStr = img.getText().toString();
                String titleStr = title.getText().toString();
                String discStr = disc.getText().toString();

                if(imgStr.length()<=0){
                    imgTv.setTextColor(Color.parseColor("#FFF0083E"));
                    return;
                }
                if(titleStr.length()<=0){
                    titleTv.setTextColor(Color.parseColor("#FFF0083E"));
                    return;
                }
                if(discStr.length()<=0){
                    discTv.setTextColor(Color.parseColor("#FFF0083E"));
                    return;
                }


                GymExercice GymExercice = new GymExercice(gymId,imgStr,titleStr,discStr, "");



                //添加todo
                addGymExercice(GymExercice);

                dialog.dismiss();

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                dialog.dismiss();
            }
        });

        dialog.show();
    }


//------------------------删除--------------------------
    void deleteGymExercice(){

}


}