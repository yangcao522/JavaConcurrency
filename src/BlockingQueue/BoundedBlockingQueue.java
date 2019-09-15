package BlockingQueue;

import java.util.LinkedList;
import java.util.Queue;

public class BoundedBlockingQueue {
    private Queue<Integer> q;
    private int cap;

    public BoundedBlockingQueue(int capacity) {
        this.q = new LinkedList<>();
        this.cap = capacity;
    }

    public void enqueue(int element) throws InterruptedException {
        synchronized(this) {
            while (q.size() == cap) {
                this.wait();
            }
            if (q.size() == 0) this.notifyAll();
            q.offer(element);
        }
    }

    public int dequeue() throws InterruptedException {
        synchronized(this) {
            while (q.size() == 0) {
                this.wait();
            }
            if (q.size() == cap) this.notifyAll();
            return q.poll();
        }
    }

    public int size() {
        return q.size();
    }
}
