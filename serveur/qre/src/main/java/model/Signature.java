package model;


import java.sql.Date;

public class Signature {
    private int emargement_id;
    private int etudiant_id;
    private Date date;
    private boolean signee;

    public Signature() {}

    public Signature(int emargement_id, int etudiant_id, Date date, boolean signee) {
        super();
        this.emargement_id = emargement_id;
        this.etudiant_id = etudiant_id;
        this.date = date;
        this.signee = signee;
    }

    public int getEmargement_id() {
        return emargement_id;
    }

    public void setEmargement_id(int emargement_id) {
        this.emargement_id = emargement_id;
    }

    public int getEtudiant_id() {
        return etudiant_id;
    }

    public void setEtudiant_id(int etudiant_id) {
        this.etudiant_id = etudiant_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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
        return "Signature [emargement_id=" + emargement_id
                + ", etudiant_id=" + etudiant_id
                + ", date=" + date
                + ", signe=" + signee + "]";
    }

}
