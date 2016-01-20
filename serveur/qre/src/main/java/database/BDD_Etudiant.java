package database;

import model.Etudiant;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class BDD_Etudiant {

    private static String name_table = "etudiant";

    public static ArrayList<Etudiant> getAll(Connection con) throws SQLException {
        String query = "SELECT * FROM "+name_table;

        ArrayList<Etudiant> etudiantList = new ArrayList<Etudiant>();
        PreparedStatement stmt = con.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();

        while(rs.next()) {
            Etudiant etudiant = new Etudiant();
            etudiant.setId(rs.getInt("id"));
            etudiant.setNom(rs.getString("nom"));
            etudiant.setPrenom(rs.getString("prenom"));
            etudiant.setEmail(rs.getString("email"));
            etudiant.setDate_naiss(rs.getDate("date_naiss"));
            etudiant.setNum_etu(rs.getString("num_etu"));
            etudiant.setClasse_id(rs.getInt("classe_id"));
            etudiant.setGroupe_id(rs.getInt("groupe_id"));
            etudiantList.add(etudiant);
        }

        return etudiantList;
    }

    public static Etudiant getById(Connection con, int id) throws SQLException {
        String query = "SELECT * FROM "+name_table+" WHERE id = ?";

        PreparedStatement stmt = con.prepareStatement(query);
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
            etudiant.setDate_naiss(rs.getDate("date_naiss"));
            etudiant.setNum_etu(rs.getString("num_etu"));
            etudiant.setClasse_id(rs.getInt("classe_id"));
            etudiant.setGroupe_id(rs.getInt("groupe_id"));
        }

        return etudiant;
    }

    public static Etudiant getByNumEtu(Connection con, String num_etu) throws SQLException {
        String query = "SELECT * FROM "+name_table+" WHERE num_etu = ?";

        PreparedStatement stmt = con.prepareStatement(query);
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
            etudiant.setDate_naiss(rs.getDate("date_naiss"));
            etudiant.setNum_etu(rs.getString("num_etu"));
            etudiant.setClasse_id(rs.getInt("classe_id"));
            etudiant.setGroupe_id(rs.getInt("groupe_id"));
        }

        return etudiant;
    }

    public static ArrayList<Etudiant> getByClassId(Connection con, int classe_id) throws SQLException {
        String query = "SELECT * FROM "+name_table+" WHERE classe_id = ?";

        ArrayList<Etudiant> etudiantList = new ArrayList<Etudiant>();
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setInt(1, classe_id);
        ResultSet rs = stmt.executeQuery();
        while(rs.next()) {
            Etudiant etudiant = new Etudiant();
            etudiant.setId(rs.getInt("id"));
            etudiant.setNom(rs.getString("nom"));
            etudiant.setPrenom(rs.getString("prenom"));
            etudiant.setEmail(rs.getString("email"));
            etudiant.setDate_naiss(rs.getDate("date_naiss"));
            etudiant.setNum_etu(rs.getString("num_etu"));
            etudiant.setClasse_id(rs.getInt("classe_id"));
            etudiant.setGroupe_id(rs.getInt("groupe_id"));
           etudiantList.add(etudiant);
        }

        return etudiantList;
    }

    public static ArrayList<Etudiant> getByGroupeId(Connection con, int groupe_id) throws SQLException {
        String query = "SELECT * FROM "+name_table+" WHERE groupe_id = ?";

        ArrayList<Etudiant> etudiantList = new ArrayList<Etudiant>();
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setInt(1, groupe_id);
        ResultSet rs = stmt.executeQuery();
        while(rs.next()) {
            Etudiant etudiant = new Etudiant();
            etudiant.setId(rs.getInt("id"));
            etudiant.setNom(rs.getString("nom"));
            etudiant.setPrenom(rs.getString("prenom"));
            etudiant.setEmail(rs.getString("email"));
            etudiant.setDate_naiss(rs.getDate("date_naiss"));
            etudiant.setNum_etu(rs.getString("num_etu"));
            etudiant.setClasse_id(rs.getInt("classe_id"));
            etudiant.setGroupe_id(rs.getInt("groupe_id"));
            etudiantList.add(etudiant);
        }

        return etudiantList;
    }

    public static boolean insert(Connection con, Etudiant etudiant) throws SQLException {

        boolean success = false;

        String query = "INSERT INTO "+name_table+" (nom, prenom, email, date_naiss, num_etu, classe_id, groupe_id) VALUES (?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, etudiant.getNom());
        stmt.setString(2, etudiant.getPrenom());
        stmt.setString(3, etudiant.getEmail());
        stmt.setDate(4, etudiant.getDate_naiss());
        stmt.setString(5, etudiant.getNum_etu());
        stmt.setInt(6, etudiant.getClasse_id());
        stmt.setInt(7, etudiant.getGroupe_id());

        int rowsUpdated = stmt.executeUpdate();

        if(rowsUpdated > 0){
            con.commit();
            success = true;
        }

        return success;
    }

    public static boolean update(Connection con, Etudiant etudiant) throws SQLException {

        boolean success = false;

        String query = "UPDATE "+name_table+" SET nom= ?, prenom= ?, email= ?, date_naiss= ?, num_etu= ?, classe_id= ?, groupe_id= ? WHERE id= ?";

        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, etudiant.getNom());
        stmt.setString(2, etudiant.getPrenom());
        stmt.setString(3, etudiant.getEmail());
        stmt.setDate(4, etudiant.getDate_naiss());
        stmt.setString(5, etudiant.getNum_etu());
        stmt.setInt(6, etudiant.getClasse_id());
        stmt.setInt(7, etudiant.getGroupe_id());
        stmt.setInt(8, etudiant.getId());

        int rowsUpdated = stmt.executeUpdate();

        if(rowsUpdated > 0){
            con.commit();
            success = true;
        }

        return success;
    }

    public static boolean delete(Connection con, Etudiant etudiant) throws SQLException {

        boolean success = false;

        String query = "DELETE FROM "+name_table+" WHERE id = ?";

        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setInt(1, etudiant.getId());

        int rowsUpdated = stmt.executeUpdate();

        if(rowsUpdated > 0){
            con.commit();
            success = true;
        }

        return success;
    }

    public static boolean checkAuth(Connection con, String login, String password) throws SQLException, ParseException {

        // Password = date_naiss, Login = num_etu OR email

        String query = "SELECT id FROM "+name_table+" WHERE date_naiss = ? AND (email = ? OR num_etu = ?)";

        // TODO verifier format date de password
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date parsed = formatter.parse(password);
        java.sql.Date date_naiss = new Date(parsed.getTime());

        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setDate(1, date_naiss);
        stmt.setString(2, login);
        stmt.setString(3, login);
        ResultSet rs = stmt.executeQuery();

        return rs.isBeforeFirst();
    }

}