package database;

import model.Emargement;
import model.Professeur;
import utils.EncrypteString;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BDD_Emargement {

    public ArrayList<Emargement> getAll(Connection con) throws SQLException {
        String query = "SELECT * FROM Emargement";

        ArrayList<Emargement> emargementList = new ArrayList<Emargement>();
        PreparedStatement stmt = con.prepareStatement(query);
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

    public Emargement getById(Connection con, int id) throws SQLException {
        String query = "SELECT * FROM Emargement WHERE id="+id;

        PreparedStatement stmt = con.prepareStatement(query);
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

    public ArrayList<Emargement> getByProfesseurId(Connection con, int professeur_id) throws SQLException {
        String query = "SELECT * FROM Emargement WHERE professeur_id="+professeur_id;

        ArrayList<Emargement> emargementList = new ArrayList<Emargement>();
        PreparedStatement stmt = con.prepareStatement(query);
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

    public ArrayList<Emargement> getByMatiereId(Connection con, int matiere_id) throws SQLException {
        String query = "SELECT * FROM Emargement WHERE matiere_id="+matiere_id;

        ArrayList<Emargement> emargementList = new ArrayList<Emargement>();
        PreparedStatement stmt = con.prepareStatement(query);
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

    public ArrayList<Emargement> getByClasseId(Connection con, int classe_id) throws SQLException {
        String query = "SELECT * FROM Emargement e" +
                "JOIN Emargement_has_classe ehp ON e.id = ehp.emargement_id" +
                "WHERE classe_id="+classe_id;

        ArrayList<Emargement> emargementList = new ArrayList<Emargement>();
        PreparedStatement stmt = con.prepareStatement(query);
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

}