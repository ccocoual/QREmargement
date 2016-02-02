package database;

import model.Emargement;
import model.Groupe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BDD_Emargement {

    private static String name_table = "emargement";
    private static String join_table_groupe = "emargement_has_groupe";
    private static String groupe_table = "groupe";

    public static ArrayList<Emargement> getAll(int professeur_id) throws SQLException {
        Connection connection = Database.getDbCon().conn;

        String query = "SELECT * FROM "+name_table+" WHERE professeur_id= ?";

        ArrayList<Emargement> emargementList = new ArrayList<Emargement>();

        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, professeur_id);

        ResultSet rs = stmt.executeQuery();
        while(rs.next()) {
            int emargement_id = rs.getInt("id");
            query = "SELECT * FROM "+join_table_groupe+" eg JOIN "+groupe_table+" g ON eg.groupe_id=g.id WHERE emargement_id=?";
            PreparedStatement stmt_bis = connection.prepareStatement(query);
            stmt_bis.setInt(1, emargement_id);
            ResultSet rs_bit = stmt_bis.executeQuery();

            ArrayList<Groupe> groupes = new ArrayList<Groupe>();
            while(rs_bit.next()) {
                Groupe groupe = new Groupe();
                groupe.setId(rs_bit.getInt("id"));
                groupe.setLibelle(rs_bit.getString("libelle"));
                groupe.setClasse_id(rs_bit.getInt("classe_id"));
                groupes.add(groupe);
            }

            Emargement emargement = new Emargement();
            emargement.setId(emargement_id);
            emargement.setDate(rs.getTimestamp("date"));
            emargement.setType_cours(rs.getString("type_cours"));
            emargement.setMatiere_id(rs.getInt("matiere_id"));
            emargement.setProfesseur_id(rs.getInt("professeur_id"));
            emargement.setGroupes(groupes);
            emargementList.add(emargement);
        }
        return emargementList;
    }

    public static Emargement getById(int id, int professeur_id) throws SQLException {
        Connection connection = Database.getDbCon().conn;

        String query = "SELECT * FROM "+name_table+" WHERE id = ? AND professeur_id= ?";

        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, id);
        stmt.setInt(2, professeur_id);
        ResultSet rs = stmt.executeQuery();

        Emargement emargement = null;
        if (rs.isBeforeFirst()){
            rs.first();

            int emargement_id = rs.getInt("id");
            query = "SELECT * FROM "+groupe_table+" g JOIN "+join_table_groupe+" eg ON eg.groupe_id=g.id WHERE eg.emargement_id= ?";
            PreparedStatement stmt_bis = connection.prepareStatement(query);
            stmt_bis.setInt(1, emargement_id);
            ResultSet rs_bit = stmt_bis.executeQuery();

            ArrayList<Groupe> groupes = new ArrayList<Groupe>();
            while(rs_bit.next()) {
                Groupe groupe = new Groupe();
                groupe.setId(rs_bit.getInt("id"));
                groupe.setLibelle(rs_bit.getString("libelle"));
                groupe.setClasse_id(rs_bit.getInt("classe_id"));
                groupes.add(groupe);
            }

            emargement = new Emargement();
            emargement.setId(emargement_id);
            emargement.setDate(rs.getTimestamp("date"));
            emargement.setType_cours(rs.getString("type_cours"));
            emargement.setMatiere_id(rs.getInt("matiere_id"));
            emargement.setProfesseur_id(rs.getInt("professeur_id"));
            emargement.setGroupes(groupes);
        }

        return emargement;
    }

    public static ArrayList<Emargement> getByMatiereId(int matiere_id, int professeur_id) throws SQLException {
        Connection connection = Database.getDbCon().conn;

        String query = "SELECT * FROM "+name_table+" WHERE matiere_id = ? AND professeur_id=?";

        ArrayList<Emargement> emargementList = new ArrayList<Emargement>();
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, matiere_id);
        stmt.setInt(2, professeur_id);
        ResultSet rs = stmt.executeQuery();

        while(rs.next()) {

            int emargement_id = rs.getInt("id");
            query = "SELECT * FROM "+join_table_groupe+" eg JOIN "+groupe_table+" g ON eg.groupe_id=g.id WHERE emargement_id= ?";
            PreparedStatement stmt_bis = connection.prepareStatement(query);
            stmt_bis.setInt(1, emargement_id);
            ResultSet rs_bit = stmt_bis.executeQuery();

            ArrayList<Groupe> groupes = new ArrayList<Groupe>();
            while(rs_bit.next()) {
                Groupe groupe = new Groupe();
                groupe.setId(rs_bit.getInt("id"));
                groupe.setLibelle(rs_bit.getString("libelle"));
                groupe.setClasse_id(rs_bit.getInt("classe_id"));
                groupes.add(groupe);
            }

            Emargement emargement = new Emargement();
            emargement.setId(rs.getInt("id"));
            emargement.setDate(rs.getTimestamp("date"));
            emargement.setType_cours(rs.getString("type_cours"));
            emargement.setMatiere_id(rs.getInt("matiere_id"));
            emargement.setProfesseur_id(rs.getInt("professeur_id"));
            emargement.setGroupes(groupes);
            emargementList.add(emargement);
        }

        return emargementList;
    }

    public static ArrayList<Emargement> getByClasseId(int classe_id, int professeur_id) throws SQLException {
        Connection connection = Database.getDbCon().conn;

        String query = "SELECT * FROM "+name_table+" e JOIN "+join_table_groupe+" ehp ON e.id = ehp.emargement_id WHERE classe_id = ? AND professeur_id=?";

        ArrayList<Emargement> emargementList = new ArrayList<Emargement>();
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, classe_id);
        stmt.setInt(2, professeur_id);
        ResultSet rs = stmt.executeQuery();

        while(rs.next()) {

            int emargement_id = rs.getInt("id");
            query = "SELECT * FROM "+join_table_groupe+" eg JOIN "+groupe_table+" g ON eg.groupe_id=g.id WHERE emargement_id= ?";
            PreparedStatement stmt_bis = connection.prepareStatement(query);
            stmt_bis.setInt(1, emargement_id);
            ResultSet rs_bit = stmt_bis.executeQuery();

            ArrayList<Groupe> groupes = new ArrayList<Groupe>();
            while(rs_bit.next()) {
                Groupe groupe = new Groupe();
                groupe.setId(rs_bit.getInt("id"));
                groupe.setLibelle(rs_bit.getString("libelle"));
                groupe.setClasse_id(rs_bit.getInt("classe_id"));
                groupes.add(groupe);
            }

            Emargement emargement = new Emargement();
            emargement.setId(rs.getInt("Emargement.id"));
            emargement.setDate(rs.getTimestamp("date"));
            emargement.setType_cours(rs.getString("type_cours"));
            emargement.setMatiere_id(rs.getInt("matiere_id"));
            emargement.setProfesseur_id(rs.getInt("professeur_id"));
            emargement.setGroupes(groupes);
            emargementList.add(emargement);
        }

        return emargementList;
    }

    public static ArrayList<Emargement> getByGroupeId(int groupe_id, int professeur_id) throws SQLException {
        Connection connection = Database.getDbCon().conn;

        String query = "SELECT * FROM "+name_table+" e JOIN "+join_table_groupe+" ehp ON e.id = ehp.emargement_id WHERE groupe_id = ? AND professeur_id=?";

        ArrayList<Emargement> emargementList = new ArrayList<Emargement>();
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, groupe_id);
        stmt.setInt(2, professeur_id);
        ResultSet rs = stmt.executeQuery();

        while(rs.next()) {

            int emargement_id = rs.getInt("id");
            query = "SELECT * FROM "+join_table_groupe+" eg JOIN "+groupe_table+" g ON eg.groupe_id=g.id WHERE emargement_id= ?";
            PreparedStatement stmt_bis = connection.prepareStatement(query);
            stmt_bis.setInt(1, emargement_id);
            ResultSet rs_bit = stmt_bis.executeQuery();

            ArrayList<Groupe> groupes = new ArrayList<Groupe>();
            while(rs_bit.next()) {
                Groupe groupe = new Groupe();
                groupe.setId(rs_bit.getInt("id"));
                groupe.setLibelle(rs_bit.getString("libelle"));
                groupe.setClasse_id(rs_bit.getInt("classe_id"));
                groupes.add(groupe);
            }

            Emargement emargement = new Emargement();
            emargement.setId(emargement_id);
            emargement.setDate(rs.getTimestamp("date"));
            emargement.setType_cours(rs.getString("type_cours"));
            emargement.setMatiere_id(rs.getInt("matiere_id"));
            emargement.setProfesseur_id(rs.getInt("professeur_id"));
            emargement.setGroupes(groupes);
            emargementList.add(emargement);
        }

        return emargementList;
    }

    public static boolean containsGroupe(int emargement_id, int groupe_id) throws SQLException {
        Connection connection = Database.getDbCon().conn;

        String query = "SELECT * FROM "+join_table_groupe+" WHERE emargement_id=? AND groupe_id=?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, emargement_id);
        stmt.setInt(2, groupe_id);
        ResultSet rs = stmt.executeQuery();

        return rs.isBeforeFirst();
    }

    public static boolean insert(Emargement emargement, int professeur_id) throws SQLException {
        Connection connection = Database.getDbCon().conn;

        String query = "INSERT INTO "+name_table+" (date, type_cours, matiere_id, professeur_id) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setTimestamp(1, emargement.getDate());
        stmt.setString(2, emargement.getType_cours());
        stmt.setInt(3, emargement.getMatiere_id());
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

        String query = "UPDATE "+name_table+" SET date= ?, type_cours= ?, matiere_id= ? WHERE id= ? AND professeur_id=?";

        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setTimestamp(1, emargement.getDate());
        stmt.setString(2, emargement.getType_cours());
        stmt.setInt(3, emargement.getMatiere_id());
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

    public static boolean delete(int id, int professeur_id) throws SQLException {
        Connection connection = Database.getDbCon().conn;

        String query = "DELETE FROM "+name_table+" WHERE id = ? AND professeur_id=?";

        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, id);
        stmt.setInt(2, professeur_id);

        int rowsUpdated = stmt.executeUpdate();

        if(rowsUpdated > 0){
            query = "DELETE FROM "+join_table_groupe+" WHERE emargement_id = ?";
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.executeUpdate();

            return true;
        }

        return false;
    }

}