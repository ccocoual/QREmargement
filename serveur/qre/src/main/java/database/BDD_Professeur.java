package database;

import model.Etudiant;
import model.Professeur;
import utils.EncrypteString;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BDD_Professeur {

    public ArrayList<Professeur> getAll(Connection con) throws SQLException {
        String query = "SELECT * FROM Professeur";

        ArrayList<Professeur> professeurList = new ArrayList<Professeur>();
        PreparedStatement stmt = con.prepareStatement(query);
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

    public Professeur getById(Connection con, int id) throws SQLException {
        String query = "SELECT * FROM Professeur WHERE id="+id;

        PreparedStatement stmt = con.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();
        rs.first();

        Professeur professeur = new Professeur();
        professeur.setId(rs.getInt("id"));
        professeur.setNom(rs.getString("nom"));
        professeur.setPrenom(rs.getString("prenom"));
        professeur.setEmail(rs.getString("email"));

        return professeur;
    }

    public boolean checkAuth(Connection con, String login, String password) throws SQLException {
        String hash_password = EncrypteString.encode(password);
        String query = "SELECT id FROM Professeur " +
                "WHERE password="+hash_password +
                "AND email="+ login + ")";

        PreparedStatement stmt = con.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();

        return rs.isBeforeFirst();

    }

}