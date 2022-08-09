package canada.montreal.pierre.andoird2_labo1;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


public class TotalFragment extends Fragment {

    List<Produit> list;
    TextView tv;
    public TotalFragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_total, null);
        tv = view.findViewById(R.id.textView3);
        DataBaseHelper dbh = DataBaseHelper.getInstance(getContext());
        ProjectDAO pd = dbh.getProductDao();
        new TotalAsyncTask(pd).execute();

        return view;
    }

    //静态内部类，防止内存泄漏,但是为了list能在这里被访问，我还是不写静态了
    class  TotalAsyncTask extends AsyncTask<Void,Void,Void> {
        private  ProjectDAO pd;

        public TotalAsyncTask(ProjectDAO pd) {

            this.pd = pd;
        }

        @Override
        protected Void doInBackground(Void...voids){

            list = pd.obtenirListeProduits();

            return null;
        }

        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            Log.d("onPostExecute", "running");
            //必须在这实现，否则异步，取不到数据
            float total  = 0;
            for(int i = 0;i<list.size();i++){
                total = total + list.get(i).getPrice()*list.get(i).getQuantity();

            }
            tv.setText(String.valueOf(total)+"$");
            //rva.notifyDataSetChanged();
            Log.d("populateList", "finished");


        }

    }
}