package model;

public class Matiere {
    private int id;
    private String libelle;

    public Matiere() {}

    public Matiere(int id, String libelle) {
        super();
        this.id = id;
        this.libelle = libelle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public String toString() {
        return "MatiereResource [id=" + id
                + ", libelle=" + libelle + "]";
    }

}
