package model;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Emargement {
    private int id;
    private Timestamp date;
    private String type_cours;
    private Matiere matiere;
    private Professeur professeur;
    private ArrayList<Groupe> groupes;
    private ArrayList<Signature> signatures;

    public enum Type_cours {
        CM, TD, TP
    }

    public Emargement() { }

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

    public Matiere getMatiere() {
        return matiere;
    }

    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }

    public Professeur getProfesseur() {
        return professeur;
    }

    public void setProfesseur(Professeur professeur) {
        this.professeur = professeur;
    }

    public void setGroupes(ArrayList<Groupe> groupes){
        this.groupes = groupes;
    }

    public ArrayList<Groupe> getGroupes(){
        return groupes;
    }

    public void addGroupe(Groupe groupe){
        if(groupes == null) groupes = new ArrayList<Groupe>();
        groupes.add(groupe);
    }

    public ArrayList<Signature> getSignatures() {
        return signatures;
    }

    public void setSignatures(ArrayList<Signature> signatures) {
        this.signatures = signatures;
    }

    public void addSignature(Signature signature){
        if(signatures == null) signatures = new ArrayList<Signature>();
        signatures.add(signature);
    }

    public boolean containsEmargement(final List<Emargement> list){
        return list.stream().filter(o -> String.valueOf(o.getId()).equals(String.valueOf(this.id))).findFirst().isPresent();
    }

    @Override
    public String toString() {
        String str = "Emargement [id=" + id
                + ", date=" + date
                + ", type_cours=" + type_cours
                + ", matiere=\n\t" + matiere.toString()
                + ", professeur=\n\t" + professeur.toString()
                + ", groupes= \n";
        for(Groupe groupe : groupes){
            str += "\t"+groupe.toString()+"\n";
        }
        str += ", signatures= \n";
        for(Signature signature : signatures){
            str += "\t"+signature.toString()+"\n";
        }
        str += "]";
        return str;
    }

}
