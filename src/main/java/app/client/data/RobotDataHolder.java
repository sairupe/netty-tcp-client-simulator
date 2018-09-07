package app.client.data;

import app.client.vo.RobotVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
public class RobotDataHolder {

    private static final Logger logger = LoggerFactory.getLogger(RobotDataHolder.class);

    public static final int robotClientCount = 5000;

    private static final CountDownLatch robotLatch = new CountDownLatch(robotClientCount);

    private static final Map<Integer, RobotVo> id2RotbotVoMap = new HashMap<>();

    private static final Map<String, RobotVo> mac2RobotVoMap = new HashMap<>();

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

        String queryRobotInfo = "SELECT id, mac, serial_no, brand_id, robot_name, product_type, customer_type, status " +
                "FROM usr_robot_info_xb";
        try(Statement stmt = con.createStatement();
            ResultSet rs0 = stmt.executeQuery(queryRobotInfo)) {
            while (rs0.next()) {
                Integer id = rs0.getInt(1);
                String mac = rs0.getString(2);
                RobotVo robotVo = new RobotVo();
                robotVo.setMac(mac);
                id2RotbotVoMap.put(id, robotVo);
                mac2RobotVoMap.put(mac, robotVo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        logger.info("====== >>> 机器数据初始化完毕");
    }

    public static Map<Integer, RobotVo> getId2RotbotVoMap(){
        return id2RotbotVoMap;
    }

    public static CountDownLatch getRobotLatch() {
        return robotLatch;
    }
}
