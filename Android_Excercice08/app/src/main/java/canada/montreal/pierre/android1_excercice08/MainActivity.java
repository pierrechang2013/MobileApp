package canada.montreal.pierre.android1_excercice08;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    Button button;
    RecyclerView rv;
    RecyclerView.Adapter rva;


    ListView listview;
    List<People> list;
    boolean isRecycler = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createNotificationChannel();

        list = Functions.getList();

        //创建Recycler对象
        rv = findViewById(R.id.rv);
        //设置布局
        rv.setLayoutManager(new LinearLayoutManager(this));

        rva = new ListAdapter(list);
        //填充内容子对象
        rv.setAdapter(rva);

        button = findViewById(R.id.button2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                People p = new People();
                p.setFirstName("jjjj");
                p.setLastName("QQQ");

                //添加到指定位置
                list.add(0, p);
                //重新加載adapter適配器
                rva.notifyDataSetChanged();
                rv.setAdapter(rva);
                //rva.notifyItemRangeChanged(0,1,"addItem");
                //rv.setAdapter(rva);

                //---------发送通知---------------
                sendNotification();
                //-------------------------
            }
        });

    }

    //点击菜单事件
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        //button = findViewById(R.id.button);
        //button.setVisibility(View.VISIBLE);
        //创建Recycler对象
        rv = findViewById(R.id.rv);
        rv.setVisibility(View.VISIBLE);
        //设置布局
        rv.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        rva = new ListAdapter(list);
        //填充内容子对象
        rv.setAdapter(rva);

        item.setTitle(R.string.lv);
        isRecycler = false;

        return super.onOptionsItemSelected(item);

    }

    //创建顶部菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu__main, menu);


        SearchView sv = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();

        sv.setMaxWidth(500);//保證不讓搜索框遮住標題

        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                Log.d("搜索康,輸入的", s);
                List<People> temp = new ArrayList<People>();

                for (int i = 0; i < list.size(); i++) {
                    People p = list.get(i);
                    Log.d("p.getFirstName()", p.getLastName());
                    if (p.getLastName().toLowerCase().contains(s)) {
                        Log.d("包含了", p.getLastName());
                        temp.add(p);
                    }


                }

                Log.d(" temp長度", String.valueOf(temp.size()));

                rva.notifyDataSetChanged();//必須加
                rv.setAdapter(new ListAdapter(temp));


                return true;//事件不會向下繼續傳遞了
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "name1";
            String description ="description1";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("ID1", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void sendNotification(){

        String CHANNEL_ID = "ID1";//注意这个，和channel里的要对应上

//        不能加pendInt，31以上版本，这样写有错，
//        Intent notIntent = new Intent(MainActivity.this, MainActivity.class);
//        notIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        PendingIntent pendInt = PendingIntent.getActivity(MainActivity.this, 0,
//                notIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(MainActivity.this,CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentText("Test")
                .setContentTitle("Content")
                .setAutoCancel(true);
                //.setContentIntent(pendInt);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
        notificationManager.notify(1, notificationBuilder.build());
    }


}