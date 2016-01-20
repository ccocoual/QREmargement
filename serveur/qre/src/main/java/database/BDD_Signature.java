package database;

import model.Signature;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BDD_Signature {

    private static String name_table = "signature";

    public static ArrayList<Signature> getAll(Connection con) throws SQLException {
        String query = "SELECT * FROM "+name_table;

        ArrayList<Signature> signatureList = new ArrayList<Signature>();
        PreparedStatement stmt = con.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();

        while(rs.next()) {
            Signature signature = new Signature();
            signature.setEmargement_id(rs.getInt("emargement_id"));
            signature.setEtudiant_id(rs.getInt("etudiant_id"));
            signature.setDate(rs.getDate("date"));
            signature.setSignee(rs.getBoolean("signee"));
            signatureList.add(signature);
        }

        return signatureList;
    }

    public static Signature getById(Connection con, int emargement_id, int etudiant_id) throws SQLException {
        String query = "SELECT * FROM "+name_table+" WHERE emargement_id = ? AND etudiant_id = ?";

        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setInt(1, emargement_id);
        stmt.setInt(2, etudiant_id);
        ResultSet rs = stmt.executeQuery();

        Signature signature = null;
        if (rs.isBeforeFirst()) {
            rs.first();
            signature = new Signature();;
            signature.setEmargement_id(rs.getInt("emargement_id"));
            signature.setEtudiant_id(rs.getInt("etudiant_id"));
            signature.setDate(rs.getDate("date"));
            signature.setSignee(rs.getBoolean("signee"));
        }

        return signature;
    }

    public static ArrayList<Signature> getByEmargementId(Connection con, int emargement_id) throws SQLException {
        String query = "SELECT * FROM "+name_table+" WHERE emargement_id = ?";

        ArrayList<Signature> signatureList = new ArrayList<Signature>();
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setInt(1, emargement_id);
        ResultSet rs = stmt.executeQuery();

        while(rs.next()) {
            Signature signature = new Signature();
            signature.setEmargement_id(rs.getInt("emargement_id"));
            signature.setEtudiant_id(rs.getInt("etudiant_id"));
            signature.setDate(rs.getDate("date"));
            signature.setSignee(rs.getBoolean("signee"));
            signatureList.add(signature);
        }

        return signatureList;
    }

    public static ArrayList<Signature> getByEtudiantId(Connection con, int etudiant_id) throws SQLException {
        String query = "SELECT * FROM "+name_table+" WHERE etudiant_id = ?";

        ArrayList<Signature> signatureList = new ArrayList<Signature>();
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setInt(1, etudiant_id);
        ResultSet rs = stmt.executeQuery();

        while(rs.next()) {
            Signature signature = new Signature();
            signature.setEmargement_id(rs.getInt("emargement_id"));
            signature.setEtudiant_id(rs.getInt("etudiant_id"));
            signature.setDate(rs.getDate("date"));
            signature.setSignee(rs.getBoolean("signee"));
            signatureList.add(signature);
        }

        return signatureList;
    }

    public static boolean insert(Connection con, Signature signature) throws SQLException {

        boolean success = false;

        String query = "INSERT INTO "+name_table+" (emargement_id, etudiant_id, date, signee) VALUES (?, ?, ?, ?)";

        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setInt(1, signature.getEmargement_id());
        stmt.setInt(2, signature.getEtudiant_id());
        stmt.setDate(3, signature.getDate());
        stmt.setBoolean(4, signature.isSignee());

        int rowsUpdated = stmt.executeUpdate();

        if(rowsUpdated > 0){
            con.commit();
            success = true;
        }

        return success;
    }

    public static boolean update(Connection con, Signature signature) throws SQLException {

        boolean success = false;

        String query = "UPDATE "+name_table+" SET date= ?, signee= ? WHERE emargement_id= ? AND etudiant_id= ?";

        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setDate(1, signature.getDate());
        stmt.setBoolean(2, signature.isSignee());
        stmt.setInt(3, signature.getEmargement_id());
        stmt.setInt(4, signature.getEtudiant_id());

        int rowsUpdated = stmt.executeUpdate();

        if(rowsUpdated > 0){
            con.commit();
            success = true;
        }

        return success;
    }

    public static boolean delete(Connection con, Signature signature) throws SQLException {

        boolean success = false;

        String query = "DELETE FROM "+name_table+" WHERE emargement_id = ? AND etudiant_id = ?";

        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setInt(1, signature.getEmargement_id());
        stmt.setInt(2, signature.getEtudiant_id());

        int rowsUpdated = stmt.executeUpdate();

        if(rowsUpdated > 0){
            con.commit();
            success = true;
        }

        return success;
    }

}