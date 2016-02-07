package model;

import java.util.ArrayList;

public class Groupe {
    private int id;
    private String libelle;
    private Classe classe;
    private ArrayList<Etudiant> etudiants;

    public Groupe(){
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

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public void setEtudiants(ArrayList<Etudiant> etudiants){
        this.etudiants = etudiants;
    }

    public ArrayList<Etudiant> getEtudiants(){
        return etudiants;
    }

    public void addEtudiant(Etudiant etudiant){
        if(etudiants == null) etudiants = new ArrayList<Etudiant>();
        etudiants.add(etudiant);
    }

    @Override
    public String toString() {
        String str = "Groupe [id=" + id
                + ", libelle=" + libelle
                + ", classe=\n\t" + classe.toString()
                + ", etudiants=";
        for(Etudiant etudiant : etudiants){
            str += "\t"+etudiant.toString()+"\n";
        }
        str += "]";
        return str;
    }

}
