package agh.ics.concurrency.lab5;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class NestedLocks {
    private int buffer = 0;
    private final int bufferMax;
    private final ReentrantLock prodLock = new ReentrantLock(true);
    private final ReentrantLock consLock = new ReentrantLock(true);
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition prod = lock.newCondition();
    private final Condition cons = lock.newCondition();


    public NestedLocks(int bufferMax) {
        this.bufferMax = bufferMax;
    }   

    public void produce(int amount) {
        prodLock.lock();
        lock.lock();
        try {
            while (buffer + amount >= bufferMax)
                prod.await();
            buffer += amount;
            cons.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            prodLock.unlock();    
        }    
    }

    public void consume(int amount) {
        consLock.lock();
        lock.lock();
        try {
            while (buffer - amount < 0)
                cons.await();
            buffer -= amount;
            prod.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            consLock.unlock();    
        }    
    }

    public void print() {
        System.out.println(this.buffer);
    }

}
