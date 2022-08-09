package canada.montreal.pierre.andoird2_exam1;

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


        List<Employes> list = pjd.obtenirListeEmployes();

        Log.d("测试,当前list.size()",String.valueOf(list.size()));

    }

    @Test
    public void insertData(){

        Employes p1 = new Employes("Lalonde","Karl","M","Ouvrier",37100);
        Employes p2 = new Employes("Savoie","Jean","M","Vendeur",31500);
        Employes p3 = new Employes("St-Pierre","Aline","F","Secritaire",22500);
        Employes p4 = new Employes("Depuis","Josee","F","Vendeur",22500);
        Employes p5 = new Employes("Bibeau","Rita","F","Administrateur",27000);
        Employes p6 = new Employes("Gingras","Marc","M","Administrateur",40500);
        Employes p7 = new Employes("Cardinal","Paul","M","Ouvrier",20000);
        Employes p8 = new Employes("Thibault","Gratien","M","Administrateur",32000);


        pjd.insertEmployes(p1,p2,p3,p4,p5,p6,p7,p8);

    }

    @Test
    public void deleteData(){




        pjd.deleteSomeDate();

    }


}