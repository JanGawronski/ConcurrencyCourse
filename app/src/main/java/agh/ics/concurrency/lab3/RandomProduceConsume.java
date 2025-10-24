package agh.ics.concurrency.lab3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class RandomProduceConsume {
    private int buffer = 0;
    private final int bufferMax;
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    public RandomProduceConsume(int bufferMax) {
        this.bufferMax = bufferMax;
    }   

    public void produce() {
        lock.lock();
        try {
            while (buffer >= bufferMax)
                notFull.await();
            buffer += (int)(Math.random() * bufferMax / 2) + 1;
            notEmpty.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();    
        }    
    }

    public void consume() {
        lock.lock();
        try {
            while (buffer <= 0)
                notEmpty.await();
            buffer -= (int)(Math.random() * bufferMax / 2) + 1;
            notFull.signal();
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
