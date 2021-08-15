package com.github.codingob.se.juc.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock示例
 *
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
public class ReentrantLockDemo {

    /**
     * 卖票示例
     */
    public static void ticketDemo() {
        class Ticket {
            private int ticket = 99;
            private final Lock lock = new ReentrantLock();

            public void sale() {
                lock.lock();
                try {
                    if (ticket > 0) {
                        System.out.println(Thread.currentThread().getName() + "---卖出了第" + ticket-- + "张票,剩余" + ticket + "张票");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
        Ticket ticket = new Ticket();
        class TicketThread implements Runnable {
            final int number = 99;

            @Override
            public void run() {
                for (int i = 0; i < number; i++) {
                    ticket.sale();
                }
            }
        }
        System.out.println("---------卖票示例---------");
        new Thread(new TicketThread()).start();
        new Thread(new TicketThread()).start();
        new Thread(new TicketThread()).start();
    }

    /**
     * 生产者消费者示例
     */
    public static void flagDemo() {
        class Flag {
            private int flag = 0;
            private final Lock lock = new ReentrantLock();
            private final Condition condition = lock.newCondition();

            public void provider() {
                lock.lock();
                try {
                    while (flag == 1) {
                        condition.await();
                    }
                    flag++;
                    System.out.println(Thread.currentThread().getName() + "生产者->" + flag);
                    condition.signalAll();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }

            public void consumer() {
                lock.lock();
                try {
                    while (flag == 0) {
                        condition.await();
                    }
                    flag--;
                    System.out.println(Thread.currentThread().getName() + "消费者->" + flag);
                    condition.signalAll();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
        Flag flag = new Flag();
        final int number = 10;
        class FlagThread implements Runnable {
            private final int thread;

            public FlagThread(int thread) {
                this.thread = thread;
            }

            @Override
            public void run() {
                for (int i = 0; i < number; i++) {
                    if (thread == 0) {
                        flag.provider();
                    } else {
                        flag.consumer();
                    }
                }
            }
        }
        System.out.println("---------生产者消费者示例---------");
        new Thread(new FlagThread(0)).start();
        new Thread(new FlagThread(0)).start();
        new Thread(new FlagThread(1)).start();
        new Thread(new FlagThread(1)).start();
    }

    /**
     * 指定唤醒示例
     */
    public static void conditionDemo() {
        class Flag {
            private int thread = 1;
            private final Lock lock = new ReentrantLock();
            private final Condition condition1 = lock.newCondition();
            private final Condition condition2 = lock.newCondition();
            private final Condition condition3 = lock.newCondition();

            public void condition1() {
                lock.lock();
                try {
                    while (thread != 1) {
                        condition1.await();
                    }
                    System.out.println(Thread.currentThread().getName() + "->A");
                    thread = 2;
                    condition2.signal();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }

            public void condition2() {
                lock.lock();
                try {
                    int i = 2;
                    while (thread != i) {
                        condition2.await();
                    }
                    System.out.println(Thread.currentThread().getName() + "->B");
                    thread = 3;
                    condition3.signal();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }

            public void condition3() {
                lock.lock();
                try {
                    int i = 3;
                    while (thread != i) {
                        condition3.await();
                    }
                    System.out.println(Thread.currentThread().getName() + "->C");
                    thread = 1;
                    condition1.signal();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }

        }
        Flag flag = new Flag();
        final int number = 10;
        class ConditionThread implements Runnable {
            private final int thread;

            public ConditionThread(int thread) {
                this.thread = thread;
            }

            @Override
            public void run() {
                for (int i = 0; i < number; i++) {
                    switch (thread) {
                        case 2:
                            flag.condition2();
                            break;
                        case 3:
                            flag.condition3();
                            break;
                        default:
                            flag.condition1();
                            break;
                    }
                }
            }
        }
        System.out.println("---------指定唤醒示例---------");
        new Thread(new ConditionThread(1)).start();
        new Thread(new ConditionThread(2)).start();
        new Thread(new ConditionThread(3)).start();
    }
}
