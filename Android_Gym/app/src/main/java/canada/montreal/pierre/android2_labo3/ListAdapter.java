package canada.montreal.pierre.android2_labo3;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.bumptech.glide.Glide;


public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    List<Gym> list = new ArrayList<Gym>();

    private int Load_IMG;

    private Context context;
    String imgUrl = "https://scontent-lga3-1.xx.fbcdn.net/v/t39.30808-6/273778818_419417599960995_4914654670609075314_n.jpg?_nc_cat=102&ccb=1-7&_nc_sid=730e14&_nc_ohc=ZH54a7vEwPYAX-fYI7J&_nc_ht=scontent-lga3-1.xx&oh=00_AT-D4fd3sdigKq57M0zJG6PPmPAzriyd-MZcjg5WeEuHBg&oe=62C8F5C5";
    Bitmap bitmap = null;
    public ListAdapter(List<Gym> list1) {
        this.list = list1;
        //Log.d("现在列表", "长度：" + String.valueOf(list1.size()));
        //Log.d("现在列表", "长度：" + String.valueOf(list1.size()));

    }


    //创建子项的内容的对应类，包括视图和每一个组件
    static class ViewHolder extends RecyclerView.ViewHolder {
        View item;

        ImageView img;
        TextView title;


        public ViewHolder(@NonNull View view) {
            super(view);

            item = view;


            img = view.findViewById(R.id.gymImg);
            title = view.findViewById(R.id.gymTitle);


        }
    }

    @NonNull
    @Override
    //创建viewHolder，就是view
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //list = Functions.getList();
        //动态创建子项的视图
        if (context == null) {
            context = parent.getContext();
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        //把子项的视图 放到viewHolder
        final ViewHolder viewHolder = new ViewHolder(view);


        viewHolder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                Gym gym = list.get(position);
                Toast.makeText(v.getContext(), "你点击了这个布局", Toast.LENGTH_SHORT).show();

                long gymId = gym.getId();
                String gymTitle = gym.getTitle();
                Intent intern = new Intent(context, ListActivity.class);
                intern.putExtra("gymId", gymId);
                intern.putExtra("gymTitle", gymTitle);
                context.startActivity(intern);


                Log.d("适配器", "目前是第" + String.valueOf(position) + "个，当前还有" + list.size() + "个数据");


            }
        });


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Gym gym = list.get(position);

        //给个默认值

        //这个getImg是R.drawable.xxx的id号

        String imgName = gym.getImg();
        Log.d("!!!imgName",imgName);
        ApplicationInfo appInfo = context.getApplicationInfo();
        int imgId = context.getResources().getIdentifier(imgName, "drawable", appInfo.packageName);
        Log.d("!!!imgId",String.valueOf(imgId));
        Glide.with(context).load(imgId).into(holder.img);
        holder.title.setText(gym.getTitle());



    }

    @Override
    public int getItemCount() {

        return list.size();
    }

}
