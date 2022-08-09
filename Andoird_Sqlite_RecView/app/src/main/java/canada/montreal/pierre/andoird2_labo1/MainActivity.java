package canada.montreal.pierre.andoird2_labo1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {

    android.view.MenuItem showList;
    android.view.MenuItem showCate;
    android.view.MenuItem showTotal;







    /*
    * mFirstBt = (Button) view.findViewById(R.id.firstbt);
        mSecondBt = (Button) view.findViewById(R.id.secondbt);
        mThreadBt = (Button) view.findViewById(R.id.threadbt);
        Log.i(TAG, "按钮绑定");
        mFirstBt.setOnClickListener(new btOnclick());
        mSecondBt.setOnClickListener(new btOnclick());
        mThreadBt.setOnClickListener(new btOnclick());
        *
        * class btOnclick implements OnClickListener {


        @Override
        public void onClick(View v) {
            switch (v.getId()) {
            case R.id.firstbt:
                // 点击第一个按钮  创建第一个Fragment
                selectFM(1);
                break;
            case R.id.secondbt:
                // 点击第二个按钮  创建第二个Fragment
                selectFM(2);
                break;
            case R.id.threadbt:
                // 点击第三个按钮  创建第三个Fragment
                selectFM(3);
                break;
            default:
                break;
            }
        }

    }

    void selectFM(int i) {
        fm = getFragmentManager();
        transaction = fm.beginTransaction();
        switch (i) {
        case 1:
            FirstFragment fm_first = new FirstFragment();
            transaction.hide(this);
            transaction.add(R.id.id_content, fm_first);
            transaction.addToBackStack(null);
            Log.i("TAG", "进入port");
            break;
        case 2:
            SecondFragment fm_second = new SecondFragment();
            transaction.hide(this);
            transaction.add(R.id.id_content, fm_second);
            transaction.addToBackStack(null);
            break;
        case 3:
            ThreadFragment fm_thread = new ThreadFragment();
            transaction.hide(this);
            transaction.add(R.id.id_content, fm_thread);
            transaction.addToBackStack(null);
            break;
        default:
            break;
        }
        transaction.commit();
    }
}
    * */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //transaction.add(R.id.cateFragment, CateFragment.class, null);
        //transaction.add(R.id.listFragment, ListFragment.class, null);
        //transaction.commit();
//        getSupportFragmentManager().beginTransaction()
//                .setReorderingAllowed(true)
//                .add(R.id.cateFragment, CateFragment.class, null)
//                .commit();
//        CateFragment cf = new CateFragment();
//        getSupportFragmentManager().beginTransaction()
//                .setReorderingAllowed(true)
//                .hide(cf)
//                .commit();
////
//        getSupportFragmentManager().beginTransaction()
//                .setReorderingAllowed(true)
//                .add(R.id.listFragment, canada.montreal.pierre.andoird2_labo1.ListFragment.class, null)
//                .commit();
//        ListFragment lf = new ListFragment();
//        getSupportFragmentManager().beginTransaction()
//                .setReorderingAllowed(true)
//                .show(lf)
//                .commit();


//        transaction.add(R.id.cateFragment,CateFragment.class,null);
//        transaction.add(R.id.listFragment,lf);
//        transaction.show(lf);
//                transaction.commit();


    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {


        showList.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                ListFragment lf = new ListFragment();
                FragmentManager fm;
                FragmentTransaction transaction;
                fm = getSupportFragmentManager();
                transaction = fm.beginTransaction();
                transaction.setReorderingAllowed(true);
                transaction.replace(R.id.fragmentContainer, lf,
                        "cf");

                //transaction.show(lf);
                transaction.commitAllowingStateLoss();
                return false;
            }
        });

        showCate.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                FragmentManager fm;
                FragmentTransaction transaction;
                fm = getSupportFragmentManager();
                transaction = fm.beginTransaction();
                transaction.setReorderingAllowed(true);
                //transaction.addToBackStack(null);
                CateFragment cf = new CateFragment();
                transaction.replace(R.id.fragmentContainer, CateFragment.class, null)
                        .setReorderingAllowed(true);

                transaction.addToBackStack("listFragment"); // name can be null
                transaction.commit();




                return false;
            }
        });

        showTotal.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                FragmentManager fm;
                FragmentTransaction transaction;
                fm = getSupportFragmentManager();
                transaction = fm.beginTransaction();
                transaction.setReorderingAllowed(true);
                //transaction.addToBackStack(null);
                CateFragment cf = new CateFragment();
                transaction.replace(R.id.fragmentContainer, TotalFragment.class, null)
                        .setReorderingAllowed(true);

                transaction.addToBackStack("totalFragment"); // name can be null
                transaction.commit();




                return false;
            }
        });

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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        showList = menu.findItem(R.id.list);
        showCate = menu.findItem(R.id.cate);
        showTotal = menu.findItem(R.id.total);



        return super.onCreateOptionsMenu(menu);
    }
}