package database;

import model.Classe;
import model.Groupe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BDD_Groupe {

    public ArrayList<Groupe> getAll(Connection con) throws SQLException {
        String query = "SELECT * FROM Groupe";

        ArrayList<Groupe> groupeList = new ArrayList<Groupe>();
        PreparedStatement stmt = con.prepareStatement(query);
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
        String query = "SELECT * FROM Groupe WHERE id="+id;

        PreparedStatement stmt = con.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();
        rs.first();

        Groupe groupe = new Groupe();
        groupe.setId(rs.getInt("id"));
        groupe.setLibelle(rs.getString("libelle"));
        groupe.setClasse_id(rs.getInt("classe_id"));

        return groupe;
    }

    public ArrayList<Groupe> getByClassId(Connection con, int classe_id) throws SQLException {
        String query = "SELECT * FROM Groupe WHERE classe_id="+classe_id;

        ArrayList<Groupe> groupeList = new ArrayList<Groupe>();
        PreparedStatement stmt = con.prepareStatement(query);
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

}