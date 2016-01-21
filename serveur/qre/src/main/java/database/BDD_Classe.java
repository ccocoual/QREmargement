package database;

import model.Classe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BDD_Classe {

    private static String name_table = "classe";

    public static ArrayList<Classe> getAll() throws SQLException {
        Connection connection = Database.getDbCon().conn;

        String query = "SELECT * FROM "+name_table;

        ArrayList<Classe> classeList = new ArrayList<Classe>();
        PreparedStatement stmt = connection.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();
        while(rs.next()) {
            Classe classe = new Classe();
            classe.setId(rs.getInt("id"));
            classe.setLibelle(rs.getString("libelle"));
            classeList.add(classe);
        }
        return classeList;
    }

    public static Classe getById(int id) throws SQLException {
        Connection connection = Database.getDbCon().conn;

        String query = "SELECT * FROM "+name_table+" WHERE id = ?";

        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        Classe classe = null;
        if (rs.isBeforeFirst()){
            rs.first();
            classe = new Classe();
            classe.setId(rs.getInt("id"));
            classe.setLibelle(rs.getString("libelle"));
        }

        return classe;
    }

    public static ArrayList<Classe> getByEmargementId(int emargement_id) throws SQLException {
        Connection connection = Database.getDbCon().conn;

        String query = "SELECT * FROM "+name_table+" c JOIN emargement_has_classe ehp ON c.id = ehp.classe_id WHERE emargement_id = ?";

        ArrayList<Classe> classeList = new ArrayList<Classe>();
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, emargement_id);
        ResultSet rs = stmt.executeQuery();
        while(rs.next()) {
            Classe classe = new Classe();
            classe.setId(rs.getInt("id"));
            classe.setLibelle(rs.getString("libelle"));
            classeList.add(classe);
        }
        return classeList;
    }


    public static boolean insert(Classe classe) throws SQLException {
        Connection connection = Database.getDbCon().conn;

        String query = "INSERT INTO "+name_table+" (libelle) VALUES (?)";

        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, classe.getLibelle());

        int rowsUpdated = stmt.executeUpdate();

        if(rowsUpdated > 0){
            connection.commit();
            return true;
        }

        return false;
    }

    public static boolean update(Classe classe) throws SQLException {
        Connection connection = Database.getDbCon().conn;

        String query = "UPDATE "+name_table+" SET libelle = ? WHERE id = ?";

        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, classe.getLibelle());
        stmt.setInt(2, classe.getId());

        int rowsUpdated = stmt.executeUpdate();

        if(rowsUpdated > 0){
            connection.commit();
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
            connection.commit();
            return true;
        }

        return false;
    }


}