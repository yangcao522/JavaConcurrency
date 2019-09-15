package ThreadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ThreadPool {
    private BlockingQueue<Runnable> taskQueue;
    private List<PoolThread> threadPool;
    private boolean isStopped;

    public ThreadPool(int numOfThreads, int numOfTasks) {
        taskQueue = new ArrayBlockingQueue<>(numOfTasks);
        threadPool = new ArrayList<>();
        for (int i = 0; i < numOfThreads; i ++) {
            threadPool.add(new PoolThread(taskQueue));
        }
        for (int i = 0; i < numOfThreads; i ++) {
            threadPool.get(i).start();
        }
    }

    public synchronized void execute(Runnable task) throws InterruptedException{
        if (this.isStopped) throw new IllegalStateException("thread pool is dead");
        this.taskQueue.add(task);
    }

    public synchronized void stop() {
        isStopped = true;
        for (int i = 0; i < threadPool.size(); i ++) {
            threadPool.get(i).doStop();
        }
    }

}
