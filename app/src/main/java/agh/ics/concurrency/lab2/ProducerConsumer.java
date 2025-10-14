package agh.ics.concurrency.lab2;

public class ProducerConsumer {
    private boolean available = false;

    public ProducerConsumer() {
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
