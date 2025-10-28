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

    public void produce(int id) {
        lock.lock();
        try {
            while (lock.hasWaiters(firstProd)) {
                System.out.println(id + " waits on restProd");
                restProd.await();
            }
            int toAdd = (int)(Math.random() * bufferMax / 2) + 1;
            while (buffer + toAdd >= bufferMax) {
                System.out.println(id + " waits on firstProd");
                firstProd.await();
            }           
            buffer += toAdd;
            System.out.println(id + " produced " + toAdd);
            restProd.signal();
            firstCons.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();    
        }    
    }

    public void consume(int id) {
        lock.lock();
        try {
            while (lock.hasWaiters(firstCons)) {
                System.out.println(id + " waits on restCons");
                restCons.await();
            }
            int toRemove = (int)(Math.random() * bufferMax / 2) + 1;
            while (buffer - toRemove < 0) {
                System.out.println(id + " waits on firstCons");
                firstCons.await();
            }
            buffer -= toRemove;
            System.out.println(id + " consumed " + toRemove);
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
