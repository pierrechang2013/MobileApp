package canada.montreal.pierre.android2_exam2;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import canada.montreal.pierre.android2_exam2.mysql.DbUtil;


public class ListAdapter2 extends RecyclerView.Adapter<ListAdapter2.ViewHolder> {
    private final String TAG = this.getClass().getSimpleName();
    List<Client> list = new ArrayList<Client>();


    private Context context;


    String imgUrl = "https://scontent-lga3-1.xx.fbcdn.net/v/t39.30808-6/273778818_419417599960995_4914654670609075314_n.jpg?_nc_cat=102&ccb=1-7&_nc_sid=730e14&_nc_ohc=ZH54a7vEwPYAX-fYI7J&_nc_ht=scontent-lga3-1.xx&oh=00_AT-D4fd3sdigKq57M0zJG6PPmPAzriyd-MZcjg5WeEuHBg&oe=62C8F5C5";
    Bitmap bitmap = null;
    private int Load_IMG;
    public ListAdapter2(List<Client> list1) {
        this.list = list1;

        Log.d(TAG, "ListAdapter2 长度：" + String.valueOf(list1.size()));


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

        //给图片增加监听
//         viewHolder.img.setOnClickListener(new View.OnClickListener() {
//             @Override
//             public void onClick(View v) {
//                 //Toast.makeText(v.getContext(), "你点击了这个图片", Toast.LENGTH_SHORT).show();
//                 int position = viewHolder.getAdapterPosition();
//                 //Toast.makeText(v.getContext(), "你点击了这个图片  当前postion is:"+position, Toast.LENGTH_SHORT).show();
//
//                 Client ge = list.get(position);
//
//
//                 long gymExId = ge.getId();
//                 String gymExTitle = ge.getTitle();
//                 Intent intern = new Intent(context,VideoActivity.class);
//
//                 intern.putExtra("gymExId",gymExId);
//                 intern.putExtra("gymExTitle",gymExTitle);
//                 context.startActivity(intern);
//
//
//             }
//         });

        //给删除imageview增加监听
//        viewHolder.delete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int position = viewHolder.getAdapterPosition();
//
//                GymExercice ge = list.get(position);
//               //删除
//                Toast.makeText(context, "删除"+String.valueOf(ge.getId()),Toast.LENGTH_LONG);
//                deleteGymExercice(ge.getId());
//                list.remove(position);
//
//                notifyItemRemoved(position);
//                //刷新删除后每个item的position
//                notifyItemRangeChanged(position, list.size() - position, "removeItem");
//                //long gymExId = ge.getId();
//
//            }
//        });

        //给更新imageview增加监听
//        viewHolder.update.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int position = viewHolder.getAdapterPosition();
//
//                GymExercice ge = list.get(position);
//                showDialog(ge);
//
//            }
//        });

        //整个item增加监听
//        viewHolder.item.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int position = viewHolder.getAdapterPosition();
//                GymExercice ge = list.get(position);
//                //Toast.makeText(v.getContext(), "你点击了这个布局", Toast.LENGTH_SHORT).show();
//                long gymExId = ge.getId();
//
//                Intent intern = new Intent(context,VideoActivity.class);
//
//                intern.putExtra("gymExId",gymExId);
//                context.startActivity(intern);
//
//
//
//            }
//        });


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


        //Log.d("R列表", "长度：" + String.valueOf(list.size()));
        return list.size();
    }

    //------------------------更新并保存--------------------------






//    protected void showDialog(GymExercice ge ) {
//
//        final Dialog dialog = new Dialog(context);
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setContentView(R.layout.fragment_del_dialog);
//
//        final EditText img =  dialog.findViewById(R.id.editTextTextPersonName);
//        final EditText title =  dialog.findViewById(R.id.editTextTextPersonName2);
//        final EditText disc =  dialog.findViewById(R.id.editTextTextPersonName3);
//
//
//        final TextView imgTv =  dialog.findViewById(R.id.textView);
//        final TextView titleTv =  dialog.findViewById(R.id.textView2);
//        final TextView discTv =  dialog.findViewById(R.id.textView9);
//
//
//
//        Button cancel = dialog.findViewById(R.id.button);
//        Button save = dialog.findViewById(R.id.button2);
//
//        img.setText(ge.getImg());
//        title.setText(ge.getTitle());
//        disc.setText(ge.getDisc());
//
//
//        save.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//
//                String imgStr = img.getText().toString();
//                String titleStr = title.getText().toString();
//                String discStr = disc.getText().toString();
//
//                if(imgStr.length()<=0){
//                    imgTv.setTextColor(Color.parseColor("#FFF0083E"));
//                    return;
//                }
//                if(titleStr.length()<=0){
//                    titleTv.setTextColor(Color.parseColor("#FFF0083E"));
//                    return;
//                }
//                if(discStr.length()<=0){
//                    discTv.setTextColor(Color.parseColor("#FFF0083E"));
//                    return;
//                }
//
//                ge.setImg(imgStr);
//                ge.setTitle(titleStr);
//                ge.setDisc(discStr);
//
//
//                //更新
//                updateGymExercice(ge);
//                notifyDataSetChanged();
//                dialog.dismiss();
//
//            }
//        });
//
//        cancel.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//
//
//                dialog.dismiss();
//            }
//        });
//
//        dialog.show();
//    }
//
//    void deleteGymExercice(long id){
//          new Thread(new Runnable() {
//              @Override
//              public void run() {
//                 Connection con = DbUtil.getConn();
//                  DbUtil.deleteGymExercice(con,id);
//              }
//          }).start();
//
//    }


}
