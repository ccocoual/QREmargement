package database;

import model.Classe;
import model.Etudiant;
import model.Groupe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BDD_Groupe {


    private static String name_table = "Groupe";

    public ArrayList<Groupe> getAll(Connection con) throws SQLException {
        String query = "SELECT * FROM ?";

        ArrayList<Groupe> groupeList = new ArrayList<Groupe>();
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, name_table);
        ResultSet rs = stmt.executeQuery();
        try {
            while(rs.next()) {
                Groupe groupe = new Groupe();
                groupe.setId(rs.getInt("id"));
                groupe.setLibelle(rs.getString("libelle"));
                groupe.setId(rs.getInt("classe_id"));
                groupeList.add(groupe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return groupeList;
    }

    public Groupe getById(Connection con, int id) throws SQLException {
        String query = "SELECT * FROM ? WHERE id = ?";

        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, name_table);
        stmt.setInt(2, id);
        ResultSet rs = stmt.executeQuery();
        rs.first();

        Groupe groupe = new Groupe();
        groupe.setId(rs.getInt("id"));
        groupe.setLibelle(rs.getString("libelle"));
        groupe.setClasse_id(rs.getInt("classe_id"));

        return groupe;
    }

    public ArrayList<Groupe> getByClassId(Connection con, int classe_id) throws SQLException {
        String query = "SELECT * FROM ? WHERE classe_id = ?";

        ArrayList<Groupe> groupeList = new ArrayList<Groupe>();
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, name_table);
        stmt.setInt(2, classe_id);
        ResultSet rs = stmt.executeQuery();
        try {
            while(rs.next()) {
                Groupe groupe = new Groupe();
                groupe.setId(rs.getInt("id"));
                groupe.setLibelle(rs.getString("libelle"));
                groupe.setClasse_id(rs.getInt("classe_id"));
                groupeList.add(groupe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return groupeList;
    }

    public boolean insert(Connection con, Groupe groupe) throws SQLException {

        boolean success = false;

        String query = "INSERT INTO ? (libelle, classe_id) VALUES (?, ?)";

        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, name_table);
        stmt.setString(2, groupe.getLibelle());
        stmt.setInt(3, groupe.getClasse_id());

        int rowsUpdated = stmt.executeUpdate();

        if(rowsUpdated > 0){
            con.commit();
            success = true;
        }

        return success;
    }

    public boolean update(Connection con, Groupe groupe) throws SQLException {

        boolean success = false;

        String query = "UPDATE ? SET libelle= ?, classe_id= ? WHERE id= ?";

        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, name_table);
        stmt.setString(2, groupe.getLibelle());
        stmt.setInt(3, groupe.getClasse_id());
        stmt.setInt(4, groupe.getId());

        int rowsUpdated = stmt.executeUpdate();

        if(rowsUpdated > 0){
            con.commit();
            success = true;
        }

        return success;
    }

    public boolean delete(Connection con, Groupe groupe) throws SQLException {

        boolean success = false;

        String query = "DELETE FROM ? WHERE id = ?";

        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, name_table);
        stmt.setInt(2, groupe.getId());

        int rowsUpdated = stmt.executeUpdate();

        if(rowsUpdated > 0){
            con.commit();
            success = true;
        }

        return success;
    }
}