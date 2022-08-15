package canada.montreal.pierre.apptexescalc;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
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
    private TextView titleTV;
    private TextView telTV;
    private TextView emailTV;
    private TextView bugTv;
    private TextView techTv;
    private final String TAG = this.getClass().getSimpleName();


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
        titleTV = view.findViewById(R.id.textView5);
        bugTv = view.findViewById(R.id.textView11);
        telTV =  view.findViewById(R.id.textView2);
        emailTV = view.findViewById(R.id.textView4);
        techTv = view.findViewById(R.id.textView15);
//         AssetManager am = getActivity().getAssets();
//         Typeface tf = Typeface.createFromAsset(am, "font/Avenir-Heavy.otf");//设置字体
        Typeface tf = Functions.getAvenirHeavyFonts(getActivity());
        titleTV.setTypeface(tf);
        telTV.setTypeface(tf);
        emailTV.setTypeface(tf);
        bugTv.setTypeface(tf);
        techTv.setTypeface(tf);


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

        emailTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String subject = "Une message pour AppCalcTaxes";
                String content = "";
                sendEmail(subject,content);
            }
        });

        techTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String subject = "Suggetions.....";
                String content = "";
                sendEmail(subject,content);
            }
        });


        /*
        *
        * */
        return view;
    }

    protected void sendEmail(String subject,String content) {
        Log.d("Send email", "******************************");


//        Uri uri = Uri.parse("mailto:pierrechang2013@gmail.com");
        String[] email = {"pierrechang2013@gmail.com"};
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("plain/text");//纯文本
        intent.putExtra(Intent.EXTRA_EMAIL, email); // 发送给
        intent.putExtra(Intent.EXTRA_SUBJECT, subject); // 主题
        intent.putExtra(Intent.EXTRA_TEXT, content); // 正文


        try {
            startActivity(Intent.createChooser(intent, "Choisissez une application de courriel"));
            //startActivity(Intent.createChooser(intent, "Send mail..."));
            //getActivity().finish();//加了这个就结束应用了，要小心使用
            Log.d(TAG, "Envoyez un email.....");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getContext(), "Aucune application de courriel installée", Toast.LENGTH_SHORT).show();
        }
    }
}