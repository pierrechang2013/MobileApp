package canada.montreal.pierre.android2_labo3;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class GymWithExercice {

    @Embedded
    public Gym gym;

    @Relation(
            parentColumn = "gymId",
            entityColumn = "gymCreatorId"

    )

    public List<GymExercice> gymExerciceslists;
}
