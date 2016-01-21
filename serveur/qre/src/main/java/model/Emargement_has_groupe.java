package model;

public class Emargement_has_groupe {
    private int emargement_id;
    private int matiere_id;
    private int classe_id;
    private int groupe_id;
    private int professeur_id;

    public Emargement_has_groupe() {}

    public Emargement_has_groupe(int emargement_id, int matiere_id, int classe_id, int groupe_id, int professeur_id) {
        super();
        this.emargement_id = emargement_id;
        this.matiere_id = matiere_id;
        this.classe_id = classe_id;
        this.groupe_id = groupe_id;
        this.professeur_id = professeur_id;
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

    public int getGroupe_id() {
        return groupe_id;
    }

    public void setGroupe_id(int groupe_id) {
        this.groupe_id = groupe_id;
    }

    public int getProfesseur_id() {
        return professeur_id;
    }

    public void setProfesseur_id(int professeur_id) {
        this.professeur_id = professeur_id;
    }

    @Override
    public String toString() {
        return "Emargement_has_groupe [emargement_id=" + emargement_id
                + ", matiere_id=" + matiere_id
                + ", classe_id=" + classe_id
                + ", groupe_id=" + groupe_id
                + ", professeur_id=" + professeur_id + "]";
    }

}
