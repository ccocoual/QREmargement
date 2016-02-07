package database;

import model.Classe;
import model.Groupe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BDD_Classe {

    private static String classe_table = "classe";
    private static String groupe_table = "groupe";

    public static ArrayList<Classe> getAll() throws Exception {
        Connection connection = Database.getDbCon().conn;

        String query = "SELECT * FROM "+ classe_table +" c JOIN "+ groupe_table +" g ON g.classe_id = c.id";

        ArrayList<Classe> classeList = new ArrayList<Classe>();
        PreparedStatement stmt = connection.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();

        Classe last_classe = null;
        while(rs.next()) {
            int classe_id = rs.getInt("c.id");
            if(last_classe == null || last_classe.getId() != classe_id) {
                if(last_classe != null) classeList.add(last_classe);
                last_classe = new Classe();
                last_classe.setId(classe_id);
                last_classe.setLibelle(rs.getString("c.libelle"));
            }
            Groupe groupe = new Groupe();
            groupe.setId(rs.getInt("g.id"));
            groupe.setLibelle(rs.getString("g.libelle"));

            last_classe.addGroupe(groupe);

            if(rs.isLast()){
                classeList.add(last_classe);
            }
        }

        return classeList;
    }

    public static Classe getById(int id) throws SQLException {
        Connection connection = Database.getDbCon().conn;

        String query = "SELECT * FROM "+ classe_table +" c " +
                "JOIN "+ groupe_table +" g ON g.classe_id = c.id " +
                "WHERE c.id = ?";

        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        Classe classe = null;
        while(rs.next()) {
            if(classe == null){
                classe = new Classe();
                classe.setId(rs.getInt("c.id"));
                classe.setLibelle(rs.getString("c.libelle"));
            }
            Groupe groupe = new Groupe();
            groupe.setId(rs.getInt("g.id"));
            groupe.setLibelle(rs.getString("g.libelle"));
            classe.addGroupe(groupe);
        }

        return classe;
    }

    public static boolean insert(Classe classe) throws SQLException {
        Connection connection = Database.getDbCon().conn;

        String query = "INSERT INTO "+ classe_table +" (libelle) VALUES (?)";

        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, classe.getLibelle());

        int rowsUpdated = stmt.executeUpdate();

        if(rowsUpdated > 0){
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()){
                classe.setId(generatedKeys.getInt(1));
            } else {
                throw new SQLException("Creating classe failed, no ID obtained.");
            }
            return true;
        }

        return false;
    }

    public static boolean update(Classe classe) throws SQLException {
        Connection connection = Database.getDbCon().conn;

        String query = "UPDATE "+ classe_table +" SET libelle = ? WHERE id = ?";

        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, classe.getLibelle());
        stmt.setInt(2, classe.getId());

        int rowsUpdated = stmt.executeUpdate();

        if(rowsUpdated > 0){
            return true;
        }

        return false;
    }

    public static boolean delete(int id) throws SQLException {
        Connection connection = Database.getDbCon().conn;

        String query = "DELETE FROM "+ classe_table +" WHERE id = ?";

        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, id);

        int rowsUpdated = stmt.executeUpdate();

        if(rowsUpdated > 0){
            return true;
        }

        return false;
    }


}