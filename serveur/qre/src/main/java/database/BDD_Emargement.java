package database;

import model.Classe;
import model.Emargement;
import model.Professeur;
import utils.EncrypteString;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BDD_Emargement {

    private static String name_table = "Emargement";

    public static ArrayList<Emargement> getAll(Connection con) throws SQLException {
        String query = "SELECT * FROM ?";

        ArrayList<Emargement> emargementList = new ArrayList<Emargement>();
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, name_table);
        ResultSet rs = stmt.executeQuery();
        try {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return emargementList;
    }

    public static Emargement getById(Connection con, int id) throws SQLException {
        String query = "SELECT * FROM ? WHERE id = ?";

        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, name_table);
        stmt.setInt(2, id);
        ResultSet rs = stmt.executeQuery();
        rs.first();

        Emargement emargement = new Emargement();
        emargement.setId(rs.getInt("id"));
        emargement.setDate(rs.getDate("date"));
        emargement.setUrl_generated(rs.getString("url_generated"));
        emargement.setType_cours(rs.getString("type_cours"));
        emargement.setMatiere_id(rs.getInt("matiere_id"));
        emargement.setProfesseur_id(rs.getInt("professeur_id"));

        return emargement;
    }

    public static ArrayList<Emargement> getByProfesseurId(Connection con, int professeur_id) throws SQLException {
        String query = "SELECT * FROM ? WHERE professeur_id = ?";

        ArrayList<Emargement> emargementList = new ArrayList<Emargement>();
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, name_table);
        stmt.setInt(2, professeur_id);
        ResultSet rs = stmt.executeQuery();

        try {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return emargementList;
    }

    public static ArrayList<Emargement> getByMatiereId(Connection con, int matiere_id) throws SQLException {
        String query = "SELECT * FROM ? WHERE matiere_id = ?";

        ArrayList<Emargement> emargementList = new ArrayList<Emargement>();
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, name_table);
        stmt.setInt(2, matiere_id);
        ResultSet rs = stmt.executeQuery();

        try {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return emargementList;
    }

    public static ArrayList<Emargement> getByClasseId(Connection con, int classe_id) throws SQLException {
        String query = "SELECT * FROM ? e JOIN Emargement_has_classe ehp ON e.id = ehp.emargement_id WHERE classe_id = ?";

        ArrayList<Emargement> emargementList = new ArrayList<Emargement>();
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, name_table);
        stmt.setInt(2, classe_id);
        ResultSet rs = stmt.executeQuery();

        try {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return emargementList;
    }

    public static boolean insert(Connection con, Emargement emargement) throws SQLException {

        boolean success = false;

        String query = "INSERT INTO ? (date, url_generated, type, matiere_id, professeur_id) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, name_table);
        stmt.setDate(2, emargement.getDate());
        stmt.setString(3, emargement.getUrl_generated());
        stmt.setString(4, emargement.getType_cours());
        stmt.setInt(5, emargement.getMatiere_id());
        stmt.setInt(6, emargement.getProfesseur_id());

        int rowsUpdated = stmt.executeUpdate();

        if(rowsUpdated > 0){
            con.commit();
            success = true;
        }

        return success;
    }

    public static boolean update(Connection con, Emargement emargement) throws SQLException {

        boolean success = false;

        String query = "UPDATE ? SET date= ?, url_generated= ?, type= ?, matiere_id= ?, professeur_id= ? WHERE id= ?";

        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, name_table);
        stmt.setDate(2, emargement.getDate());
        stmt.setString(3, emargement.getUrl_generated());
        stmt.setString(4, emargement.getType_cours());
        stmt.setInt(5, emargement.getMatiere_id());
        stmt.setInt(6, emargement.getProfesseur_id());
        stmt.setInt(7, emargement.getId());


        int rowsUpdated = stmt.executeUpdate();

        if(rowsUpdated > 0){
            con.commit();
            success = true;
        }

        return success;
    }

    public static boolean delete(Connection con, Emargement emargement) throws SQLException {

        boolean success = false;

        String query = "DELETE FROM ? WHERE id = ?";

        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, name_table);
        stmt.setInt(2, emargement.getId());

        int rowsUpdated = stmt.executeUpdate();

        if(rowsUpdated > 0){
            con.commit();
            success = true;
        }

        return success;
    }

}