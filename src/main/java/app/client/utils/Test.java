package app.client.utils;

import java.util.concurrent.Executors;

/**
 * Created by zh on 2018/10/30.
 */
public class Test {
    public static void main(String[] args){
        System.out.println(65687 >> 16);
        System.out.println(1 << 16);
        System.out.println(65687 - (1 << 16));
    }
}
