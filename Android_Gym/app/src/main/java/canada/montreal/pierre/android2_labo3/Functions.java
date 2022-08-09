package canada.montreal.pierre.android2_labo3;

import java.util.ArrayList;
import java.util.List;

public class Functions {

    static List<Gym>  getList(){

        List<Gym> list = new ArrayList<Gym>();
        int sss = R.drawable.aaa;
        for(int i = 0;i<10;i++){
            //2131165191
//            Gym gym = new Gym( 2131165191,"sssssss");
//            list.add(gym);
        }

          return list;
    }

    static List<GymExercice>  getList2(int imgId){

        List<GymExercice> list = new ArrayList<GymExercice>();
        String disc = "\"Améliorer votre endurance cardiovasculaire Stimuler votre système immunitaire Gérer et réduire le stress n Améliorer votre humeur Brûler des calories Réduire les risques de maladie cardiovasculaire et d’autres problèmes de santé";
        for(int i = 0;i<10;i++){
            //2131165191
            GymExercice GymExercice = new GymExercice(0,"bbb","cccccc",disc,"");
            list.add(GymExercice);
        }

        return list;
    }


}
