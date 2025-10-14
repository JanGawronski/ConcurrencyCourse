package agh.ics.concurrency.lab1;

public class IncDec {
    int number = 0;

    public IncDec() {
    }

    public void run() {
        IncDec IncDec = new IncDec();
        Thread[] threads = new Thread[100];

        for (int i = 0; i < 50; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    IncDec.inc();
                }
            });
        }

        for (int i = 50; i < 100; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    IncDec.dec();
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

        IncDec.print();

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
