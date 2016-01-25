package database;

import model.Professeur;
import utils.EncrypteString;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BDD_Professeur {

    private static String name_table = "professeur";

    public static ArrayList<Professeur> getAll() throws SQLException {
        Connection connection = Database.getDbCon().conn;

        String query = "SELECT * FROM "+name_table;

        ArrayList<Professeur> professeurList = new ArrayList<Professeur>();
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, name_table);
        ResultSet rs = stmt.executeQuery();

        while(rs.next()) {
            Professeur professeur = new Professeur();
            professeur.setId(rs.getInt("id"));
            professeur.setNom(rs.getString("nom"));
            professeur.setPrenom(rs.getString("prenom"));
            professeur.setEmail(rs.getString("email"));
            professeurList.add(professeur);
        }

        return professeurList;
    }

    public static Professeur getById(int id) throws SQLException {
        Connection connection = Database.getDbCon().conn;

        String query = "SELECT * FROM "+name_table+" WHERE id = ?";

        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        Professeur professeur = null;
        if (rs.isBeforeFirst()) {
            rs.first();
            professeur = new Professeur();
            professeur.setId(rs.getInt("id"));
            professeur.setNom(rs.getString("nom"));
            professeur.setPrenom(rs.getString("prenom"));
            professeur.setEmail(rs.getString("email"));
        }

        return professeur;
    }

    public static Professeur checkAuth(String login, String password) throws SQLException, NoSuchAlgorithmException {
        Connection connection = Database.getDbCon().conn;

        String query = "SELECT * FROM "+name_table+" WHERE email = ? AND password = ?";

        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, login);
        stmt.setString(2, EncrypteString.encode(password));
        ResultSet rs = stmt.executeQuery();

        Professeur professeur = null;
        if (rs.isBeforeFirst()) {
            rs.first();
            professeur = new Professeur();
            professeur.setId(rs.getInt("id"));
            professeur.setNom(rs.getString("nom"));
            professeur.setPrenom(rs.getString("prenom"));
            professeur.setEmail(rs.getString("email"));
        }

        return professeur;
    }

    public static boolean insert(Professeur professeur) throws SQLException, NoSuchAlgorithmException {
        Connection connection = Database.getDbCon().conn;

        String query = "INSERT INTO "+name_table+" (nom, prenom, email, password) VALUES (?, ?, ?, ?)";

        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, professeur.getNom());
        stmt.setString(2, professeur.getPrenom());
        stmt.setString(3, professeur.getEmail());
        stmt.setString(4, EncrypteString.encode(professeur.getPassword()));

        int rowsUpdated = stmt.executeUpdate();

        if(rowsUpdated > 0){
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()){
                professeur.setId(generatedKeys.getInt(1));
            }
            return true;
        }

        return false;
    }

    public static boolean update(Professeur professeur) throws SQLException {
        Connection connection = Database.getDbCon().conn;

        String query = "UPDATE "+name_table+" SET nom= ?, prenom= ?, email= ? WHERE id= ?";

        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, professeur.getNom());
        stmt.setString(2, professeur.getPrenom());
        stmt.setString(3, professeur.getEmail());
        stmt.setInt(4, professeur.getId());

        int rowsUpdated = stmt.executeUpdate();

        if(rowsUpdated > 0){
            return true;
        }

        return false;
    }

    public static boolean updatePassword(Professeur professeur, String newpassword) throws SQLException, NoSuchAlgorithmException {
        Connection connection = Database.getDbCon().conn;

        String query = "UPDATE "+name_table+" SET password= ? WHERE id= ?";

        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, EncrypteString.encode(newpassword));
        stmt.setInt(2, professeur.getId());

        int rowsUpdated = stmt.executeUpdate();

        if(rowsUpdated > 0){
            return true;
        }

        return false;
    }

    public boolean delete(int id) throws SQLException {
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