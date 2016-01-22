package database;

import model.Groupe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BDD_Groupe {


    private static String name_table = "groupe";

    public static ArrayList<Groupe> getAll() throws SQLException {
        Connection connection = Database.getDbCon().conn;

        String query = "SELECT * FROM "+name_table;

        ArrayList<Groupe> groupeList = new ArrayList<Groupe>();
        PreparedStatement stmt = connection.prepareStatement(query);
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

    public static Groupe getById(int id) throws SQLException {
        Connection connection = Database.getDbCon().conn;

        String query = "SELECT * FROM ? WHERE id = ?";

        PreparedStatement stmt = connection.prepareStatement(query);
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

    public static ArrayList<Groupe> getByClassId(int classe_id) throws SQLException {
        Connection connection = Database.getDbCon().conn;

        String query = "SELECT * FROM "+name_table+" WHERE classe_id = ?";

        ArrayList<Groupe> groupeList = new ArrayList<Groupe>();
        PreparedStatement stmt = connection.prepareStatement(query);
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

    public static boolean insert(Groupe groupe) throws SQLException {
        Connection connection = Database.getDbCon().conn;

        String query = "INSERT INTO "+name_table+" (libelle, classe_id) VALUES (?, ?)";

        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, groupe.getLibelle());
        stmt.setInt(2, groupe.getClasse_id());

        int rowsUpdated = stmt.executeUpdate();

        if(rowsUpdated > 0){
            return true;
        }

        return false;
    }

    public static boolean update(Groupe groupe) throws SQLException {
        Connection connection = Database.getDbCon().conn;

        String query = "UPDATE "+name_table+" SET libelle= ?, classe_id= ? WHERE id= ?";

        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, groupe.getLibelle());
        stmt.setInt(2, groupe.getClasse_id());
        stmt.setInt(3, groupe.getId());

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
}