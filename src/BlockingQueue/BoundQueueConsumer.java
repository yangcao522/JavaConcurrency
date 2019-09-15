package BlockingQueue;

public class BoundQueueConsumer implements Runnable {
    private BoundedBlockingQueue q;

    public BoundQueueConsumer(BoundedBlockingQueue q) {
        this.q = q;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int take = q.dequeue();
                System.out.println("[Consumer] Take : " + take);
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
