//package canada.montreal.pierre.andoird2_exam1;
//
//import android.os.Parcel;
//import android.os.Parcelable;
//
//import java.lang.reflect.Member;
//import java.util.List;
//
//public class Salaire implements Parcelable {
//
//
//    public List<Integer> getPlusSalaire() {
//        return plusSalaire;
//    }
//
//    public void setPlusSalaire(List<Integer> plusSalaire) {
//        this.plusSalaire = plusSalaire;
//    }
//
//    public List<Integer> getEqualSalaire() {
//        return equalSalaire;
//    }
//
//    public void setEqualSalaire(List<Integer> equalSalaire) {
//        this.equalSalaire = equalSalaire;
//    }
//
//    public List<Integer> getMoinsSalaire() {
//        return moinsSalaire;
//    }
//
//    public void setMoinsSalaire(List<Integer> moinsSalaire) {
//        this.moinsSalaire = moinsSalaire;
//    }
//
//    List<Integer> plusSalaire;
//    List<Integer> equalSalaire;
//    List<Integer> moinsSalaire;
//
//
//        @Override
//
//        public int describeContents() {
//
//            return 0;
//
//        }
//
//        @Override
//
//        public void writeToParcel(Parcel dest, int flags) {
//
//            dest.writeArray(plusSalaire);//写出name
//
//            dest.writeArray(lastName);//写出Year
//
//            dest.writeString(birthDate);//写出birthDate
//
//            dest.writeString(memberNum);//写出memberNum
//
//            dest.writeString(seasonYear);//写出seasonYear
//
//            dest.writeString(clubName);//写出clubName
//
//            dest.writeString(branch);//branch
//
//            dest.writeString(beltLevel);//beltLevel
//
//        }
//
//        public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){
//
//            @Override
//            public Salaire createFromParcel(Parcel source) {
//
//                Salaire member = new Salaire();
//
//                member.firstName = source.readString();//读取name,顺序一致
//
//                member.lastName = source.readString();//读取name,顺序一致
//
//                member.birthDate = source.readString();//读取age
//
//                member.memberNum = source.readString();//读取name,顺序一致
//
//                member.seasonYear = source.readString();//读取age
//
//                member.clubName = source.readString();
//
//                member.branch = source.readString();//读取name,顺序一致
//
//                member.beltLevel = source.readString();//读取age
//
//                return member;
//
//            }
//
//            @Override
//
//            public Salaire[] newArray(int size) {
//
//                return new Salaire[size];
//
//            }
//
//        };
//}
