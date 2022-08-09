package canada.montreal.pierre.android2_labo3;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class GymExercice {

    @PrimaryKey( autoGenerate = true)
    @ColumnInfo(name = "geId",typeAffinity = ColumnInfo.INTEGER)
    private long id = 0;

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    private int imgId = 0;//动态加载，需要使用整数R.drawable.xxx这个ID,数据库存的是文件名，加载的时候使用的是这个imgId

    @ColumnInfo(name = "gymCreatorId",typeAffinity = ColumnInfo.INTEGER)
    private long gymId = 0;

    public long getGymId() {
        return gymId;
    }

    public void setGymId(long gymId) {
        this.gymId = gymId;
    }

    @ColumnInfo(name = "title",typeAffinity = ColumnInfo.TEXT)
    private String title;

    @ColumnInfo(name = "img",typeAffinity = ColumnInfo.TEXT)
    private String img;//图片名，都在drawble里

    @ColumnInfo(name = "disc",typeAffinity = ColumnInfo.TEXT)
    private String disc;

    @ColumnInfo(name = "video")
    private String video;//video的在线地址

    public GymExercice(long gymId,String img,  String title, String disc, String video){
           this.gymId = gymId;
           this.title = title;
           this.img = img;
           this.disc = disc;
           this.video = video;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getJymId() {
        return gymId;
    }

    public void setJymId(long jymId) {
        this.gymId = jymId;
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

    public String getDisc() {
        return disc;
    }

    public void setDisc(String disc) {
        this.disc = disc;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }




}
