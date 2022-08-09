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
import android.view.MenuItem;

import java.sql.Connection;
import java.util.List;
import java.util.Objects;

import canada.montreal.pierre.android2_exam2.mysql.DbUtil;

public class ListBySexeActivity extends AppCompatActivity {

    Handler handler;
    final int GET_LIST = 0;

    RecyclerView rv;
    RecyclerView.Adapter rva;
    List<Client> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_by_sexe);
        Toolbar toolbar = findViewById(R.id.toolbar3);
        Intent intent = getIntent();
        String sex = intent.getStringExtra("sex");

        toolbar.setTitle("Sexe est "+sex);
        setSupportActionBar(toolbar);
        //必须这样写
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

//        Intent intent = getIntent();
//        String sex = intent.getStringExtra("sex");


        handler = new Handler(Looper.myLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                if (msg.what == GET_LIST) {
                    rv = findViewById(R.id.rv3);
                    rv.setLayoutManager(new LinearLayoutManager(ListBySexeActivity.this));

                    rva = new ListAdapter2(list);

                    rv.setAdapter(rva);
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
                list = DbUtil.getClientBySex(con,sex);
                //Log.d("************ageInt ",String.valueOf(ageInt));
                Message message = new Message();
                //
                message.what = GET_LIST;
                handler.sendMessage(message);
            }
        }).start();

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
}