package canada.montreal.pierre.andoird2_exam1;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;



public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    List<Employes> list = new ArrayList<Employes>();

    public ListAdapter(List<Employes> list1) {
        this.list = list1;
        Log.d("现在列表", "长度：" + String.valueOf(list1.size()));
        Log.d("现在列表", "长度：" + String.valueOf(list1.size()));

    }


    //创建子项的内容的对应类，包括视图和每一个组件
    static class ViewHolder extends RecyclerView.ViewHolder {
        View item;

        TextView id;
        TextView nom;
        TextView prenom;
        TextView sexe;
        TextView titre;
        TextView salaire;

        public ViewHolder(@NonNull View view) {
            super(view);

            item = view;

            id =   view.findViewById(R.id.textView6);
            nom =  view.findViewById(R.id.textView8);
            prenom =  view.findViewById(R.id.textView4);
            sexe =  view.findViewById(R.id.textView5);
            titre =  view.findViewById(R.id.textView7);
            salaire =  view.findViewById(R.id.textView12);


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
//                Employes Employes = list.get(position);
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
//                Employes Employes = list.get(position);
//                Toast.makeText(v.getContext(), "你点击了图片", Toast.LENGTH_SHORT).show();
//            }
//        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Employes employes = list.get(position);

        holder.id.setText(String.valueOf(employes.getId()));
        holder.nom.setText(employes.getNom());
        holder.prenom.setText(employes.getPrenom());
        holder.sexe.setText(employes.getSexe());
        holder.titre.setText(employes.getTitre());//一定要转换成String否则运行会报错
        holder.salaire.setText(String.valueOf(employes.getSalaire()));


    }

    @Override
    public int getItemCount() {


        Log.d("R列表", "长度：" + String.valueOf(list.size()));
        return list.size();
    }

}
