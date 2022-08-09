package canada.montreal.pierre.android2_exam2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.sql.Connection;

import canada.montreal.pierre.android2_exam2.mysql.DbUtil;

public class RegisterActivity extends AppCompatActivity {

    EditText lastName;
    EditText firstName;
    EditText age;
    Spinner spinner;
    Button saveBtn;
    Button returnBtn;

    String lastNameStr;
    String firstNameStr;
    String ageStr;
    String sexStr;

    Handler handler;

    private final int AFTER_ADD = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        lastName = findViewById(R.id.editTextTextPersonName4);
//        lastName.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                lastNameStr = lastName.getText().toString();
//            }
//        });
        firstName = findViewById(R.id.editTextTextPersonName5);
//        firstName.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                firstNameStr = firstName.getText().toString();
//            }
//        });
        age = findViewById(R.id.editTextTextPersonName6);
//        age.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ageStr = age.getText().toString();
//            }
//        });

        spinner = this.creatSpinner();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                sexStr = adapterView.getItemAtPosition(i).toString();//


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

        });

        saveBtn = findViewById(R.id.button7);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Connection con = DbUtil.getConn();
                        Client client = new Client(lastName.getText().toString(), firstName.getText().toString(),age.getText().toString().length()>0?Integer.parseInt(age.getText().toString()):0,sexStr);
                        DbUtil.addClient(con, client);
                        Message message = new Message();
                        message.what = AFTER_ADD;

//                con = DbUtil.getConn();
//                list =  DbUtil.getGeList(con,ge.getGymId());
                        handler.sendMessage(message);

                    }
                }).start();


            }
        });
        returnBtn = findViewById(R.id.button8);
        returnBtn.setOnClickListener(new View.OnClickListener() {//return
            @Override
            public void onClick(View v) {
                Intent intern = new Intent(RegisterActivity.this, MainActivity.class);
//                intern.putExtra("gymId", gymId);
//                intern.putExtra("gymTitle", gymTitle);
                startActivity(intern);
            }
        });

        handler = new Handler(Looper.myLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                if (msg.what == AFTER_ADD) {
                    Intent intern = new Intent(RegisterActivity.this, ListActivity.class);
//                intern.putExtra("gymId", gymId);
//                intern.putExtra("gymTitle", gymTitle);
                    startActivity(intern);
                }
//                else if(msg.what == Add_GE){
//                     listerGeExcercies(gymId);
//                }
            }
        };
    }

    private Spinner creatSpinner() {

        Spinner spinner = findViewById(R.id.spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.sexe, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        return spinner;
    }
}