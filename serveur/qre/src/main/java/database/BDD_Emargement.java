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

    public static ArrayList<Emargement> getAll(int professeur_id) throws SQLException {
        Connection connection = Database.getDbCon().conn;

        String query = "SELECT * FROM "+name_table+" WHERE professeur_id=?";

        ArrayList<Emargement> emargementList = new ArrayList<Emargement>();

        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, professeur_id);

        ResultSet rs = stmt.executeQuery();
        while(rs.next()) {
            Emargement emargement = new Emargement();
            emargement.setId(rs.getInt("id"));
            emargement.setDate(rs.getDate("date"));
            emargement.setUrl_generated(rs.getString("url_generated"));
            emargement.setType_cours(rs.getString("type_cours"));
            emargement.setMatiere_id(rs.getInt("matiere_id"));
            emargement.setProfesseur_id(rs.getInt("professeur_id"));
            emargementList.add(emargement);
        }
        return emargementList;
    }

    public static Emargement getById(int id, int professeur_id) throws SQLException {
        Connection connection = Database.getDbCon().conn;

        String query = "SELECT * FROM "+name_table+" WHERE id = ? AND professeur_id=?";

        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, id);
        stmt.setInt(2, professeur_id);
        ResultSet rs = stmt.executeQuery();

        Emargement emargement = null;
        if (rs.isBeforeFirst()){
            rs.first();
            emargement = new Emargement();
            emargement.setId(rs.getInt("id"));
            emargement.setDate(rs.getDate("date"));
            emargement.setUrl_generated(rs.getString("url_generated"));
            emargement.setType_cours(rs.getString("type_cours"));
            emargement.setMatiere_id(rs.getInt("matiere_id"));
            emargement.setProfesseur_id(rs.getInt("professeur_id"));
        }

        return emargement;
    }

    public static Emargement getByURL(String url_generated) throws SQLException {
        Connection connection = Database.getDbCon().conn;

        String query = "SELECT * FROM "+name_table+" WHERE url_generated = ?";

        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, url_generated);
        ResultSet rs = stmt.executeQuery();

        Emargement emargement = null;
        if (rs.isBeforeFirst()){
            rs.first();
            emargement = new Emargement();
            emargement.setId(rs.getInt("id"));
            emargement.setDate(rs.getDate("date"));
            emargement.setUrl_generated(rs.getString("url_generated"));
            emargement.setType_cours(rs.getString("type_cours"));
            emargement.setMatiere_id(rs.getInt("matiere_id"));
            emargement.setProfesseur_id(rs.getInt("professeur_id"));
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
            Emargement emargement = new Emargement();
            emargement.setId(rs.getInt("id"));
            emargement.setDate(rs.getDate("date"));
            emargement.setUrl_generated(rs.getString("url_generated"));
            emargement.setType_cours(rs.getString("type_cours"));
            emargement.setMatiere_id(rs.getInt("matiere_id"));
            emargement.setProfesseur_id(rs.getInt("professeur_id"));
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
            Emargement emargement = new Emargement();
            emargement.setId(rs.getInt("Emargement.id"));
            emargement.setDate(rs.getDate("date"));
            emargement.setUrl_generated(rs.getString("url_generated"));
            emargement.setType_cours(rs.getString("type_cours"));
            emargement.setMatiere_id(rs.getInt("matiere_id"));
            emargement.setProfesseur_id(rs.getInt("professeur_id"));
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
            Emargement emargement = new Emargement();
            emargement.setId(rs.getInt("Emargement.id"));
            emargement.setDate(rs.getDate("date"));
            emargement.setUrl_generated(rs.getString("url_generated"));
            emargement.setType_cours(rs.getString("type_cours"));
            emargement.setMatiere_id(rs.getInt("matiere_id"));
            emargement.setProfesseur_id(rs.getInt("professeur_id"));
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

        String query = "INSERT INTO "+name_table+" (date, url_generated, type_cours, matiere_id, professeur_id) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setDate(1, emargement.getDate());
        stmt.setString(2, emargement.getUrl_generated());
        stmt.setString(3, emargement.getType_cours());
        stmt.setInt(4, emargement.getMatiere_id());
        stmt.setInt(5, professeur_id);

        int rowsUpdated = stmt.executeUpdate();

        if(rowsUpdated > 0){
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()){
                emargement.setId(generatedKeys.getInt(1));
            }
            else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
            return true;
        }

        return false;
    }

    public static boolean update(Emargement emargement, int professeur_id) throws SQLException {
        Connection connection = Database.getDbCon().conn;

        String query = "UPDATE "+name_table+" SET date= ?, url_generated= ?, type_cours= ?, matiere_id= ? WHERE id= ? AND professeur_id=?";

        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setDate(1, emargement.getDate());
        stmt.setString(2, emargement.getUrl_generated());
        stmt.setString(3, emargement.getType_cours());
        stmt.setInt(4, emargement.getMatiere_id());
        stmt.setInt(5, emargement.getId());
        stmt.setInt(6, professeur_id);

        int rowsUpdated = stmt.executeUpdate();

        if(rowsUpdated > 0){
            return true;
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
            return true;
        }

        return false;
    }

}