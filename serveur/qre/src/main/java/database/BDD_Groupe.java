package database;

import model.Classe;
import model.Etudiant;
import model.Groupe;
import utils.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BDD_Groupe {


    private static String groupe_table = "groupe";
    private static String classe_table = "classe";
    private static String etudiant_table = "etudiant";
    private static String professeur_classe_table = "professeur_has_classe";

    public static ArrayList<Groupe> getAll(int professeur_id) throws SQLException {
        Connection connection = Database.getDbCon().conn;

        String query = "SELECT * FROM "+ groupe_table+ " g " +
                "JOIN "+classe_table+" c ON c.id = g.classe_id " +
                "JOIN "+ professeur_classe_table + "pg ON pg.classe_id = c.id " +
                "LEFT JOIN "+etudiant_table+" e ON e.groupe_id = g.id " +
                "WHERE pg.professeur_id = ?";

        ArrayList<Groupe> groupeList = new ArrayList<Groupe>();
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, professeur_id);
        ResultSet rs = stmt.executeQuery();

        Groupe last_groupe = null;
        while(rs.next()) {
            int groupe_id = rs.getInt("g.id");
            if(last_groupe == null || last_groupe.getId() != groupe_id) {
                if(last_groupe != null) groupeList.add(last_groupe);
                last_groupe = new Groupe();
                last_groupe.setId(groupe_id);
                last_groupe.setLibelle(rs.getString("g.libelle"));

                Classe classe = new Classe();
                classe.setId(rs.getInt("c.id"));
                classe.setLibelle(rs.getString("c.libelle"));
                last_groupe.setClasse(classe);
            }
            Etudiant etudiant = new Etudiant();
            etudiant.setId(rs.getInt("e.id"));
            etudiant.setNom(rs.getString("e.nom"));
            etudiant.setPrenom(rs.getString("e.prenom"));
            etudiant.setEmail(rs.getString("e.email"));
            etudiant.setNum_etu(rs.getString("e.num_etu"));

            last_groupe.addEtudiant(etudiant);

            if(rs.isLast()){
                groupeList.add(last_groupe);
            }
        }

        return groupeList;
    }

    public static Groupe getById(int id, int professeur_id) throws SQLException {
        Connection connection = Database.getDbCon().conn;

        String query = "SELECT * FROM "+ groupe_table+ " g " +
                "JOIN "+classe_table+" c ON c.id = g.classe_id " +
                "JOIN "+ professeur_classe_table + "pg ON pg.classe_id = c.id " +
                "LEFT JOIN "+etudiant_table+" e ON e.groupe_id = g.id "+
                "WHERE g.id = ? " +
                "AND pg.professeur_id = ?";

        Logger.getInstance().info(query);

        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        Groupe groupe = null;
        while(rs.next()) {
            if(groupe == null) {
                groupe = new Groupe();
                groupe.setId(rs.getInt("g.id"));
                groupe.setLibelle(rs.getString("g.libelle"));

                Classe classe = new Classe();
                classe.setId(rs.getInt("c.id"));
                classe.setLibelle(rs.getString("c.libelle"));
                groupe.setClasse(classe);
            }

            Etudiant etudiant = new Etudiant();
            etudiant.setId(rs.getInt("e.id"));
            etudiant.setNom(rs.getString("e.nom"));
            etudiant.setPrenom(rs.getString("e.prenom"));
            etudiant.setEmail(rs.getString("e.email"));
            etudiant.setNum_etu(rs.getString("e.num_etu"));
            if (etudiant.getId() != 0)
                groupe.addEtudiant(etudiant);
        }

        return groupe;
    }

    public static boolean insert(Groupe groupe) throws SQLException {
        Connection connection = Database.getDbCon().conn;

        String query = "INSERT INTO "+ groupe_table +" (libelle, classe_id) VALUES (?, ?)";

        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, groupe.getLibelle());
        stmt.setInt(2, groupe.getClasse().getId());

        int rowsUpdated = stmt.executeUpdate();

        if(rowsUpdated > 0){
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()){
                groupe.setId(generatedKeys.getInt(1));
            } else {
                throw new SQLException("Creating groupe failed, no ID obtained.");
            }
            return true;
        }

        return false;
    }

    public static boolean update(Groupe groupe) throws SQLException {
        Connection connection = Database.getDbCon().conn;

        String query = "UPDATE "+ groupe_table +" SET libelle= ?, classe_id= ? WHERE id= ?";

        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, groupe.getLibelle());
        stmt.setInt(2, groupe.getClasse().getId());
        stmt.setInt(3, groupe.getId());

        int rowsUpdated = stmt.executeUpdate();

        if(rowsUpdated > 0){
            return true;
        }

        return false;
    }

    public static boolean delete(int id) throws SQLException {
        Connection connection = Database.getDbCon().conn;

        String query = "DELETE FROM "+ groupe_table +" WHERE id = ?";

        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, id);

        int rowsUpdated = stmt.executeUpdate();

        if(rowsUpdated > 0){
            return true;
        }

        return false;
    }
}