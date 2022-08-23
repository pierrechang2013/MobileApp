package canada.montreal.pierre.apptexescalc;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ProjectDAO {

    @Insert
    void insertBills(Bill... bills);

    @Query("SELECT * FROM Bill where id = :id")
    public List<Bill> getBills(long id);

//    @Insert
//    long insertGym(Gym... gym);
    @Update
    void updateBills(Bill... bills);


//
//    @Query("delete from Bill")
//    void deleteAllGym();



    @Query("delete from Bill where id = :id")
    void deleteBillById(long id);




    @Query("select * from Bill  order by id desc")
    List<Bill> getBills();









}
