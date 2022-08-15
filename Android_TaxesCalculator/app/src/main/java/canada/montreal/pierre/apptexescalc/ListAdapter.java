package canada.montreal.pierre.apptexescalc;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    final String  TAG = this.getClass().getSimpleName();
    final int lengthLimit = 10;
    List<Bill> list = new ArrayList<Bill>();
    private Context context;
    private ProjectDAO pd;
    private OnItemClickListener onItemClickListener;
    Typeface tf;


    public ListAdapter(List<Bill> list,ProjectDAO pd) {

        this.list = list;
        this.pd = pd;
    }


    //创建子项的内容的对应类，包括视图和每一个组件
    static class ViewHolder extends RecyclerView.ViewHolder {
        View item;
        TextView article;
        TextView magasin;
        TextView price;

        public ViewHolder(@NonNull View view) {
            super(view);
            item = view;
            //item.setBackgroundColor(Color.parseColor("#ffffff"));
            article = view.findViewById(R.id.itemArticle);
            magasin = view.findViewById(R.id.itemMagasin);
            price = view.findViewById(R.id.itemPrice);

        }
    }

    @NonNull
    @Override
    //创建viewHolder，就是view
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (context == null) {
            context = parent.getContext();
        }
        //动态创建子项的视图
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        //把子项的视图 放到viewHolder

        final ViewHolder viewHolder = new ViewHolder(view);

        viewHolder.article.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int position = viewHolder.getAdapterPosition();
                Log.d("适配器position", String.valueOf(position));
                //Log.d("适配器position","wwwwwwwwwww" );
                Bill bill = list.get(position);
                bill.setPosition(position);
                Log.d("适配器 bill magasion ",bill.getMagasin() );
                showDialog(bill);
            }
        });
        viewHolder.magasin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int position = viewHolder.getAdapterPosition();
                Log.d("适配器position", String.valueOf(position));
                //Log.d("适配器position","wwwwwwwwwww" );
                Bill bill = list.get(position);
                bill.setPosition(position);
                showDialog(bill);
            }
        });
        viewHolder.price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context.getApplicationContext(), "您点击了当前的:", Toast.LENGTH_SHORT).show();

                int position = viewHolder.getAdapterPosition();
                Log.d("适配器position", String.valueOf(position));
                Bill bill = list.get(position);
                bill.setPosition(position);
                showDialog(bill);
            }
        });

