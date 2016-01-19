package model;

import java.sql.Date;

public class Etudiant {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private Date date_naiss;
    private String num_etu;
    private int groupe_id;
    private int classe_id;

    public Etudiant() {}

    public Etudiant(int id, String nom, String prenom, String email, Date date_naiss, String num_etu, int groupe_id, int classe_id) {
        super();
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.date_naiss = date_naiss;
        this.num_etu = num_etu;
        this.groupe_id = groupe_id;
        this.classe_id = classe_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDate_naiss() {
        return date_naiss;
    }

    public void setDate_naiss(Date date_naiss) {
        this.date_naiss = date_naiss;
    }

    public String getNum_etu() {
        return num_etu;
    }

    public void setNum_etu(String num_etu) {
        this.num_etu = num_etu;
    }

    public int getGroupe_id() {
        return groupe_id;
    }

    public void setGroupe_id(int groupe_id) {
        this.groupe_id = groupe_id;
    }

    public int getClasse_id() {
        return classe_id;
    }

    public void setClasse_id(int classe_id) {
        this.classe_id = classe_id;
    }

    @Override
    public String toString() {
        return "Etudiant [id=" + id
                + ", nom=" + nom
                + ", prenom=" + prenom
                + ", email=" + email
                + ", date_naiss=" + date_naiss
                + ", num_etu=" + nom
                + ", groupe_id=" + groupe_id
                + ", classe_id=" + classe_id + "]";
    }

}
