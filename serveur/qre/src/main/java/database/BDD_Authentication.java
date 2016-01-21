package database;

import model.Authentication;
import model.Classe;
import model.Etudiant;
import model.Signature;
import org.glassfish.grizzly.http.server.Response;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;

public class BDD_Authentication {

    private static String name_table = "authentication";

    public static boolean insertOrUpdateToken(Connection con, String token, int professeur_id) throws SQLException {

        boolean success = false;

        // CHECK IF EXIST
        String query = "SELECT id FROM "+name_table+" WHERE professeur_id = ?";

        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setInt(1, professeur_id);
        ResultSet rs = stmt.executeQuery();

        Calendar cal = Calendar.getInstance(); // creates calendar
        cal.setTime(new java.util.Date()); // sets calendar time/date
        cal.add(Calendar.HOUR_OF_DAY, 1); // adds one hour
        Date date_expiration = new Date(cal.getTime().getTime());

        if (rs.isBeforeFirst()) {
            // UPDATE IF EXIST
            rs.first();

            query = "UPDATE "+name_table+" SET token = ?, date_expiration= ? WHERE professeur_id = ?";

            stmt = con.prepareStatement(query);
            stmt.setString(1, token);
            stmt.setDate(2, date_expiration);
            stmt.setInt(3, professeur_id);

        } else {
            // OR INSERT
            query = "INSERT INTO "+name_table+" (token, date_expiration, professeur_id) VALUES (?, ?, ?)";

            stmt = con.prepareStatement(query);
            stmt.setString(1, token);
            stmt.setDate(2, date_expiration);
            stmt.setInt(3, professeur_id);
        }

        int rowsUpdated = stmt.executeUpdate();

        if(rowsUpdated > 0){
            con.commit();
            success = true;
        }

        return success;
    }

}