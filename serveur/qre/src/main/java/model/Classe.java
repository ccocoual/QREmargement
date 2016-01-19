package model;

public class Classe {
    private int id;
    private String libelle;

    public Classe() {}

    public Classe(int id, String libelle) {
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
        return "Classe [id=" + id
                + ", libelle=" + libelle + "]";
    }

}
