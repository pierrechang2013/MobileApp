package canada.montreal.pierre.android1_exercice9;

import java.util.ArrayList;
import java.util.List;

public class Functions {




    public static List<canada.montreal.pierre.android1_exercice9.People> getList() {

        List<canada.montreal.pierre.android1_exercice9.People> list = new ArrayList<canada.montreal.pierre.android1_exercice9.People>();

        String[] lastNames = {"Patton", "Allen", "Alice", "Alice", "Julie","Kathy", "Vivian", "Denise", "Nancy", "Mary","Emma", "Olivia", "Marilyn", "Helen", "Paula", "Elaine", "Carrie", "Deborah", "Charlotte,","Eva"};
        String[] fisrtNames = {"Emma", "Olivia", "Marilyn", "Helen", "Paula", "Elaine", "Carrie", "Deborah", "Charlotte,","Eva","Patton", "Allen", "Alice", "Alice", "Julie","Kathy", "Vivian", "Denise", "Nancy", "Mary"};

       for(int i = 0;i<lastNames.length;i++){
               canada.montreal.pierre.android1_exercice9.People p = new canada.montreal.pierre.android1_exercice9.People();
               p.setFirstName(fisrtNames[i]);
               p.setLastName(lastNames[i]);
               list.add(p);

       }

        return list;

    }
}
