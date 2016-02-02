package database;

import model.Matiere;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BDD_Matiere {


    private static String name_table = "matiere";

    public static ArrayList<Matiere> getAll() throws SQLException {
        Connection connection = Database.getDbCon().conn;

        String query = "SELECT * FROM "+name_table;

        ArrayList<Matiere> matiereList = new ArrayList<Matiere>();
        PreparedStatement stmt = connection.prepareStatement(query);
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

    public static Matiere getById(int id) throws SQLException {
        Connection connection = Database.getDbCon().conn;

        String query = "SELECT * FROM "+name_table+" WHERE id = ?";

        PreparedStatement stmt = connection.prepareStatement(query);
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

    public static boolean insert(Matiere matiere) throws SQLException {
        Connection connection = Database.getDbCon().conn;

        String query = "INSERT INTO "+name_table+" (libelle) VALUES (?)";

        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, matiere.getLibelle());

        int rowsUpdated = stmt.executeUpdate();

        if(rowsUpdated > 0){
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()){
                matiere.setId(generatedKeys.getInt(1));
            }
            return true;
        }

        return false;
    }

    public static boolean update(Matiere matiere) throws SQLException {
        Connection connection = Database.getDbCon().conn;

        String query = "UPDATE "+name_table+" SET libelle= ? WHERE id= ?";

        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, matiere.getLibelle());
        stmt.setInt(2, matiere.getId());

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