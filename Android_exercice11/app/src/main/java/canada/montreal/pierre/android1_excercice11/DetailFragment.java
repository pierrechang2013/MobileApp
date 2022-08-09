package canada.montreal.pierre.android1_excercice11;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class DetailFragment extends Fragment {


    //----------------------------------


    android.view.MenuItem save;
    android.view.MenuItem delete;

    @BindView(R.id.textView10)
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //用了绑定这个view就必须这么写，否则就报错
        View view = inflater.inflate( R.layout.fragment_detail, null);
        //View view = inflater.inflate( R.layout.fragment_list, container,false);
        setHasOptionsMenu(true);
        vm = new ViewModelProvider(requireActivity()).get(ViewModel.class);
        ButterKnife.bind(this, view);


        //vm = new ViewModelProvider(this).get(ViewModel.class);
        vm.getSelected().observe(getViewLifecycleOwner(), item -> {
            // Update the UI.
            currentForm = item;
            Log.d("bianlllllllllllllllllll",currentForm.getAmount().toString());
            Log.d("bianlllllllllllllllllll",String.valueOf(currentForm.getCurrentPosition()));



        if(currentForm == null || currentForm.getCurrentPosition()<=0){
            Log.d("detail  vm.list.size()",String.valueOf(vm.list.size()));

        }else if(currentForm.getCurrentPosition()>0){

            Log.d("detaillll 多少钱",currentForm.getAmount());




            etFirstName.setText(currentForm.getFirstName());
            etLastName.setText(currentForm.getLastName());
            etSpeed.setText(currentForm.getSpeed());
            etAmount.setText(currentForm.getAmount());
            checkBox.setChecked(currentForm.getIsSchoolOrWork());

            Functions.setSpinnerItemSelectedByValue(spinner, currentForm.getZone());
        }

        });
        vm.getColorInt().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                tvNote.setTextColor(integer.intValue());
                //tvNote.setVisibility(View.VISIBLE);
                tvLastName.setTextColor(integer.intValue());
                tvFirstName.setTextColor(integer.intValue());
                tvSpeed.setTextColor(integer.intValue());
            }
        });

        vm.getIsVisible().observe(getViewLifecycleOwner(), new Observer<Integer>() {
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


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.zone, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        etAmount.setEnabled(false);//不可编辑，xml文件里不能设置

        vm.getZoneLiveData().observe(getViewLifecycleOwner(), new Observer<String>() {
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

        vm.getIsSchoolOrWorkLiveData().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
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

        vm.getSpeedLiveData().observe(getViewLifecycleOwner(), new Observer<String>() {
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



        return view;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        super.onCreateOptionsMenu(menu, menuInflater);
        menuInflater.inflate(R.menu.menu2, menu);

        save = (android.view.MenuItem) menu.findItem(R.id.save);
        delete = (android.view.MenuItem) menu.findItem(R.id.delete);

        save.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {



            @Override
            public boolean onMenuItemClick(MenuItem item) {
                lastName = etLastName.getText().toString();
                firstName = etFirstName.getText().toString();
                speed = etSpeed.getText().toString();
                amount = etAmount.getText().toString() + "$";
                isSchoolOrWork = checkBox.isChecked();
                zone = spinner.getSelectedItem().toString();
                //有记录，这是更新
                if(currentForm != null && currentForm.getCurrentPosition() >0){

                    Log.d("主要delete..Position", String.valueOf(currentForm.getCurrentPosition()));

                    Form form = new Form();
                    form.setCurrentPosition(currentForm.getCurrentPosition());
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
                    backToOriginal();

                    //rva.notifyDataSetChanged();

                }else{//这是添加


                    int speedInt = Integer.parseInt(speed);


                    int zoneInt = 0;

                    if (zone.length() > 0) {

                        zoneInt = Integer.parseInt(zone);

                    }


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
                        df.show(getActivity().getSupportFragmentManager(), "dialog");
                    } else {//put into list

                        Form form = new Form();

                        form.setLastName(lastName);
                        form.setFirstName(firstName);
                        form.setDate(Functions.getDate());
                        form.setAmount(etAmount.getText().toString());
                        form.setZone(spinner.getSelectedItem().toString());
                        form.setSpeed(etSpeed.getText().toString());
                        form.setIsSchoolOrWork(checkBox.isChecked());

//                    int accuelSpeed = Integer.parseInt( etSpeed.getText().toString());
                        //form.setAmount( Functions.caculateFine(Integer.parseInt(spinner.getSelectedItem().toString()), accuelSpeed) + "$");
                        form.setAmount(etAmount.getText().toString());
                        vm.list.add(1, form);
//                    rva.notifyDataSetChanged();
                        Log.d("detail fragment vm.list", String.valueOf(vm.list.size()));

                        backToOriginal();
                        vm.changeColor(Color.parseColor("#FF000000"));
                        vm.changeVisible(0);
                }

                }



                return false;
            }
        });


        delete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                lastName = etLastName.getText().toString();
                firstName = etFirstName.getText().toString();
                speed = etSpeed.getText().toString();
                amount = etAmount.getText().toString() + "$";
                isSchoolOrWork = checkBox.isChecked();
                zone = spinner.getSelectedItem().toString();

                int speedInt = Integer.parseInt(speed);


                int zoneInt = 0;

                if (zone.length() > 0) {

                    zoneInt = Integer.parseInt(zone);

                }
                //有记录，删除
                if(currentForm != null && currentForm.getCurrentPosition() >0){

                    Log.d("主要delete..Position", String.valueOf(currentForm.getCurrentPosition()));

                    Form form = new Form();
                    form.setCurrentPosition(currentForm.getCurrentPosition());
                    vm.list.remove(currentForm);
                    //rva.notifyDataSetChanged();
                    //rv.setAdapter(rva);


                    vm.changeColor(Color.parseColor("#FF000000"));
                    vm.changeVisible(0);


                    backToOriginal();

                }



                return false;
            }
        });



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