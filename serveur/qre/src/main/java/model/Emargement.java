package model;


import java.sql.Date;

public class Emargement {
    private int id;
    private Date date;
    private String url_generated;
    private String type_cours;
    private int matiere_id;
    private int professeur_id;

    public enum Type_cours {
        CM, TD, TP
    }

    public Emargement() {}

    public Emargement(int id, Date date, String url_generated, Type_cours type_cours, int matiere_id) {
        super();
        this.id = id;
        this.date = date;
        this.url_generated = url_generated;
        this.type_cours = type_cours.toString();
        this.matiere_id = matiere_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUrl_generated() {
        return url_generated;
    }

    public void setUrl_generated(String url_generated) {
        this.url_generated = url_generated;
    }

    public String getType_cours() {
        return type_cours;
    }

    public void setType_cours(String type_cours) {
        this.type_cours = type_cours.toString();
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

    @Override
    public String toString() {
        return "Emargement [id=" + id
                + ", date=" + date
                + ", url_generated=" + url_generated
                + ", type_cours=" + type_cours
                + ", matiere_id=" + matiere_id
                + ", professeur_id=" + professeur_id + "]";
    }

}