//        viewHolder.item.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //Log.d("适配器position","111111111111111" );
//                int position = viewHolder.getAdapterPosition();
//                //Log.d("适配器position","wwwwwwwwwww" );
//                Bill bill = list.get(position);
//                Log.d("适配器position", String.valueOf(position));
//                bill.setPosition(position);
//                showDialog(bill);
//
//            }
//        });


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        tf = Functions.getAvenirHeavyFonts(context);
        Bill bill = list.get(position);
        holder.article.setText(Functions.getBreafString(bill.getArticleName(),this.lengthLimit));
        holder.article.setTypeface(tf);

        holder.magasin.setText(Functions.getBreafString(bill.getMagasin(),this.lengthLimit));
        holder.magasin.setTypeface(tf);

        holder.price.setText(Functions.getBreafString(bill.getPrice(),this.lengthLimit));
        holder.price.setTypeface(tf);

        final int pos = holder.getLayoutPosition();
        if(position%2==0){
            //Log.d(bill.getArticleName()+" 位置是"+position,"绑定颜色***********************颜色");
            //holder.itemView.setBackgroundColor(Color.parseColor("#DBDBDB"));
        }

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
        //Log.d("R列表", "长度：" + String.valueOf(list.size()));
        return list.size();
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int pos);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    protected void showDialog(Bill bill) {

        final Dialog dialog = new Dialog(context);
        Typeface tf = Functions.getAvenirHeavyFonts(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.fragment_dialog_list);


        final EditText articleNameET =  dialog.findViewById(R.id.listeditTextArticleName);
        final EditText magasinET =  dialog.findViewById(R.id.listeditTextMagasin);
        articleNameET.setTypeface(tf);
        magasinET.setTypeface(tf);
        articleNameET.setText(bill.getArticleName());
        magasinET.setText(bill.getMagasin());

        final TextView priceTV =  dialog.findViewById(R.id.listtextView6);
        final TextView priceResTV =  dialog.findViewById(R.id.listtextView7);
        priceTV.setTypeface(tf);
        priceResTV.setTypeface(tf);
        priceResTV.setText(bill.getPrice());

        final TextView discountTV =  dialog.findViewById(R.id.listtextView8);
        final TextView discountResTV =  dialog.findViewById(R.id.listtextView22);
        discountTV.setTypeface(tf);
        discountResTV.setTypeface(tf);
        discountTV.setText(discountTV.getText().toString()+" "+bill.getDiscount()+"%");
        discountResTV.setText(bill.getDiscountRes());

        final TextView tpsTV =  dialog.findViewById(R.id.listtextView25);
        final TextView tpsResTV =  dialog.findViewById(R.id.listtextView29);
        tpsTV.setTypeface(tf);
        tpsResTV.setTypeface(tf);
        tpsResTV.setText(bill.getTpsRes());

        final TextView tvqTV =  dialog.findViewById(R.id.listtextView26);
        final TextView tvqResTV =  dialog.findViewById(R.id.listtextView30);
        tvqTV.setTypeface(tf);
        tvqResTV.setTypeface(tf);
        tvqResTV.setText(bill.getTvqRes());

        final TextView totalTaxTV =  dialog.findViewById(R.id.listtextView27);
        final TextView totalTaxResTV =  dialog.findViewById(R.id.listtextView31);
        totalTaxTV.setTypeface(tf);
        totalTaxResTV.setTypeface(tf);
        totalTaxResTV.setText(bill.getTotalTaxRes());

        final TextView tipsTV =  dialog.findViewById(R.id.listtextView28);
        final TextView tipsResTV =  dialog.findViewById(R.id.listtextView32);
        tipsTV.setTypeface(tf);
        tipsResTV.setTypeface(tf);
        tipsTV.setText(tipsTV.getText().toString()+" "+bill.getTips()+"%");
        tipsResTV.setText(bill.getTipsRes());


        //detailTV.setText(bill.getDetails());
        final TextView totalTv =  dialog.findViewById(R.id.listtextView21);
        totalTv.setTypeface(tf);
        totalTv.setText("Total            "+String.valueOf(bill.getTotal()));



        Button ok = dialog.findViewById(R.id.listbutton);
        ok.setTypeface(tf);

        Button  delete= dialog.findViewById(R.id.listbutton2);
        delete.setTypeface(tf);



        ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String articleName = articleNameET.getText().toString();
                String magasin = magasinET.getText().toString();


                if(articleName.length()<=0){
                    articleNameET.setHint("Entrez le nom de l'article SVP");
                    return;
                }
                if(magasin.length()<=0){
                    magasinET.setHint("Entrez le nom du magasin SVP");
                    return;
                }


                bill.setArticleName(articleName);
                bill.setMagasin(magasin);

                //保存

                new UpdateAsyncTask(pd).execute(bill);
                notifyDataSetChanged();
                dialog.dismiss();

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub


                new DeleteAsyncTask(pd).execute(bill);

                list.remove(bill.getPosition());

                notifyItemRemoved(bill.getPosition());
                
                //刷新删除后每个item的position
                notifyItemRangeChanged(bill.getPosition(), list.size() - bill.getPosition(), "removeItem");
                notifyDataSetChanged();
                Toast.makeText(context,"Vous avez supprimé l'article: "+bill.getArticleName(),Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        });

        dialog.show();
    }


    class  DeleteAsyncTask extends AsyncTask< Bill,Void,Void > {
        private  ProjectDAO pd;

        public DeleteAsyncTask(ProjectDAO pd) {

            this.pd = pd;
        }

        @Override
        protected Void doInBackground(Bill...GymExercices){

            Bill bill = GymExercices[0];
            pd.deleteBillById(bill.getId());

            return null;
        }

        @Override
        protected void onPostExecute(Void avoid) {
            super.onPostExecute(avoid);

            //rva.notifyDataSetChanged();
            Log.i(TAG, "delete finished");

        }

    }

    class  UpdateAsyncTask extends AsyncTask< Bill,Void,Void > {
        private  ProjectDAO pd;

        public UpdateAsyncTask(ProjectDAO pd) {

            this.pd = pd;
        }

        @Override
        protected Void doInBackground(Bill...bills){
            Bill bill = bills[0];

           pd.updateBills(bill);


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);





        }

    }


}
