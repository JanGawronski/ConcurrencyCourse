package agh.ics.concurrency.lab3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class LockConditionProducerConsumer {
    private boolean available = true;
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition availableLock = lock.newCondition();
    private final Condition notAvailableLock = lock.newCondition();

    public LockConditionProducerConsumer() {
    }   

    public void produce() {
        lock.lock();
        try {
            while (!available)
                availableLock.await();
            available = false;
            notAvailableLock.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();    
        }    
    }

    public void consume() {
        lock.lock();
        try {
            while (available)
                notAvailableLock.await();
            available = true;
            availableLock.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();        
        }
    }

    public void print() {
        System.out.println(this.available);
    }

}
