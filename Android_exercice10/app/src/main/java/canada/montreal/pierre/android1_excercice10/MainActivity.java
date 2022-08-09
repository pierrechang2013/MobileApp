package canada.montreal.pierre.android1_excercice10;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import android.widget.Spinner;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {
    //widget

    //tests menu connection bool
    boolean _ticket = false;

    android.view.MenuItem add;
    android.view.MenuItem delete;



    @BindView(R.id.textView9)
    TextView tvNote;
    @BindView(R.id.textView)
    TextView tvLastName;
    @BindView(R.id.textView2)
    TextView tvFirstName;
    @BindView(R.id.textView4)
    TextView tvSpeed;


    @BindView(R.id.editText7)
    EditText etLastName;
    @BindView(R.id.editText8)
    EditText etFirstName;
    @BindView(R.id.spinner)
    Spinner spinner;
    @BindView(R.id.checkBox)
    CheckBox checkBox;
    @BindView(R.id.editText3)
    EditText etSpeed;
    @BindView(R.id.editText4)
    EditText etAmount;

    @BindView(R.id.rv)
    RecyclerView rv;

    ListAdapter rva;


    String lastName;
    String firstName;
    String speed = "0";
    String amount;
    Boolean isSchoolOrWork;
    String zone;
    int times = 1;

    //ViewMode
    ViewModel vm;
    Form currentForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("主要", "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //---ViewModel--------
//        add = findViewById(R.id.add);
//        delete = findViewById(R.id.delete);
//
//        tvNote = findViewById(R.id.textView9);
//        tvNote.setTextColor(Functions.getRedColorInt());
//
//        tvLastName = findViewById(R.id.textView);
//
//        tvFirstName = findViewById(R.id.textView2);
//        tvSpeed = findViewById(R.id.textView4);

        vm = new ViewModelProvider(this).get(ViewModel.class);

        vm.getColorInt().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                tvNote.setTextColor(integer.intValue());
                //tvNote.setVisibility(View.VISIBLE);
                tvLastName.setTextColor(integer.intValue());
                tvFirstName.setTextColor(integer.intValue());
                tvSpeed.setTextColor(integer.intValue());
            }
        });

        vm.getIsVisible().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                Log.d("主要  Visible observe", String.valueOf(integer));
                if (integer == 1) {
                    tvNote.setVisibility(View.VISIBLE);
                } else if (integer == 0) {
                    tvNote.setVisibility(View.INVISIBLE);
                }

            }
        });

//        etLastName = findViewById(R.id.editText7);
//        etFirstName = findViewById(R.id.editText8);

        //spinner = this.creatSpinner();//下拉列表
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.zone, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

//        checkBox = findViewById(R.id.checkBox);
//        etSpeed = findViewById(R.id.editText3);
//        etAmount = findViewById(R.id.editText4);
        etAmount.setEnabled(false);//不可编辑，xml文件里不能设置


//        rv = findViewById(R.id.rv);

        rv.setLayoutManager(new LinearLayoutManager(this));

        rva = new ListAdapter(vm.list);

        rv.setAdapter(rva);


        vm.getZoneLiveData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String str) {//observer 启动onChanged的前提是必须使用对应的setValue,change方法里是对应的返回值

                int accuelSpeed = 0;

                try {

                    if (!etSpeed.getText().toString().equals("")) {


                        accuelSpeed = Integer.parseInt(etSpeed.getText().toString());


                    }

                    etAmount.setText(times * Functions.caculateFine(Integer.parseInt(str), accuelSpeed) + "$");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String info = adapterView.getItemAtPosition(i).toString();//
                vm.changeZone(info);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

        });

        vm.getIsSchoolOrWorkLiveData().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean bool) {//observer 启动onChanged的前提是必须使用对应的setValue,change方法里是对应的返回值


                try {
                    Log.d("主要 ", String.valueOf(bool));
                    times = bool == true ? 2 : 1;


                    etAmount.setText(times * Functions.caculateFine(Integer.parseInt(spinner.getSelectedItem().toString()), Integer.parseInt(etSpeed.getText().toString())) + "$");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                vm.choosSchoolOrWork(b);
            }
        });

        vm.getSpeedLiveData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {

                if (Integer.parseInt(s) <= Integer.parseInt(spinner.getSelectedItem().toString())) {

                } else {
                    etAmount.setText(times * Functions.caculateFine(Integer.parseInt(spinner.getSelectedItem().toString()), Integer.parseInt(s)) + "$");


                }

            }
        });

        etSpeed.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void afterTextChanged(Editable editable) {
                //Log.d("主要",editable.toString());

                if (editable.toString().length() > 0) {
                    vm.changeSpeed(editable.toString());
                }


            }

        });


        //rva.setOnItemClickListener();


        rva.setOnItemClickListener(new ListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
Log.d("现在点了",String.valueOf(position));
//                add = findViewById(R.id.add);
//                add.setEnabled(false);
                add.setTitle("Update");
                currentForm = vm.list.get(position);
                currentForm.setCurrentPosition(position);
                etFirstName.setText(currentForm.getFirstName());
                etLastName.setText(currentForm.getLastName());
                etSpeed.setText(currentForm.getSpeed());
                etAmount.setText(currentForm.getAmount());
                checkBox.setChecked(currentForm.getIsSchoolOrWork());

                Functions.setSpinnerItemSelectedByValue(spinner, currentForm.getZone());

            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);

    }

