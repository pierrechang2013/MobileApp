package canada.montreal.pierre.android1_excercice08;

import java.util.ArrayList;
import java.util.List;

public class Functions {




    public static List<People> getList() {

        List<People> list = new ArrayList<People>();

        String[] lastNames = {"Patton", "Allen", "Alice", "Alice", "Julie","Kathy", "Vivian", "Denise", "Nancy", "Mary","Emma", "Olivia", "Marilyn", "Helen", "Paula", "Elaine", "Carrie", "Deborah", "Charlotte,","Eva"};
        String[] fisrtNames = {"Emma", "Olivia", "Marilyn", "Helen", "Paula", "Elaine", "Carrie", "Deborah", "Charlotte,","Eva","Patton", "Allen", "Alice", "Alice", "Julie","Kathy", "Vivian", "Denise", "Nancy", "Mary"};

       for(int i = 0;i<lastNames.length;i++){
               People p = new People();
               p.setFirstName(fisrtNames[i]);
               p.setLastName(lastNames[i]);
               list.add(p);

       }

        return list;

    }
}
