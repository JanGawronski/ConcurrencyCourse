package agh.ics.concurrency.lab2;

public class ProducerConsumer {
    private boolean available = false;

    public ProducerConsumer() {
    }

    synchronized public void produce() {
        while(available) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        available = true;
        notify();
    }

    synchronized public void consume() {
        while(!available) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        available = false;
        notify();
    }

    synchronized public void print() {
        System.out.println(this.available);
    }

}
