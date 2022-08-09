package canada.montreal.pierre.android2_labo3;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import canada.montreal.pierre.android2_labo3.mysql.DbUtil;


public class ListAdapter2 extends RecyclerView.Adapter<ListAdapter2.ViewHolder> {
    private final String TAG = this.getClass().getSimpleName();
    List<GymExercice> list = new ArrayList<GymExercice>();


    private Context context;

    private ProjectDAO pd;

    String imgUrl = "https://scontent-lga3-1.xx.fbcdn.net/v/t39.30808-6/273778818_419417599960995_4914654670609075314_n.jpg?_nc_cat=102&ccb=1-7&_nc_sid=730e14&_nc_ohc=ZH54a7vEwPYAX-fYI7J&_nc_ht=scontent-lga3-1.xx&oh=00_AT-D4fd3sdigKq57M0zJG6PPmPAzriyd-MZcjg5WeEuHBg&oe=62C8F5C5";
    Bitmap bitmap = null;
    private int Load_IMG;
    public ListAdapter2(List<GymExercice> list1) {
        this.list = list1;
        this.pd = pd;
        Log.d(TAG, "ListAdapter2 长度：" + String.valueOf(list1.size()));


    }


    //创建子项的内容的对应类，包括视图和每一个组件
    static class ViewHolder extends RecyclerView.ViewHolder {
        View item;

        ImageView img;
        TextView title;
        TextView disc;

        ImageView update;
        ImageView delete;


        public ViewHolder(@NonNull View view) {
            super(view);

            item = view;


            img =  view.findViewById(R.id.geImg);
            title =  view.findViewById(R.id.geTitle);
            disc = view.findViewById(R.id.geDisc);

            update = view.findViewById(R.id.imageView);
            delete = view.findViewById(R.id.imageView2);

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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        //把子项的视图 放到viewHolder
        final ViewHolder viewHolder = new ViewHolder(view);

        //给图片增加监听
         viewHolder.img.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 //Toast.makeText(v.getContext(), "你点击了这个图片", Toast.LENGTH_SHORT).show();
                 int position = viewHolder.getAdapterPosition();
                 //Toast.makeText(v.getContext(), "你点击了这个图片  当前postion is:"+position, Toast.LENGTH_SHORT).show();

                 GymExercice ge = list.get(position);


                 long gymExId = ge.getId();
                 String gymExTitle = ge.getTitle();
                 Intent intern = new Intent(context,VideoActivity.class);

                 intern.putExtra("gymExId",gymExId);
                 intern.putExtra("gymExTitle",gymExTitle);
                 context.startActivity(intern);


             }
         });

        //给删除imageview增加监听
        viewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();

                GymExercice ge = list.get(position);
               //删除
                Toast.makeText(context, "删除"+String.valueOf(ge.getId()),Toast.LENGTH_LONG);
                deleteGymExercice(ge.getId());
                list.remove(position);

                notifyItemRemoved(position);
                //刷新删除后每个item的position
                notifyItemRangeChanged(position, list.size() - position, "removeItem");
                //long gymExId = ge.getId();

            }
        });

        //给更新imageview增加监听
        viewHolder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();

                GymExercice ge = list.get(position);
                showDialog(ge);

            }
        });

        //整个item增加监听
        viewHolder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                GymExercice ge = list.get(position);
                //Toast.makeText(v.getContext(), "你点击了这个布局", Toast.LENGTH_SHORT).show();
                long gymExId = ge.getId();

                Intent intern = new Intent(context,VideoActivity.class);

                intern.putExtra("gymExId",gymExId);
                context.startActivity(intern);



            }
        });


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        GymExercice ge = list.get(position);


        String imgName = ge.getImg();

        ApplicationInfo appInfo = context.getApplicationInfo();

        int imgId = context.getResources().getIdentifier(imgName, "drawable", appInfo.packageName);
        Glide.with(context).load(imgId).into(holder.img);
        holder.title.setText(ge.getTitle());
        holder.disc.setText(ge.getDisc());




    }

    @Override
    public int getItemCount() {


        //Log.d("R列表", "长度：" + String.valueOf(list.size()));
        return list.size();
    }

    //------------------------更新并保存--------------------------


    void updateGymExercice(GymExercice ge){
        new Thread(
            new Runnable(){
                @Override
                public void run() {
                    Connection con = DbUtil.getConn();
                    DbUtil.updateGymExercice(con,ge);
                }
            }
        ).start();
    }




    protected void showDialog(GymExercice ge ) {

        final Dialog dialog = new Dialog(context);
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

        img.setText(ge.getImg());
        title.setText(ge.getTitle());
        disc.setText(ge.getDisc());


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

                ge.setImg(imgStr);
                ge.setTitle(titleStr);
                ge.setDisc(discStr);


                //更新
                updateGymExercice(ge);
                notifyDataSetChanged();
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

    void deleteGymExercice(long id){
          new Thread(new Runnable() {
              @Override
              public void run() {
                 Connection con = DbUtil.getConn();
                  DbUtil.deleteGymExercice(con,id);
              }
          }).start();

    }


}
