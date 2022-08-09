package canada.montreal.pierre.android1_excercice11;

import android.graphics.Color;
import android.util.Log;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Functions {

    public static  List getList(){

        List<Form> list = new ArrayList<Form>();
        Form title = new Form();
        title.setLastName("Nom");
        title.setDate("Date");
        title.setAmount("Montant");
        list.add(title);

        Log.d("Functions", " 这里执行了没");
        return list;
    }

    public static Form getForm(){
        return null;
    }

    public static boolean isInfoCorrect(String ln,String fn,String speed){

        boolean bool = false;
//        Log.d("bijiao","@@@@@@@@@@@@@@@@@@");
        //final String   NAME_PATTERN = "[\u00C0-\u017Fa-zA-Z-' ]+";
        final String   NAME_PATTERN = "(?!.*([-' ])\\1)[\u00C0-\u017Fa-zA-Z-' ]+";
        //final String   NAME_PATTERN = "^[A-Za-z]+$";
//        Log.d("bijiao",String.valueOf(ln.matches(NAME_PATTERN)));
//        Log.d("bijiao",String.valueOf(fn.matches(NAME_PATTERN)));


        if(ln == null || fn == null  || speed  == null ||ln.length()<=0||fn.length()<=0||speed.length()<=0){
            Log.d("bijiao","!!!!!!!!!!!!!!!!!!!");
            bool =false;
        }else if(ln.matches(NAME_PATTERN)&&fn.matches(NAME_PATTERN)){
            Log.d("bijiao",String.valueOf(ln.matches(NAME_PATTERN)));
            bool  = true;
        }

        return bool;

    }

    public static int getRedColorInt(){

        return Color.parseColor("#FFE41414");
    }

    public static  int caculateFine(int baseSpeed,int actuelSpeed){
        int fine = 0;
        if(actuelSpeed>0) {

            int difference = actuelSpeed - baseSpeed;
            Log.d("方法,baseSpeed", String.valueOf(baseSpeed));
            Log.d("方法,actuelSpeed", String.valueOf(actuelSpeed));
            Log.d("方法,difference", String.valueOf(difference));

            int quotient = difference / 4;

            int remainder = difference % 4;

            if (quotient == 0) {
                difference = 4;
            } else if (quotient == 1 && remainder != 0) {
                difference = 5;
            } else if (quotient > 2) {
                if (difference % 10 == 0) {
                    difference = difference + 1;
                } else {
                    difference = ((difference + 4) / 5) * 4 + 1;//乱写的，随便吧
                }
            }

            fine = (difference + 1) * 5;

        }else{

        }


        return fine;
    }

    public static String getDate(){

        SimpleDateFormat  sdf = new SimpleDateFormat("yyyy-MM-dd");

        return sdf.format(new Date());
    }

    public  static void setSpinnerItemSelectedByValue(Spinner spinner, String value){
        SpinnerAdapter apsAdapter= spinner.getAdapter();
        int k= apsAdapter.getCount();
        for(int i=0;i<k;i++){
            if(value.equals(apsAdapter.getItem(i).toString())){
//                spinner.setSelection(i,true);// 默认选中项
                spinner.setSelection(i);// 默认选中项

                break;
            }
        }
    }


}
