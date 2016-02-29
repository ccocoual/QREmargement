package database;

import utils.OSValidator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class Database {
    public Connection conn;
    public static Database db;

    private Database() throws SQLException {
        String url= "jdbc:mysql://localhost:3306/";
        String dbName = "qre";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "root";
        String password = "miageQRE2016";
        if(OSValidator.isWindows() || OSValidator.isMac()){
            password = "";
        }
        try {
            Class.forName(driver).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        this.conn = DriverManager.getConnection(url+dbName+"?autoReconnect=true",userName,password);
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