package canada.montreal.pierre.andoird2_labo1;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ProjectDAO {
    @Insert
    void insertProducts(Produit... products);

    @Insert
    long insertProduct(Produit  products);
    @Update
    void updateProducts(Produit... products);
    @Delete
    void deleteProducts(Produit... products);

    @Query("delete from Produit")
    void deleteAllProducts();

    @Query("select * from Produit")
    List<Produit> getAllProducts();


    @Query("select * from Produit where product_cate = :category")
    List<Produit>  getProductsByCate(String category);


    @Query("select distinct product_cate from Produit ")
    List<String> getAllCategory();

    //----------
    @Query("select * from Produit")
    List<Produit> obtenirListeProduits();

    @Insert
    long dbhEnregistrerProduit(Produit produit);

    @Query("delete from Produit where id >7")
     void deleteSomeDate();



}
