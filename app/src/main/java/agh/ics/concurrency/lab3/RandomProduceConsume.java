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

    public void produce(int amount) {
        lock.lock();
        try {
            while (buffer + amount >= bufferMax)
                notFull.await();
            buffer += amount;
            notEmpty.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();    
        }    
    }

    public void consume(int amount) {
        lock.lock();
        try {
            while (buffer - amount < 0)
                notEmpty.await();
            buffer -= amount;
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
