package canada.montreal.pierre.android1_excercice11;

import android.graphics.Color;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;


public class ViewModel extends androidx.lifecycle.ViewModel {

    private MutableLiveData<String> zoneLiveData,speedLiveData;
    private MutableLiveData<Boolean> isSchoolOrWorkLiveData;
    private  MutableLiveData<Integer>  colorInt;
    private  MutableLiveData<Integer> isVisible;

    private MutableLiveData<List<Form>> formList;

    public List<Form> list =   Functions.getList();

    public Form currentForm = Functions.getForm();


    private final MutableLiveData<Form> selected = new MutableLiveData<Form>();

    public void select(Form item) {

        selected.setValue(item);
    }

    public LiveData<Form> getSelected() {
        return selected;
    }


    public void setCurrentForm(Form currentForm) {
        this.currentForm = currentForm;
    }

    public MutableLiveData<List<Form>> getList() {
        //public  Form currentForm = new Form();


        if(formList==null){
            formList = new MutableLiveData<List<Form>>();
            formList.setValue(list);
        }

        return formList;
    }


    public MutableLiveData<Integer> getIsVisible() {

        if(isVisible==null){
            isVisible = new MutableLiveData<Integer>();
            isVisible.setValue(0);//没变
        }
        return isVisible;
    }

    public void setIsVisible(MutableLiveData<Integer> isVisible) {
        this.isVisible = isVisible;
    }

    public void changeVisible(Integer integer){

        isVisible.setValue(integer);
    }



    public void setColorInt(MutableLiveData<Integer> colorInt) {
        this.colorInt = colorInt;
    }

    public MutableLiveData<Integer> getColorInt() {

        if(colorInt == null){
            colorInt = new  MutableLiveData<Integer>();
            colorInt.setValue(Color.parseColor("#FF000000"));
        }
        return colorInt;
    }

    public  void changeColor(Integer integer){
        colorInt.setValue(integer);
    }






    //all the changed to color red widget




    public MutableLiveData<String> getZoneLiveData() {

        if(zoneLiveData == null){
            zoneLiveData = new MutableLiveData<String>();
            zoneLiveData.setValue("30");
        }
        return zoneLiveData;
    }

    public MutableLiveData<Boolean> getIsSchoolOrWorkLiveData() {

        if(isSchoolOrWorkLiveData == null){
            isSchoolOrWorkLiveData = new MutableLiveData<Boolean>();
            isSchoolOrWorkLiveData.setValue(false);
        }
        return isSchoolOrWorkLiveData;
    }

    public MutableLiveData<String> getSpeedLiveData() {

        if(speedLiveData == null){
            speedLiveData = new MutableLiveData<String>();
            speedLiveData.setValue("0");
        }
        return speedLiveData;
    }

    //必须有一个方法触发setValue
    public void changeZone(String str) {


        zoneLiveData.setValue(str);
    }

    public void choosSchoolOrWork(Boolean bool){
        this.isSchoolOrWorkLiveData.setValue(bool);
    }

    public void changeSpeed(String str){
        speedLiveData.setValue(str);
    }

//    public Form getCurrentForm() {
//        return currentForm;
//    }
//
//    public void setCurrentForm(Form form) {
//        this.currentForm = form;
//
//    }
}