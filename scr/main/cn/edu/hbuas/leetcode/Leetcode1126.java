package cn.edu.hbuas.leetcode;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class Leetcode1126 {

    private static AtomicInteger number = new AtomicInteger(0);

    public static void main(String[] args) {

        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        DiningPhilosophers2 diningPhilosophers2 = new DiningPhilosophers2();
        for (int i = 0; i < 5; i++) {

            threadPool.execute(() -> {
                int philosopher = number.get();
                number.incrementAndGet();
                while (true) {
                    try {
                        diningPhilosophers2.wantsToEat(philosopher,
                                () -> {
                                    System.out.println(philosopher + ":拿起左筷子");
                                },
                                () -> {
                                    System.out.println(philosopher + ":拿起右筷子");
                                },
                                () -> {
                                    System.out.println(philosopher + ":吃面");
                                },
                                () -> {
                                    System.out.println(philosopher + ":放下右筷子");
                                },
                                () -> {
                                    System.out.println(philosopher + ":放下右筷子");
                                });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}

class DiningPhilosophers1 {
    // 每个lock对应一个叉子
    private final ReentrantLock[] lockList = {new ReentrantLock(),
            new ReentrantLock(),
            new ReentrantLock(),
            new ReentrantLock(),
            new ReentrantLock()};

    // 创建四个信号量，同时只有四位哲学家能够拿起叉子
    Semaphore semaphore = new Semaphore(4);

    public DiningPhilosophers1() {

    }

    // call the run() method of any runnable to execute its code
    public void wantsToEat(int philosopher,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork) throws InterruptedException {
        // 获取左右叉子编号
        int leftFork = (philosopher + 1) % 5;
        int rightFork = philosopher;

        // 一位哲学家进入
        semaphore.acquire();

        // 尝试拿起左右的叉子
        lockList[leftFork].lock();
        lockList[rightFork].lock();

        //拿起左右叉子
        pickLeftFork.run();
        pickRightFork.run();
        // 吃面
        eat.run();
        // 放下叉子
        putLeftFork.run();
        putRightFork.run();
        // 真正放下叉子
        lockList[leftFork].unlock();
        lockList[rightFork].unlock();
        // 哲学家离场
        semaphore.release();

    }
}


class DiningPhilosophers2 {
    // 每个lock对应一个叉子
    private final ReentrantLock[] lockList = {new ReentrantLock(),
            new ReentrantLock(),
            new ReentrantLock(),
            new ReentrantLock(),
            new ReentrantLock()};


    public DiningPhilosophers2() {

    }

    // call the run() method of any runnable to execute its code
    public void wantsToEat(int philosopher,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork) throws InterruptedException {
        // 获取左右叉子编号
        int leftFork = (philosopher + 1) % 5;
        int rightFork = philosopher;

        if (philosopher % 2 == 0) {
            // 尝试拿起左右的叉子
            lockList[leftFork].lock();
            lockList[rightFork].lock();
        } else {
            // 尝试拿起右左的叉子
            lockList[rightFork].lock();
            lockList[leftFork].lock();
        }
        //拿起左右叉子
        pickLeftFork.run();
        pickRightFork.run();
        // 吃面
        eat.run();
        // 放下叉子
        putLeftFork.run();
        putRightFork.run();
        // 真正放下叉子
        lockList[leftFork].unlock();
        lockList[rightFork].unlock();

    }
}