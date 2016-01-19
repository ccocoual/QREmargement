package database;

import model.Emargement;
import model.Etudiant;
import model.Signature;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class BDD_Signature {

    private static String name_table = "Signature";

    public ArrayList<Signature> getAll(Connection con) throws SQLException {
        String query = "SELECT * FROM ?";

        ArrayList<Signature> signatureList = new ArrayList<Signature>();
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, name_table);
        ResultSet rs = stmt.executeQuery();
        try {
            while(rs.next()) {
                Signature signature = new Signature();
                signature.setEmargement_id(rs.getInt("emargement_id"));
                signature.setEtudiant_id(rs.getInt("etudiant_id"));
                signature.setDate(rs.getDate("date"));
                signature.setSignee(rs.getBoolean("signee"));
                signatureList.add(signature);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return signatureList;
    }

    public Signature getById(Connection con, int id) throws SQLException {
        String query = "SELECT * FROM ? WHERE id = ?";

        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, name_table);
        stmt.setInt(2, id);
        ResultSet rs = stmt.executeQuery();
        rs.first();

        Signature signature = new Signature();
        signature.setEmargement_id(rs.getInt("emargement_id"));
        signature.setEtudiant_id(rs.getInt("etudiant_id"));
        signature.setDate(rs.getDate("date"));
        signature.setSignee(rs.getBoolean("signee"));

        return signature;
    }

    public Signature getByEmargementId(Connection con, int emargement_id) throws SQLException {
        String query = "SELECT * FROM ? WHERE emargement_id = ?";

        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, name_table);
        stmt.setInt(2, emargement_id);
        ResultSet rs = stmt.executeQuery();
        rs.first();

        Signature signature = new Signature();
        signature.setEmargement_id(rs.getInt("emargement_id"));
        signature.setEtudiant_id(rs.getInt("etudiant_id"));
        signature.setDate(rs.getDate("date"));
        signature.setSignee(rs.getBoolean("signee"));

        return signature;
    }

    public ArrayList<Signature> getByEtudiantId(Connection con, int etudiant_id) throws SQLException {
        String query = "SELECT * FROM ? WHERE etudiant_id = ?";

        ArrayList<Signature> signatureList = new ArrayList<Signature>();
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, name_table);
        stmt.setInt(2, etudiant_id);
        ResultSet rs = stmt.executeQuery();
        try {
            while(rs.next()) {
                Signature signature = new Signature();
                signature.setEmargement_id(rs.getInt("emargement_id"));
                signature.setEtudiant_id(rs.getInt("etudiant_id"));
                signature.setDate(rs.getDate("date"));
                signature.setSignee(rs.getBoolean("signee"));
                signatureList.add(signature);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return signatureList;
    }

    public boolean insert(Connection con, Signature signature) throws SQLException {

        boolean success = false;

        String query = "INSERT INTO ? (emargement_id, etudiant_id, date, signee) VALUES (?, ?, ?, ?)";

        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, name_table);
        stmt.setInt(2, signature.getEmargement_id());
        stmt.setInt(3, signature.getEtudiant_id());
        stmt.setDate(4, signature.getDate());
        stmt.setBoolean(5, signature.isSignee());
        int rowsUpdated = stmt.executeUpdate();

        if(rowsUpdated > 0){
            con.commit();
            success = true;
        }

        return success;
    }

    public boolean update(Connection con, Signature signature) throws SQLException {

        boolean success = false;

        String query = "UPDATE ? SET date= ?, signee= ? WHERE emargement_id= ? AND etudiant_id= ?";

        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, name_table);
        stmt.setDate(2, signature.getDate());
        stmt.setBoolean(3, signature.isSignee());
        stmt.setInt(4, signature.getEmargement_id());
        stmt.setInt(5, signature.getEtudiant_id());

        int rowsUpdated = stmt.executeUpdate();

        if(rowsUpdated > 0){
            con.commit();
            success = true;
        }

        return success;
    }

    public boolean delete(Connection con, Signature signature) throws SQLException {

        boolean success = false;

        String query = "DELETE FROM ? WHERE emargement_id = ? AND etudiant_id = ?";

        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, name_table);
        stmt.setInt(2, signature.getEmargement_id());
        stmt.setInt(2, signature.getEtudiant_id());

        int rowsUpdated = stmt.executeUpdate();

        if(rowsUpdated > 0){
            con.commit();
            success = true;
        }

        return success;
    }

}