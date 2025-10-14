package agh.ics.concurrency.lab2;

public class Run {

    public Run() {
    }
    
    public void run() {
        final int loops = 1000;
        ProducerConsumer pc = new ProducerConsumer();

        Thread producer = new Thread(() -> {
            for (int i = 0;; i++) {
                pc.produce();
                System.out.println("Produced");
            }
        });

        Thread producer1 = new Thread(() -> {
            for (int i = 0;; i++) {
                pc.produce();
                System.out.println("Produced");
            }
        });

        Thread consumer = new Thread(() -> {
            for (int i = 0;; i++) {
                pc.consume();
                System.out.println("Consumed");
            }
        });

        producer.start();
        producer1.start();
        consumer.start();

        try {
            producer.join();
            producer1.join();
            consumer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        pc.print();
    }

}
