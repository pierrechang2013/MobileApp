package canada.montreal.pierre.android1_exercice9;

import androidx.lifecycle.ViewModel;

import java.util.List;

public class List2ViewModel extends ViewModel {

    private List<People> list;

    public List<People> getList() {
        return list;
    }

    public void setList(List<People> list) {
        this.list = list;
    }
}
