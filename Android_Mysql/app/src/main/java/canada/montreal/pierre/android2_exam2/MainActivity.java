package canada.montreal.pierre.android2_exam2;
/*
* Le lien de video
*
* https://vimeo.com/729832694/7222d5234f
*
*
* */
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.app.Dialog;
import android.content.Intent;

import android.graphics.Color;
import android.net.Uri;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import canada.montreal.pierre.android2_exam2.mysql.DbUtil;

public class MainActivity extends AppCompatActivity {

    final String TAG = this.getClass().getSimpleName();




    List<Client> list;



    Button listBtn;
    Button registerBtn;
    Button delBtn;
    Button listSprBtn;
    Button listBySexBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listBtn = findViewById(R.id.button4);
        registerBtn = findViewById(R.id.button3);
        delBtn = findViewById(R.id.button5);
        listSprBtn = findViewById(R.id.button6);
        listBySexBtn = findViewById(R.id.button11);


        listBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intern = new Intent(MainActivity.this, ListActivity.class);
//                intern.putExtra("gymId", gymId);
//                intern.putExtra("gymTitle", gymTitle);
                startActivity(intern);
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intern = new Intent(MainActivity.this, RegisterActivity.class);
//                intern.putExtra("gymId", gymId);
//                intern.putExtra("gymTitle", gymTitle);
                startActivity(intern);
            }
        });

        listSprBtn.setOnClickListener(new View.OnClickListener() {//by age
            @Override
            public void onClick(View v) {
                showListDialog();
            }
        });

        delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDelDialog();
            }
        });

        listBySexBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSexDialog();
            }
        });




    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//
//        getMenuInflater().inflate(R.menu.menu, menu);
//        return super.onCreateOptionsMenu(menu);
//
//    }



    protected void showListDialog( ) {

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.fragment_list_dialog);

        final EditText age =  dialog.findViewById(R.id.editTextTextPersonName);



        final TextView ageTv =  dialog.findViewById(R.id.textView);
//        final TextView titleTv =  dialog.findViewById(R.id.textView2);
//        final TextView discTv =  dialog.findViewById(R.id.textView9);



        Button cancel = dialog.findViewById(R.id.button);
        Button check = dialog.findViewById(R.id.button2);

        check.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String ageStr = age.getText().toString();


                if(ageStr.length()<=0){
                    ageTv.setTextColor(Color.parseColor("#FFF0083E"));
                    return;
                }


                Intent intern = new Intent(MainActivity.this, ListSpecailActivity.class);
                intern.putExtra("age", ageStr);
                 startActivity(intern);

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

    protected void showDelDialog( ) {
        final int DEL = 0;
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.fragment_del_dialog);

        final EditText id =  dialog.findViewById(R.id.editTextTextPersonName3);



        final TextView idTv =  dialog.findViewById(R.id.textView9);




        Button cancel = dialog.findViewById(R.id.button10);
        Button del = dialog.findViewById(R.id.button9);

        del.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String idStr = id.getText().toString();
//                String titleStr = title.getText().toString();
//                String discStr = disc.getText().toString();

                if(idStr.length()<=0){
                    idTv.setTextColor(Color.parseColor("#FFF0083E"));
                    return;
                }

                Handler handler = new Handler(Looper.myLooper()) {
                    @Override
                    public void handleMessage(@NonNull Message msg) {
                        super.handleMessage(msg);
                        if (msg.what == DEL) {
                            Intent intern = new Intent(MainActivity.this, ListActivity.class);

                            startActivity(intern);
                        }
//                else if(msg.what == Add_GE){
//                     listerGeExcercies(gymId);
//                }
                    }
                };

                new Thread(new Runnable() {
                    @Override

                        public void run() {
                            Connection con = DbUtil.getConn();
                             DbUtil.deleteClient(con,Integer.parseInt(idStr));
                            //Log.d("************ageInt ",String.valueOf(ageInt));
                            Message message = new Message();

                            message.what = DEL;
                            handler.sendMessage(message);
                    }
                }).start();



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

    protected void showSexDialog() {

        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.fragment_sex_dialog);


        //final TextView sexTv =  dialog.findViewById(R.id.textView10);
        final String[] sexStr = {""};

        EditText sex = dialog.findViewById(R.id.editTextTextPersonName2);

        Button cancel = dialog.findViewById(R.id.button13);
        Button check = dialog.findViewById(R.id.button12);

        check.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                Intent intern = new Intent(MainActivity.this, ListBySexeActivity.class);
                intern.putExtra("sex",  sex.getText().toString().toUpperCase());
                startActivity(intern);

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




}