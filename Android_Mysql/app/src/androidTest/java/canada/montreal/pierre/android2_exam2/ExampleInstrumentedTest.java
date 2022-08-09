package canada.montreal.pierre.android2_exam2;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.util.Log;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    //测试，获取环境变量，并且获得对应图片的ID
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("canada.montreal.pierre.android2_labo2", appContext.getPackageName());

        ApplicationInfo appInfo = appContext.getApplicationInfo();
        int resID = appContext.getResources().getIdentifier("bbb", "drawable", appInfo.packageName);

        Log.d("bbb的ID",String.valueOf(resID));
    }



}