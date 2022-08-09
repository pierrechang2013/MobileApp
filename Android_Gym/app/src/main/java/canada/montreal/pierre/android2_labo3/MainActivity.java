package canada.montreal.pierre.android2_labo3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;

import android.net.Uri;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.sql.Connection;
import java.util.List;

import canada.montreal.pierre.android2_labo3.mysql.DbUtil;

public class MainActivity extends AppCompatActivity {

    final String TAG = this.getClass().getSimpleName();


    RecyclerView rv;
    RecyclerView.Adapter rva;

    List<Gym> list;


    Handler handler;
    final int GET_LIST = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //必须开子线程异步完成耗时操作，并通知主线程
        listerProduits();

        //主线程handler等待
        handler = new Handler(Looper.myLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                if (msg.what == GET_LIST) {
                    rv = findViewById(R.id.rv);
                    //GridLayoutManager的构造方法接收两个参数，第一个是Context,第二个是列数，这里我们希望每一行中会有两列数据
                    GridLayoutManager layoutManager = new GridLayoutManager(MainActivity.this, 2);
                    rv.setLayoutManager(layoutManager);
                    rva = new ListAdapter(list);
                    rv.setAdapter(rva);
                }
            }
        };


        //有效，按照文件名获得ID，传递给Gym的img,就可以显示了
//        ApplicationInfo appInfo = getApplicationInfo();
//        int resID = getResources().getIdentifier("aaa", "drawable", appInfo.packageName);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();


        switch (id) {

            case R.id.email:
                sendEmail();
                break;

            case R.id.phone:

                String action = Intent.ACTION_DIAL;
                Intent intent = new Intent(action);
                String number = "514 244 5352";
                intent.setData(Uri.parse("tel:" + number));
                //start
                //startActivity(intent);

                startActivity(intent);
                break;

            default:
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    protected void sendEmail() {
        Log.i("Send email", "");


        Uri uri = Uri.parse("mailto:pierrechang2013@gmail.com");
        String[] email = {"pierrechang2013@gmail.com"};
        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
        intent.putExtra(Intent.EXTRA_CC, email); // 抄送人
        intent.putExtra(Intent.EXTRA_SUBJECT, "C'est le sujet"); // 主题
        intent.putExtra(Intent.EXTRA_TEXT, "C'est le contenu"); // 正文
        startActivity(Intent.createChooser(intent, "Choissez quel email appli"));

        try {
            startActivity(Intent.createChooser(intent, "Send mail..."));
            finish();
            Log.i(TAG, "Envoyez un email.....");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }


    void listerProduits() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                Connection con;
                con = DbUtil.getConn();
//                if (con == null) {
//                    con = DbUtil.getConn();
//                    Log.d("kkkkkkkkkkkkkkkkkk", "kkkk");
//                }
                list = DbUtil.getGymList(con);
                //创建message对象
                Message message = new Message();
                //
                message.what = GET_LIST;
                handler.sendMessage(message);
            }
        }).start();

    }


}