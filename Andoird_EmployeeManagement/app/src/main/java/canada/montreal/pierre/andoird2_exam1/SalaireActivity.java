package canada.montreal.pierre.andoird2_exam1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;

public class SalaireActivity extends AppCompatActivity {

    final String  TAG = this.getClass().getSimpleName();

    RecyclerView rv;
    RecyclerView.Adapter rva;

    ProjectDAO pd;
    List<Employes> list;

    Button returnBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salaire);
        int salaireInt = getIntent().getIntExtra("salaireInt",0);

        Log.d(TAG,"@@@@@@@@@@@@@@@@"+salaireInt);
        list = new ArrayList<Employes>();
        rv = findViewById(R.id.rv1);
        rv.setLayoutManager(new LinearLayoutManager(SalaireActivity.this));
        //准备数据//通过Room数据库访问
        DataBaseHelper dbh = DataBaseHelper.getInstance(SalaireActivity.this);
        pd = dbh.getProductDao();
        Employes employes = new Employes();
        employes.setSalaire(salaireInt);
        new listAsyncTask(pd).execute(employes);

        returnBtn = findViewById(R.id.button9);

        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SalaireActivity.this, MainActivity.class);

                startActivity(intent);
            }
        });
       //通过AsyncTask访问数据库
    }



    class  listAsyncTask extends AsyncTask<Employes,Void,Void> {
        private  ProjectDAO pd;

        public listAsyncTask(ProjectDAO pd) {

            this.pd = pd;
        }

        @Override
        protected Void doInBackground(Employes...Ems){

            list = pd.getEmployBySalaire(Ems[0].getSalaire());
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
}