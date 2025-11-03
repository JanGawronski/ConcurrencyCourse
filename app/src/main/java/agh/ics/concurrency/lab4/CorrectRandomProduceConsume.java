package agh.ics.concurrency.lab4;

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

    private boolean isFirstProducerWaiting = false;
    private boolean isFirstConsumerWaiting = false;

    public CorrectRandomProduceConsume(int bufferMax) {
        this.bufferMax = bufferMax;
    }   

    public void produce(int amount) {
        lock.lock();
        try {
            while (isFirstProducerWaiting)
                restProd.await();
            isFirstProducerWaiting = true;
            while (buffer + amount >= bufferMax)
                firstProd.await();
            buffer += amount;
            isFirstProducerWaiting = false;
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
            while (isFirstConsumerWaiting)
                restCons.await();
            isFirstConsumerWaiting = true;
            while (buffer - amount < 0)
                firstCons.await();
            buffer -= amount;
            isFirstConsumerWaiting = false;
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
