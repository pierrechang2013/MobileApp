package canada.montreal.pierre.apptexescalc;

import static org.junit.Assert.assertEquals;

import android.content.Context;
import android.util.Log;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class TaxCalcDbTest {
    TaxCalcDataBase tcd;
    ProjectDAO pjd;

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("canada.montreal.pierre.android2_labo2", appContext.getPackageName());
    }

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        tcd = TaxCalcDataBase.getInstance(context);//创建数据库对象，并获得数据库实例
        pjd = tcd.getProductDao();//获得DAO
    }

    @After
    public void closeDb() throws IOException {
        tcd.close();//关闭
    }
    @Test
    public void  listerBills(){


        List<Bill> list = pjd.getBills();

        Log.d("测试,当前list.size()",String.valueOf(list.size()));

    }

    @Test
    public void insertData(){
        Bill p1 = new Bill("aaa1","Boissons","100" );
        Bill p2 = new Bill("aaa2","Boissons","22" );
        Bill p3 = new Bill("bbb3","Condiments","22" );
        Bill p4 = new Bill("bbb4","sdsdfsdf","22" );
        Bill p5 = new Bill("bbb5","wrwerewrw","22" );
        Bill p6 = new Bill("bbb6","ououiououi" ,"22" );
        Bill p7 = new Bill("aaa7","jljklkjl35" ,"22" );
        Bill p8 = new Bill("aaa8","5435435sdfsdfsd" ,"22" );
        Bill p9 = new Bill("aaa9","08908sdfdsf" ,"22" );
        Bill p10 = new Bill("aaa10","zvbzxbnbmnm" ,"22" );


        pjd.insertBills(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10);
        pjd.insertBills(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10);










    }



    //    @Test
//    public void deleteData(){
//
//
//        pjd.deleteAllGym();
//
//    }


    @Test
    public void deleteBillById() {

        pjd.deleteBillById(5);
    }


}