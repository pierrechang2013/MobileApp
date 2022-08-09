package canada.montreal.pierre.andoird2_exam1;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class Employes {

    @PrimaryKey( autoGenerate = true)
    @ColumnInfo(name = "id",typeAffinity = ColumnInfo.INTEGER)
    private long id = 0;

    @ColumnInfo(name = "nom",typeAffinity = ColumnInfo.TEXT)
    private String nom;

    @ColumnInfo(name = "prenom",typeAffinity = ColumnInfo.TEXT)
    private String prenom;

    @ColumnInfo(name = "sexe")
    private String sexe;

    @ColumnInfo(name = "titre",typeAffinity = ColumnInfo.TEXT)
    private String titre;

    @ColumnInfo(name = "salaire",typeAffinity = ColumnInfo.INTEGER)
    private int salaire;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public int getSalaire() {
        return salaire;
    }

    public void setSalaire(int salaire) {
        this.salaire = salaire;
    }

    public Employes(String nom, String prenom, String sexe,String titre,int salaire) {
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.titre = titre;
        this.salaire = salaire;
    }

    public Employes() {

    }


}
