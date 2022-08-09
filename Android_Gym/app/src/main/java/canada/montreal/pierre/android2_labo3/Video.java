package canada.montreal.pierre.android2_labo3;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Video {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "videoId")
    private long id;

    @ColumnInfo(name = "videoCreatorId")
    private long geId;

    @ColumnInfo(name = "videoAdress",typeAffinity = ColumnInfo.TEXT)
    private String videoAdress;

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public String getQuestionAnswer() {
        return questionAnswer;
    }

    public void setQuestionAnswer(String questionAnswer) {
        this.questionAnswer = questionAnswer;
    }

    @ColumnInfo(name = "advice",typeAffinity = ColumnInfo.TEXT)
    private String advice;

    @ColumnInfo(name = "questionAnswer",typeAffinity = ColumnInfo.TEXT)
    private String questionAnswer;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getGeId() {
        return geId;
    }

    public void setGeId(long geId) {
        this.geId = geId;
    }

    public String getVideoAdress() {
        return videoAdress;
    }

    public void setVideoAdress(String videoAdress) {
        this.videoAdress = videoAdress;
    }

    public Video(long geId, String videoAdress,String advice,String questionAnswer) {
        this.geId = geId;
        this.videoAdress = videoAdress;
        this.advice = advice;
        this.questionAnswer = questionAnswer;
    }
    public Video() {

    }

}
