package database;

import model.Authentication;
import model.Emargement;

import java.sql.*;
import java.util.Calendar;

public class BDD_Authentication {

    private static String name_table = "authentication";


    public static Authentication isValidTokenAndUpdateIfTrue(String token) throws SQLException {
        Connection connection = Database.getDbCon().conn;

        String query = "SELECT * FROM "+name_table+" WHERE token = ? AND date_expiration >= ?";

        Calendar cal = Calendar.getInstance(); // creates calendar
        cal.setTime(new java.util.Date()); // sets calendar time/date
        Timestamp date_expiration = new Timestamp(cal.getTime().getTime());

        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, token);
        stmt.setTimestamp(2, date_expiration);
        ResultSet rs = stmt.executeQuery();

        Authentication authentication = null;
        if (rs.isBeforeFirst()){
            rs.first();
            authentication = new Authentication();
            authentication.setToken(rs.getString("token"));
            authentication.setDate_expiration(rs.getDate("date_expiration"));
            authentication.setProfesseur_id(rs.getInt("professeur_id"));
        }

        return authentication;
    }

    public static boolean insertOrReplaceToken(String token, int professeur_id) throws SQLException {
       Connection connection = Database.getDbCon().conn;

        String query = "REPLACE INTO "+name_table+" (token, date_expiration, professeur_id) VALUES (?, ?, ? )";

        Calendar cal = Calendar.getInstance(); // creates calendar
        cal.setTime(new java.util.Date()); // sets calendar time/date
        cal.add(Calendar.HOUR_OF_DAY, 1); // adds one hour
        Timestamp date_expiration = new Timestamp(cal.getTime().getTime());

        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, token);
        stmt.setTimestamp(2, date_expiration);
        stmt.setInt(3, professeur_id);

        int rowsUpdated = stmt.executeUpdate();

        if(rowsUpdated > 0){
            return true;
        }

        return false;
    }


}