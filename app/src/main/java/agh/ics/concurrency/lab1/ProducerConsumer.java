package agh.ics.concurrency.lab1;

public class ProducerConsumer {
    boolean available = false;

    public ProducerConsumer() {
    }

    public boolean produce() {
        if (!available) {
            available = true;
            return true;
        }
        else 
            return false;
    }

    public boolean consume() {
        if (available) {
            available = false;
            return true;
        }
        else 
            return false;
    }

    public void print() {
        System.out.println(this.available);
    }

}
