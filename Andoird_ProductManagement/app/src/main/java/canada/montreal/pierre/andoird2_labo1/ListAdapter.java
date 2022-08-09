package canada.montreal.pierre.andoird2_labo1;

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

    List<Produit> list = new ArrayList<Produit>();

    public ListAdapter(List<Produit> list1) {
        this.list = list1;
        Log.d("现在列表", "长度：" + String.valueOf(list1.size()));
        Log.d("现在列表", "长度：" + String.valueOf(list1.size()));

    }


    //创建子项的内容的对应类，包括视图和每一个组件
    static class ViewHolder extends RecyclerView.ViewHolder {
        View item;

        //TextView id;
        TextView name;
        TextView category;
        TextView price;
        TextView quantity;

        public ViewHolder(@NonNull View view) {
            super(view);

            item = view;

            //id =   view.findViewById(R.id.textView6);
            name =  view.findViewById(R.id.textView8);
            category =  view.findViewById(R.id.textView4);
            price =  view.findViewById(R.id.textView5);
            quantity =  view.findViewById(R.id.textView7);

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


//        viewHolder.item.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int position = viewHolder.getAdapterPosition();
//                Produit Produit = list.get(position);
//                //Toast.makeText(v.getContext(), "你点击了这个布局", Toast.LENGTH_SHORT).show();
//
//
//                //删除
//                list.remove(position);
//
//                notifyItemRemoved(position);
//                //刷新删除后每个item的position
//                notifyItemRangeChanged(position, list.size() - position, "removeItem");
//
//                Log.d("适配器", "目前是第" + String.valueOf(position) + "个，被删除，当前还有" + list.size() + "个数据");
//
//
//
//
//            }
//        });

//        viewHolder.image.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int position = viewHolder.getAdapterPosition();
//                Produit Produit = list.get(position);
//                Toast.makeText(v.getContext(), "你点击了图片", Toast.LENGTH_SHORT).show();
//            }
//        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Produit produit = list.get(position);

        //holder.id.setText(produit.getId());
        holder.name.setText(produit.getName());
        holder.category.setText(produit.getCategery());
        holder.price.setText(String.valueOf(produit.getPrice()));
        holder.quantity.setText(String.valueOf(produit.getQuantity()));//一定要转换成String否则运行会报错


    }

    @Override
    public int getItemCount() {


        Log.d("R列表", "长度：" + String.valueOf(list.size()));
        return list.size();
    }

}
