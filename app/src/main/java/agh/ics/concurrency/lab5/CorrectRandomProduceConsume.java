package agh.ics.concurrency.lab5;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class CorrectRandomProduceConsume {
    private int buffer = 0;
    private final int bufferMax;
    private final ReentrantLock prodLock = new ReentrantLock();
    private final Condition prod = prodLock.newCondition();
    private final ReentrantLock consLock = new ReentrantLock();
    private final Condition cons = consLock.newCondition();
    private final ReentrantLock lock = new ReentrantLock();


    public CorrectRandomProduceConsume(int bufferMax) {
        this.bufferMax = bufferMax;
    }   

    public void produce(int amount) {
        prodLock.lock();
        try {
            while (buffer + amount >= bufferMax)
                prod.await();
            lock.lock();
            try {
                buffer += amount;
            } finally {
                lock.unlock();
            }
            cons.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            prodLock.unlock();    
        }    
    }

    public void consume(int amount) {
        consLock.lock();
        try {
            while (buffer - amount < 0)
                cons.await();
            lock.lock();
            try {
                buffer -= amount;
            } finally {
                lock.unlock();
            }
            prod.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            consLock.unlock();    
        }    
    }

    public void print() {
        System.out.println(this.buffer);
    }

}
