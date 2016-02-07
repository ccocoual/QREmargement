package utils;

import database.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;


/**
 * Created by stagiaire on 25/01/2016.
 */
public class Logger {

    public class Log {
        private int id;
        private String type_log;
        private Timestamp date;
        private String message;

        public Log() {}

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getType_log() {
            return type_log;
        }

        public void setType_log(String type_log) {
            this.type_log = type_log;
        }

        public Timestamp getDate() {
            return date;
        }

        public void setDate(Timestamp date) {
            this.date = date;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }


    private Logger(){ }

    /** Instance unique non préinitialisée */
    private static Logger INSTANCE = null;
    private static String name_table = "logs";

    /** Point d'accès pour l'instance unique du singleton */
    public static Logger getInstance() {
        if (INSTANCE == null) INSTANCE = new Logger();
        return INSTANCE;
    }

    public void info(String message) {
        insert("INFO", message);
    }

    public void warn(String message){
        insert("WARNING", message);
    }

    public void err(String message) {
        insert("ERROR", message);
    }

    public void insert(String typeLog, String message) {

        try {
            Connection connection = Database.getDbCon().conn;

            String query = "INSERT INTO "+name_table+" (type_log, date, message) VALUES (?, ?, ?)";

            Calendar cal = Calendar.getInstance(); // creates calendar
            cal.setTime(new java.util.Date()); // sets calendar time/date
            cal.add(Calendar.HOUR_OF_DAY, 1); // adds one hour
            Timestamp date_insert = new Timestamp(cal.getTime().getTime());

            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, typeLog);
            stmt.setTimestamp(2, date_insert);
            stmt.setString(3, message);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Log> getLastLogs() throws SQLException {
        Connection connection = Database.getDbCon().conn;

        String query = "SELECT * FROM "+name_table+" ORDER BY date DESC LIMIT 15";

        ArrayList<Log> logs = new ArrayList<Log>();
        PreparedStatement stmt = connection.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();

        while(rs.next()) {
            Log log = new Log();
            log.setId(rs.getInt("id"));
            log.setType_log(rs.getString("type_log"));
            log.setDate(rs.getTimestamp("date"));
            log.setMessage(rs.getString("message"));
            logs.add(log);
        }

        return logs;
    }

}
