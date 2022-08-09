package canada.montreal.pierre.android2_exam2;




public class Client {



    private long id = 0;


    private String lastName;


    private String firstName;

    private int age;

    private String sex;




    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Client(String lastName, String firstName, int age, String sex) {

        this.lastName = lastName;
        this.firstName = firstName;
        this.age = age;
        this.sex = sex;

    }


}
