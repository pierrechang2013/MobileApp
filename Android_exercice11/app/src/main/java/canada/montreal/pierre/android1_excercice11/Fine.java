package canada.montreal.pierre.android1_excercice11;

public class Fine {





    public static  int caculateFine(int baseSpeed,int actuelSpeed){



        int difference = actuelSpeed - baseSpeed;

        int quotient = difference/4;

        int remainder = difference%4;



       if(quotient==0){
           difference = 4;
       }else if(quotient==1&&remainder!=0){
           difference = 5;
       }else if(quotient>2){
           if(difference%10==0){
               difference = difference+1;
           }else {
               difference = ((difference + 4)/5)*4 + 1;//乱写的，随便吧
           }
       }

        int fine = (difference+1)*5;


         return fine;
    }

}
