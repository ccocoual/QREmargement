package database;

import model.Classe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BDD_Classe {

    public ArrayList<Classe> getAll(Connection con) throws SQLException {
        String query = "SELECT * FROM Classe";

        ArrayList<Classe> classeList = new ArrayList<Classe>();
        PreparedStatement stmt = con.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();
        try {
            while(rs.next()) {
                Classe classe = new Classe();
                classe.setId(rs.getInt("id"));
                classe.setLibelle(rs.getString("libelle"));
                classeList.add(classe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classeList;
    }

    public Classe getById(Connection con, int id) throws SQLException {
        String query = "SELECT * FROM Classe WHERE id="+id;

        PreparedStatement stmt = con.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();
        rs.first();

        Classe classe = new Classe();
        classe.setId(rs.getInt("id"));
        classe.setLibelle(rs.getString("libelle"));

        return classe;
    }

}