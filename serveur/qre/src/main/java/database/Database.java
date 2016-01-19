package database;

import java.sql.*;

public final class Database {
    public Connection conn;
    private Statement statement;
    public static Database db;

    private Database() {
        String url= "jdbc:mysql://localhost:3306/qre";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "root";
        String password = "miageQRE2016";
        try {
            Class.forName(driver).newInstance();
            this.conn = (Connection)DriverManager.getConnection(url,userName,password);
        }
        catch (Exception sqle) {
            sqle.printStackTrace();
        }
    }

    /**
     *
     * @return Database Database connection object
     */
    public static synchronized Database getDbCon() {
        if ( db == null ) {
            db = new Database();
        }
        return db;

    }


}