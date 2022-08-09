package canada.montreal.pierre.android1_excercice08;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    List<People> list = new ArrayList<People>();

    public ListAdapter(List<People> list) {
        this.list = list;
    }


    //创建子项的内容的对应类，包括视图和每一个组件
    static class ViewHolder extends RecyclerView.ViewHolder {
        View item;
        ImageView image;
        TextView lastName;
        TextView firstName;

        public ViewHolder(@NonNull View view) {
            super(view);
            item = view;
            image = (ImageView) view.findViewById(R.id.imageView);
            lastName = (TextView) view.findViewById(R.id.textView);
            firstName = (TextView) view.findViewById(R.id.textView2);

        }
    }

    @NonNull
    @Override
    //创建viewHolder，就是view
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //list = Functions.getList();
        //动态创建子项的视图
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        //把子项的视图 放到viewHolder
        final ViewHolder viewHolder = new ViewHolder(view);


        viewHolder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                People people = list.get(position);
                //Toast.makeText(v.getContext(), "你点击了这个布局", Toast.LENGTH_SHORT).show();
                DialogFrag df = new DialogFrag();

                //删除
                list.remove(position);

                notifyItemRemoved(position);
                //刷新删除后每个item的position
                notifyItemRangeChanged(position, list.size() - position, "removeItem");

                Log.d("适配器", "目前是第" + String.valueOf(position) + "个，被删除，当前还有" + list.size() + "个数据");
                //---------------------发送通知------------------
                String CHANNEL_ID = "ID1";//这个CHANNEL_ID要和onCreate里初始化的一致

//                //        不能加pendInt，31以上版本，这样写有错，
//                Intent notIntent = new Intent(parent.getContext(), MainActivity.class);
//                notIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                PendingIntent pendInt = PendingIntent.getActivity(parent.getContext(), 0,
//                        notIntent, PendingIntent.FLAG_UPDATE_CURRENT);

                NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(parent.getContext(), CHANNEL_ID)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentText("Student " + people.getFirstName() + "  " + people.getLastName() + " deleted")
                        .setContentTitle("Notification")
                        .setAutoCancel(true);
                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(parent.getContext());
                notificationManager.notify(1, notificationBuilder.build());

            }
        });

        viewHolder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                People people = list.get(position);
                Toast.makeText(v.getContext(), "你点击了图片", Toast.LENGTH_SHORT).show();
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        People people = list.get(position);
        holder.firstName.setText(people.getFirstName());
        holder.lastName.setText(people.getLastName());
    }

    @Override
    public int getItemCount() {
        Log.d("R列表", "长度：" + String.valueOf(list.size()));
        return list.size();
    }

}
