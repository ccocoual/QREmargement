package database;

import model.Etudiant;
import sun.security.provider.MD5;
import utils.EncrypteString;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BDD_Etudiant {

    public ArrayList<Etudiant> getAll(Connection con) throws SQLException {
        String query = "SELECT * FROM Etudiant";

        ArrayList<Etudiant> etudiantList = new ArrayList<Etudiant>();
        PreparedStatement stmt = con.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();
        try {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return etudiantList;
    }

    public Etudiant getById(Connection con, int id) throws SQLException {
        String query = "SELECT * FROM Etudiant WHERE id="+id;

        PreparedStatement stmt = con.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();
        rs.first();

        Etudiant etudiant = new Etudiant();
        etudiant.setId(rs.getInt("id"));
        etudiant.setNom(rs.getString("nom"));
        etudiant.setPrenom(rs.getString("prenom"));
        etudiant.setEmail(rs.getString("email"));
        etudiant.setDate_naiss(rs.getDate("date_naiss"));
        etudiant.setNum_etu(rs.getString("num_etu"));
        etudiant.setClasse_id(rs.getInt("classe_id"));
        etudiant.setGroupe_id(rs.getInt("groupe_id"));

        return etudiant;
    }

    public Etudiant getByNumEtu(Connection con, String num_etu) throws SQLException {
        String query = "SELECT * FROM Etudiant WHERE num_etu="+num_etu;

        PreparedStatement stmt = con.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();
        rs.first();

        Etudiant etudiant = new Etudiant();
        etudiant.setId(rs.getInt("id"));
        etudiant.setNom(rs.getString("nom"));
        etudiant.setPrenom(rs.getString("prenom"));
        etudiant.setEmail(rs.getString("email"));
        etudiant.setDate_naiss(rs.getDate("date_naiss"));
        etudiant.setNum_etu(rs.getString("num_etu"));
        etudiant.setClasse_id(rs.getInt("classe_id"));
        etudiant.setGroupe_id(rs.getInt("groupe_id"));

        return etudiant;
    }

    public ArrayList<Etudiant> getByClassId(Connection con, int classe_id) throws SQLException {
        String query = "SELECT * FROM Etudiant WHERE classe_id="+classe_id;

        ArrayList<Etudiant> etudiantList = new ArrayList<Etudiant>();
        PreparedStatement stmt = con.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();
        try {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return etudiantList;
    }

    public ArrayList<Etudiant> getByGroupeId(Connection con, int groupe_id) throws SQLException {
        String query = "SELECT * FROM Etudiant WHERE groupe_id="+groupe_id;

        ArrayList<Etudiant> etudiantList = new ArrayList<Etudiant>();
        PreparedStatement stmt = con.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();
        try {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return etudiantList;
    }

    public boolean checkAuth(Connection con, String login, String password) throws SQLException {
       String query = "SELECT id FROM Etudiant " +
                "WHERE date_naiss="+password +
                "AND (email="+ login + " OR num_etu=" + login + ")";

        PreparedStatement stmt = con.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();
        rs.first();

        if (!rs.isBeforeFirst() ) {
            return false;
        }

        return true;
    }

}