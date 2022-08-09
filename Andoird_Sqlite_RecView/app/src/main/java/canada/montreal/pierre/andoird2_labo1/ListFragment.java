package canada.montreal.pierre.andoird2_labo1;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class ListFragment extends Fragment {

    RecyclerView rv;
    RecyclerView.Adapter rva;

    ProjectDAO pd;
    List<Produit> list;

    FloatingActionButton fab;

    public ListFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list = new ArrayList<Produit>();
        //准备数据//通过Room数据库访问
        DataBaseHelper dbh = DataBaseHelper.getInstance(getContext());
        pd = dbh.getProductDao();
        listerProduits();//通过AsyncTask访问数据库
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, null);
        //setHasOptionsMenu(true);

        rv = view.findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        fab = view.findViewById(R.id.floatingActionButton);

        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                FragmentTransaction ft = getFragmentManager().beginTransaction();
//                DialogFrag df = new DialogFrag();
//                df.show(ft,"sdfsf");

                showDialog();

            }
        });

    }
    //静态内部类，防止内存泄漏,但是为了list能在这里被访问，我还是不写静态了
     class  listAsyncTask extends AsyncTask<Void,Void,Void> {
           private  ProjectDAO pd;

        public listAsyncTask(ProjectDAO pd) {

            this.pd = pd;
        }

        @Override
        protected Void doInBackground(Void...voids){

            list = pd.obtenirListeProduits();
            Log.d("$$$$$$$$$$ list size",String.valueOf(list.size()));
           return null;
        }

        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            Log.i("onPostExecute", "running");
            //必须在这实现，否则异步，取不到数据
            rva = new ListAdapter(list);
            rv.setAdapter(rva);
            //rva.notifyDataSetChanged();
            Log.i("populateList", "finished");


        }

    }

     void listerProduits(){

        new listAsyncTask(pd).execute();

    }
                                          //参数   //过程  //返回值
    class  SaveAsyncTask extends AsyncTask<Produit,Void,List<Produit>> {
        private  ProjectDAO pd;

        public SaveAsyncTask(ProjectDAO pd) {

            this.pd = pd;
        }

        @Override
        protected List<Produit> doInBackground(Produit...produits){
            Produit produit = produits[0];
            long id = pd.dbhEnregistrerProduit(produit);
            produit.setId(id);
            Log.d("$$$$$$$$$$ list size",String.valueOf(list.size()));
            List<Produit> list = new ArrayList<Produit>();
            list.add(produits[0]);
            return list;
        }

        @Override
        protected void onPostExecute(List<Produit> produits) {
            super.onPostExecute(produits);


            //必须在这实现，否则异步，取不到数据
            list.add(produits.get(0));
            //Log.d("^^^^^^^^^^^^^^^^",produits[0].getName());
            rva = new ListAdapter(list);
            Log.d("^^^^^^^^^^^^^^^^",String.valueOf(list.size()));
            rva.notifyDataSetChanged();
            rv.setAdapter(rva);

            Toast.makeText(getContext(),"Produit "+produits.get(0).getId()+" a été bien enregistré",Toast.LENGTH_LONG).show();


        }

    }

    protected void showDialog( ) {

        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.fragment_dialog);

        final EditText name =  dialog.findViewById(R.id.editTextTextPersonName);
        final EditText cate =  dialog.findViewById(R.id.editTextTextPersonName2);
        final EditText price =  dialog.findViewById(R.id.editTextTextPersonName3);
        final EditText quantity =  dialog.findViewById(R.id.editTextTextPersonName4);

        final TextView nameTv =  dialog.findViewById(R.id.textView);
        final TextView cateTv =  dialog.findViewById(R.id.textView2);
        final TextView priceTv =  dialog.findViewById(R.id.textView9);
        final TextView quantityTv =  dialog.findViewById(R.id.textView10);


        Button cancel = (Button)dialog.findViewById(R.id.button);
        Button save = (Button)dialog.findViewById(R.id.button2);

        save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String nameStr = name.getText().toString();
                String cateStr = cate.getText().toString();
                String priceStr = price.getText().toString();
                String quantityStr = quantity.getText().toString();
                if(nameStr.length()<=0){
                    nameTv.setTextColor(Color.parseColor("#FFF0083E"));
                    return;
                }
                if(cateStr.length()<=0){
                    cateTv.setTextColor(Color.parseColor("#FFF0083E"));
                    return;
                }
                if(priceStr.length()<=0){
                    priceTv.setTextColor(Color.parseColor("#FFF0083E"));
                    return;
                }
                if(quantityStr.length()<=0){
                    quantityTv.setTextColor(Color.parseColor("#FFF0083E"));
                    return;
                }

                Produit produit = new Produit(nameStr,cateStr,Float.parseFloat(priceStr),Integer.parseInt(quantityStr));
                DataBaseHelper dbh = DataBaseHelper.getInstance(getContext());

                ProjectDAO pd = dbh.getProductDao();

                new SaveAsyncTask(pd).execute(produit);

                //Toast.makeText(getActivity(),"00000000000000",Toast.LENGTH_LONG);
                dialog.dismiss();

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                dialog.dismiss();
            }
        });

        dialog.show();
    }

}