package model;


import utils.EncrypteString;

import java.security.NoSuchAlgorithmException;

public class Professeur {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String password;

    public enum Type_cours {
        CM, TD, TP
    }

    public Professeur() {}

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean checkPassword(String passwordCompare) throws NoSuchAlgorithmException {
        return this.password.equals(EncrypteString.encode(passwordCompare));
    }

    @Override
    public String toString() {
        return "Professeur [id=" + id
                + ", nom=" + nom
                + ", prenom=" + prenom
                + ", email=" + email
                + ", password=" + password + "]";
    }

}
