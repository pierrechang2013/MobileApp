package canada.montreal.pierre.andoird2_exam1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

//    android.view.MenuItem showList;
//    android.view.MenuItem showCate;
//    android.view.MenuItem showTotal;
final String  TAG = this.getClass().getSimpleName();

    RecyclerView rv;
    RecyclerView.Adapter rva;

    ProjectDAO pd;
    List<Employes> list;

    Button deleteBtn;
    Button salaireBtn;
    FloatingActionButton fab;

    String idStringBySalaire;

    //List<Employes> globalList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = new ArrayList<Employes>();
        rv = findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        //准备数据//通过Room数据库访问
        DataBaseHelper dbh = DataBaseHelper.getInstance(MainActivity.this);
        pd = dbh.getProductDao();
        listerProduits();//通过AsyncTask访问数据库

        deleteBtn = findViewById(R.id.button3);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo
                showDelteDialog();
            }
        });

        salaireBtn = findViewById(R.id.button6);

        salaireBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSalaireDialog();
            }
        });

        fab = findViewById(R.id.floatingActionButton);
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

            list = pd.obtenirListeEmployes();
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

    //-------------------------salaire--------------------


    class  SalaireAsyncTask extends AsyncTask<Employes,Void,Void> {
        private  ProjectDAO pd;

        public SalaireAsyncTask(ProjectDAO pd) {

            this.pd = pd;
        }

        @Override
        protected Void doInBackground(Employes...em){

            List<Employes> listBySalaire = pd.getEmployBySalaire(em[0].getSalaire());
            for(int i = 0;i<listBySalaire.size(); i++){

                    idStringBySalaire = idStringBySalaire + ","+listBySalaire.get(i).getId();
            }
            Log.d("$$$$$$$$$$ list size",String.valueOf(list.size()));
            return null;
        }

        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            Log.i("onPostExecute", "running");
            //必须在这实现，否则异步，取不到数据
            //rva = new ListAdapter(list);
            //rv.setAdapter(rva);
            //rva.notifyDataSetChanged();
            Intent intent = new Intent(MainActivity.this, SalaireActivity.class);
            intent.putExtra("idStringBySalaire",idStringBySalaire);
            startActivity(intent);
            //Log.i("populateList", "finished");


        }

    }

    //参数   //过程  //返回值
    class  SaveAsyncTask extends AsyncTask<Employes,Void,List<Employes>> {
        private  ProjectDAO pd;

        public SaveAsyncTask(ProjectDAO pd) {

            this.pd = pd;
        }

        @Override
        protected List<Employes> doInBackground(Employes... employes){
            Employes employe = employes[0];
            long id = pd.dbhEnregistrerProduit(employe);
            employe.setId(id);
            Log.d("$$$$$$$$$$ list size",String.valueOf(list.size()));
            List<Employes> list = new ArrayList<Employes>();
            list.add(employe);
            return list;
        }

        @Override
        protected void onPostExecute(List<Employes> employes) {
            super.onPostExecute(employes);


            //必须在这实现，否则异步，取不到数据
            list.add(employes.get(0));
            //Log.d("^^^^^^^^^^^^^^^^",employes[0].getName());
            rva = new ListAdapter(list);
            Log.d("^^^^^^^^^^^^^^^^",String.valueOf(list.size()));
            rva.notifyDataSetChanged();
            rv.setAdapter(rva);

            Toast.makeText(MainActivity.this,"Employes "+ employes.get(0).getId()+" a été bien enregistré",Toast.LENGTH_LONG).show();


        }

    }
