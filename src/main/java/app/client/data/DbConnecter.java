package app.client.data;

import app.client.testchain.sdk.SdkTestConst;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by zh on 2018/9/4.
 */
public class DbConnecter {

    private static Connection robotDbConncetion;

    private static Connection passportDbConnection;

    private static Connection tokenDbConnection;

    static {
        String sql = "";
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.print("classnotfoundexception :");
            System.err.print(e.getMessage());
        }
        try {
            robotDbConncetion = DriverManager.getConnection(SdkTestConst.ROBOT_DB_URL, "root", "123456");
            passportDbConnection = DriverManager.getConnection(SdkTestConst.PASSPORT_DB_URL, "root", "123456");
            tokenDbConnection = DriverManager.getConnection(SdkTestConst.TOKEN_DB_URL, "root", "123456");
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("sqlexception :" + ex.getMessage());
            System.err.println("sql :" + sql);
        }
    }

    public static Connection getRobotDbConncetion() {
        return robotDbConncetion;
    }

    public static Connection getPassportDbConnection() {
        return passportDbConnection;
    }

    public static Connection getTokenDbConnection(){ return tokenDbConnection; }
}
