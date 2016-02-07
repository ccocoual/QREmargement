package model;


import java.sql.Timestamp;

public class Signature {
    private Emargement emargement;
    private Etudiant etudiant;
    private Timestamp date;
    private boolean signee;

    public Signature() {}

    public Emargement getEmargement() {
        return emargement;
    }

    public void setEmargement(Emargement emargement) {
        this.emargement = emargement;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public boolean isSignee() {
        return signee;
    }

    public void setSignee(boolean signee) {
        this.signee = signee;
    }

    @Override
    public String toString() {
        return "Signature [emargement=\n\t" + emargement.toString()
                + ", etudiant=\n\t" + etudiant.toString()
                + ", date=" + date
                + ", signe=" + signee + "]";
    }

}
