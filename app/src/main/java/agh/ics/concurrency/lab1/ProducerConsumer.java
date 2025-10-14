package agh.ics.concurrency.lab1;

public class ProducerConsumer {
    boolean available = false;

    public ProducerConsumer() {
    }

    public void runOne() {
        final int loops = 1000;
        Thread producer = new Thread(() -> {
            for (int i = 0; i < loops; i++) {
                int wait = 0;
                while (!produce()) {
                    wait++;
                    if (wait % 100 == 0)
                        System.out.println("Producer waiting");
                }
                System.out.println("Produced " + i);
            }
        });

        Thread consumer = new Thread(() -> {
            for (int i = 0; i < loops; i++) {
                int wait = 0;
                while (!consume()) {
                    wait++;
                    if (wait % 100 == 0)
                        System.out.println("Consumer waiting");
                
                }
                System.out.println("Consumed " + i);
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

        print();
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
