package canada.montreal.pierre.android1_exercice9;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class List2Fragment extends Fragment {



    Button button;
    RecyclerView rv;
    RecyclerView.Adapter rva;

    String CHANNEL_ID = "ID2";

    List<People> list;

    List2ViewModel lvm2;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate( R.layout.fragment_list2, null);
        setHasOptionsMenu(true);
        lvm2 =new ViewModelProvider(this).get(List2ViewModel.class);
        list = lvm2.getList();
        if(list==null){
            Log.d("List2Fragment", "list nulllllllllllllllll");

            list = Functions.getList();
            lvm2.setList(list);
        }else{

            Log.d("List2Fragment list size", String.valueOf(list.size()));
        }

        //创建Recycler对象
        rv = view.findViewById(R.id.rv2);
        //设置布局
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        rva = new ListAdapter(list);
        //填充内容子对象
        rv.setAdapter(rva);

        button = view.findViewById(R.id.button1);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                People p = new People();
                p.setFirstName("ccccc");
                p.setLastName("sssss");

                //添加到指定位置
                list.add(0, p);
                //重新加載adapter適配器
                rva.notifyDataSetChanged();
                rv.setAdapter(rva);
                //rva.notifyItemRangeChanged(0,1,"addItem");
                //rv.setAdapter(rva);

                //---------发送通知---------------
                NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getContext(), CHANNEL_ID)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentText("Student " + p.getFirstName() + "  " + p.getLastName() + " added")
                        .setContentTitle("Notification")
                        .setAutoCancel(true);
                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getContext());
                notificationManager.notify(1, notificationBuilder.build());
                //-------------------------
            }
        });


        createNotificationChannel();


        return view;
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        super.onCreateOptionsMenu(menu, menuInflater);
        menuInflater.inflate(R.menu.main, menu);

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
                return true;
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "name1";
            String description ="description1";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getContext().getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}