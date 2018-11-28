package app.client.data;

import app.client.vo.RobotVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * Created by zh on 2018/9/4.
 */
public class RobotDataHolder {

    private static final Logger logger = LoggerFactory.getLogger(RobotDataHolder.class);

    private static final Map<Integer, RobotVo> id2RotbotVoMap = new LinkedHashMap<>();

    static {
        Connection con = DbConnecter.getRobotDbConncetion();
//        logger.info("====== >>> 以下是jarvis_device_db的表");
//        String showTableTest = "show tables";
//        try(Statement stmt = con.createStatement();
//            ResultSet rs0 = stmt.executeQuery(showTableTest)) {
//            while (rs0.next()) {
//                logger.info(rs0.getString(1));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        String queryRobotInfo = "SELECT t1.id, " +
                "t1.mac, " +
                "t1.serial_no, " +
                "t1.brand_id, " +
                "t1.robot_name, " +
                "t1.product_type, " +
                "t1.customer_type, " +
                "t1.status, " +
                "t3.id AS accountId " +
                "FROM jarvis_device_db.usr_robot_info_xb t1 " +
                "LEFT JOIN jarvis_device_db.usr_robot_bind_xb t2 ON t1.id = t2.robot_info_id " +
                "LEFT JOIN gowild_passport_db.account t3 ON t2.account_id = t3.id " +
                "ORDER BY t1.id ASC";
        try(Statement stmt = con.createStatement();
            ResultSet rs0 = stmt.executeQuery(queryRobotInfo)) {
            while (rs0.next()) {
                Integer id = rs0.getInt("id");
                String mac = rs0.getString("mac");
                String accountId = rs0.getString("accountId");
                RobotVo robotVo = new RobotVo();
                robotVo.setMac(mac);
                robotVo.setRobotId(id);
                robotVo.setAccountId(accountId);
                id2RotbotVoMap.put(id, robotVo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        logger.info("====== >>> 机器数据初始化完毕");
    }

    public static Map<Integer, RobotVo> getId2RotbotVoMap(){
        return id2RotbotVoMap;
    }
}
