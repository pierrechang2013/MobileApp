package canada.montreal.pierre.android1_excercice08;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ListViewAdapt extends ArrayAdapter<People> {
    private int resourceId;

     private List<People> list;

    public ListViewAdapt(Context context, int textViewResourceId, List<People> list) {

        super(context, textViewResourceId, list);
        resourceId = textViewResourceId;
        this.list = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //根据position获取待显示的实例
        People people = getItem(position);
        //获取子视图控件实例
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        TextView lastName = (TextView) view.findViewById(R.id.textView);
        TextView firstName = (TextView) view.findViewById(R.id.textView2);
        //将 实例内的值赋值给子视图控件实例
        firstName.setText(people.getFirstName());
        lastName.setText(people.getLastName());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.remove(position);
                Log.d("LV 删除 ", "list 大小"+list.size());

                remove(people);


                notifyDataSetChanged();
            }
        });

//        AlertDialog：adapter = new MyListAdapter(this);
//        lv = (ListView) findViewById(android.R.id.list);
//        lv.setAdapter(adapter);
//
//        lv.setOnItemClickListener(new OnItemClickListener() {
//            public void onItemClick(AdapterView> a, View v, int position, long id) {
//                AlertDialog.Builder adb=new AlertDialog.Builder(MyActivity.this);
//                adb.setTitle("Delete?");
//                adb.setMessage("Are you sure you want to delete " + position);
//                final int positionToRemove = position;
//                adb.setNegativeButton("Cancel", null);
//                adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        MyDataObject.remove(positionToRemove);
//                        adapter.notifyDataSetChanged();
//                    }});
//                adb.show();
//            }
//        });

        return view;
    }
}