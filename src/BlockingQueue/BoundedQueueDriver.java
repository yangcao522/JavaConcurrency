package BlockingQueue;

public class BoundedQueueDriver {

    public static void main(String[] args) throws InterruptedException {
        BoundedBlockingQueue bq = new BoundedBlockingQueue(10);
        Thread producer = new Thread(new BoundQueueProducer(bq));
        Thread consumer = new Thread(new BoundQueueConsumer(bq));
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
    }

}
