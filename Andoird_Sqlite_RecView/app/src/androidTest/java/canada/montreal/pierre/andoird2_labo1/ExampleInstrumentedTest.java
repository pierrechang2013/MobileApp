package canada.montreal.pierre.andoird2_labo1;

import android.content.Context;
import android.util.Log;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    DataBaseHelper dbh;
    ProjectDAO pjd;


    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("canada.montreal.pierre.andoird2_labo1", appContext.getPackageName());
    }

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        dbh = DataBaseHelper.getInstance(context);//创建数据库对象，并获得数据库实例
        pjd = dbh.getProductDao();//获得DAO
    }

    @After
    public void closeDb() throws IOException {
        dbh.close();//关闭
    }
    @Test
    public void  listerProduits(){


        List<Produit> list = pjd.obtenirListeProduits();

        Log.d("测试,当前list.size()",String.valueOf(list.size()));

    }

    @Test
    public void insertData(){

        Produit p1 = new Produit("Chai","Boissons",90,39);
        Produit p2 = new Produit("Chang","Boissons",95,17);
        Produit p3 = new Produit("Aniseed Syrup","Condiments",50,13);
        Produit p4 = new Produit("Chef Anton's Cajun Seasoning","Condiments",110,53);
        Produit p5 = new Produit("Chef Anton's Gumbo Mix","Condiments",106,0);
        Produit p6 = new Produit("Grandma's Boysenberry Spread","Condiments",125,120);
        Produit p7 = new Produit("Uncle Bob's Organic Dried Pears","Produits secs",150,15);


        pjd.insertProducts(p1,p2,p3,p4,p5,p6,p7);

    }

    @Test
    public void deleteData(){




        pjd.deleteSomeDate();

    }


}