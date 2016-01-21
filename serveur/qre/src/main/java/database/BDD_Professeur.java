package database;

import model.Professeur;
import utils.EncrypteString;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BDD_Professeur {

    private static String name_table = "professeur";

    public static ArrayList<Professeur> getAll(Connection con) throws SQLException {
        String query = "SELECT * FROM "+name_table;

        ArrayList<Professeur> professeurList = new ArrayList<Professeur>();
        PreparedStatement stmt = con.prepareStatement(query);
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

    public static Professeur getById(Connection con, int id) throws SQLException {
        String query = "SELECT * FROM "+name_table+" WHERE id = ?";

        PreparedStatement stmt = con.prepareStatement(query);
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

    public static Professeur checkAuth(Connection con, String login, String password) throws SQLException {
        String query = "SELECT id FROM "+name_table+" WHERE email = ? AND password = ?";

        PreparedStatement stmt = con.prepareStatement(query);
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

    public static boolean insert(Connection con, Professeur professeur) throws SQLException {

        boolean success = false;

        String query = "INSERT INTO "+name_table+" (nom, prenom, email, password) VALUES (?, ?, ?, ?)";

        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, professeur.getNom());
        stmt.setString(2, professeur.getPrenom());
        stmt.setString(3, professeur.getEmail());
        stmt.setString(4, EncrypteString.encode(professeur.getPassword()));

        int rowsUpdated = stmt.executeUpdate();

        if(rowsUpdated > 0){
            con.commit();
            success = true;
        }

        return success;
    }

    public static boolean update(Connection con, Professeur professeur) throws SQLException {

        boolean success = false;

        String query = "UPDATE "+name_table+" SET nom= ?, prenom= ?, email= ? WHERE id= ?";

        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, professeur.getNom());
        stmt.setString(2, professeur.getPrenom());
        stmt.setString(3, professeur.getEmail());
        stmt.setInt(4, professeur.getId());

        int rowsUpdated = stmt.executeUpdate();

        if(rowsUpdated > 0){
            con.commit();
            success = true;
        }

        return success;
    }

    public static boolean updatePassword(Connection con, Professeur professeur, String newpassword) throws SQLException {

        boolean success = false;

        String query = "UPDATE "+name_table+" SET password= ? WHERE id= ?";

        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, EncrypteString.encode(newpassword));
        stmt.setInt(2, professeur.getId());

        int rowsUpdated = stmt.executeUpdate();

        if(rowsUpdated > 0){
            con.commit();
            success = true;
        }

        return success;
    }

    public boolean delete(Connection con, int id) throws SQLException {

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