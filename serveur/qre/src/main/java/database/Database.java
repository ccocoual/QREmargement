package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public final class Database {
    public Connection conn;
    private Statement statement;
    public static Database db;

    private Database() throws SQLException {
        String url= "jdbc:mysql://localhost:3306/";
        String dbName = "qre";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "root";
        String password = "";
        try {
            Class.forName(driver).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        this.conn = (Connection)DriverManager.getConnection(url+dbName,userName,password);
    }
    /**
     *
     * @return MysqlConnect Database connection object
     */
    public static synchronized Database getDbCon() throws SQLException {
        if ( db == null ) {
            db = new Database();
        }
        return db;

    }
}