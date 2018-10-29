package app.client.utils;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.ThreadFactory;

/**
 * Created by zh on 2018/10/18.
 */
public class DisruptorMain2 {
    public static void main(String[] args) throws Exception {
        // 队列中的元素
        class Element {

            private int value;

            public int get() {
                return value;
            }

            public void set(int value) {
                this.value = value;
            }

        }

        int b = 0;
        while(true){
            b++;
            Element a =  new Element();
            a.set(b);
            System.out.println(a.get());
        }
    }
}
