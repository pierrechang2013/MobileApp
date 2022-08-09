package canada.montreal.pierre.andoird2_exam1;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ProjectDAO {
    @Insert
    void insertEmployes(Employes... employes);
    @Query("delete from Employes where id = :id")
    void deleteEmployById(long id);

    @Query("select * from Employes where id = :id")
    Employes getEmployById(long id);


    @Query("select * from Employes where salaire >= :salaire")
    List<Employes> getEmployBySalaire(int salaire);



    @Insert
    long insertProduct(Employes products);
    @Update
    void updateProducts(Employes... products);
    @Delete
    void deleteProducts(Employes... products);

    @Query("delete from Employes")
    void deleteAllProducts();

    @Query("select * from Employes")
    List<Employes> getAllEmployes();


//    @Query("select * from Employes where product_cate = :category")
//    List<Employes>  getProductsByCate(String category);


//    @Query("select distinct product_cate from Employes ")
//    List<String> getAllCategory();

    //----------
    @Query("select * from Employes")
    List<Employes> obtenirListeEmployes();

    @Insert
    long dbhEnregistrerProduit(Employes employes);

    @Query("delete from Employes where id >7")
     void deleteSomeDate();



}
