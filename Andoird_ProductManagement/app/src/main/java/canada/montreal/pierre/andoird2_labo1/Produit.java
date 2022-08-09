package canada.montreal.pierre.andoird2_labo1;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class Produit {

    @PrimaryKey( autoGenerate = true)
    @ColumnInfo(name = "id",typeAffinity = ColumnInfo.INTEGER)
    private long id = 0;

    @ColumnInfo(name = "product_name",typeAffinity = ColumnInfo.TEXT)
    private String name;

    @ColumnInfo(name = "product_cate",typeAffinity = ColumnInfo.TEXT)
    private String categery;

    @ColumnInfo(name = "product_price")
    private float price;

    @ColumnInfo(name = "product_quantity",typeAffinity = ColumnInfo.INTEGER)
    private int quantity;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategery() {
        return categery;
    }

    public void setCategery(String categery) {
        this.categery = categery;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Produit(String name, String categery, float price, int quantity) {
        this.name = name;
        this.categery = categery;
        this.price = price;
        this.quantity = quantity;
    }


}
