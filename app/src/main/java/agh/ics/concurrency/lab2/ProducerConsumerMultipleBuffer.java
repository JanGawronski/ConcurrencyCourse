package agh.ics.concurrency.lab2;

public class ProducerConsumerMultipleBuffer {
    private int buffer = 0;
    private final int bufferMax;

    public ProducerConsumerMultipleBuffer(int bufferMax) {
        this.bufferMax = bufferMax;
    }   

    synchronized public void produce() {
        while(buffer >= bufferMax) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        buffer++;
        notify();
    }

    synchronized public void consume() {
        while(buffer <= 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        buffer--;
        notify();
    }

    public void print() {
        System.out.println(this.buffer);
    }

}
