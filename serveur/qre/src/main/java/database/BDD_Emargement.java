package database;

import model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BDD_Emargement {

    private static String emargement_table = "emargement";
    private static String join_table_groupe = "groupe_has_emargement";
    private static String groupe_table = "groupe";
    private static String classe_table = "groupe";
    private static String professeur_table = "professeur";
    private static String matiere_table = "matiere";
    private static String signature_table = "signature";
    private static String etudiant_table = "etudiant";

    public static ArrayList<Emargement> getAll(int professeur_id) throws SQLException {
        Connection connection = Database.getDbCon().conn;

        String query = "SELECT * FROM "+emargement_table+" e " +
                "JOIN "+matiere_table+"     m ON m.id = e.matiere_id " +
                "JOIN "+professeur_table+"  p ON p.id = e.professeur_id " +
                "JOIN "+join_table_groupe+" j ON j.emargement_id = e.id " +
                "JOIN "+groupe_table+"      g ON j.groupe_id = g.id " +
                "JOIN "+classe_table+"      c ON g.groupe_id = c.id " +
                "WHERE e.professeur_id= ?";

        ArrayList<Emargement> emargementList = new ArrayList<Emargement>();

        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, professeur_id);

        ResultSet rs = stmt.executeQuery();
        Emargement emargement = null;
        int last_id = 0;
        while(rs.next()) {
            int emargement_id = rs.getInt("e.id");
            if(emargement_id != last_id || emargement == null) {
                if(emargement != null) emargementList.add(emargement);
                emargement = new Emargement();
                emargement.setId(emargement_id);
                emargement.setDate(rs.getTimestamp("e.date"));
                emargement.setType_cours(rs.getString("e.type_cours"));

                Matiere matiere = new Matiere();
                matiere.setId(rs.getInt("m.id"));
                matiere.setLibelle(rs.getString("m.libelle"));
                emargement.setMatiere(matiere);

                Professeur professeur = new Professeur();
                professeur.setId(rs.getInt("p.id"));
                professeur.setNom(rs.getString("p.nom"));
                professeur.setPrenom(rs.getString("p.prenom"));
                emargement.setProfesseur(professeur);
            }

            Classe classe = new Classe();
            classe.setId(rs.getInt("c.id"));
            classe.setLibelle(rs.getString("c.libelle"));

            Groupe groupe = new Groupe();
            groupe.setId(rs.getInt("g.id"));
            groupe.setLibelle(rs.getString("g.libelle"));
            groupe.setClasse(classe);
            emargement.addGroupe(groupe);

            if(rs.isLast()){
                emargementList.add(emargement);
            }

            last_id = emargement_id;
        }
        return emargementList;
    }

    public static Emargement getById(int emargement_id, int professeur_id) throws SQLException {
        Connection connection = Database.getDbCon().conn;

        String query = "SELECT * FROM "+emargement_table+" e " +
                "JOIN "+matiere_table+"     m ON m.id = e.matiere_id " +
                "JOIN "+professeur_table+"  p ON p.id = e.professeur_id " +
                "JOIN "+join_table_groupe+" j ON j.emargement_id = e.id " +
                "JOIN "+groupe_table+"      g ON j.groupe_id = g.id " +
                "JOIN "+classe_table+"      c ON g.groupe_id = c.id " +
                "WHERE e.id = ? "+
                "AND e.professeur_id = ?";

        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, emargement_id);
        stmt.setInt(2, professeur_id);

        ResultSet rs = stmt.executeQuery();
        Emargement emargement = null;
        while(rs.next()) {
            if(emargement == null) {
                emargement = new Emargement();
                emargement.setId(emargement_id);
                emargement.setDate(rs.getTimestamp("e.date"));
                emargement.setType_cours(rs.getString("e.type_cours"));

                Matiere matiere = new Matiere();
                matiere.setId(rs.getInt("m.id"));
                matiere.setLibelle(rs.getString("m.libelle"));
                emargement.setMatiere(matiere);

                Professeur professeur = new Professeur();
                professeur.setId(rs.getInt("p.id"));
                professeur.setNom(rs.getString("p.nom"));
                professeur.setPrenom(rs.getString("p.prenom"));
                emargement.setProfesseur(professeur);
            }

            Classe classe = new Classe();
            classe.setId(rs.getInt("c.id"));
            classe.setLibelle(rs.getString("c.libelle"));

            Groupe groupe = new Groupe();
            groupe.setId(rs.getInt("g.id"));
            groupe.setLibelle(rs.getString("g.libelle"));
            groupe.setClasse(classe);
            emargement.addGroupe(groupe);

        }
        return emargement;
    }

    public static Emargement getSignatures(int emargement_id, int professeur_id) throws SQLException {
        Connection connection = Database.getDbCon().conn;

        String query = "SELECT * FROM "+emargement_table+" em " +
                "JOIN "+signature_table+" s ON s.emargement_id = em.id " +
                "JOIN "+etudiant_table+"  et ON s.etudiant_id = et.id " +
                "WHERE em.id = ? "+
                "AND em.professeur_id = ?";

        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, emargement_id);
        stmt.setInt(2, professeur_id);

        ResultSet rs = stmt.executeQuery();
        Emargement emargement = null;
        while(rs.next()) {
            if(emargement == null) {
                emargement = new Emargement();
                emargement.setId(emargement_id);
                emargement.setDate(rs.getTimestamp("em.date"));
                emargement.setType_cours(rs.getString("em.type_cours"));
            }

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

            emargement.addSignature(signature);
        }
        return emargement;
    }

    public static boolean insert(Emargement emargement, int professeur_id) throws SQLException {
        Connection connection = Database.getDbCon().conn;

        String query = "INSERT INTO "+ emargement_table +" (date, type_cours, matiere_id, professeur_id) VALUES (?, ?, ?, ?)";

        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setTimestamp(1, emargement.getDate());
        stmt.setString(2, emargement.getType_cours());
        stmt.setInt(3, emargement.getMatiere().getId());
        stmt.setInt(4, professeur_id);
        int rowsUpdated = stmt.executeUpdate();

        if(rowsUpdated > 0) {
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                emargement.setId(generatedKeys.getInt(1));
            } else {
                throw new SQLException("Creating emargement failed, no ID obtained.");
            }

            for (Groupe groupe : emargement.getGroupes()) {
                query = "INSERT INTO " + join_table_groupe + " (emargement_id, groupe_id) VALUES (?, ?)";

                PreparedStatement stmt_bis = connection.prepareStatement(query);
                stmt_bis.setInt(1, emargement.getId());
                stmt_bis.setInt(2, groupe.getId());
                stmt_bis.executeUpdate();
            }
            return true;
        }
        return false;
    }

    public static boolean update(Emargement emargement, int professeur_id) throws SQLException {
        Connection connection = Database.getDbCon().conn;

        String query = "UPDATE "+ emargement_table +" SET date= ?, type_cours= ?, matiere_id= ? WHERE id= ? AND professeur_id=?";

        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setTimestamp(1, emargement.getDate());
        stmt.setString(2, emargement.getType_cours());
        stmt.setInt(3, emargement.getMatiere().getId());
        stmt.setInt(4, emargement.getId());
        stmt.setInt(5, professeur_id);
        int rowsUpdated = stmt.executeUpdate();

        query = "DELETE FROM "+join_table_groupe+" WHERE emargement_id = ?";
        stmt = connection.prepareStatement(query);
        stmt.setInt(1, emargement.getId());
        stmt.executeUpdate();

        int nb_groupes = emargement.getGroupes().size();

        if(nb_groupes > 0) {
            query = "INSERT INTO " + join_table_groupe + " (emargement_id, groupe_id) VALUES ";
            for(int i = 0; i < nb_groupes; i++ ) {
                if(i == 0) {
                    query += "(?, ?)";
                } else {
                    query += ", (?, ?)";
                }
            }

            stmt = connection.prepareStatement(query);
            int i = 0, j = 0;
            while(i < nb_groupes*2){
                stmt.setInt(i+1, emargement.getId());
                stmt.setInt(i+2, emargement.getGroupes().get(j).getId());
                i += 2;
                j += 1;
            }
            int rowsUpdated_bis = stmt.executeUpdate();

            if(rowsUpdated > 0 && rowsUpdated_bis > 0){
                return true;
            }
        } else {
            if(rowsUpdated > 0){
                return true;
            }
        }
        return false;
    }

    public static boolean delete(int emargement_id, int professeur_id) throws SQLException {
        Connection connection = Database.getDbCon().conn;

        String query = "DELETE FROM "+join_table_groupe+" WHERE emargement_id = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, emargement_id);
        stmt.executeUpdate();

        query = "DELETE FROM "+ emargement_table +" WHERE id = ? AND professeur_id=?";
        stmt = connection.prepareStatement(query);
        stmt.setInt(1, emargement_id);
        stmt.setInt(2, professeur_id);
        int rowsUpdated = stmt.executeUpdate();

        if(rowsUpdated > 0){
            return true;
        }

        return false;
    }

}