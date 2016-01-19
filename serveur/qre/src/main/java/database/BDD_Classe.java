package database;

import model.Classe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BDD_Classe {

    private static String name_table = "Classe";

    public static ArrayList<Classe> getAll(Connection con) throws SQLException {
        String query = "SELECT * FROM ?";

        ArrayList<Classe> classeList = new ArrayList<Classe>();
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, name_table);
        ResultSet rs = stmt.executeQuery();
        try {
            while(rs.next()) {
                Classe classe = new Classe();
                classe.setId(rs.getInt("id"));
                classe.setLibelle(rs.getString("libelle"));
                classeList.add(classe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classeList;
    }


    public static boolean insert(Connection con, Classe classe) throws SQLException {

        boolean success = false;

        String query = "INSERT INTO ? (libelle) VALUES (?)";

        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, name_table);
        stmt.setString(2, classe.getLibelle());

        int rowsUpdated = stmt.executeUpdate();

        if(rowsUpdated > 0){
            con.commit();
            success = true;
        }

        return success;
    }

    public static boolean update(Connection con, Classe classe) throws SQLException {

        boolean success = false;

        String query = "UPDATE ? SET libelle = ? WHERE id = ?";

        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, name_table);
        stmt.setString(2, classe.getLibelle());
        stmt.setInt(3, classe.getId());

        int rowsUpdated = stmt.executeUpdate();

        if(rowsUpdated > 0){
            con.commit();
            success = true;
        }

        return success;
    }

    public static boolean delete(Connection con, Classe classe) throws SQLException {

        boolean success = false;

        String query = "DELETE FROM ? WHERE id = ?";

        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, name_table);
        stmt.setInt(2, classe.getId());

        int rowsUpdated = stmt.executeUpdate();

        if(rowsUpdated > 0){
            con.commit();
            success = true;
        }

        return success;
    }


}