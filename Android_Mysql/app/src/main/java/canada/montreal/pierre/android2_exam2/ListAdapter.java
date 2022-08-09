package canada.montreal.pierre.android2_exam2;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    List<Client> list = new ArrayList<Client>();

    private int Load_IMG;

    private Context context;
//    String imgUrl = "https://scontent-lga3-1.xx.fbcdn.net/v/t39.30808-6/273778818_419417599960995_4914654670609075314_n.jpg?_nc_cat=102&ccb=1-7&_nc_sid=730e14&_nc_ohc=ZH54a7vEwPYAX-fYI7J&_nc_ht=scontent-lga3-1.xx&oh=00_AT-D4fd3sdigKq57M0zJG6PPmPAzriyd-MZcjg5WeEuHBg&oe=62C8F5C5";
//    Bitmap bitmap = null;
    public ListAdapter(List<Client> list1) {
        this.list = list1;
        //Log.d("现在列表", "长度：" + String.valueOf(list1.size()));
        //Log.d("现在列表", "长度：" + String.valueOf(list1.size()));

    }


    //创建子项的内容的对应类，包括视图和每一个组件
    static class ViewHolder extends RecyclerView.ViewHolder {
        View item;
        TextView id;
        TextView lastName;
        TextView firstName;
        TextView age;
        TextView sex;



        public ViewHolder(@NonNull View view) {
            super(view);

            item = view;


            id = view.findViewById(R.id.textView3);
            lastName = view.findViewById(R.id.textView4);
            firstName = view.findViewById(R.id.textView5);
            age = view.findViewById(R.id.textView6);
            sex = view.findViewById(R.id.textView7);




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
                Client client = list.get(position);
                Toast.makeText(v.getContext(), "你点击了这个布局", Toast.LENGTH_SHORT).show();

//                long gymId = client.getId();
//                String gymTitle = client.getTitle();
//                Intent intern = new Intent(context, ListActivity.class);
//                intern.putExtra("gymId", gymId);
//                intern.putExtra("gymTitle", gymTitle);
//                context.startActivity(intern);


                Log.d("适配器", "目前是第" + String.valueOf(position) + "个，当前还有" + list.size() + "个数据");


            }
        });


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Client client = list.get(position);

        //给个默认值

        //这个getImg是R.drawable.xxx的id号

//        String imgName = client.getImg();
//        Log.d("!!!imgName",imgName);
//        ApplicationInfo appInfo = context.getApplicationInfo();
//        int imgId = context.getResources().getIdentifier(imgName, "drawable", appInfo.packageName);
//        Log.d("!!!imgId",String.valueOf(imgId));
//        Glide.with(context).load(imgId).into(holder.img);
        holder.id.setText(String.valueOf(client.getId()));
        holder.lastName.setText(client.getLastName());
        holder.firstName.setText(client.getFirstName());
        holder.age.setText(String.valueOf(client.getAge()));
        holder.sex.setText(client.getSex());



    }

    @Override
    public int getItemCount() {

        return list.size();
    }

}
