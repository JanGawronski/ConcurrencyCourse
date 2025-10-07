package agh.ics.concurrency;

public class Lab1 {
    int number = 0;

    public Lab1() {
    }

    public void run() {
        Lab1 lab1 = new Lab1();
        Thread[] threads = new Thread[100];

        for (int i = 0; i < 50; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    lab1.inc();
                }
            });
        }

        for (int i = 50; i < 100; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    lab1.dec();
                }
            });
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        lab1.print();

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
