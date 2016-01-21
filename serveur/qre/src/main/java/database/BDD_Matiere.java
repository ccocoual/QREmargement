package database;

import model.Groupe;
import model.Matiere;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BDD_Matiere {


    private static String name_table = "matiere";

    public static ArrayList<Matiere> getAll(Connection con) throws SQLException {
        String query = "SELECT * FROM "+name_table;

        ArrayList<Matiere> matiereList = new ArrayList<Matiere>();
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, name_table);
        ResultSet rs = stmt.executeQuery();

        while(rs.next()) {
            Matiere matiere = new Matiere();
            matiere.setId(rs.getInt("id"));
            matiere.setLibelle(rs.getString("libelle"));
            matiere.setId(rs.getInt("classe_id"));
            matiereList.add(matiere);
        }

        return matiereList;
    }

    public static Matiere getById(Connection con, int id) throws SQLException {
        String query = "SELECT * FROM ? WHERE id = ?";

        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, name_table);
        stmt.setInt(2, id);
        ResultSet rs = stmt.executeQuery();

        Matiere  matiere = null;
        if (rs.isBeforeFirst()) {
            rs.first();
            matiere = new Matiere();
            matiere.setId(rs.getInt("id"));
            matiere.setLibelle(rs.getString("libelle"));
        }

        return matiere;
    }

    public static boolean insert(Connection con, Matiere matiere) throws SQLException {

        boolean success = false;

        String query = "INSERT INTO "+name_table+" (libelle) VALUES (?)";

        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, matiere.getLibelle());

        int rowsUpdated = stmt.executeUpdate();

        if(rowsUpdated > 0){
            con.commit();
            success = true;
        }

        return success;
    }

    public static boolean update(Connection con, Matiere matiere) throws SQLException {

        boolean success = false;

        String query = "UPDATE "+name_table+" SET libelle= ? WHERE id= ?";

        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, matiere.getLibelle());
        stmt.setInt(2, matiere.getId());

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