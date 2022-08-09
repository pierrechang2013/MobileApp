package canada.montreal.pierre.apptexescalc;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class ViewMode extends ViewModel {

    private MutableLiveData<String> priceStr;
    private MutableLiveData<String> discountStr;
    private MutableLiveData<String> tipsStr;

    private List<Bill> list;

    public List<Bill> getList() {
        if(list==null){
            list = new ArrayList<Bill>();
        }
        return list;
    }

    public void setList(List<Bill> list) {
        this.list = list;
    }

    public MutableLiveData<String> getPriceStr() {
        if(priceStr == null){
           priceStr = new MutableLiveData<String>();
           priceStr.setValue("0");

        }
        return priceStr;
    }

    public void changePriceStr(String price) {
        this.priceStr.setValue(price);
    }

    public MutableLiveData<String> getDiscountStr() {
        if(this.discountStr == null){
            discountStr = new MutableLiveData<String>();
            discountStr.setValue("0");

        }
        return discountStr;
    }

    public void changediscountStr(String discountStr) {
          this.discountStr.setValue(discountStr);

    }

    public MutableLiveData<String> getTipsStr() {
        if(this.tipsStr == null){
            tipsStr = new MutableLiveData<String>();
            tipsStr.setValue("0");

        }
        return tipsStr;
    }

    public void changeTipsStr(String tipsStr) {
        this.tipsStr.setValue(tipsStr);
    }
}
