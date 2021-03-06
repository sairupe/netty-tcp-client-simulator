package app.client.testchain.sdk;

import app.client.user.session.UserSession;
import io.netty.util.AttributeKey;

/**
 * Created by zh on 2018/2/8.
 */
public class SdkTestConst {

//    public static final String ROBOT_DB_URL = "jdbc:mysql://localhost:3306/jarvis_device_db";
//    public static final String ROBOT_DB_URL = "jdbc:mysql://172.27.1.81:3306/jarvis_device_db";
//    public static final String ROBOT_DB_URL = "jdbc:mysql://172.27.1.73:3306/jarvis_device_db";
    public static final String ROBOT_DB_URL = "jdbc:mysql://172.27.1.49:3306/jarvis_device_db";

//    public static final String PASSPORT_DB_URL = "jdbc:mysql://localhost:3306/jarvis_device_db";
//    public static final String PASSPORT_DB_URL = "jdbc:mysql://172.27.1.81:3306/jarvis_device_db";
    public static final String PASSPORT_DB_URL = "jdbc:mysql://172.27.1.49:3306/gowild_passport_db";

    public static final String TOKEN_DB_URL = "jdbc:mysql://172.27.1.49:3306/token_db";

    public static final String EMPTY_STRING = "";

    public static final String FIRST_DEVICE_UID = "12315-1";
    public static final String SECOND_DEVICE_UID = "12315-2";
    public static final String THIRD_DEVICE_UID = "12315-3";

    public static final String FIRST_DEVICE_NAME = "设备一";
    public static final String SECOND_DEVICE_NAME = "设备二";
    public static final String THIRD_DEVICE_NAME = "设备三";

    public static final String FIRST_DEVICE_SN = FIRST_DEVICE_UID;
    public static final String SECOND_DEVICE_SN = SECOND_DEVICE_UID;
    public static final String THIRD_DEVICE_SN = THIRD_DEVICE_UID;

    public static final String FIRST_HOME_TID = "home1";
    public static final String SECOND_HOME_TID = "home2";

    public static final String FIRST_HOME_NAME = "家庭一";
    public static final String SECOND_HOME_NAME = "家庭二";

    public static final String FIRST_FLOOR_TID = "floor1";
    public static final String SECOND_FLOOR_TID = "floor2";

    public static final String FIRST_FLOOR_NAME = "楼层一";
    public static final String SECOND_FLOOR_NAME = "楼层二";

    public static final String FIRST_AREA_TID = "area1";
    public static final String SECOND_AREA_TID = "area2";

    public static final String FIRST_AREA_NAME = "区域一";
    public static final String SECOND_AREA_NAME = "区域二";

    public static final String FIRST_SCENE_TID = "scene1";
    public static final String SECOND_SCENE_TID = "scene2";

    public static final String FIRST_SCENE_NAME = "场景一";
    public static final String SECOND_SCENE_NAME = "场景二";


    // 用户会话信息KEY
    public static final AttributeKey<UserSession> USER_SESSION = AttributeKey.valueOf("USER_SESSION");
    // 解密密钥netty上下文属性
    public static final AttributeKey<int[]> DECRYPTION_KEY = AttributeKey.valueOf("DECRYPTION_KEY");
    // 加密密钥netty上下文属性
    public static final AttributeKey<int[]> ENCRYPTION_KEY = AttributeKey.valueOf("ENCRYPTION_KEY");


    public static String getAttrValueTypeStringById(int attrValueTypeId) {
        switch (attrValueTypeId) {
            case 1: {
                return "TIME";
            }
            case 2: {
                return "NUM";
            }
            case 3: {
                return "COLOR";
            }
            case 4: {
                return "SPECIAL";
            }
            default: {
                break;
            }
        }
        return null;
    }
}
