package canada.montreal.pierre.apptexescalc;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Bill {
    public Bill() {
    }

    public Bill(String articleName, String magasin, String total) {
        this.articleName = articleName;
        this.magasin = magasin;
        this.total = total;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    private int position;

    @PrimaryKey( autoGenerate = true)
    @ColumnInfo(name = "id",typeAffinity = ColumnInfo.INTEGER)
    private long id = 0;

    @ColumnInfo(name = "articleName",typeAffinity = ColumnInfo.TEXT)
    private String articleName;
    @ColumnInfo(name = "magasin",typeAffinity = ColumnInfo.TEXT)
    private String magasin;


    @ColumnInfo(name = "price",typeAffinity = ColumnInfo.TEXT)
    private String price = "0.00";

    @ColumnInfo(name = "discount",typeAffinity = ColumnInfo.TEXT)
    private String discount = "0";

    @ColumnInfo(name = "discountRes",typeAffinity = ColumnInfo.TEXT)
    private String discountRes;

    @ColumnInfo(name = "tpsRes",typeAffinity = ColumnInfo.TEXT)
    private String tpsRes;

    @ColumnInfo(name = "tvqRes",typeAffinity = ColumnInfo.TEXT)
    private String tvqRes;

    @ColumnInfo(name = "totalTaxRes",typeAffinity = ColumnInfo.TEXT)
    private String totalTaxRes;

    @ColumnInfo(name = "tips",typeAffinity = ColumnInfo.TEXT)
    private String tips = "0";

    @ColumnInfo(name = "tipsRes",typeAffinity = ColumnInfo.TEXT)
    private String tipsRes;

    @ColumnInfo(name = "total",typeAffinity = ColumnInfo.TEXT)
    private String total;

//    private String espaces = "          ";
//    private String espaces2 = "      ";

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public String getMagasin() {
        return magasin;
    }

    public void setMagasin(String magasin) {
        this.magasin = magasin;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getDiscountRes() {
        return discountRes;
    }

    public void setDiscountRes(String discountRes) {
        this.discountRes = discountRes;
    }

    public String getTpsRes() {
        return tpsRes;
    }

    public void setTpsRes(String tpsRes) {
        this.tpsRes = tpsRes;
    }

    public String getTvqRes() {
        return tvqRes;
    }

    public void setTvqRes(String tvqRes) {
        this.tvqRes = tvqRes;
    }

    public String getTotalTaxRes() {
        return totalTaxRes;
    }

    public void setTotalTaxRes(String totalTaxRes) {
        this.totalTaxRes = totalTaxRes;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public String getTipsRes() {
        return tipsRes;
    }

    public void setTipsRes(String tipsRes) {
        this.tipsRes = tipsRes;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

//    public String  getDetails(){
//       String details = "";
//
//       details = "Prix"+espaces+this.getPrice()+"\n"
//               +"Rebais "+this.getDiscount()+ espaces   +this.getDiscountRes()+"\n"
//               +"TPS 5% "+espaces+this.getTpsRes()+"\n"
//               +"TVQ 9,975%"+espaces+this.getTvqRes()+"\n"
//               +"Total des taxes"+espaces+this.getTotalTaxRes()+"\n"
//               +"Pourboire  "+this.getTips()+espaces+this.getPrice()+"\n";
//
//
//       return details;
//    }


}
