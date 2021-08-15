package com.github.codingob.se.juc;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * ForkJoin
 *
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
public class ForkJoinDemo extends RecursiveTask<Long> {
    private final Long start;
    private final Long end;
    private final static long MAX = 10_0000_0000L;

    public ForkJoinDemo(Long start, Long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long temp = 10000L;
        if ((end - start) > temp) {
            long sum = 0L;
            for (Long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        } else {
            Long middle = (start + end) / 2;
            ForkJoinDemo forkJoinDemo1 = new ForkJoinDemo(start, middle);
            forkJoinDemo1.fork();
            ForkJoinDemo forkJoinDemo2 = new ForkJoinDemo(middle, end);
            forkJoinDemo2.fork();
            return forkJoinDemo1.join() + forkJoinDemo2.join();
        }
    }

    public static void main(String[] args) {
        sum();
        forkSum();
        streamSum();
    }

    public static void sum() {
        long sum = 0;
        long start = System.currentTimeMillis();
        for (long i = 0L; i <= MAX; i++) {
            sum += i;
        }
        System.out.println(sum);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    public static void forkSum() {
        long start = System.currentTimeMillis();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinDemo forkJoinDemo = new ForkJoinDemo(0L, MAX);
        ForkJoinTask<Long> submit = forkJoinPool.submit(forkJoinDemo);
        try {
            System.out.println(submit.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    public static void streamSum() {
        long start = System.currentTimeMillis();
        System.out.println(LongStream.range(0L, MAX).parallel().reduce(0, Long::sum));
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
