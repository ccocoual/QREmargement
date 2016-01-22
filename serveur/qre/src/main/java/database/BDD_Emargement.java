package database;

import model.Emargement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BDD_Emargement {

    private static String name_table = "emargement";
    private static String join_table_groupe = "emargement_has_groupe";

    public static ArrayList<Emargement> getAll() throws SQLException {
        Connection connection = Database.getDbCon().conn;

        String query = "SELECT * FROM "+name_table;

        ArrayList<Emargement> emargementList = new ArrayList<Emargement>();
        PreparedStatement stmt = connection.prepareStatement(query);
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

    public static Emargement getById(int id) throws SQLException {
        Connection connection = Database.getDbCon().conn;

        String query = "SELECT * FROM "+name_table+" WHERE id = ?";

        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, id);
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

    public static ArrayList<Emargement> getByProfesseurId(int professeur_id) throws SQLException {
        Connection connection = Database.getDbCon().conn;

        String query = "SELECT * FROM "+name_table+" WHERE professeur_id = ?";

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

    public static ArrayList<Emargement> getByMatiereId(int matiere_id) throws SQLException {
        Connection connection = Database.getDbCon().conn;

        String query = "SELECT * FROM "+name_table+" WHERE matiere_id = ?";

        ArrayList<Emargement> emargementList = new ArrayList<Emargement>();
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, matiere_id);
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

    public static ArrayList<Emargement> getByClasseId(int classe_id) throws SQLException {
        Connection connection = Database.getDbCon().conn;

        String query = "SELECT * FROM "+name_table+" e JOIN "+join_table_groupe+" ehp ON e.id = ehp.emargement_id WHERE classe_id = ?";

        ArrayList<Emargement> emargementList = new ArrayList<Emargement>();
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, classe_id);
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

    public static boolean insert(Emargement emargement) throws SQLException {
        Connection connection = Database.getDbCon().conn;

        String query = "INSERT INTO "+name_table+" (date, url_generated, type, matiere_id, professeur_id) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setDate(1, emargement.getDate());
        stmt.setString(2, emargement.getUrl_generated());
        stmt.setString(3, emargement.getType_cours());
        stmt.setInt(4, emargement.getMatiere_id());
        stmt.setInt(5, emargement.getProfesseur_id());

        int rowsUpdated = stmt.executeUpdate();

        if(rowsUpdated > 0){
            return true;
        }

        return false;
    }

    public static boolean update(Emargement emargement) throws SQLException {
        Connection connection = Database.getDbCon().conn;

        String query = "UPDATE "+name_table+" SET date= ?, url_generated= ?, type= ?, matiere_id= ?, professeur_id= ? WHERE id= ?";

        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setDate(1, emargement.getDate());
        stmt.setString(2, emargement.getUrl_generated());
        stmt.setString(3, emargement.getType_cours());
        stmt.setInt(4, emargement.getMatiere_id());
        stmt.setInt(5, emargement.getProfesseur_id());
        stmt.setInt(6, emargement.getId());

        int rowsUpdated = stmt.executeUpdate();

        if(rowsUpdated > 0){
            return true;
        }

        return false;
    }

    public static boolean delete(int id) throws SQLException {
        Connection connection = Database.getDbCon().conn;

        String query = "DELETE FROM "+name_table+" WHERE id = ?";

        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, id);

        int rowsUpdated = stmt.executeUpdate();

        if(rowsUpdated > 0){
            return true;
        }

        return false;
    }

}