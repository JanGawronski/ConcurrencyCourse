package agh.ics.concurrency.lab2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerMultipleBuffer {
    private int buffer = 0;
    private final int bufferMax;
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    public ProducerConsumerMultipleBuffer(int bufferMax) {
        this.bufferMax = bufferMax;
    }   

    /* why doesnt work
    p1  notifies nobody var=1 {p1, p2, c}
    p2  waits                 {p1, c}
    p1  waits                 {c}
    c   notifies p2     var=0 {p2, c}
    c   waits                 {p2}
    p2  notifies p1     var=1 {p1, p2}
    p1  waits                 {p2}
    p2  waits                 {}
    */

    public void produce() {
        lock.lock();
        try {
            while (buffer == bufferMax)
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
            while (buffer == 0)
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
