package app.client.utils;


import app.client.common.CommonConsts;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

/**
 * @NAME: StringUtils
 * @AUTHOR: huangxueqing
 * @DATE: 2020-10-28
 * @DESCRIPTION: StringUtils
 **/
public class StringUtils {


    /**
     * 取整
     *
     * @param data
     * @return
     */
    public static String formatBigDecimalRoundUp(BigDecimal data) {
        DecimalFormat df = new DecimalFormat("###.##");
        return df.format(data);
    }

    /**
     * 功能：将一个以2的整数次方为和加在一起的一个整数进行分拆，分拆成一个个2的整数次方形式
     *
     * @param n 要拆分的数
     * @return
     */
    public static List<Integer> splitPower(Integer n) {
        List<Integer> list = new ArrayList<>();
        if (n == null) {
            return list;
        }

        String s = Integer.toBinaryString(n);
        int len = s.length();
        for (int i = 0; i < len; i++) {
            Long cur = Long.parseLong(s.substring(len - i - 1, len - i));
            if (cur > 0) {
                list.add((int) Math.pow(2, i));
            }
        }
        return list;
    }

    /**
     * 功能：将一个以2的整数次方为和加在一起的一个整数进行分拆，分拆成一个个2的整数次方形式
     *
     * @param n 要拆分的数
     * @return
     */
    public static List<Long> splitPower(Long n) {
        if (n == null) {
            return Collections.emptyList();
        }
        List<Long> list = new ArrayList<>();

        String s = Long.toBinaryString(n);
        int len = s.length();
        for (int i = 0; i < len; i++) {
            Long cur = Long.parseLong(s.substring(len - i - 1, len - i));
            if (cur > 0) {
                list.add((long) Math.pow(2, i));
            }
        }
        return list;
    }


    /**
     * 设置缓存过期时间随机数
     *
     * @param origin
     * @param bound
     * @return
     */
    public static Long generateRandomCacheTime(Long origin, Long bound) {
        if (origin.equals(bound)) {
            return ThreadLocalRandom.current().nextLong(origin, (bound + 300));
        }
        return ThreadLocalRandom.current().nextLong(origin, bound);
    }

    /**
     * 数字转中文
     *
     * @return
     */
    public static String int2chineseNum(int src) {
        final String num[] = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
        final String unit[] = {"", "十", "百", "千", "万", "十", "百", "千", "亿", "十", "百", "千"};
        String dst = "";
        int count = 0;
        while (src > 0) {
            dst = (num[src % 10] + unit[count]) + dst;
            src = src / 10;
            count++;
        }
        return dst.replaceAll("零[千百十]", "零").replaceAll("零+万", "万")
                .replaceAll("零+亿", "亿").replaceAll("亿万", "亿零")
                .replaceAll("零+", "零").replaceAll("零$", "");
    }


    public static String hindPhoneNumber(String phone) {
        if (!StringUtils.isEmpty(phone)) {
            if (phone.length() == 11) {
                phone = phone.substring(0, 3) + "****" + phone.substring(7);
            } else if (phone.length() > 1) {
                phone = phone.substring(0, 1) + "****" + phone.substring(phone.length() - 1);
            } else {
                phone = "****";
            }
        }
        return phone;
    }

    public static String getHidenPhoneNo(String originPhone) {
        Integer preShowPhone = 3;
        Integer endShowPhone = 4;
        if (originPhone != null) {
            //中国:11位,展示前3后4，隐藏中间4位
            if (originPhone.length() == 11) {
                if (preShowPhone != null && endShowPhone != null) {
                    originPhone = getHiddenPhoneString(originPhone, preShowPhone, endShowPhone);
                } else {
                    originPhone = getHiddenPhoneString(originPhone, 3, 4);
                }
            } else if (originPhone.length() > 1) {
                originPhone = originPhone.substring(0, 1) + "****" + originPhone.substring(originPhone.length() - 1);
            } else {
                originPhone = "****";
            }
        }
        return originPhone;
    }

    private static String getHiddenPhoneString(String originPhone, int index, int lastIndex) {
        //利用toCharArray方法转换
        char examples[] = originPhone.toCharArray();
        lastIndex = originPhone.length() - lastIndex;
        if (index == 0 && lastIndex == 11) {
            return originPhone;
        } else {
            for (int j = index; j < lastIndex; j++) {
                examples[j] = '*';
            }
        }
        return String.copyValueOf(examples);
    }

    public static String getDeviceEndpointDictName(Integer deviceType) {
        if (deviceType == null) {
            return CommonConsts.EMPTY_STRING;
        }
        switch (deviceType) {
            case 1: {
                return "安卓";
            }
            case 2: {
                return "iOS";
            }
            case 3: {
                return "PC";
            }
            default: {
                return CommonConsts.EMPTY_STRING;
            }
        }
    }

    /**
     * 手机号验证
     *
     * @param str
     * @return 验证通过返回true
     */
    public static boolean isMobile(String str) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        // 验证手机号
        //p = compile("^[1][3,4,5,7,8][0-9]{9}$");
        p = compile("^[1][0-9][0-9]{9}$");
        m = p.matcher(str);
        b = m.matches();
        return b;
    }

    public static boolean isEmpty(String source) {
        return source == null || source.equals("");
    }

    public static String join(Object[] array, String separator) {
        return array == null ? null : org.apache.commons.lang3.StringUtils.join((Object[]) array, separator, 0, array.length);
    }

    public static String replaceString(String source, Object... obj) {
        Object[] mapObjs = Arrays.stream(obj).map(o -> {
            return o == null ? CommonConsts.EMPTY_STRING : o;
        }).toArray(Object[]::new);
        source = MessageFormat.format(source, mapObjs);
        return source;
    }

    /**
     * 利用正则表达式判断字符串是否是数字
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    public static void join() {
    }

    /**
     * byte数组转hex
     *
     * @param bytes
     * @return
     */
    public static String byteToHex(byte[] bytes) {
        String strHex = "";
        StringBuilder sb = new StringBuilder("");
        for (int n = 0; n < bytes.length; n++) {
            strHex = Integer.toHexString(bytes[n] & 0xFF);
            sb.append(strHex.toUpperCase()); // 每个字节由两个字符表示，位数不够，高位补0
        }
        return sb.toString().trim();
    }

    /**
     * hex转byte数组
     *
     * @param hex
     * @return
     */
    public static byte[] hexToByte(String hex) {
        int m = 0, n = 0;
        int byteLen = hex.length(); // 每两个字符描述一个字节
        byte[] ret = new byte[byteLen];
        for (int i = 0; i < byteLen; i++) {
            int intVal = Integer.decode("0x" + hex.charAt(i));
            ret[i] = Byte.valueOf((byte) intVal);
        }
        return ret;
    }

    public static void main(String[] args) {
        byte[] bytes = new byte[]{(byte) 9, (byte) 10, (byte) 11, (byte) 12, (byte) 13, (byte) 14, (byte) 15};
        String s = byteToHex(bytes);
        System.out.println(s);
        byte[] bytes1 = hexToByte(s);
        System.out.println((Arrays.toString(bytes1)));
    }
}
