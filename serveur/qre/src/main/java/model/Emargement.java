package model;


import java.sql.Timestamp;
import java.util.ArrayList;

public class Emargement {
    private int id;
    private Timestamp date;
    private String type_cours;
    private String libelle_matiere;
    private int matiere_id;
    private int professeur_id;
    private ArrayList<Groupe> groupes;

    public enum Type_cours {
        CM, TD, TP
    }

    public Emargement() {
        groupes = new ArrayList<Groupe>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getDate() { return date; }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getType_cours() {
        return type_cours;
    }

    public void setType_cours(String type_cours) {
        this.type_cours = type_cours.toString();
    }

    public String getLibelle_matiere() {
        return libelle_matiere;
    }

    public void setLibelle_matiere(String libelle_matiere) {
        this.libelle_matiere = libelle_matiere;
    }

    public int getMatiere_id() {
        return matiere_id;
    }

    public void setMatiere_id(int matiere_id) {
        this.matiere_id = matiere_id;
    }

    public int getProfesseur_id() {
        return professeur_id;
    }

    public void setProfesseur_id(int professeur_id) {
        this.professeur_id = professeur_id;
    }

    public void setGroupes(ArrayList<Groupe> groupes){
        this.groupes = groupes;
    }

    public ArrayList<Groupe> getGroupes(){
        return groupes;
    }

    public void addGroupe(Groupe groupe){
        groupes.add(groupe);
    }

    @Override
    public String toString() {
        String str = "Emargement [id=" + id
                + ", date=" + date
                + ", type_cours=" + type_cours
                + ", matiere_id=" + matiere_id
                + ", professeur_id=" + professeur_id
                + ", groupes= \n";
        for(Groupe groupe : groupes){
            str += "\t"+groupe.toString()+"\n";
        }
        str += "]";
        return str;
    }

}
