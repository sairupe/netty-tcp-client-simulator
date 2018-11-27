package app.client.data;


import app.client.net.test.QuickStarter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zh on 2018/9/6.
 */
public class TokenDataHolder {

    private static Logger logger = LoggerFactory.getLogger(TokenDataHolder.class);
    
    private static final Map<String, String> identify2TokenMap = new HashMap<>();

    public static void loadAllRobotToken() {
        if(QuickStarter.PRESS_TEST){
            Connection con = DbConnecter.getTokenDbConnection();
            String queryAccount = "SELECT id, identify, token FROM token";
            try (Statement stmt = con.createStatement();
                 ResultSet rs0 = stmt.executeQuery(queryAccount)) {
                while (rs0.next()) {
                    Integer id = rs0.getInt(1);
                    String identify = rs0.getString(2);
                    String userName = rs0.getString(3);
                    identify2TokenMap.put(identify, userName);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void updateToken(String identify, String token) {
        final Connection con = DbConnecter.getTokenDbConnection();
        String updateToken = "INSERT INTO token (identify, token) VALUES ('"
                + identify + "', '"
                + token + "') ON DUPLICATE KEY UPDATE token='" + token + "'";
//        logger.info(updateToken);
        try (Statement stmt = con.createStatement();) {
            stmt.executeUpdate(updateToken);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getIdentifyToken(String identify){
        return identify2TokenMap.get(identify);
    }
}