//    //参数   //过程  //返回值
//    class  SaveAsyncTask extends AsyncTask<Employes,Void,List<Employes>> {
//        private  ProjectDAO pd;
//
//        public SaveAsyncTask(ProjectDAO pd) {
//
//            this.pd = pd;
//        }
//
//        @Override
//        protected List<Employes> doInBackground(Employes... employes){
//            Employes employe = employes[0];
//            long id = pd.dbhEnregistrerProduit(employe);
//            employe.setId(id);
//            Log.d("$$$$$$$$$$ list size",String.valueOf(list.size()));
//            List<Employes> list = new ArrayList<Employes>();
//            list.add(employe);
//            return list;
//        }
//
//        @Override
//        protected void onPostExecute(List<Employes> employes) {
//            super.onPostExecute(employes);
//
//
//            //必须在这实现，否则异步，取不到数据
//            list.add(employes.get(0));
//            //Log.d("^^^^^^^^^^^^^^^^",employes[0].getName());
//            rva = new ListAdapter(list);
//            Log.d("^^^^^^^^^^^^^^^^",String.valueOf(list.size()));
//            rva.notifyDataSetChanged();
//            rv.setAdapter(rva);
//
//            Toast.makeText(MainActivity.this,"Employes "+ employes.get(0).getId()+" a été bien enregistré",Toast.LENGTH_LONG).show();
//
//
//        }
//
//    }

    protected void showSalaireDialog( ) {

        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_salaire);

        final EditText salaire =  dialog.findViewById(R.id.editTextTextPersonName7);


        final TextView salaireTv =  dialog.findViewById(R.id.textView14);



        Button yes = dialog.findViewById(R.id.button7);
        Button no = dialog.findViewById(R.id.button8);

        yes.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String salaireString = salaire.getText().toString();

                if(salaire.length()<=0){
                    salaireTv.setTextColor(Color.parseColor("#FFF0083E"));
                    return;
                }

                int salaireInt = Integer.parseInt(salaireString);
                //Employes employes = new Employes(nomStr,prenomStr,sexeStr,titreStr,Integer.parseInt(salaireStr));
