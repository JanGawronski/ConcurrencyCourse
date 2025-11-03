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
        this.bufferMax = bufferMax;
    }   

    public void produce(int amount) {
        lock.lock();
        try {
            while (lock.hasWaiters(firstProd))
                restProd.await();
            while (buffer + amount >= bufferMax)
                firstProd.await();
            buffer += amount;
            restProd.signal();
            firstCons.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();    
        }    
    }

    public void consume(int amount) {
        lock.lock();
        try {
            while (lock.hasWaiters(firstCons))
                restCons.await();
            while (buffer - amount < 0)
                firstCons.await();
            buffer -= amount;
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
