package model;

public class Emargement_has_classe {
    private int emargement_id;
    private int matiere_id;
    private int classe_id;

    public Emargement_has_classe() {}

    public Emargement_has_classe(int emargement_id, int matiere_id, int classe_id) {
        super();
        this.emargement_id = emargement_id;
        this.matiere_id = matiere_id;
        this.classe_id = classe_id;
    }

    public int getEmargement_id() {
        return emargement_id;
    }

    public void setEmargement_id(int emargement_id) {
        this.emargement_id = emargement_id;
    }

    public int getMatiere_id() {
        return matiere_id;
    }

    public void setMatiere_id(int matiere_id) {
        this.matiere_id = matiere_id;
    }

    public int getClasse_id() {
        return classe_id;
    }

    public void setClasse_id(int classe_id) {
        this.classe_id = classe_id;
    }

    @Override
    public String toString() {
        return "Emargement_has_classe [emargement_id=" + emargement_id
                + ", matiere_id=" + matiere_id
                + ", classe_id=" + classe_id + "]";
    }

}
