package app.client.testchain.db;

import app.client.service.sdk.device.DeviceServiceImpl;
import app.client.testchain.DbDataInitialNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by zh on 2017/11/21.
 */
public class BaseDbInfoInsertNode extends DbDataInitialNode {

    private static final Logger logger = LoggerFactory.getLogger(BaseDbInfoInsertNode.class);

    @Override
    public void doExecute() {
//        String sql = "show tables";
//        try(Statement stmt = connection.createStatement();
//            ResultSet rs0 = stmt.executeQuery(sql)) {
//            while (rs0.next()) {
//                logger.info(rs0.getString(1));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }
}
