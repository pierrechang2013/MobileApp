package canada.montreal.pierre.android2_labo3;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ProjectDAO {
    @Insert
    void insertGyms(Gym... gyms);

    @Insert
    long insertGymExcercice(GymExercice ge);

    @Insert
    void insertGymExcercices(GymExercice...ge);
    @Insert
    void insertExvideo(Video... videos);

    @Query("SELECT * FROM GymExercice where title = :title and img = :img")
    public List<GymExercice> getGymExercices(String title,String img);

//    @Insert
//    long insertGym(Gym... gym);
    @Update
    void updateGyms(Gym... gyms);

    @Update
    void updateGymExercices(GymExercice... gymExercices);

    @Query("delete from Gym")
    void deleteAllGym();
    @Query("delete from Video")
    void deleteAllVideo();

    @Query("delete from gymexercice where geId = :id")
    void deleteGeByid(long id);


    @Query("select * from Gym")
    List<Gym> getAllGyms();

    //这个是把Jym和JymExercice关联的数据都查出来，看这个@Transaction
    @Transaction
    @Query("SELECT * FROM Gym")
    public List<GymWithExercice> getJymWithExerciceists();



    //这个是把video和JymExercice关联的数据都查出来，看这个@Transaction
    @Transaction
    @Query("SELECT * FROM GymExercice")
    public List<ExcerciceWithVideo> getVideoWithExerciceists();




}