//                DataBaseHelper dbh = DataBaseHelper.getInstance(MainActivity.this);
//
//                ProjectDAO pd = dbh.getProductDao();
//
//                Employes em = new Employes();
//                em.setSalaire(salaireInt);

                //new SalaireAsyncTask(pd).equals(em);
                //Toast.makeText(getActivity(),"00000000000000",Toast.LENGTH_LONG);
                Intent intent = new Intent(MainActivity.this, SalaireActivity.class);
                intent.putExtra("salaireInt",salaireInt);
                startActivity(intent);
                dialog.dismiss();

            }
        });

        no.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                dialog.dismiss();
            }
        });

        dialog.show();
    }

    protected void showDelteDialog( ) {

        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_delete);

        final EditText id =  dialog.findViewById(R.id.editTextTextPersonName6);


        final TextView idTv =  dialog.findViewById(R.id.textView13);



        Button delete = dialog.findViewById(R.id.button4);
        Button cancel = dialog.findViewById(R.id.button5);

        delete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String idString = id.getText().toString();

                if(idString.length()<=0){
                    idTv.setTextColor(Color.parseColor("#FFF0083E"));
                    return;
                }

                long id = Long.parseLong(idString);
                //Employes employes = new Employes(nomStr,prenomStr,sexeStr,titreStr,Integer.parseInt(salaireStr));
                DataBaseHelper dbh = DataBaseHelper.getInstance(MainActivity.this);

                ProjectDAO pd = dbh.getProductDao();

                Employes em = new Employes();
                em.setId(id);
                new DeleteAsyncTask(pd).execute(em);

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

    //--------------------------Delete--------------------------------------------
    class  DeleteAsyncTask extends AsyncTask< Employes,Void,List<Employes> > {
        private  ProjectDAO pd;

        public DeleteAsyncTask(ProjectDAO pd) {

            this.pd = pd;
        }

        @Override
        protected List<Employes> doInBackground(Employes...ems){

            Employes em = ems[0];
            em = pd.getEmployById(em.getId());//获取当前
            //Toast.makeText(MainActivity.this,"当前获得对象 id:"+em.getId()+" ",Toast.LENGTH_LONG).show();

            pd.deleteEmployById(em.getId());//从表里删

            list = pd.obtenirListeEmployes();
            //list.remove(em);//从队列里删


            return list;
        }

        @Override
        protected void onPostExecute(List<Employes> employes) {
            super.onPostExecute(employes);

            List<Employes> geList = new ArrayList<Employes>();

            rva = new ListAdapter(list);
            rv.setAdapter(rva);
            rva.notifyDataSetChanged();
            Log.i(TAG, "finished");

            Toast.makeText(MainActivity.this,"geList "+employes.get(0).getId()+" est supprimee",Toast.LENGTH_LONG).show();



        }

    }

    void deleteGe(Employes em){

        new  DeleteAsyncTask(pd).execute(em);
    }

    protected void showDialog( ) {

        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.fragment_dialog);

        final EditText nom =  dialog.findViewById(R.id.editTextTextPersonName);
        final EditText prenom =  dialog.findViewById(R.id.editTextTextPersonName2);
        final EditText sexe =  dialog.findViewById(R.id.editTextTextPersonName3);
        final EditText titre =  dialog.findViewById(R.id.editTextTextPersonName4);
        final EditText salaire =  dialog.findViewById(R.id.editTextTextPersonName5);

        final TextView nomTv =  dialog.findViewById(R.id.textView);
        final TextView prenomTv =  dialog.findViewById(R.id.textView2);
        final TextView sexeTv =  dialog.findViewById(R.id.textView9);
        final TextView titreTv =  dialog.findViewById(R.id.textView10);
        final TextView salaireTv =  dialog.findViewById(R.id.textView11);


        Button cancel = (Button)dialog.findViewById(R.id.button);
        Button save = (Button)dialog.findViewById(R.id.button2);

        save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String nomStr = nom.getText().toString();
                String prenomStr = prenom.getText().toString();
                String sexeStr = sexe.getText().toString();
                String titreStr = titre.getText().toString();
                String salaireStr = salaire.getText().toString();
                if(nomStr.length()<=0){
                    nomTv.setTextColor(Color.parseColor("#FFF0083E"));
                    return;
                }
                if(prenomStr.length()<=0){
                    prenomTv.setTextColor(Color.parseColor("#FFF0083E"));
                    return;
                }
                if(sexeStr.length()<=0){
                    sexeTv.setTextColor(Color.parseColor("#FFF0083E"));
                    return;
                }
                if(titreStr.length()<=0){
                    titreTv.setTextColor(Color.parseColor("#FFF0083E"));
                    return;
                }
                if(salaireStr.length()<=0){
                    salaireTv.setTextColor(Color.parseColor("#FFF0083E"));
                    return;
                }

                Employes employes = new Employes(nomStr,prenomStr,sexeStr,titreStr,Integer.parseInt(salaireStr));
                DataBaseHelper dbh = DataBaseHelper.getInstance(MainActivity.this);

                ProjectDAO pd = dbh.getProductDao();

                new SaveAsyncTask(pd).execute(employes);

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
//
//    @Override
//    public boolean onPrepareOptionsMenu(Menu menu) {
//
//
//        showList.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//
//                ListFragment lf = new ListFragment();
//                FragmentManager fm;
//                FragmentTransaction transaction;
//                fm = getSupportFragmentManager();
//                transaction = fm.beginTransaction();
//                transaction.setReorderingAllowed(true);
//                transaction.replace(R.id.fragmentContainer, lf,
//                        "cf");
//
//                //transaction.show(lf);
//                transaction.commitAllowingStateLoss();
//                return false;
//            }
//        });
//
//        showCate.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//
//                FragmentManager fm;
//                FragmentTransaction transaction;
//                fm = getSupportFragmentManager();
//                transaction = fm.beginTransaction();
//                transaction.setReorderingAllowed(true);
//                //transaction.addToBackStack(null);
//                CateFragment cf = new CateFragment();
//                transaction.replace(R.id.fragmentContainer, CateFragment.class, null)
//                        .setReorderingAllowed(true);
//
//                transaction.addToBackStack("listFragment"); // name can be null
//                transaction.commit();
//
//
//
//
//                return false;
//            }
//        });
//
//        showTotal.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//
//                FragmentManager fm;
//                FragmentTransaction transaction;
//                fm = getSupportFragmentManager();
//                transaction = fm.beginTransaction();
//                transaction.setReorderingAllowed(true);
//                //transaction.addToBackStack(null);
//                CateFragment cf = new CateFragment();
//                transaction.replace(R.id.fragmentContainer, TotalFragment.class, null)
//                        .setReorderingAllowed(true);
//
//                transaction.addToBackStack("totalFragment"); // name can be null
//                transaction.commit();
//
//
//
//
//                return false;
//            }
//        });

//
//
//

//        if (!_ticket) {
//            add =  menu.findItem(R.id.add);
//            add.setEnabled(false);
//
//        }else{
//            menu.findItem(R.id.delete).setEnabled(false);
//        }
//        return true;
//    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu, menu);
//        showList = menu.findItem(R.id.list);
//        showCate = menu.findItem(R.id.cate);
//        showTotal = menu.findItem(R.id.total);
//
//
//
//        return super.onCreateOptionsMenu(menu);
//    }
}