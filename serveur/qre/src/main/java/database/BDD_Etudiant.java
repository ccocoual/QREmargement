package database;

import model.Etudiant;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;

public class BDD_Etudiant {

    private static String name_table = "etudiant";

    public static ArrayList<Etudiant> getAll() throws SQLException {
        Connection connection = Database.getDbCon().conn;

        String query = "SELECT * FROM "+name_table;

        ArrayList<Etudiant> etudiantList = new ArrayList<Etudiant>();
        PreparedStatement stmt = connection.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();

        while(rs.next()) {
            Etudiant etudiant = new Etudiant();
            etudiant.setId(rs.getInt("id"));
            etudiant.setNom(rs.getString("nom"));
            etudiant.setPrenom(rs.getString("prenom"));
            etudiant.setEmail(rs.getString("email"));
            etudiant.setNum_etu(rs.getString("num_etu"));
            etudiant.setClasse_id(rs.getInt("classe_id"));
            etudiant.setGroupe_id(rs.getInt("groupe_id"));
            etudiantList.add(etudiant);
        }

        return etudiantList;
    }

    public static Etudiant getById(int id) throws SQLException {
        Connection connection = Database.getDbCon().conn;

        String query = "SELECT * FROM "+name_table+" WHERE id = ?";

        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        Etudiant etudiant = null;
        if (rs.isBeforeFirst()) {
            rs.first();
            etudiant = new Etudiant();
            etudiant.setId(rs.getInt("id"));
            etudiant.setNom(rs.getString("nom"));
            etudiant.setPrenom(rs.getString("prenom"));
            etudiant.setEmail(rs.getString("email"));
            etudiant.setNum_etu(rs.getString("num_etu"));
            etudiant.setClasse_id(rs.getInt("classe_id"));
            etudiant.setGroupe_id(rs.getInt("groupe_id"));
        }

        return etudiant;
    }

    public static Etudiant getByNumEtu(String num_etu) throws SQLException {
        Connection connection = Database.getDbCon().conn;

        String query = "SELECT * FROM "+name_table+" WHERE num_etu = ?";

        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, num_etu);

        ResultSet rs = stmt.executeQuery();

        Etudiant etudiant = null;
        if (rs.isBeforeFirst()) {
            rs.first();
            etudiant = new Etudiant();
            etudiant.setId(rs.getInt("id"));
            etudiant.setNom(rs.getString("nom"));
            etudiant.setPrenom(rs.getString("prenom"));
            etudiant.setEmail(rs.getString("email"));
            etudiant.setNum_etu(rs.getString("num_etu"));
            etudiant.setClasse_id(rs.getInt("classe_id"));
            etudiant.setGroupe_id(rs.getInt("groupe_id"));
        }

        return etudiant;
    }

    public static ArrayList<Etudiant> getByClassId(int classe_id) throws SQLException {
        Connection connection = Database.getDbCon().conn;

        String query = "SELECT * FROM "+name_table+" WHERE classe_id = ?";

        ArrayList<Etudiant> etudiantList = new ArrayList<Etudiant>();
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, classe_id);
        ResultSet rs = stmt.executeQuery();
        while(rs.next()) {
            Etudiant etudiant = new Etudiant();
            etudiant.setId(rs.getInt("id"));
            etudiant.setNom(rs.getString("nom"));
            etudiant.setPrenom(rs.getString("prenom"));
            etudiant.setEmail(rs.getString("email"));
            etudiant.setNum_etu(rs.getString("num_etu"));
            etudiant.setClasse_id(rs.getInt("classe_id"));
            etudiant.setGroupe_id(rs.getInt("groupe_id"));
           etudiantList.add(etudiant);
        }

        return etudiantList;
    }

    public static ArrayList<Etudiant> getByGroupeId(int groupe_id) throws SQLException {
        Connection connection = Database.getDbCon().conn;

        String query = "SELECT * FROM "+name_table+" WHERE groupe_id = ?";

        ArrayList<Etudiant> etudiantList = new ArrayList<Etudiant>();
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, groupe_id);
        ResultSet rs = stmt.executeQuery();
        while(rs.next()) {
            Etudiant etudiant = new Etudiant();
            etudiant.setId(rs.getInt("id"));
            etudiant.setNom(rs.getString("nom"));
            etudiant.setPrenom(rs.getString("prenom"));
            etudiant.setEmail(rs.getString("email"));
            etudiant.setNum_etu(rs.getString("num_etu"));
            etudiant.setClasse_id(rs.getInt("classe_id"));
            etudiant.setGroupe_id(rs.getInt("groupe_id"));
            etudiantList.add(etudiant);
        }

        return etudiantList;
    }

    public static boolean insert(Etudiant etudiant) throws SQLException {
        Connection connection = Database.getDbCon().conn;

        String query = "INSERT INTO "+name_table+" (nom, prenom, email, num_etu, classe_id, groupe_id) VALUES (?, ?, ?, ?, ?, ?)";

        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, etudiant.getNom());
        stmt.setString(2, etudiant.getPrenom());
        stmt.setString(3, etudiant.getEmail());
        stmt.setString(4, etudiant.getNum_etu());
        stmt.setInt(5, etudiant.getClasse_id());
        stmt.setInt(6, etudiant.getGroupe_id());

        int rowsUpdated = stmt.executeUpdate();

        if(rowsUpdated > 0){
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()){
                etudiant.setId(generatedKeys.getInt(1));
            } else {
                throw new SQLException("Creating etudiant failed, no ID obtained.");
            }
            return true;
        }

        return false;
    }

    public static boolean update(Etudiant etudiant) throws SQLException {
        Connection connection = Database.getDbCon().conn;

        String query = "UPDATE "+name_table+" SET nom= ?, prenom= ?, email= ?, num_etu= ?, classe_id= ?, groupe_id= ? WHERE id= ?";

        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, etudiant.getNom());
        stmt.setString(2, etudiant.getPrenom());
        stmt.setString(3, etudiant.getEmail());
        stmt.setString(4, etudiant.getNum_etu());
        stmt.setInt(5, etudiant.getClasse_id());
        stmt.setInt(6, etudiant.getGroupe_id());
        stmt.setInt(7, etudiant.getId());

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

    public static Etudiant checkAuth(String login, String password) throws SQLException, ParseException {
        Connection connection = Database.getDbCon().conn;

        String query = "SELECT * FROM "+name_table+" WHERE email = ? AND num_etu = ?";

        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, login);
        stmt.setString(2, password);
        ResultSet rs = stmt.executeQuery();

        Etudiant etudiant = null;
        if (rs.isBeforeFirst()) {
            rs.first();
            etudiant = new Etudiant();
            etudiant.setId(rs.getInt("id"));
            etudiant.setNom(rs.getString("nom"));
            etudiant.setPrenom(rs.getString("prenom"));
            etudiant.setEmail(rs.getString("email"));
            etudiant.setNum_etu(rs.getString("num_etu"));
            etudiant.setClasse_id(rs.getInt("classe_id"));
            etudiant.setGroupe_id(rs.getInt("groupe_id"));
        }

        return etudiant;
    }

}