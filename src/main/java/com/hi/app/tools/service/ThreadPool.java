package com.hi.app.tools.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

public class ThreadPool {

    private static Logger logger = LoggerFactory.getLogger(ThreadPool.class);

    private static Integer corePoolSize = 70; //最大核心线程数
    private static Integer maximumPoolSize = 100; //最大线程数
    private static Integer keepAliveTime = 10; //线程允许空闲时间
    private static Integer maxQueueSize = 500; //阻塞队列最大数量
    //线程池
    private static ExecutorService executor = new ThreadPoolExecutor(corePoolSize, //核心线程数
            maximumPoolSize, //最大线程数
            keepAliveTime,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(maxQueueSize, true), //线程池阻塞队列
            new MyHandler());

    //缓存线程池
    private static ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

    //创建定长线程池 线程池的最大核心线程数和最大线程数相等且固定，为入参
    private static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(corePoolSize);

    //创建单例线程池 线程池中只有一个线程只执行任务，其他任务都在排队
    private static ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();

    //创建定长可执行任务线程池
    private static ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(corePoolSize);

    //创建单例可执行任务线程池
    private static ScheduledExecutorService singleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor();

    public static void main(String[] args) {
        String message = "";
        executor.execute(new MyTherd(message));
        cachedThreadPool.execute(new MyTherd(message));
        fixedThreadPool.execute(new MyTherd(message));
        singleThreadExecutor.execute(new MyTherd(message));

        //定时任务线程池的使用，singleThreadScheduledExecutor同理
        scheduledThreadPool.execute(new MyTherd(message)); //普通使用
        scheduledThreadPool.schedule(new MyTherd(message), 2, TimeUnit.SECONDS); //延迟两秒执行
        scheduledThreadPool.scheduleAtFixedRate(new MyTherd(message), 2, 4, TimeUnit.SECONDS); //延迟两秒每4秒执行一次

        singleThreadScheduledExecutor.scheduleAtFixedRate(new MyTherd(message), 2, 4, TimeUnit.SECONDS); //延迟两秒每4秒执行一次
    }

    /**
     * 线程工厂，用于提交任务给线程池
     */
    static class MyTherd implements Runnable {

        private String message;

        public MyTherd(String message) {
            this.message = message;
        }

        @Override
        public void run() {

        }
    }

    /**
     * 线程池队列中任务超出最大数量处理机制
     */
    static class MyHandler implements RejectedExecutionHandler{
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            try {
                logger.info("current threads count is: " + executor.getActiveCount());
                Thread.sleep(3000); //等待3S,消息队列有空闲，重新提交
                executor.execute(r);
            }catch (Exception e){
                logger.error("RejectedExecutionHandler Error", e);
            }
        }
    }
}