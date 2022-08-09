package canada.montreal.pierre.judocanada;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class ContactFragment extends Fragment {

    private TextView telTV;
    private final String TAG = this.getClass().getSimpleName();
    private Button button;
    private EditText firstNameEt;
    private EditText lastNameEt;
    private EditText msgEt;

    public ContactFragment() {
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
        View view = inflater.inflate(R.layout.fragment_contact,null);
        telTV =  view.findViewById(R.id.textView2);
        button = view.findViewById(R.id.submitButton);
        this.lastNameEt = view.findViewById(R.id.contactLastNameEt);
        this.firstNameEt = view.findViewById(R.id.contactFirstNameEt);
        this.msgEt  = view.findViewById(R.id.contactMessageEt);

        telTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getContext(),"dddddddddddddddddddd",Toast.LENGTH_LONG).show();
                String action = Intent.ACTION_DIAL;
                Intent intent = new Intent(action);
                String number = "514-255-5836";
                intent.setData(Uri.parse("tel:" + number));
                startActivity(intent);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = "From "+ firstNameEt.getText().toString()+" "+ lastNameEt.getText().toString()+":\n\n"+ msgEt.getText().toString();

                sendEmail("A message to JudoCanada",content);
            }
        });
        /*
        *
        * */
        return view;
    }

    protected void sendEmail(String subject,String content) {
        Log.d("Send email", "******************************");


        Uri uri = Uri.parse("mailto:pierrechang2013@gmail.com");
         String[] email = {"info@judocanada.org"};
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("plain/text");//纯文本
        intent.putExtra(Intent.EXTRA_EMAIL, email); // 发送给
        intent.putExtra(Intent.EXTRA_SUBJECT, subject); // 主题
        intent.putExtra(Intent.EXTRA_TEXT, content); // 正文
        startActivity(Intent.createChooser(intent, "Choissez quel email appli"));

        try {
            startActivity(Intent.createChooser(intent, "Send mail..."));
            getActivity().finish();
            Log.d(TAG, "Envoyez un email.....");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getContext(), "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }
}