package canada.montreal.pierre.android2_exam2;

import static org.junit.Assert.assertEquals;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.runner.RunWith;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ListActivityInstrumentedTest {
//    DataBaseHelper dbh;
//    ProjectDAO pjd;
//
//    @Test
//    public void useAppContext() {
//        // Context of the app under test.
//        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
//        assertEquals("canada.montreal.pierre.android2_labo2", appContext.getPackageName());
//    }
//
//    @Before
//    public void createDb() {
//        Context context = ApplicationProvider.getApplicationContext();
//        dbh = DataBaseHelper.getInstance(context);//创建数据库对象，并获得数据库实例
//        pjd = dbh.getProductDao();//获得DAO
//    }
//
//    @After
//    public void closeDb() throws IOException {
//        dbh.close();//关闭
//    }
//    @Test
//    public void  listerGyms(){
//
//
//        List<Client> list = pjd.getAllGyms();
//
//        Log.d("测试,当前list.size()",String.valueOf(list.size()));
//
//    }
//
////    @Test
////    public void insertData(){
////
////        Client p1 = new Client("aaa","Boissons" );
////        Client p2 = new Client("aaa","Boissons" );
////        Client p3 = new Client("bbb","Condiments" );
////        Client p4 = new Client("bbb","sdsdfsdf" );
////        Client p5 = new Client("bbb","wrwerewrw" );
////        Client p6 = new Client("bbb","ououiououi" );
////        Client p7 = new Client("aaa","jljklkjl35" );
////        Client p8 = new Client("aaa","5435435sdfsdfsd" );
////        Client p9 = new Client("aaa","08908sdfdsf" );
////        Client p10 = new Client("aaa","zvbzxbnbmnm" );
////
////
////
////        pjd.insertGyms(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10);
////
////    }
//
//    @Test
//    public void insertDataToGymExercice(){
//
//        GymExercice ge0 = new GymExercice(1,"aaa","sdfdsfs","The matter is that3425345 the system destroys the activity when a change in the configuration occurs. See ConfigurationChanges" ,"");
//
//        GymExercice ge1 = new GymExercice(1 ,"bbb","325345","The matter is tha3535t the system destroys the activity when a change in the configuration occurs. See ConfigurationChanges" ,"");
//        GymExercice ge2 = new GymExercice(1, "bbb","iouo","The matter is 35345 the s destroys the activity when a change in the configuration occurs. See ConfigurationChanges" ,"");
//        GymExercice ge3 = new GymExercice(1,"aaa","5435","The matter is that the sdfsd destroys the 345435 a change in the configuration occurs. See ConfigurationChanges" ,"");
//        GymExercice ge4 = new GymExercice(1,"aaa","0-0==90-","The matter is that the system destrsdfsdfoys the activity 3543 a change in the configuration occurs. See ConfigurationChanges" ,"");
//        GymExercice ge5 = new GymExercice(1,"aaa","fhfghs","The matter is p[]p[]p[]p[that the system destroys the activitsdfsdfy when a change in the configuration occurs. See ConfigurationChanges" ,"");
//        GymExercice ge6= new GymExercice(2,"bbb","vbnnv","The matter 35345is that the system destroys the activity when a change in the configuration occurs. See ConfigurationChanges" ,"");
//        GymExercice ge7 = new GymExercice(2,"aaa","kl;lk;ljk;'" +
//                ";'","The matter is that the system destroys the activity when a changp]p[]e in the configuration occurs. See ConfigurationChanges" ,"");
//        GymExercice ge8 = new GymExercice(2,"bbb","234512342","The matter is that the system destroys the actp]p[]pivity whesdfsn a change in the configuration occurs. See ConfigurationChanges" ,"");
//        GymExercice ge9= new GymExercice(2,"bbb","sdfdsfs","The matter isp[]p[] that the system destroys the activity when a change in the configuration occurs. See ConfigurationChanges" ,"");
//        GymExercice ge10 = new GymExercice(2,"aaa","sdfdsfs","The matter is that the system destroys the activity when a chanp[]p[]p[]ge in the configuration occurs. See ConfigurationChanges" ,"");
//
//
//
//
//        pjd.insertGymExcercices(ge0,ge1,ge2,ge3,ge4,ge5,ge6,ge7,ge8,ge9,ge10);
//
//    }
//
////    @Test
////    public void deleteData(){
////
////
////        pjd.deleteAllGym();
////
////    }
//    @Test
//    public void deleteAllVideo() {
//
//        pjd.deleteAllVideo();
//    }
//
//    @Test
//    public void insertDataToExVideo(){
//        String advice = "Il y aura une troisième activité que sera appelée lorsque vous cliquerez sur un élément de la « ListView » de l’activité 2. Dans  " +
//                "l’activité 3 vous allez afficher un vidéo (via un url) qui montre comment faire l’exercice ainsi que plusieurs éléments de conseil  " +
//                "concernant l’exercice en question (voir site 1 pour la vidéo et les conseils). Ces informations seront dans une autre table de la  " +
//                "base de données. Vous pouvez ajouter tout ce que vous voulez au projet pour le rendre plus complet. On discutera en classe plus en détail  " +
//                "concernant ce projet.";
//
//        String questionAnswer = "En 1 lorsqu’on clique sur « Biceps » vous serez redirigez vers une autre activité qu’affichera les exercices de cette " +
//                "catégorie dans un « ListView ». Dans celui- ci vous aurez l’image de l’exercice, le titre et un brève description. Tout est dans  " +
//                "les sites donnés en haut, surtout le site 1, prenez les textes et images. Les données seront gardées dans votre base de  " +
//                "données dans les tables correspondantes. Prévoir dans cette activité un bouton flottant en bas à droite pour pouvoir  " +
//                "ajouter un nouvel exercice via un formulaire. Prévoir aussi la possibilité de supprimer un exercice ainsi que la possibilité de  " +
//                "modifier les informations d’un exercice. Ainsi dans cette activité ont implémente toutes les opérations du CRUD (Create,  " +
//                "Read, Update et Delete).";
//        Video v0 = new Video(2,"http://vfx.mtime.cn/Video/2019/03/19/mp4/190319104618910544.mp4",advice,questionAnswer);
//
//        Video v1 = new Video(2 ,"http://vfx.mtime.cn/Video/2019/03/14/mp4/190314223540373995.mp4",advice,questionAnswer);
//
//        Video v3 = new Video(3,"http://vfx.mtime.cn/Video/2019/03/19/mp4/190319104618910544.mp4",advice,questionAnswer);
//
//        Video v4 = new Video(3 ,"http://vfx.mtime.cn/Video/2019/03/14/mp4/190314223540373995.mp4",advice,questionAnswer);
//
//
//        pjd.insertExvideo(v0,v1);
//
//    }







}