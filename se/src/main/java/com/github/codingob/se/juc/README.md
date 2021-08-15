# Java多线程

## 创建线程方式

1. 继承Thread类
2. 实现Runnable接口
3. 实现Callable接口
4. 线程池

[示例](./src/main/java/com/github/codingob/juc/thread)

## 创建线程池方式

1. 4个方法
    * SingleThreadExecutor
    * FixedThreadPool
    * CachedThreadPool
    * ScheduledThreadPool
    * ThreadPoolExecutor: 手动创建线程池
2. 7个参数
    * 核心线程池大小
    * 最大核心线程池大小
        * CPU密集型,几核CUP定义几`Runtime.getRuntime().availableProcessors()`
        * IO密集型,2倍IO任务数
        * 混合型
    * 超时时间
    * 超时时间单位
    * 阻塞队列
    * 线程工厂
    * 拒绝策略
3. 4种拒绝策略
    * AbortPolicy
    * CallerRunsPolicy
    * DiscardPolicy
    * DiscardOldestPolicy

[示例](./src/main/java/com/github/codingob/juc/thread/pool)

## `volatile`

1. 保证可见性
2. 不保证原子性
3. 禁止指令重排

[示例](./src/main/java/com/github/codingob/juc/VolatileDemo.java)

## 原子类

[示例](./src/main/java/com/github/codingob/juc/AtomicDemo.java)

## `synchronize`

同步

多线程协调

1. wait: 使线程进入等待状态
2. notify: 唤醒其他等待线程

wait和sleep的区别

1. wait会释放锁，sleep不会释放锁
2. wait是Object类的方法，用于线程通信
3. wait必须在同步代码块或方法中

[示例](src/main/java/com/github/codingob/juc/SynchronizedDemo.java)

## `Lock`

1. 什么是锁？
    * 锁是 java 并发编程中最重要地同步机制
2. 锁的是谁？
    * 实例对象
    * 类

### 分类

1. 公平锁: 不可插队
2. 非公平锁(默认): 可插队

操作

1. lock.lock(): 上锁
2. lock.unlock(): 释放锁

### `Condition`

监视器

1. await: 使线程进入等待状态
2. signal: 唤醒其他等待线程

### `ReentrantLock`

可重入锁

[示例](./src/main/java/com/github/codingob/juc/lock/ReentrantLockDemo.java)

### `ReentrantReadWriteLock`

可重入锁读写锁

[示例](./src/main/java/com/github/codingob/juc/lock/ReentrantReadWriteLockDemo.java)

synchronize和Lock区别:

1. synchronize是内置关键字;Lock是Java类
2. synchronize无法判断获取锁的状态;Lock可以
3. synchronize会自动释放锁;lock手动释放锁，不释放会**死锁**
4. synchronize会等待锁;lock不一定会
5. synchronize可重入锁，不可中断的，非公平的;lock可重入锁,可判断锁，非公平/公平
7. 适合锁少量的代码同步问题;Lock适合锁大量的同步代码

## 集合操作

多线程下集合操作并不都是线程安全的

1. 安全
    * Vector
    * Stack
    * HashTable

通过以下方式解决

1. list
    * synchronized
    * Vector
    * Collection.synchronizedList
    * CopyOnWriteArrayList
2. set
    * Collections.synchronizedSet
    * CopyOnWriteArraySet
3. Map
    * Collection.synchronizedMap
    * ConcurrentHashMap

[示例](./src/main/java/com/github/codingob/juc/collection)

## `BlockingQueue`

阻塞队列

[示例](./src/main/java/com/github/codingob/juc/queue/BlockingQueueDemo.java)

## `SynchronousQueue`

同步队列

[示例](./src/main/java/com/github/codingob/juc/queue/SynchronousQueueDemo.java)

## `CountDownLatchDemo`

## `CyclicBarrier`

## `Semaphore`

## `Fork/Join`