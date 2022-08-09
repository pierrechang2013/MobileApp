package canada.montreal.pierre.android2_labo3;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class ExcerciceWithVideo {

    @Embedded
    public GymExercice ge;

    @Relation(
            parentColumn = "geId",
            entityColumn = "videoCreatorId"

    )

    public List<Video> gymVideolists;//其实按照目前的需求是一对一，获取以后只取list中的第一个就可以了
}
