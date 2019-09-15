package BlockingQueue;

public class BoundQueueProducer implements Runnable{
    private BoundedBlockingQueue q;

    public BoundQueueProducer(BoundedBlockingQueue q) {
        this.q = q;
    }

    @Override
    public void run() {
        try {
            // Put 20 ints into Queue
            for (int i = 0; i < 20; i++) {
                System.out.println("[Producer] Put : " + i);
                q.enqueue(i);
                System.out.println("[Producer] Queue remainingCapacity : " + q.size());
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
