package com.github.codingob.se.colleticon.queue;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * DelayQueue
 *
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
public class DelayQueueDemo {
    public static void main(String[] args) {
        DelayQueue<Delayed> queue = new DelayQueue<>();
        queue.add(new DelayedTask());
        System.out.println(queue);
    }
}

class DelayedTask implements Delayed {

    @Override
    public long getDelay(TimeUnit unit) {
        return 0;
    }

    @Override
    public int compareTo(Delayed o) {
        return 0;
    }
}
