package database;

import model.Emargement;
import model.Signature;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BDD_Signature {

    public ArrayList<Signature> getAll(Connection con) throws SQLException {
        String query = "SELECT * FROM Signature";

        ArrayList<Signature> signatureList = new ArrayList<Signature>();
        PreparedStatement stmt = con.prepareStatement(query);
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
        String query = "SELECT * FROM Signature WHERE id="+id;

        PreparedStatement stmt = con.prepareStatement(query);
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
        String query = "SELECT * FROM Signature WHERE emargement_id="+emargement_id;

        PreparedStatement stmt = con.prepareStatement(query);
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
        String query = "SELECT * FROM Signature WHERE etudiant_id="+etudiant_id;

        ArrayList<Signature> signatureList = new ArrayList<Signature>();
        PreparedStatement stmt = con.prepareStatement(query);
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

}