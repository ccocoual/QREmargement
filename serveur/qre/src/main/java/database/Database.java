package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public final class Database {
    public Connection conn;
    private Statement statement;
    public static Database db;

    private Database() {
        String url= "jdbc:mysql://localhost:3306/";
        String dbName = "qre";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "root";
        String password = "";
        try {
            Class.forName(driver).newInstance();
            this.conn = (Connection)DriverManager.getConnection(url+dbName,userName,password);
        }
        catch (Exception sqle) {
            sqle.printStackTrace();
        }
    }
    /**
     *
     * @return MysqlConnect Database connection object
     */
    public static synchronized Database getDbCon() {
        if ( db == null ) {
            db = new Database();
        }
        return db;

    }
}