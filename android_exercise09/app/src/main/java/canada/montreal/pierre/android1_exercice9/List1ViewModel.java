package canada.montreal.pierre.android1_exercice9;

import androidx.lifecycle.ViewModel;

import java.util.List;

public class List1ViewModel extends ViewModel {

    private List<People> list;
    private List<People> list2;


    public List<People> getList2() {
        return list2;
    }

    public void setList2(List<People> list2) {
        this.list2 = list2;
    }


    public List<People> getList() {
        return list;
    }

    public void setList(List<People> list) {
        this.list = list;
    }
}
