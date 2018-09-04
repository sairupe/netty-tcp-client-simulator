package app.client.data;

/**
 * Created by zh on 2018/9/4.
 */
public class StatisticHolder {

    private static int clientCount = 0;

    public static int getClientCount() {
        return clientCount;
    }

    public static void incClientCount(int clientCount) {
        ++StatisticHolder.clientCount;
    }
}
