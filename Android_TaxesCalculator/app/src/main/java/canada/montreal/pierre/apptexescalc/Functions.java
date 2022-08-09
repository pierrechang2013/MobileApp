package canada.montreal.pierre.apptexescalc;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;

import com.google.gson.Gson;

import java.lang.reflect.Member;


public class Functions {

    private static Functions functions = null;

    private Functions(){


    }

    static Functions getInstance(){

        if(functions==null){
            functions=new Functions();
        }
        return functions;
    }


    static  Typeface getAvenirHeavyFonts(Context context){

        AssetManager am = context.getAssets();
        Typeface tf = Typeface.createFromAsset(am, "font/Avenir-Heavy.otf");//设置字体

        return tf;
    }

    static String getBreafString(String originalStr,int length){

        String str = "";

        if(originalStr!=null&&originalStr.length()>length){

            str = originalStr.substring(0,length)+"...";
        }else{

            str = originalStr;
        }
       return str;

    }




}
