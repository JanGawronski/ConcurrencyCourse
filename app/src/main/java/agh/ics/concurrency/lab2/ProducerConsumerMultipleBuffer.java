package agh.ics.concurrency.lab2;

import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerMultipleBuffer {
    private int buffer = 0;
    private final int bufferMax;
    private final ReentrantLock lock = new ReentrantLock();

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
        while (true) {
            lock.lock();
            if (buffer == bufferMax) {
                lock.unlock();
            } else {
                break;
            }
        }

        buffer++;
        lock.unlock();
    }

    public void consume() {
        while (true) {
            lock.lock();
            if (buffer == 0) {
                lock.unlock();
            } else {
                break;
            }
        }

        buffer--;
        lock.unlock();
    }

    public void print() {
        System.out.println(this.buffer);
    }

}
