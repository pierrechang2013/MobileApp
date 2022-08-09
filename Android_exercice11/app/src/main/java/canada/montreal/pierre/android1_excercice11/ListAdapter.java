package canada.montreal.pierre.android1_excercice11;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
//import androidx.core.app.NotificationCompat;
//import androidx.core.app.NotificationManagerCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    List<Form> list = new ArrayList<Form>();

    private ViewModel vm;

    private OnItemClickListener onItemClickListener;

    public ListAdapter(List<Form> list) {

        this.list = list;
    }


    //创建子项的内容的对应类，包括视图和每一个组件
    static class ViewHolder extends RecyclerView.ViewHolder {
        View item;
        @BindView(R.id.textView6) TextView name;
        @BindView(R.id.textView7) TextView date;
        @BindView(R.id.textView8) TextView amount;

        public ViewHolder(@NonNull View view) {
            super(view);
            item = view;
            ButterKnife.bind(this, view);


        }
    }

    @NonNull
    @Override
    //创建viewHolder，就是view
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        //动态创建子项的视图
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        //把子项的视图 放到viewHolder
        final ViewHolder viewHolder = new ViewHolder(view);


        viewHolder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                Form form = list.get(position);
                Log.d("适配器position", String.valueOf(position));
                if (position > 0) {

                    //Log.d("适配器getSpeed", form.getSpeed());
                    //Log.d("适配器getZone", form.getZone());
                }


                Log.d("适配器", "目前是第" + String.valueOf(position) + "个，被删除，当前还有" + list.size() + "个数据");


            }
        });


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Form form = list.get(position);
        holder.name.setText(form.getName());
        holder.date.setText(form.getDate());
        holder.amount.setText(form.getAmount());

        final int pos = holder.getLayoutPosition();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null)
                    onItemClickListener.onItemClick(view, pos);
            }
        });



    }

    @Override
    public int getItemCount() {
        Log.d("R列表", "长度：" + String.valueOf(list.size()));
        return list.size();
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int pos);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

}
