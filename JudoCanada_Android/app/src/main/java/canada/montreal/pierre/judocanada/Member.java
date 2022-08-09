package canada.montreal.pierre.judocanada;

import android.os.Parcel;
import android.os.Parcelable;

public class Member implements Parcelable {

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getMemberNum() {
        return memberNum;
    }

    public void setMemberNum(String memberNum) {
        this.memberNum = memberNum;
    }

    public String getSeasonYear() {
        return seasonYear;
    }

    public void setSeasonYear(String seasonYear) {
        this.seasonYear = seasonYear;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getBeltLevel() {
        return beltLevel;
    }

    public void setBeltLevel(String beltLevel) {
        this.beltLevel = beltLevel;
    }
    String firstName;
    String lastName;
    String birthDate;
    String memberNum;
    String seasonYear;
    String clubName;
    String branch;
    String beltLevel;

    @Override

    public int describeContents() {

        return 0;

    }

    @Override

    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(firstName);//写出name

        dest.writeString(lastName);//写出Year

        dest.writeString(birthDate);//写出birthDate

        dest.writeString(memberNum);//写出memberNum

        dest.writeString(seasonYear);//写出seasonYear

        dest.writeString(clubName);//写出clubName

        dest.writeString(branch);//branch

        dest.writeString(beltLevel);//beltLevel

    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){

        @Override
        public Member createFromParcel(Parcel source) {

            Member member = new Member();

            member.firstName = source.readString();//读取name,顺序一致

            member.lastName = source.readString();//读取name,顺序一致

            member.birthDate = source.readString();//读取age

            member.memberNum = source.readString();//读取name,顺序一致

            member.seasonYear = source.readString();//读取age

            member.clubName = source.readString();

            member.branch = source.readString();//读取name,顺序一致

            member.beltLevel = source.readString();//读取age

            return member;

        }

        @Override

        public Film[] newArray(int size) {

            return new Film[size];

        }

    };
}
