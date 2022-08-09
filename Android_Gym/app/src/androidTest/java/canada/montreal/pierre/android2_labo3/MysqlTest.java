package canada.montreal.pierre.android2_labo3;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import canada.montreal.pierre.android2_labo3.mysql.DbUtil;

@RunWith(AndroidJUnit4.class)
public class MysqlTest {

    final String TAG = this.getClass().getSimpleName();
    static Connection con = null;
    PreparedStatement ps = null;

    @Before
    public void createDb() {
        con = DbUtil.getConn();


    }


    @After
    public void closeDb() throws IOException {

        DbUtil.close(ps, con);//关闭
    }

    @Test
    public void testConnection() {

        Log.d(TAG, con.toString());


    }

    @Test
    public void addGym() {
        String[] titles = {"sdfsdf", "aaaa", "cccc", "ddd", "342423", "8678", "fsdfsf", "sdfds", "ruuyuiyu", "-0=-0=", "vxczxcv", "12321", "sdfas", "sdfdsf", "sdfsdf", "sdfsdfd"};
        String[] img = {"aaa", "bbb"};

        for (int i = 0; i < titles.length; i++) {

            String str = "insert into gym(title,img) values(?,?)";
            try {
                ps = con.prepareStatement(str);
                ps.setString(1, titles[i]);
                ps.setString(2,img[i % 2]);
                ps.execute();
            } catch (Exception e) {
                e.printStackTrace();

            }


        }

    }

    @Test
    public void addGymExercices() {
        String[] titles = {"sdfsdf", "aaaa", "cccc", "ddd", "342423", "8678", "fsdfsf", "sdfds", "ruuyuiyu", "-0=-0=", "vxczxcv", "12321", "sdfas", "sdfdsf", "sdfsdf", "sdfsdfd"};
        String[] img = {"aaa", "bbb"};
        String[] discs = {"Pour décrire une personne, on utilise des adjectifs. Ces adjectifs s'accordent avec le masculin, le féminin et le pluriel. Voici une liste des principales caractéristiques pour parler du physique d'une personne, de sa taille, de la couleur de ses yeux, de sa chevelure, etc",
                "Décrire une personne, c'est parler de son physique et de son caractère. Parler du physique d'une personne, c'est indiquer ses traits physiques. Parler du caractère d'une personne, c'est indiquer sa manière d'être. Pour tout cela, il y a plusieurs adjectifs qui ont une forme masculine et une forme féminine",
                "Le visage peut être (maigre, osseux, ridé, lisse...).\n" +
                        "Sa forme (ovale, carré, arrondi...).\n" +
                        "Le teint (blanc, brun, rose, injecté de sang, bronzé, blême...)\n" +
                        "La physionomie (gaie, triste, froide, souriante...).\n" +
                        "Les cheveux (châtains, roux, ondulés, dorés, fauve, lisses, crépus, touffus...).\n" +
                        "Le front (étroit, large, bombé, aplati...)."};

        for (int i = 0; i < titles.length; i++) {

            String str = "insert into gymExercice(gymID,title,img,disc) values(?,?,?,?)";
            try {
                ps = con.prepareStatement(str);
                ps.setInt(1, 2);
                ps.setString(2, titles[i]);
                ps.setString(3,img[i % 2]);
                ps.setString(4,discs[i % 3]);
                ps.execute();
            } catch (Exception e) {
                e.printStackTrace();

            }


        }

    }

    @Test
    public void addVideo() {
        String[] videoAddress = {"http://vfx.mtime.cn/Video/2019/03/14/mp4/190314223540373995.mp4",
                "http://vfx.mtime.cn/Video/2019/03/14/mp4/190314102306987969.mp4",

                "http://vfx.mtime.cn/Video/2019/03/13/mp4/190313094901111138.mp4",
                "http://vfx.mtime.cn/Video/2019/03/12/mp4/190312143927981075.mp4"};
        String[] questionAnswers = {"Pour décrire une personne, on utilise des adjectifs. Ces adjectifs s'accordent avec le masculin, le féminin et le pluriel. Voici une liste des principales caractéristiques pour parler du physique d'une personne, de sa taille, de la couleur de ses yeux, de sa chevelure, etc",
                "Décrire une personne, c'est parler de son physique et de son caractère. Parler du physique d'une personne, c'est indiquer ses traits physiques. Parler du caractère d'une personne, c'est indiquer sa manière d'être. Pour tout cela, il y a plusieurs adjectifs qui ont une forme masculine et une forme féminine",
                "Le visage peut être (maigre, osseux, ridé, lisse...).\n" +
                        "Sa forme (ovale, carré, arrondi...).\n" +
                        "Le teint (blanc, brun, rose, injecté de sang, bronzé, blême...)\n" +
                        "La physionomie (gaie, triste, froide, souriante...).\n" +
                        "Les cheveux (châtains, roux, ondulés, dorés, fauve, lisses, crépus, touffus...).\n" +
                        "Le front (étroit, large, bombé, aplati...)."};

        for (int i = 0; i < 40; i++) {

            String str = "insert into video(geID,videoAdress,questionAnswer) values(?,?,?)";
            try {
                ps = con.prepareStatement(str);
                ps.setInt(1, i);
                ps.setString(2, videoAddress[i % 4]);
                ps.setString(3,questionAnswers[i % 2]);

                ps.execute();
            } catch (Exception e) {
                e.printStackTrace();

            }


        }

    }

    @Test
    public void getGymList(){
        DbUtil.getGymList(con);
    }
}
