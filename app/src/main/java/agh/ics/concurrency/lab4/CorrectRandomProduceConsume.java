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

    private int firstProdWait = 0;
    private int firstConsWait = 0;

    public CorrectRandomProduceConsume(int bufferMax) {
        this.bufferMax = bufferMax;
    }   

    public void produce() {
        lock.lock();
        try {
            while (firstProdWait > 0)
                restProd.await();
            firstProdWait++;
            int toAdd = (int)(Math.random() * bufferMax / 2) + 1;
            while (buffer + toAdd >= bufferMax)
                firstProd.await();
            buffer += toAdd;
            firstProdWait--;
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
            while (firstConsWait > 0)
                restCons.await();
            firstConsWait++;
            int toRemove = (int)(Math.random() * bufferMax / 2) + 1;
            while (buffer - toRemove < 0)
                firstCons.await();
            buffer -= toRemove;
            firstConsWait--;
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
