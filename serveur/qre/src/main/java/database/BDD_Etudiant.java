package database;

import model.Classe;
import model.Etudiant;
import model.Groupe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public class BDD_Etudiant {

    private static String etudiant_table = "etudiant";
    private static String classe_table = "classe";
    private static String groupe_table = "groupe";

    public static ArrayList<Etudiant> getAll() throws SQLException {
        Connection connection = Database.getDbCon().conn;

        String query = "SELECT * FROM "+ etudiant_table+ "e " +
                "JOIN "+classe_table+" c ON c.id = e.classe_id " +
                "JOIN "+groupe_table+" g ON g.id = e.groupe_id";

        ArrayList<Etudiant> etudiantList = new ArrayList<Etudiant>();
        PreparedStatement stmt = connection.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();

        while(rs.next()) {
            Etudiant etudiant = new Etudiant();
            etudiant.setId(rs.getInt("e.id"));
            etudiant.setNom(rs.getString("e.nom"));
            etudiant.setPrenom(rs.getString("e.prenom"));
            etudiant.setEmail(rs.getString("e.email"));
            etudiant.setNum_etu(rs.getString("e.num_etu"));

            Classe classe = new Classe();
            classe.setId(rs.getInt("c.id"));
            classe.setLibelle(rs.getString("c.libelle"));
            etudiant.setClasse(classe);

            Groupe groupe = new Groupe();
            groupe.setId(rs.getInt("g.id"));
            groupe.setLibelle(rs.getString("g.libelle"));
            etudiant.setGroupe(groupe);

            etudiantList.add(etudiant);
        }

        return etudiantList;
    }

    public static Etudiant getById(int id) throws SQLException {
        Connection connection = Database.getDbCon().conn;

        String query = "SELECT * FROM "+ etudiant_table+ "e " +
                "JOIN "+classe_table+" c ON c.id = e.classe_id " +
                "JOIN "+groupe_table+" g ON g.id = e.groupe_id " +
                "WHERE e.id = ?";

        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        Etudiant etudiant = null;
        while(rs.next()) {
            etudiant = new Etudiant();
            etudiant.setId(rs.getInt("e.id"));
            etudiant.setNom(rs.getString("e.nom"));
            etudiant.setPrenom(rs.getString("e.prenom"));
            etudiant.setEmail(rs.getString("e.email"));
            etudiant.setNum_etu(rs.getString("e.num_etu"));

            Classe classe = new Classe();
            classe.setId(rs.getInt("c.id"));
            classe.setLibelle(rs.getString("c.libelle"));
            etudiant.setClasse(classe);

            Groupe groupe = new Groupe();
            groupe.setId(rs.getInt("g.id"));
            groupe.setLibelle(rs.getString("g.libelle"));
            etudiant.setGroupe(groupe);
        }

        return etudiant;
    }

    public static Etudiant getByNumEtu(String num_etu) throws SQLException {
        Connection connection = Database.getDbCon().conn;

        String query = "SELECT * FROM "+ etudiant_table+ "e " +
                "JOIN "+classe_table+" c ON c.id = e.classe_id " +
                "JOIN "+groupe_table+" g ON g.id = e.groupe_id " +
                "WHERE e.num_etu = ?";

        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, num_etu);

        ResultSet rs = stmt.executeQuery();

        Etudiant etudiant = null;
        while(rs.next()) {
            etudiant = new Etudiant();
            etudiant.setId(rs.getInt("e.id"));
            etudiant.setNom(rs.getString("e.nom"));
            etudiant.setPrenom(rs.getString("e.prenom"));
            etudiant.setEmail(rs.getString("e.email"));
            etudiant.setNum_etu(rs.getString("e.num_etu"));

            Classe classe = new Classe();
            classe.setId(rs.getInt("c.id"));
            classe.setLibelle(rs.getString("c.libelle"));
            etudiant.setClasse(classe);

            Groupe groupe = new Groupe();
            groupe.setId(rs.getInt("g.id"));
            groupe.setLibelle(rs.getString("g.libelle"));
            etudiant.setGroupe(groupe);
        }

        return etudiant;
    }

    public static boolean insert(Etudiant etudiant) throws SQLException {
        Connection connection = Database.getDbCon().conn;

        String query = "INSERT INTO "+ etudiant_table +" (nom, prenom, email, num_etu, classe_id, groupe_id) VALUES (?, ?, ?, ?, ?, ?)";

        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, etudiant.getNom());
        stmt.setString(2, etudiant.getPrenom());
        stmt.setString(3, etudiant.getEmail());
        stmt.setString(4, etudiant.getNum_etu());
        stmt.setInt(5, etudiant.getClasse().getId());
        stmt.setInt(6, etudiant.getGroupe().getId());

        int rowsUpdated = stmt.executeUpdate();

        if(rowsUpdated > 0){
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()){
                etudiant.setId(generatedKeys.getInt(1));
            } else {
                throw new SQLException("Creating etudiant failed, no ID obtained.");
            }
            return true;
        }
        return false;
    }

    public static boolean update(Etudiant etudiant) throws SQLException {
        Connection connection = Database.getDbCon().conn;

        String query = "UPDATE "+ etudiant_table +" SET nom= ?, prenom= ?, email= ?, num_etu= ?, classe_id= ?, groupe_id= ? WHERE id= ?";

        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, etudiant.getNom());
        stmt.setString(2, etudiant.getPrenom());
        stmt.setString(3, etudiant.getEmail());
        stmt.setString(4, etudiant.getNum_etu());
        stmt.setInt(5, etudiant.getClasse().getId());
        stmt.setInt(6, etudiant.getGroupe().getId());
        stmt.setInt(7, etudiant.getId());

        int rowsUpdated = stmt.executeUpdate();

        if(rowsUpdated > 0){
            return true;
        }
        return false;
    }

    public static boolean delete(int id) throws SQLException {
        Connection connection = Database.getDbCon().conn;

        String query = "DELETE FROM "+ etudiant_table +" WHERE id = ?";

        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, id);

        int rowsUpdated = stmt.executeUpdate();

        if(rowsUpdated > 0){
            return true;
        }
        return false;
    }

    public static Etudiant checkAuth(String login, String password) throws SQLException, ParseException {
        Connection connection = Database.getDbCon().conn;

        String query = "SELECT * FROM "+ etudiant_table+ "e " +
                "JOIN "+classe_table+" c ON c.id = e.classe_id " +
                "JOIN "+groupe_table+" g ON g.id = e.groupe_id " +
                "WHERE email = ? AND num_etu = ?";

        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, login);
        stmt.setString(2, password);
        ResultSet rs = stmt.executeQuery();

        Etudiant etudiant = null;
        while(rs.next()) {
            etudiant = new Etudiant();
            etudiant.setId(rs.getInt("e.id"));
            etudiant.setNom(rs.getString("e.nom"));
            etudiant.setPrenom(rs.getString("e.prenom"));
            etudiant.setEmail(rs.getString("e.email"));
            etudiant.setNum_etu(rs.getString("e.num_etu"));

            Classe classe = new Classe();
            classe.setId(rs.getInt("c.id"));
            classe.setLibelle(rs.getString("c.libelle"));
            etudiant.setClasse(classe);

            Groupe groupe = new Groupe();
            groupe.setId(rs.getInt("g.id"));
            groupe.setLibelle(rs.getString("g.libelle"));
            etudiant.setGroupe(groupe);
        }

        return etudiant;
    }

}