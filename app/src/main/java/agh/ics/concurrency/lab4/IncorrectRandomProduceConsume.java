package agh.ics.concurrency.lab4;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class IncorrectRandomProduceConsume {
    private int buffer = 0;
    private final int bufferMax;
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition firstProd = lock.newCondition();
    private final Condition restProd = lock.newCondition();
    private final Condition firstCons = lock.newCondition();
    private final Condition restCons = lock.newCondition();

    public IncorrectRandomProduceConsume(int bufferMax) {
        this.bufferMax = 2 * bufferMax;
    }   

    public void produce() {
        lock.lock();
        try {
            while (lock.hasWaiters(firstProd))
                restProd.await();
            int toAdd = (int)(Math.random() * bufferMax);
            while (buffer + toAdd >= bufferMax)
                firstProd.await();
            buffer += toAdd;
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
            while (lock.hasWaiters(firstCons))
                restCons.await();
            int toRemove = (int)(Math.random() * bufferMax);
            while (buffer - toRemove < 0)
                firstCons.await();
            buffer -= toRemove;
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
