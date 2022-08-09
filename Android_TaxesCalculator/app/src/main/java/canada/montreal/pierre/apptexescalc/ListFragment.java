package canada.montreal.pierre.apptexescalc;

import android.app.Dialog;
import android.app.ListActivity;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;


public class ListFragment extends Fragment {

    final String  TAG = this.getClass().getSimpleName();
    private TextView titleTv;
    private TextView subjectArticle;
    private TextView subjectMagasin;
    private TextView subjectPrix;
    private RecyclerView rv;
    private ListAdapter rva;


    private  Typeface tf;
    private ViewMode vm;
    TaxCalcDataBase tdb;
    ProjectDAO pd;
    List<Bill> list;

    public ListFragment() {
        // Required empty public constructor
    }


//    // TODO: Rename and change types and number of parameters
//    public static ListFragment newInstance(String param1, String param2) {
//        ListFragment fragment = new ListFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list,null);
        vm = new ViewMode();
        rv = view.findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        tf = Functions.getAvenirHeavyFonts(getActivity());

        titleTv = view.findViewById(R.id.textView19);
        titleTv.setTypeface(tf);

        subjectArticle = view.findViewById(R.id.textView33);
        subjectArticle.setTypeface(tf);

        subjectMagasin = view.findViewById(R.id.textView34);
        subjectMagasin.setTypeface(tf);

        subjectPrix = view.findViewById(R.id.textView35);
        subjectPrix.setTypeface(tf);

        tdb = TaxCalcDataBase.getInstance(getActivity());
        pd = tdb.getProductDao();



        new listAsyncTask(pd).execute();



        return view;
    }

    class  listAsyncTask extends AsyncTask<Void,Void,Void> {
        private  ProjectDAO pd;

        public listAsyncTask(ProjectDAO pd) {

            this.pd = pd;
        }

        @Override
        protected Void doInBackground(Void...voids){

            list = pd.getBills();
            vm.setList(list);
            Log.d("listActivity list size",String.valueOf(list.size()));
            return null;
        }

        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            Log.i("onPostExecute", "running");


            rv.setItemAnimator(new DefaultItemAnimator());
            rva = new ListAdapter(vm.getList(),pd);
            rv.setAdapter(rva);

//            rva.setOnItemClickListener(new ListAdapter.OnItemClickListener() {
//                @Override
//                public void onItemClick(View view, int position) {
//
//                    Log.d("适配器position","wwwwwwwwwww" );
//                    Bill bill = vm.getList().get(position);
//                    showDialog(bill);
//
//                }
//            });

//            ItemTouchHelper ith = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.START|ItemTouchHelper.END) {
//                @Override
//                public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
//                    return false;
//                }
//
//                @Override
//                public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
//
//                    //geList.remove(viewHolder.getAdapterPosition());
//                    //geList.notifyItemRemoved(viewHolder.getAdapterPosition());
//                    GymExercice ge = globalGeList.get(viewHolder.getAdapterPosition());
//                    //删除
//                   new ListActivity.DeleteAsyncTask(pd).execute(ge);
//
//                }
//            });
//
//            ith.attachToRecyclerView(rv);
            //ItemTouchHelper swipeItemHelper= new ItemTouchHelper(new SwipeCallback<GymExercice>(rva,geList));
            //swipeItemHelper.attachToRecyclerView(rv);

            Log.i(TAG, "finished");

        }

    }

    protected void showDialog(Bill bill) {

        final Dialog dialog = new Dialog(getActivity());
        Typeface tf = Functions.getAvenirHeavyFonts(getActivity());
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


        //删除
        delete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String articleName = articleNameET.getText().toString();
                String magasin = magasinET.getText().toString();


                if(articleName.length()<=0){
                    articleNameET.setHint("Entrez le nom de article svp");
                    return;
                }
                if(magasin.length()<=0){
                    magasinET.setHint("Entrez le nom de magasin svp");
                    return;
                }


                bill.setArticleName(articleName);
                bill.setMagasin(magasin);



                //保存
                //new UpdateAsyncTask(pd).execute(ge);
                //notifyDataSetChanged();
                dialog.dismiss();

            }
        });

        ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                dialog.dismiss();
            }
        });

        dialog.show();
    }


}