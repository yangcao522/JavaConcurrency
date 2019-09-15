package ReadWriteLock;

import java.util.Map;

public class ReadWriteLock {
    private Map<Thread, Integer> readingThreads;

    private int writeAccesses;
    private int writeRequests;
    private Thread writingThread;

    public synchronized void lockRead() throws InterruptedException {
        Thread currentThread = Thread.currentThread();
        while (!isGrantedRead()) {
            wait();
        }

        readingThreads.put(currentThread, readingThreads.getOrDefault(currentThread, 0) + 1);
    }

    public synchronized void unlockRead() {
        Thread currentThread = Thread.currentThread();
        int count = readingThreads.getOrDefault(currentThread, 0);
        if (count == 1) readingThreads.remove(currentThread);
        else readingThreads.put(currentThread, count - 1);
        notifyAll();
    }

    public synchronized void lockWrite() throws InterruptedException {
        writeRequests ++;
        while (!isGrantedWrite()) {
            wait();
        }
        writeRequests --;
        writeAccesses ++;
        writingThread = Thread.currentThread();
    }

    public synchronized void unlockWrite() {
        writeAccesses --;
        if (writeAccesses == 0) writingThread = null;
        notifyAll();
    }

    private boolean isGrantedRead() {
        if (writeAccesses > 0) return false;
        if (curThreadAlreadyGetReadAccess(Thread.currentThread())) return true;
        if (writeRequests > 0) return false;
        return true;
    }

    private boolean curThreadAlreadyGetReadAccess(Thread thread) {
        return readingThreads.containsKey(thread);
    }

    private boolean isGrantedWrite() {
        if (readingThreads.size() > 0) return false;
        if(writingThread == null)    return true;
        if (writingThread != Thread.currentThread()) return false;
        return true;
    }
}
