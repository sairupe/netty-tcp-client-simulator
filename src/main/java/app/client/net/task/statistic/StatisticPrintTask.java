package app.client.net.task.statistic;

import app.client.data.StatisticHolder;

/**
 * Created by zh on 2018/9/6.
 */
public class StatisticPrintTask implements Runnable {
    @Override
    public void run() {
        StatisticHolder.print();
    }
}
