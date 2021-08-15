package com.github.codingob.se.juc;

/**
 * Synchronized
 *
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
public class SynchronizedDemo {
    public static void main(String[] args) {

    }

    /**
     * 卖票示例
     */
    public static void ticketDemo() {
        System.out.println("---------卖票示例---------");
        Ticket ticket = new Ticket();
        class TicketThread implements Runnable {
            final int number = 99;

            @Override
            public void run() {
                for (int i = 0; i < number; i++) {
//                    ticket.sale();
                    ticket.saleSynchronized();
                }
            }
        }
        new Thread(new TicketThread()).start();
        new Thread(new TicketThread()).start();
        new Thread(new TicketThread()).start();
    }

    /**
     * 生产者消费者示例
     */
    public static void flagDemo() {
        System.out.println("---------生产者消费者示例---------");
        Flag flag = new Flag();
        class FlagThread implements Runnable {
            final int number = 10;
            private final int thread;

            public FlagThread(int thread) {
                this.thread = thread;
            }

            @Override
            public void run() {
                for (int i = 0; i < number; i++) {
                    if (thread==0){
                        flag.provider();
                    }
                    else {
                        flag.consumer();
                    }
                }
            }
        }
        new Thread(new FlagThread(0)).start();
        new Thread(new FlagThread(0)).start();
        new Thread(new FlagThread(1)).start();
        new Thread(new FlagThread(1)).start();
    }

}

class Ticket {
    private int ticket = 99;

    /**
     * 卖票(synchronized)
     */
    public synchronized void saleSynchronized() {
        if (ticket > 0) {
            System.out.println(Thread.currentThread().getName() + "---卖出了第" + ticket-- + "张票,剩余" + ticket + "张票");
        }
    }

    /**
     * 卖票
     */
    public void sale() {
        if (ticket > 0) {
            System.out.println(Thread.currentThread().getName() + "---卖出了第" + ticket-- + "张票,剩余" + ticket + "张票");
        }
    }
}

class Flag {
    private int flag = 0;

    public synchronized void provider() {
        // 注意点，防止虚假唤醒if
        while (flag == 1) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        flag++;
        System.out.println(Thread.currentThread().getName() + "生产者->" + flag);
        this.notifyAll();
    }

    public synchronized void consumer() {
        while (flag == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        flag--;
        System.out.println(Thread.currentThread().getName() + "消费者->" + flag);
        this.notifyAll();
    }
}
