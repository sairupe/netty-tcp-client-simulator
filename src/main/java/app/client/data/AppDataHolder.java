package app.client.data;

import app.client.vo.UserVo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * Created by zh on 2018/9/4.
 */
public class AppDataHolder {

    public static final int appClientCount = 5000;

    private static final CountDownLatch appLatch = new CountDownLatch(appClientCount);

    private static final Map<Integer, UserVo> id2UserVoMap = new HashMap<>();

    private static final Map<String, UserVo> account2UserVoMap = new HashMap<>();

    static {
        Connection con = DbConnecter.getPassportDbConnection();
//        String sql = "show tables";
//        System.out.println("====== >>> 以下是gowild_passport_db的表");
//        try(Statement stmt = con.createStatement();
//            ResultSet rs0 = stmt.executeQuery(sql)) {
//            while (rs0.next()) {
//                System.out.println(rs0.getString(1));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        String queryAccount = "SELECT id, username, password FROM account";
        try(Statement stmt = con.createStatement();
            ResultSet rs0 = stmt.executeQuery(queryAccount)) {
            while (rs0.next()) {
                Integer id = rs0.getInt(1);
                String userName = rs0.getString(2);
                UserVo userVo = new UserVo();
                userVo.setUserName(userName);
                id2UserVoMap.put(id, userVo);
                account2UserVoMap.put(userName, userVo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("====== >>> 用户数据初始化完毕");
    }

    public static Map<Integer, UserVo> getId2UserVoMap() {
        return id2UserVoMap;
    }
}