//    private Spinner creatSpinner() {
//
//         = findViewById(R.id.spinner);
//
//
//        return spinner;
//    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
//        Log.d("onPrepareOptionsMenu","@@@@@@@@@@@@@@@@@@@@@@@@@@@@@s");
        add = menu.findItem(R.id.add);
        delete = menu.findItem(R.id.delete);
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
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        lastName = etLastName.getText().toString();
        firstName = etFirstName.getText().toString();
        speed = etSpeed.getText().toString();
        this.amount = etAmount.getText().toString() + "$";
        this.isSchoolOrWork = checkBox.isChecked();
        this.zone = spinner.getSelectedItem().toString();

        int speedInt = Integer.parseInt(speed);

        int zoneInt = 0;
        if (zone.length() > 0) {
            zoneInt = Integer.parseInt(zone);
        }

        //listening button Ajout ou update

        int id = item.getItemId();

        switch (id) {

            case R.id.add:

                if (add.getTitle().equals("Update")) {


                    //delete first
                    if (currentForm != null && currentForm.getCurrentPosition() > 0) {
                        Log.d("主要delete..Position", String.valueOf(this.currentForm.getCurrentPosition()));
                        Form form = currentForm;
                        rva.list.remove(currentForm.getCurrentPosition());

                        vm.list.remove(currentForm);
                        //rva.notifyDataSetChanged();
                        //rv.setAdapter(rva);


                        vm.changeColor(Color.parseColor("#FF000000"));
                        vm.changeVisible(0);

                        //add as the position

                        form.setLastName(lastName);
                        form.setFirstName(firstName);
                        form.setDate(Functions.getDate());
                        form.setAmount(etAmount.getText().toString());
                        form.setZone(spinner.getSelectedItem().toString());
                        form.setSpeed(etSpeed.getText().toString());
                        form.setIsSchoolOrWork(checkBox.isChecked());
                        //int accuelSpeed = Integer.parseInt(this.etSpeed.getText().toString());
                        //form.setAmount( Functions.caculateFine(Integer.parseInt(spinner.getSelectedItem().toString()), accuelSpeed) + "$");
                        form.setAmount(etAmount.getText().toString());
                        vm.list.add(form.getCurrentPosition(), form);
                        this.backToOriginal();

                        rva.notifyDataSetChanged();

                    }





                } else {

                    if (!Functions.isInfoCorrect(lastName, firstName, speed)) {

                        tvNote.setVisibility(View.VISIBLE);
                        vm.changeVisible(1);
                        Log.d("主要add", String.valueOf(tvNote.getVisibility()));
                        int colorInt = Functions.getRedColorInt();
                        tvLastName.setTextColor(colorInt);
                        tvFirstName.setTextColor(colorInt);
                        tvSpeed.setTextColor(colorInt);

                        vm.changeColor(Integer.valueOf(colorInt));
                        Log.d("主要 R.id.add", "!!!!!!!!!!!!");
                        backToOriginal();

                    } else if (zoneInt >= speedInt) {

                        DialogFrag df = new DialogFrag();
                        df.show(getSupportFragmentManager(), "dialog");
                    } else {//put into list

                        Form form = new Form();

                        form.setLastName(lastName);
                        form.setFirstName(firstName);
                        form.setDate(Functions.getDate());
                        form.setAmount(etAmount.getText().toString());
                        form.setZone(spinner.getSelectedItem().toString());
                        form.setSpeed(etSpeed.getText().toString());
                        form.setIsSchoolOrWork(checkBox.isChecked());

                        int accuelSpeed = Integer.parseInt(this.etSpeed.getText().toString());
                        //form.setAmount( Functions.caculateFine(Integer.parseInt(spinner.getSelectedItem().toString()), accuelSpeed) + "$");
                        form.setAmount(etAmount.getText().toString());
                        vm.list.add(1, form);
                        rva.notifyDataSetChanged();


                        backToOriginal();
                        vm.changeColor(Color.parseColor("#FF000000"));
                        vm.changeVisible(0);
                    }

                }

                break;

            case R.id.erase:


                backToOriginal();
                vm.changeColor(Color.parseColor("#FF000000"));
                vm.changeVisible(0);

                break;
            case R.id.delete:

                if (currentForm != null && currentForm.getCurrentPosition() > 0) {
                    Log.d("主要delete..Position", String.valueOf(this.currentForm.getCurrentPosition()));

                    rva.list.remove(currentForm.getCurrentPosition());

                    vm.list.remove(currentForm);
                    rva.notifyDataSetChanged();
                    //rv.setAdapter(rva);

                    backToOriginal();
                    vm.changeColor(Color.parseColor("#FF000000"));
                    vm.changeVisible(0);
                }


                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void backToOriginal() {
        //tvNote.setVisibility(View.INVISIBLE);
        etLastName.setText("");
        etFirstName.setText("");
        etSpeed.setText("0");
        etAmount.setText("0$");
        spinner.setSelection(0, true);
        checkBox.setChecked(false);
//        add = findViewById(R.id.add);
//        add.setEnabled(true);
        currentForm = null;//只要点了其他按钮，就使失去当前的对象，
        //vm.changeVisible(0);


    }
}