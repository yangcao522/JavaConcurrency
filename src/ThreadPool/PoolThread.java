package ThreadPool;


import java.util.concurrent.BlockingQueue;

public class PoolThread extends Thread {
    private BlockingQueue<Runnable> taskQueue;
    private boolean isStop;
    public PoolThread(BlockingQueue<Runnable> taskQueue) {
        this.taskQueue = taskQueue;
    }

    public void run() {
        while (!isStop()) {
            try {
                Runnable runnable = taskQueue.poll();
                runnable.run();
            } catch(Exception e) {

            }
        }
    }

    public synchronized void doStop() {
        isStop = true;
        this.interrupt();
    }

    private synchronized boolean isStop() {
        return isStop;
    }

}
