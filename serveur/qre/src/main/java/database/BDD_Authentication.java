package database;

import model.Authentication;

import java.sql.*;
import java.util.Calendar;

public class BDD_Authentication {

    private static String name_table = "authentication";


    public static Authentication isValidTokenAndUpdateIfTrue(String token) throws SQLException {
        Connection connection = Database.getDbCon().conn;

        String query = "SELECT professeur_id FROM "+name_table+" WHERE token = ? AND date_expiration >= ?";

        Calendar cal = Calendar.getInstance(); // creates calendar
        cal.setTime(new java.util.Date()); // sets calendar time/date
        cal.add(Calendar.HOUR_OF_DAY, 1); // adds one hour
        Date date_expiration = new Date(cal.getTime().getTime());

        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, token);
        stmt.setDate(2, date_expiration);
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

        String query = "INSERT OR REPLACE INTO "+name_table+" (id, token, date_expiration, professeur_id) VALUES ((SELECT id FROM "+name_table+" WHERE professeur_id = ?), ?, ?, ? )";

        Calendar cal = Calendar.getInstance(); // creates calendar
        cal.setTime(new java.util.Date()); // sets calendar time/date
        cal.add(Calendar.HOUR_OF_DAY, 1); // adds one hour
        Date date_expiration = new Date(cal.getTime().getTime());

        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, professeur_id);
        stmt.setString(2, token);
        stmt.setDate(3, date_expiration);
        stmt.setInt(4, professeur_id);

        int rowsUpdated = stmt.executeUpdate();

        if(rowsUpdated > 0){
            connection.commit();
            return true;
        }

        return false;
    }


}