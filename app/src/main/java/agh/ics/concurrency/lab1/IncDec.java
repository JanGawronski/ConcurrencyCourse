package agh.ics.concurrency.lab1;

public class IncDec {
    int number = 0;

    public IncDec() {
    }

    public void inc() {
        this.number++;
    }

    public void dec() {
        this.number--;
    }

    public void print() {
        System.out.println(this.number);
    }

    
}
