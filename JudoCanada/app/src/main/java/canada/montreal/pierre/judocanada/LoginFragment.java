package canada.montreal.pierre.judocanada;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Calendar;
import java.util.Locale;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class LoginFragment extends Fragment  {

    private static final String TAG = LoginFragment.class.getSimpleName();
    //组件
    EditText firstNameEt;
    EditText lastNameEt;
    TextView dateTv;
    EditText memberNumEt;

    Button submitBt;
    //Film film;
    Member member;


    //变量
    //初始化日历类
    Calendar calendar = Calendar.getInstance(Locale.CANADA);

    String firstName;
    String lastName;
    String dateStr;
    String membership;



    public static void showDatePickerDialog(Activity activity, int themeResId, final TextView tv, final Calendar calendar) {
        // 直接创建一个DatePickerDialog对话框实例，并将它显示出来
        new DatePickerDialog(activity, themeResId, new DatePickerDialog.OnDateSetListener() {
            // 绑定监听器(How the parent is notified that the date is set.)
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // 此处得到选择的时间，可以进行你想要的操作
                tv.setText("" + year + "-" + (monthOfYear + 1) + "-" + dayOfMonth + "");

            }
        }
                // 设置初始日期
                , calendar.get(Calendar.YEAR)
                , calendar.get(Calendar.MONTH)
                , calendar.get(Calendar.DAY_OF_MONTH)).show();

    }


//
//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
//        super.onCreateOptionsMenu(menu, menuInflater);
//        menuInflater.inflate(R.menu.menu, menu);
//
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        boolean bool = false;

//        switch (item.getItemId()) {
//            case android.R.id.home:
//                NavUtils.navigateUpFromSameTask(this);
//                bool = true;
//        }

        return bool;
    }



    public static OkHttpClient getUnsafeOkHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[] {
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public X509Certificate[] getAcceptedIssuers() {
                            return new X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

//            okhttp3.OkHttpClient.Builder builder = new OkHttpClient.Builder();
//            builder.sslSocketFactory(sslSocketFactory);
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory, new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            });
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });

            OkHttpClient okHttpClient = builder.build();
            return okHttpClient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_login, null);
        //setHasOptionsMenu(true);

        firstNameEt = view.findViewById(R.id.loginFirstNameEt);
        lastNameEt = view.findViewById(R.id.loginLastNameEt);

        memberNumEt = view.findViewById(R.id.loginNumberEt);
        //日期
        dateTv = view.findViewById(R.id.birthTv);
        dateTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                日期选择
                showDatePickerDialog(getActivity(),0,dateTv,calendar);
            }
        });


        submitBt = view.findViewById(R.id.submitButton);

        submitBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstName = firstNameEt.getText().toString().trim();
                lastName  = lastNameEt.getText().toString().trim();
                dateStr = dateTv.getText().toString().trim();

                membership = memberNumEt.getText().toString();
                Log.d(TAG+" firstName",firstName);
                Log.d(TAG+" lastName",lastName);
                Log.d(TAG+" dateStr",dateStr);
                Log.d(TAG+" membership",membership);
                //------------------------
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        //String path = "http://49.232.114.172:8080/yunying/qufumanage/getList.json";
                        //String path = "https://www.omdbapi.com/?i=tt0000100&apikey=e0a35fb0";
                        //String path = "https://www.trackie.com/api/request/?api=judoCanadaMemberLookup&token=JH&SD^FfF_3THQ<_j},vh#!qD@,&firstName=Tierry&lastName=A .coutu&memberNum =0218414";
                        //OkHttpClient okHttpClient = getUnsafeOkHttpClient();
                        OkHttpClient okHttpClient = new OkHttpClient();
                        String url = "https://www.trackie.com/api/request/";//


                        RequestBody body = new FormBody.Builder()
                                .add(" api","judoCanadaMemberLookup")
                                .add("token","JH&SD^FfF_3THQ<_j},vh#!qD@,")
                                .add("firstName", firstName)
                                .add("lastName", lastName)
                                .add("birthDate", dateStr)
                                .add("memberNum", membership)
                                .build();

                        Request request = new okhttp3.Request.Builder()
                                .url(url)
                                .addHeader("token",   "JH&SD^FfF_3THQ<_j},vh#!qD@,")
                                //.addHeader("token","JH&SD^FfF_3THQ<_j},vh#!qD@,")
                                .post(body)
                                .build();


                        Call call = okHttpClient.newCall(request);

                        try {
                            Response response = call.execute();
                            String data = response.body().string();
                            Log.d(TAG,"******************************************");
                            Log.d(TAG,data);

                            Functions fun = new Functions();
                            //film = fun.getFilmObject(data);

                            member= fun.getMemberObject(data);

                            if(member==null||member.lastName==null||member.lastName.length()<=0){

                                Log.d(TAG,"  member is empty");
                                member = new Member();
                                member.setMemberNum("NULL");


                            }
                            //序列化对象然后传值
                            Bundle bundle = new Bundle();
                            bundle.putParcelable("member",  member);
                            DetailFragment df = new DetailFragment();
                            df.setArguments(bundle);


                            ((MainActivity)getActivity()).setFragment(df);

//                            Log.d(TAG,"***********************");

                        } catch (IOException e) {

                            e.printStackTrace();

                        }


                    }

                }).start();




                //-------------------------

            }
        });
        return view;
    }
}