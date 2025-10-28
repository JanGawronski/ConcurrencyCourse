package agh.ics.concurrency.lab4;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class CorrectRandomProduceConsume {
    private int buffer = 0;
    private final int bufferMax;
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition firstProd = lock.newCondition();
    private final Condition restProd = lock.newCondition();
    private final Condition firstCons = lock.newCondition();
    private final Condition restCons = lock.newCondition();

    private final AtomicInteger firstProdWait = new AtomicInteger(0);
    private final AtomicInteger firstConsWait = new AtomicInteger(0);

    public CorrectRandomProduceConsume(int bufferMax) {
        this.bufferMax = 2 * bufferMax;
    }   

    public void produce() {
        lock.lock();
        try {
            while (firstProdWait.get() > 0)
                restProd.await();
            firstProdWait.incrementAndGet();
            int toAdd = (int)(Math.random() * bufferMax);
            while (buffer + toAdd >= bufferMax)
                firstProd.await();
            buffer += toAdd;
            firstProdWait.decrementAndGet();
            restProd.signal();
            firstCons.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();    
        }    
    }

    public void consume() {
        lock.lock();
        try {
            while (firstConsWait.get() > 0)
                restCons.await();
            firstConsWait.incrementAndGet();
            int toRemove = (int)(Math.random() * bufferMax);
            while (buffer - toRemove < 0)
                firstCons.await();
            buffer -= toRemove;
            firstConsWait.decrementAndGet();
            restCons.signal();
            firstProd.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();    
        }
    }

    public void print() {
        System.out.println(this.buffer);
    }

}
