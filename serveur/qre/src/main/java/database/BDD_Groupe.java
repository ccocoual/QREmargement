package database;

import model.Groupe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BDD_Groupe {


    private static String name_table = "groupe";

    public static ArrayList<Groupe> getAll(Connection con) throws SQLException {
        String query = "SELECT * FROM "+name_table;

        ArrayList<Groupe> groupeList = new ArrayList<Groupe>();
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, name_table);
        ResultSet rs = stmt.executeQuery();

        while(rs.next()) {
            Groupe groupe = new Groupe();
            groupe.setId(rs.getInt("id"));
            groupe.setLibelle(rs.getString("libelle"));
            groupe.setId(rs.getInt("classe_id"));
            groupeList.add(groupe);
        }

        return groupeList;
    }

    public static Groupe getById(Connection con, int id) throws SQLException {
        String query = "SELECT * FROM ? WHERE id = ?";

        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, name_table);
        stmt.setInt(2, id);
        ResultSet rs = stmt.executeQuery();

        Groupe  groupe = null;
        if (rs.isBeforeFirst()) {
            rs.first();
            groupe = new Groupe();
            groupe.setId(rs.getInt("id"));
            groupe.setLibelle(rs.getString("libelle"));
            groupe.setClasse_id(rs.getInt("classe_id"));
        }

        return groupe;
    }

    public static ArrayList<Groupe> getByClassId(Connection con, int classe_id) throws SQLException {
        String query = "SELECT * FROM "+name_table+" WHERE classe_id = ?";

        ArrayList<Groupe> groupeList = new ArrayList<Groupe>();
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setInt(1, classe_id);
        ResultSet rs = stmt.executeQuery();

        while(rs.next()) {
            Groupe groupe = new Groupe();
            groupe.setId(rs.getInt("id"));
            groupe.setLibelle(rs.getString("libelle"));
            groupe.setClasse_id(rs.getInt("classe_id"));
            groupeList.add(groupe);
        }

        return groupeList;
    }

    public static boolean insert(Connection con, Groupe groupe) throws SQLException {

        boolean success = false;

        String query = "INSERT INTO "+name_table+" (libelle, classe_id) VALUES (?, ?)";

        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, groupe.getLibelle());
        stmt.setInt(2, groupe.getClasse_id());

        int rowsUpdated = stmt.executeUpdate();

        if(rowsUpdated > 0){
            con.commit();
            success = true;
        }

        return success;
    }

    public static boolean update(Connection con, Groupe groupe) throws SQLException {

        boolean success = false;

        String query = "UPDATE "+name_table+" SET libelle= ?, classe_id= ? WHERE id= ?";

        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, groupe.getLibelle());
        stmt.setInt(2, groupe.getClasse_id());
        stmt.setInt(3, groupe.getId());

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