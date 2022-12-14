package canada.montreal.pierre.andoird2_labo1;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;


public class CateFragment extends Fragment {


    List<Produit> list ;
    List<String> categorys;

    Spinner spinner;
    ArrayAdapter<String>  adapter;

    RecyclerView rv;

    RecyclerView.Adapter rva;

    public CateFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        list = new ArrayList<Produit>();
        View view = inflater.inflate(R.layout.fragment_cate,null);
        spinner = view.findViewById(R.id.spinner);
        rv = view.findViewById(R.id.rv1);

        rv.setLayoutManager(new LinearLayoutManager(getContext()));


         DataBaseHelper dbh = DataBaseHelper.getInstance(getContext());
         final ProjectDAO pd = dbh.getProductDao();
         new CateAsyncTask(pd).execute();
         dbh.close();
         //spinner.setAdapter(adapter);

         spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        String info = adapterView.getItemAtPosition(i).toString();//
                        DataBaseHelper dbh = DataBaseHelper.getInstance(getContext());

                        ProjectDAO pd = dbh.getProductDao();
                           new ListAsyncTask(pd).execute(info);


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                    }

                });

        return view;
    }

    List<Produit> listeProduitSelonCategorie(String cate){

        return list;

    }



    //????????????????????????????????????,????????????list????????????????????????????????????????????????
    class  ListAsyncTask extends AsyncTask<String,Void,Void> {
        private  ProjectDAO pd;

        public ListAsyncTask(ProjectDAO pd) {

            this.pd = pd;
        }

        @Override
        protected Void doInBackground(String...str){

            list = pd.getProductsByCate(str[0]);

            return null;
        }

        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            Log.i("onPostExecute", "running");
            //???????????????????????????????????????????????????
            rva = new ListAdapter(list);
            rv.setAdapter(rva);
            //rva.notifyDataSetChanged();
            Log.i("populateList", "finished");


        }

    }
    //??????????????????category
    class  CateAsyncTask extends AsyncTask<Void,Void,Void> {
        private  ProjectDAO pd;

        public CateAsyncTask(ProjectDAO pd) {

            this.pd = pd;
        }

        @Override
        protected Void doInBackground(Void...voids){

            categorys = pd.getAllCategory();



            return null;
        }

        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            //??????????????????

//            adapter = ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, str);
             adapter = new ArrayAdapter<String>
                    (getContext(), android.R.layout.simple_spinner_item,
                            categorys);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);


        }

    }
}