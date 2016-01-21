package database;

import model.Emargement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BDD_Emargement {

    private static String name_table = "emargement";

    public static ArrayList<Emargement> getAll(Connection con) throws SQLException {
        String query = "SELECT * FROM "+name_table;

        ArrayList<Emargement> emargementList = new ArrayList<Emargement>();
        PreparedStatement stmt = con.prepareStatement(query);
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

    public static Emargement getById(Connection con, int id) throws SQLException {
        String query = "SELECT * FROM "+name_table+" WHERE id = ?";

        PreparedStatement stmt = con.prepareStatement(query);
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

    public static Emargement getByURL(Connection con, String url_generated) throws SQLException {
        String query = "SELECT * FROM "+name_table+" WHERE url_generated = ?";

        PreparedStatement stmt = con.prepareStatement(query);
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

    public static ArrayList<Emargement> getByProfesseurId(Connection con, int professeur_id) throws SQLException {
        String query = "SELECT * FROM "+name_table+" WHERE professeur_id = ?";

        ArrayList<Emargement> emargementList = new ArrayList<Emargement>();
        PreparedStatement stmt = con.prepareStatement(query);
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

    public static ArrayList<Emargement> getByMatiereId(Connection con, int matiere_id) throws SQLException {
        String query = "SELECT * FROM "+name_table+" WHERE matiere_id = ?";

        ArrayList<Emargement> emargementList = new ArrayList<Emargement>();
        PreparedStatement stmt = con.prepareStatement(query);
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

    public static ArrayList<Emargement> getByClasseId(Connection con, int classe_id) throws SQLException {
        String query = "SELECT * FROM "+name_table+" e JOIN emargement_has_classe ehp ON e.id = ehp.emargement_id WHERE classe_id = ?";

        ArrayList<Emargement> emargementList = new ArrayList<Emargement>();
        PreparedStatement stmt = con.prepareStatement(query);
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

    public static boolean insert(Connection con, Emargement emargement) throws SQLException {

        boolean success = false;

        String query = "INSERT INTO "+name_table+" (date, url_generated, type, matiere_id, professeur_id) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setDate(1, emargement.getDate());
        stmt.setString(2, emargement.getUrl_generated());
        stmt.setString(3, emargement.getType_cours());
        stmt.setInt(4, emargement.getMatiere_id());
        stmt.setInt(5, emargement.getProfesseur_id());

        int rowsUpdated = stmt.executeUpdate();

        if(rowsUpdated > 0){
            con.commit();
            success = true;
        }

        return success;
    }

    public static boolean update(Connection con, Emargement emargement) throws SQLException {

        boolean success = false;

        String query = "UPDATE "+name_table+" SET date= ?, url_generated= ?, type= ?, matiere_id= ?, professeur_id= ? WHERE id= ?";

        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setDate(1, emargement.getDate());
        stmt.setString(2, emargement.getUrl_generated());
        stmt.setString(3, emargement.getType_cours());
        stmt.setInt(4, emargement.getMatiere_id());
        stmt.setInt(5, emargement.getProfesseur_id());
        stmt.setInt(6, emargement.getId());

        int rowsUpdated = stmt.executeUpdate();

        if(rowsUpdated > 0){
            con.commit();
            success = true;
        }

        return success;
    }

    public static boolean delete(Connection con, int id) throws SQLException {

        boolean success = false;

        String query = "DELETE FROM "+name_table+" WHERE id = ?";

        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setInt(1, id);

        int rowsUpdated = stmt.executeUpdate();

        if(rowsUpdated > 0){
            con.commit();
            success = true;
        }

        return success;
    }

}