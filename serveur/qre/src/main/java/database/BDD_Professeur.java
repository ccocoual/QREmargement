package database;

import model.Etudiant;
import model.Groupe;
import model.Professeur;
import utils.EncrypteString;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BDD_Professeur {

    private static String name_table = "Professeur";

    public static ArrayList<Professeur> getAll(Connection con) throws SQLException {
        String query = "SELECT * FROM ?";

        ArrayList<Professeur> professeurList = new ArrayList<Professeur>();
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, name_table);
        ResultSet rs = stmt.executeQuery();
        try {
            while(rs.next()) {
                Professeur professeur = new Professeur();
                professeur.setId(rs.getInt("id"));
                professeur.setNom(rs.getString("nom"));
                professeur.setPrenom(rs.getString("prenom"));
                professeur.setEmail(rs.getString("email"));
                professeurList.add(professeur);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return professeurList;
    }

    public static Professeur getById(Connection con, int id) throws SQLException {
        String query = "SELECT * FROM ? WHERE id = ?";

        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, name_table);
        stmt.setInt(2, id);
        ResultSet rs = stmt.executeQuery();
        rs.first();

        Professeur professeur = new Professeur();
        professeur.setId(rs.getInt("id"));
        professeur.setNom(rs.getString("nom"));
        professeur.setPrenom(rs.getString("prenom"));
        professeur.setEmail(rs.getString("email"));

        return professeur;
    }

    public static boolean checkAuth(Connection con, String login, String password) throws SQLException {
        String query = "SELECT id FROM ? WHERE email = ? AND password = ?";

        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, name_table);
        stmt.setString(2, login);
        stmt.setString(3, EncrypteString.encode(password));
        ResultSet rs = stmt.executeQuery();

        return rs.isBeforeFirst();
    }

    public static boolean insert(Connection con, Professeur professeur) throws SQLException {

        boolean success = false;

        String query = "INSERT INTO ? (nom, prenom, email, password) VALUES (?, ?, ?, ?)";

        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, name_table);
        stmt.setString(2, professeur.getNom());
        stmt.setString(3, professeur.getPrenom());
        stmt.setString(4, professeur.getEmail());
        stmt.setString(5, EncrypteString.encode(professeur.getPassword()));

        int rowsUpdated = stmt.executeUpdate();

        if(rowsUpdated > 0){
            con.commit();
            success = true;
        }

        return success;
    }

    public static boolean update(Connection con, Professeur professeur) throws SQLException {

        boolean success = false;

        String query = "UPDATE ? SET nom= ?, prenom= ?, email= ? WHERE id= ?";

        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, name_table);
        stmt.setString(2, professeur.getNom());
        stmt.setString(3, professeur.getPrenom());
        stmt.setString(4, professeur.getEmail());
        stmt.setInt(5, professeur.getId());

        int rowsUpdated = stmt.executeUpdate();

        if(rowsUpdated > 0){
            con.commit();
            success = true;
        }

        return success;
    }

    public static boolean updatePassword(Connection con, Professeur professeur, String newpassword) throws SQLException {

        boolean success = false;

        String query = "UPDATE ? SET password= ? WHERE id= ?";

        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, name_table);
        stmt.setString(2, EncrypteString.encode(newpassword));
        stmt.setInt(4, professeur.getId());

        int rowsUpdated = stmt.executeUpdate();

        if(rowsUpdated > 0){
            con.commit();
            success = true;
        }

        return success;
    }

    public static boolean delete(Connection con, Professeur professeur) throws SQLException {

        boolean success = false;

        String query = "DELETE FROM ? WHERE id = ?";

        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, name_table);
        stmt.setInt(2, professeur.getId());

        int rowsUpdated = stmt.executeUpdate();

        if(rowsUpdated > 0){
            con.commit();
            success = true;
        }

        return success;
    }

}