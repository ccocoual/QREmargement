package database;

import model.Emargement;
import model.Etudiant;
import model.Matiere;
import model.Signature;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BDD_Signature {

    private static String signature_table = "signature";
    private static String etudiant_table = "etudiant";
    private static String emargement_table = "emargement";
    private static String matiere_table = "matiere";

    public static ArrayList<Signature> getAll(int professeur_id) throws SQLException {
        Connection connection = Database.getDbCon().conn;

        String query = "SELECT * FROM "+ signature_table +"s " +
                "JOIN "+etudiant_table+" et ON et.id = s.etudiant_id " +
                "JOIN "+emargement_table+" em ON em.id = s.emargement_id " +
                "JOIN "+matiere_table+" m ON em.matiere_id = m.id " +
                "WHERE em.professeur_id = ?";

        ArrayList<Signature> signatureList = new ArrayList<Signature>();
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, professeur_id);
        ResultSet rs = stmt.executeQuery();

        while(rs.next()) {
            Signature signature = new Signature();
            signature.setDate(rs.getTimestamp("s.date"));
            signature.setSignee(rs.getBoolean("s.signee"));

            Etudiant etudiant = new Etudiant();
            etudiant.setId(rs.getInt("et.id"));
            etudiant.setNom(rs.getString("et.nom"));
            etudiant.setPrenom(rs.getString("et.prenom"));
            etudiant.setEmail(rs.getString("et.email"));
            etudiant.setNum_etu(rs.getString("et.num_etu"));
            signature.setEtudiant(etudiant);

            Matiere matiere = new Matiere();
            matiere.setId(rs.getInt("m.id"));
            matiere.setLibelle(rs.getString("m.libelle"));

            Emargement emargement = new Emargement();
            emargement.setId(rs.getInt("em.id"));
            emargement.setType_cours(rs.getString("em.type_cours"));
            emargement.setDate(rs.getTimestamp("em.date"));
            emargement.setMatiere(matiere);
            signature.setEmargement(emargement);

            signatureList.add(signature);
        }

        return signatureList;
    }

    public static Signature getById(int emargement_id, int etudiant_id) throws SQLException {
        Connection connection = Database.getDbCon().conn;

        String query = "SELECT * FROM "+ signature_table +" s " +
                "JOIN "+etudiant_table+" et ON et.id = s.etudiant_id " +
                "JOIN "+emargement_table+" em ON em.id = s.emargement_id " +
                "JOIN "+matiere_table+" m ON em.matiere_id = m.id " +
                "WHERE emargement_id = ? AND etudiant_id = ?";

        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, emargement_id);
        stmt.setInt(2, etudiant_id);
        ResultSet rs = stmt.executeQuery();

        Signature signature = null;
        while(rs.next()) {
            signature = new Signature();
            signature.setDate(rs.getTimestamp("s.date"));
            signature.setSignee(rs.getBoolean("s.signee"));

            Etudiant etudiant = new Etudiant();
            etudiant.setId(rs.getInt("et.id"));
            etudiant.setNom(rs.getString("et.nom"));
            etudiant.setPrenom(rs.getString("et.prenom"));
            etudiant.setEmail(rs.getString("et.email"));
            etudiant.setNum_etu(rs.getString("et.num_etu"));
            signature.setEtudiant(etudiant);

            Matiere matiere = new Matiere();
            matiere.setId(rs.getInt("m.id"));
            matiere.setLibelle(rs.getString("m.libelle"));

            Emargement emargement = new Emargement();
            emargement.setId(rs.getInt("em.id"));
            emargement.setType_cours(rs.getString("em.type_cours"));
            emargement.setDate(rs.getTimestamp("em.date"));
            emargement.setMatiere(matiere);
            signature.setEmargement(emargement);
        }

        return signature;
    }

    public static ArrayList<Signature> getByEmargementId(int emargement_id) throws SQLException {
        Connection connection = Database.getDbCon().conn;

        String query = "SELECT * FROM "+ signature_table +" s " +
                "JOIN "+etudiant_table+" et ON et.id = s.etudiant_id " +
                "JOIN "+emargement_table+" em ON em.id = s.emargement_id " +
                "JOIN "+matiere_table+" m ON em.matiere_id = m.id " +
                "WHERE emargement_id = ?";

        ArrayList<Signature> signatures = new ArrayList<Signature>();
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, emargement_id);
        ResultSet rs = stmt.executeQuery();

        Signature signature = null;
        while(rs.next()) {
            signature = new Signature();
            signature.setDate(rs.getTimestamp("s.date"));
            signature.setSignee(rs.getBoolean("s.signee"));

            Etudiant etudiant = new Etudiant();
            etudiant.setId(rs.getInt("et.id"));
            etudiant.setNom(rs.getString("et.nom"));
            etudiant.setPrenom(rs.getString("et.prenom"));
            etudiant.setEmail(rs.getString("et.email"));
            etudiant.setNum_etu(rs.getString("et.num_etu"));
            signature.setEtudiant(etudiant);

            Matiere matiere = new Matiere();
            matiere.setId(rs.getInt("m.id"));
            matiere.setLibelle(rs.getString("m.libelle"));

            Emargement emargement = new Emargement();
            emargement.setId(rs.getInt("em.id"));
            emargement.setType_cours(rs.getString("em.type_cours"));
            emargement.setDate(rs.getTimestamp("em.date"));
            emargement.setMatiere(matiere);
            signature.setEmargement(emargement);

            signatures.add(signature);
        }

        return signatures;
    }

    public static boolean update(Signature signature) throws SQLException {
        Connection connection = Database.getDbCon().conn;

        String query = "UPDATE "+ signature_table +" SET date= ?, signee= ? WHERE emargement_id= ? AND etudiant_id= ?";

        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setTimestamp(1, signature.getDate());
        stmt.setBoolean(2, signature.isSignee());
        stmt.setInt(3, signature.getEmargement().getId());
        stmt.setInt(4, signature.getEtudiant().getId());

        int rowsUpdated = stmt.executeUpdate();

        if(rowsUpdated > 0){
            return true;
        }

        return false;
    }

}