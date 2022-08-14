package canada.montreal.pierre.apptexescalc;

import android.app.Dialog;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DefaultItemAnimator;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CalcFragment extends Fragment {

    private final String TAG = this.getTag();

    private final double tps = 0.05;
    private final double tvq = 0.0975;
    private final double discountLimit=99;
    private final double tipsLimit=300;

    private final double totalTaxPercent = 1 + tps + tvq;


    private TextView priceTV;
    private TextView titleTV;
    private EditText priceET;
    private TextView discountTV;
    private EditText discountET;
    private TextView disResTV;
    private TextView tpsTV;
    private TextView tpsResTV;

    private TextView tvqTV;
    private TextView tvqResTV;
    private TextView totalTaxTV;
    private TextView totalTaxResTV;
    private TextView tipsTV;
    private EditText tipsET;
    private TextView tipsResTV;
    private TextView totalTV;
    private TextView totalResTV;
    private Button wipeBtn;
    private Button saveBtn;

    ViewMode vm;

    TaxCalcDataBase tdb;
    ProjectDAO pd;

    public CalcFragment() {

    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calc, null);
        vm = new ViewMode();
        tdb = TaxCalcDataBase.getInstance(getActivity());
        pd = tdb.getProductDao();
        // Inflate the layout for this fragment
        Typeface tf = Functions.getAvenirHeavyFonts(getActivity());
        //title
        titleTV = view.findViewById(R.id.itemPrice);
        titleTV.setTypeface(tf);
        //price
        priceTV = view.findViewById(R.id.textView9);
        priceET = view.findViewById(R.id.editTextPrice);
        priceTV.setTypeface(tf);
        priceET.setTypeface(tf);
        discountTV = view.findViewById(R.id.textView10);
        discountET = view.findViewById(R.id.editTextDiscount);
        disResTV = view.findViewById(R.id.textViewDiscount);
        discountTV.setTypeface(tf);
        discountET.setTypeface(tf);
        disResTV.setTypeface(tf);
        tpsTV = view.findViewById(R.id.textView12);
        tpsResTV = view.findViewById(R.id.textViewTPS);
        tpsTV.setTypeface(tf);
        tpsResTV.setTypeface(tf);

        tvqTV = view.findViewById(R.id.textView14);
        tvqResTV = view.findViewById(R.id.textViewTVQ);
        tvqTV.setTypeface(tf);
        tvqResTV.setTypeface(tf);

        totalTaxTV = view.findViewById(R.id.textView16);
        totalTaxResTV = view.findViewById(R.id.textViewTotalTax);
        totalTaxTV.setTypeface(tf);
        totalTaxResTV.setTypeface(tf);

        tipsTV = view.findViewById(R.id.textView18);
        tipsET = view.findViewById(R.id.editTextTips);
        tipsResTV = view.findViewById(R.id.textViewTips);
        tipsTV.setTypeface(tf);
        tipsET.setTypeface(tf);
        tipsResTV.setTypeface(tf);

        totalTV = view.findViewById(R.id.textView20);
        totalResTV = view.findViewById(R.id.textViewTotal);
        totalTV.setTypeface(tf);
        totalResTV.setTypeface(tf);

        wipeBtn = view.findViewById(R.id.button3);
        saveBtn = view.findViewById(R.id.button);
        wipeBtn.setTypeface(tf);
        saveBtn.setTypeface(tf);

        

        priceET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void afterTextChanged(Editable editable) {
                String str = editable.toString();



                if (str.indexOf(",") > -1) {
                    str = str.replaceAll(",", ".");
                    //Log.d(TAG,"**"+str);
                    priceET.setText(str);
                    priceET.setSelection(str.length());
                } else if (str.startsWith(".")) {
                    str = "0.";
                    //Log.d(TAG,"**"+str);
                    priceET.setText(str);
                    priceET.setSelection(str.length());//设置光标位置在最后，默认是在最前面
                }
//               


                vm.changePriceStr(str);


            }

        });

        vm.getPriceStr().observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {

                try {

                    double price = s.length() > 0 ? Double.parseDouble(s) : 0;

                    double discount = discountET.getText() != null && discountET.getText().toString().length() > 0 ? Double.parseDouble(discountET.getText().toString()) : 0;

                    double tips = tipsET.getText().toString().length() > 0 ? Double.parseDouble(tipsET.getText().toString()) : 0;

                    calculate(price, discount, tips);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        discountET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void afterTextChanged(Editable editable) {
                String str = editable.toString();
                if (str.indexOf(",") > -1) {
                    str = str.replaceAll(",", ".");
                    //Log.d(TAG,"**"+str);
                    discountET.setText(str);
                    discountET.setSelection(str.length());
                } else if (str.startsWith(".")) {
                    str = "0.";
                    //Log.d(TAG,"**"+str);
                    discountET.setText(str);
                    discountET.setSelection(str.length());//设置光标位置在最后，默认是在最前面
                }
                vm.changediscountStr(str);


            }

        });

        vm.getDiscountStr().observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                try {

                    double price = priceET.getText().toString().length() > 0 ? Double.parseDouble(priceET.getText().toString()) : 0;

                    double discount = s.length() > 0 ? Double.parseDouble(s) : 0;
                    if(discount>discountLimit){
                        discount = discountLimit;
                        discountET.setText(String.valueOf(discount));
                    }
                    double tips = tipsET.getText().toString().length() > 0 ? Double.parseDouble(tipsET.getText().toString()) : 0;

                    calculate(price, discount, tips);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        tipsET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void afterTextChanged(Editable editable) {
                String str = editable.toString();
                if (str.indexOf(",") > -1) {
                    str = str.replaceAll(",", ".");
                    //Log.d(TAG,"**"+str);
                    tipsET.setText(str);
                    tipsET.setSelection(str.length());
                } else if (str.startsWith(".")) {
                    str = "0.";
                    //Log.d(TAG,"**"+str);
                    tipsET.setText(str);
                    tipsET.setSelection(str.length());//设置光标位置在最后，默认是在最前面
                }
                vm.changeTipsStr(str);

            }

        });

        vm.getTipsStr().observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                try {
                    double tips = s.length() > 0 ? Double.parseDouble(s) : 0;
                    if(tips>tipsLimit){
                        tips = tipsLimit;
                        tipsET.setText(String.valueOf(tips));
                    }
                    double price = priceET.getText().toString().length() > 0 ? Double.parseDouble(priceET.getText().toString()) : 0;
                    double discount = discountET.getText().toString().length() > 0 ? Double.parseDouble(discountET.getText().toString()) : 0;

                    calculate(price, discount, tips);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        wipeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                efface();
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bill bill = new Bill();

                bill.setPrice(priceET.getText().toString().isEmpty() ? bill.getPrice() : priceET.getText().toString());
                bill.setDiscount(discountET.getText().toString().isEmpty() ? bill.getDiscount() : discountET.getText().toString());
                bill.setDiscountRes(disResTV.getText().toString());
                bill.setTpsRes(tpsResTV.getText().toString());
                bill.setTvqRes(tvqResTV.getText().toString());
                bill.setTips(tipsET.getText().toString().isEmpty() ? bill.getTips() : tipsET.getText().toString());
                bill.setTipsRes(tipsResTV.getText().toString());
                bill.setTotalTaxRes(totalTaxResTV.getText().toString());
                bill.setTotal(totalResTV.getText().toString());
                showDialog(bill);
            }
        });

        return view;
    }

    private void calculate(double price, double discount, double tips) {

        Log.d(TAG, "****price" + String.valueOf(price));

        double discountRes = price * (discount / 100);
        if (discountRes == 0) {
            this.disResTV.setText(String.format("%.2f", discountRes));
        } else {
            this.disResTV.setText("-" + String.format("%.2f", discountRes));
        }


        double tpsRes = (price - discountRes) * this.tps;
        this.tpsResTV.setText(String.format("%.2f", tpsRes));

        double tvqRes = (price - discountRes) * this.tvq;
        this.tvqResTV.setText(String.format("%.2f", tvqRes));

        double totalTaxRes = tpsRes + tvqRes;
        this.totalTaxResTV.setText(String.format("%.2f", totalTaxRes));

        double tipsRes = (price - discountRes) * (tips / 100);
        this.tipsResTV.setText(String.format("%.2f", tipsRes));

        double total = price * (1 - discount / 100) * (this.totalTaxPercent) + tipsRes;
        this.totalResTV.setText(String.format("%.2f", total));


    }


    private void efface() {
        this.priceET.setText("");
        this.priceET.setHint("0.00");

        this.discountET.setText("");
        this.discountET.setHint("0");

        this.tipsET.setText("");
        this.tipsET.setHint("0");

    }

    protected void showDialog(Bill bill) {

        final Dialog dialog = new Dialog(getActivity());
        Typeface tf = Functions.getAvenirHeavyFonts(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.fragment_dialog);


        final EditText articleNameET = dialog.findViewById(R.id.editTextArticleName);
        final EditText magasinET = dialog.findViewById(R.id.editTextMagasin);
        articleNameET.setTypeface(tf);
        magasinET.setTypeface(tf);

        final TextView priceTV = dialog.findViewById(R.id.textView6);
        final TextView priceResTV = dialog.findViewById(R.id.textView7);
        priceTV.setTypeface(tf);
        priceResTV.setTypeface(tf);
        priceResTV.setText(bill.getPrice());

        final TextView discountTV = dialog.findViewById(R.id.textView8);
        final TextView discountResTV = dialog.findViewById(R.id.textView22);
        discountTV.setTypeface(tf);
        discountResTV.setTypeface(tf);
        discountTV.setText(discountTV.getText().toString() + " " + bill.getDiscount() + "%");
        discountResTV.setText(bill.getDiscountRes());

        final TextView tpsTV = dialog.findViewById(R.id.textView25);
        final TextView tpsResTV = dialog.findViewById(R.id.textView29);
        tpsTV.setTypeface(tf);
        tpsResTV.setTypeface(tf);
        tpsResTV.setText(bill.getTpsRes());

        final TextView tvqTV = dialog.findViewById(R.id.textView26);
        final TextView tvqResTV = dialog.findViewById(R.id.textView30);
        tvqTV.setTypeface(tf);
        tvqResTV.setTypeface(tf);
        tvqResTV.setText(bill.getTvqRes());

        final TextView totalTaxTV = dialog.findViewById(R.id.textView27);
        final TextView totalTaxResTV = dialog.findViewById(R.id.textView31);
        totalTaxTV.setTypeface(tf);
        totalTaxResTV.setTypeface(tf);
        totalTaxResTV.setText(bill.getTotalTaxRes());

        final TextView tipsTV = dialog.findViewById(R.id.textView28);
        final TextView tipsResTV = dialog.findViewById(R.id.textView32);
        tipsTV.setTypeface(tf);
        tipsResTV.setTypeface(tf);
        tipsTV.setText(tipsTV.getText().toString() + " " + bill.getTips() + "%");
        tipsResTV.setText(bill.getTipsRes());


        //detailTV.setText(bill.getDetails());
        final TextView totalTv = dialog.findViewById(R.id.textView21);
        totalTv.setTypeface(tf);
        totalTv.setText("Total            " + String.valueOf(bill.getTotal()));


        Button save = dialog.findViewById(R.id.button);
        save.setTypeface(tf);
        Button cancel = dialog.findViewById(R.id.button2);
        cancel.setTypeface(tf);

        save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String articleName = articleNameET.getText().toString();
                String magasin = magasinET.getText().toString();


                if (articleName.length() <= 0) {
                    articleNameET.setHint("Entrez le nom de article svp");
                    return;
                }
                if (magasin.length() <= 0) {
                    magasinET.setHint("Entrez le nom de magasin svp");
                    return;
                }


                bill.setArticleName(articleName);
                bill.setMagasin(magasin);

                //保存
                new SaveAsyncTask(pd).execute(bill);
                efface();
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

    class SaveAsyncTask extends AsyncTask<Bill, Void, Void> {
        private ProjectDAO pd;

        public SaveAsyncTask(ProjectDAO pd) {

            this.pd = pd;
        }

        @Override
        protected Void doInBackground(Bill... bills) {
            Bill bill = bills[0];
            //long id = pd.insertGymExcercice(gymExercice);//存入数据，得到数据库里的唯一
            //gymExercice.setId(id);//给了id值
            pd.insertBills(bill);//存入数据
            vm.setList(pd.getBills());
            //gymExercice.setId(id);//给了id值
//            Log.d("$$$$globlelist size",String.valueOf(globalGeList.size()));
//            List<GymExercice> list = new ArrayList<GymExercice>();
//            list.add(gymExercice);//返回给onPost方法
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);


            List<Bill> geList = new ArrayList<Bill>();



        }

    }


}