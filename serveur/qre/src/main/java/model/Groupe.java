package model;

public class Groupe {
    private int id;
    private String libelle;
    private int classe_id;

    public Groupe() {}

    public Groupe(int id, String libelle, int classe_id) {
        super();
        this.id = id;
        this.libelle = libelle;
        this.classe_id = classe_id;
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

    public int getClasse_id() {
        return classe_id;
    }

    public void setClasse_id(int classe_id) {
        this.classe_id = classe_id;
    }

    @Override
    public String toString() {
        return "Groupe [id=" + id
                + ", libelle=" + libelle
                + ", classe_id=" + classe_id + "]";
    }

}
