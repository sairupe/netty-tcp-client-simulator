package app.client.data;

import app.client.testchain.sdk.SdkTestConst;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by zh on 2018/9/4.
 */
public class DbConnecter {

    private static Connection con;

    static {
        String urlstr = SdkTestConst.DB_URL;
        String sql = "";
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.print("classnotfoundexception :");
            System.err.print(e.getMessage());
        }
        try {
            con = DriverManager.getConnection(urlstr, "root", "123456");
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("sqlexception :" + ex.getMessage());
            System.err.println("sql :" + sql);
        }
    }

    public static Connection getCon() {
        return con;
    }
}
