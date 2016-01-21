package database;

import model.Classe;
import model.Emargement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BDD_Classe {

    private static String name_table = "classe";

    public static ArrayList<Classe> getAll(Connection con) throws SQLException {
        String query = "SELECT * FROM "+name_table;

        ArrayList<Classe> classeList = new ArrayList<Classe>();
        PreparedStatement stmt = con.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();
        while(rs.next()) {
            Classe classe = new Classe();
            classe.setId(rs.getInt("id"));
            classe.setLibelle(rs.getString("libelle"));
            classeList.add(classe);
        }
        return classeList;
    }

    public static Classe getById(Connection con, int id) throws SQLException {
        String query = "SELECT * FROM "+name_table+" WHERE id = ?";

        PreparedStatement stmt = con.prepareStatement(query);
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

    public static ArrayList<Classe> getByEmargementId(Connection con, int emargement_id) throws SQLException {
        String query = "SELECT * FROM "+name_table+" c JOIN emargement_has_classe ehp ON c.id = ehp.classe_id WHERE emargement_id = ?";

        ArrayList<Classe> classeList = new ArrayList<Classe>();
        PreparedStatement stmt = con.prepareStatement(query);
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


    public static boolean insert(Connection con, Classe classe) throws SQLException {

        boolean success = false;

        String query = "INSERT INTO "+name_table+" (libelle) VALUES (?)";

        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, classe.getLibelle());

        int rowsUpdated = stmt.executeUpdate();

        if(rowsUpdated > 0){
            con.commit();
            success = true;
        }

        return success;
    }

    public static boolean update(Connection con, Classe classe) throws SQLException {

        boolean success = false;

        String query = "UPDATE "+name_table+" SET libelle = ? WHERE id = ?";

        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, classe.getLibelle());
        stmt.setInt(2, classe.getId());

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