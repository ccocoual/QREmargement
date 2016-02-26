package model;

import java.util.ArrayList;

public class Classe {
    private int id;
    private String libelle;
    private ArrayList<Groupe> groupes;

    public Classe() {
        groupes = new ArrayList<Groupe>();
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
        String str = "Classe [id=" + id
            + ", libelle=" + libelle
            + ", groupes= \n";
        for(Groupe groupe : groupes){
            str += "\t"+groupe.toString()+"\n";
        }
        str += "]";

        return str;
    }

}
