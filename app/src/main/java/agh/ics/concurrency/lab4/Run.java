package agh.ics.concurrency.lab4;

public class Run {

    public Run() {
    }
    
    public void run(int producersCount, int consumersCount, int bufferMax) {
        IncorrectRandomProduceConsume pc = new IncorrectRandomProduceConsume(bufferMax);

        Thread[] producers = new Thread[producersCount];
        Thread[] consumers = new Thread[consumersCount];

        for (int j = 0; j < producersCount; j++) {
            final int producerId = j;
            producers[j] = new Thread(() -> {
                for (int i = 0;; i++) {
                    pc.produce(producerId);
                }
            });
        }

        for (int j = 0; j < consumersCount; j++) {
            final int consumerId = j;
            consumers[j] = new Thread(() -> {
                for (int i = 0;; i++) {
                    pc.consume(consumerId);
                }
            });
        }

        for (Thread producer : producers) {
            producer.start();
        }

        for (Thread consumer : consumers) {
            consumer.start();
        }

        try {
            for (Thread producer : producers) {
                producer.join();
            }
            for (Thread consumer : consumers) {
                consumer.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        pc.print();
    }

}
