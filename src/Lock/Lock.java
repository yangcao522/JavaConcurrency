package Lock;

public class Lock {
    private boolean isLocked;

    public void lock() throws InterruptedException {
        while (isLocked) {
            wait();
        }
        isLocked = true;
    }

    public void unlock() {
        isLocked = false;
        notifyAll();
    }
}
