package canada.montreal.pierre.android2_labo3;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class Gym {

    @PrimaryKey( autoGenerate = true)
    @ColumnInfo(name = "gymId",typeAffinity = ColumnInfo.INTEGER)
    private long id = 0;

    @ColumnInfo(name = "title",typeAffinity = ColumnInfo.TEXT)
    private String title;

    @ColumnInfo(name = "img",typeAffinity = ColumnInfo.TEXT)
    private String img;

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    private int imgId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Gym(String img, String title) {

        this.img = img;
        this.title = title;

    }


}
