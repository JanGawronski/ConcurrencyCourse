package agh.ics.concurrency.lab1;

public class Run {

    public Run() {
    }
    
    public void runIncDec() {
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

    public void runProducerConsumer() {
        final int loops = 1000;
        ProducerConsumer pc = new ProducerConsumer();

        Thread producer = new Thread(() -> {
            for (int i = 0; i < loops; i++) {
                while (!pc.produce()) {};
                System.out.println("Produced");
            }
        });

        Thread consumer = new Thread(() -> {
            for (int i = 0; i < loops; i++) {
                while (!pc.consume()) {};
                System.out.println("Consumed");
            }
        });

        producer.start();
        consumer.start();

        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        pc.print();
    }

}
