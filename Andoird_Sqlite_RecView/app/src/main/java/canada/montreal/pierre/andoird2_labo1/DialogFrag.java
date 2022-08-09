package canada.montreal.pierre.andoird2_labo1;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

public class DialogFrag extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

//        builder.setMessage("Message").setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                Log.d("对话框",getActivity().getLocalClassName().toString());
//            }
//        }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//
//            }
//        });

        return builder.create();
    }



    int mNum;
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        mNum = getArguments().getInt("num");
//
//        // Pick a style based on the num.
//        int style = DialogFragment.STYLE_NORMAL, theme = 0;
//        switch ((mNum-1)%6) {
//            case 1: style = DialogFragment.STYLE_NO_TITLE; break;
//            case 2: style = DialogFragment.STYLE_NO_FRAME; break;
//            case 3: style = DialogFragment.STYLE_NO_INPUT; break;
//            case 4: style = DialogFragment.STYLE_NORMAL; break;
//            case 5: style = DialogFragment.STYLE_NORMAL; break;
//            case 6: style = DialogFragment.STYLE_NO_TITLE; break;
//            case 7: style = DialogFragment.STYLE_NO_FRAME; break;
//            case 8: style = DialogFragment.STYLE_NORMAL; break;
//        }
//        switch ((mNum-1)%6) {
//            case 4: theme = android.R.style.Theme_Holo; break;
//            case 5: theme = android.R.style.Theme_Holo_Light_Dialog; break;
//            case 6: theme = android.R.style.Theme_Holo_Light; break;
//            case 7: theme = android.R.style.Theme_Holo_Light_Panel; break;
//            case 8: theme = android.R.style.Theme_Holo_Light; break;
//        }
//        setStyle(style, theme);
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dialog, container, false);
        Button save = v.findViewById(R.id.button);
        Button cancel = v.findViewById(R.id.button2);


        // Watch for button clicks.

        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // When button is clicked, call up to owning activity.

            }
        });

        return v;
    }

    //保存
    void enregistrerProduit(Produit product){

        DataBaseHelper dbh;
        ProjectDAO pjd;

        Context context =  getContext().getApplicationContext();
        dbh = DataBaseHelper.getInstance(context);//创建数据库对象，并获得数据库实例
        pjd = dbh.getProductDao();//获得DAO

        pjd.dbhEnregistrerProduit(product);
        dbh.close();




    }


}