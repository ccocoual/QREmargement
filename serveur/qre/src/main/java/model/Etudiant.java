package model;

public class Etudiant {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String num_etu;
    private Groupe groupe;
    private Classe classe;

    public Etudiant() {}

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

    public String getNum_etu() {
        return num_etu;
    }

    public void setNum_etu(String num_etu) {
        this.num_etu = num_etu;
    }

    public Groupe getGroupe() {
        return groupe;
    }

    public void setGroupe(Groupe groupe) {
        this.groupe = groupe;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    @Override
    public String toString() {
        return "Etudiant [id=" + id
                + ", nom=" + nom
                + ", prenom=" + prenom
                + ", email=" + email
                + ", num_etu=" + nom
                + ", groupe=\n\t" + groupe.toString()
                + ", classe_id=\n\t" + classe.toString()
                + "]";
    }

}
