package canada.montreal.pierre.android1_excercice10;

import java.util.ArrayList;
import java.util.List;

public class Form {


    private String zone;
    private boolean isSchoolOrWork;
    private String speed;
    private String firstName = "";
    private String lastName = "";



    private String name;
    private String date;
    private String amount;

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }

    private int currentPosition;

    public String getSpeed() {
        return speed;
    }

    public String getZone() {
        return zone;
    }


    public String getDate() {
        return date;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getName() {


        return    this.lastName + " " + this.firstName;
    }

    public String getAmount() {
        return amount;
    }

    public boolean getIsSchoolOrWork(){
        return this.isSchoolOrWork;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setLastName(String name) {
        this.lastName = name;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

//    public void setName(String name) {
//        this.name = name;
//    }

    public void setIsSchoolOrWork(boolean isSchoolOrWork) {

        this.isSchoolOrWork = isSchoolOrWork;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }


    public List getList(){

        List<Form> list = new ArrayList<Form>();
        Form title = new Form();
        title.name = "Nom";
        title.date = "Date";
        title.amount = "Montant";
        return list;
    }


}
