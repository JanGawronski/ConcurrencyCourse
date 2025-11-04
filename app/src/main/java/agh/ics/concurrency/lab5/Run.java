package agh.ics.concurrency.lab5;

public class Run {

    public Run() {
    }
    
    public void run(int producersCount, int consumersCount, int bufferMax) {
        CorrectRandomProduceConsume pc = new CorrectRandomProduceConsume(bufferMax);

        Thread[] producers = new Thread[producersCount];
        Thread[] consumers = new Thread[consumersCount];

        int[] produceLog = new int[producersCount];
        int[] consumeLog = new int[consumersCount];

        int[] produceAmounts = new int[producersCount];
        int[] consumeAmounts = new int[consumersCount];

        for (int j = 0; j < producersCount; j++) {
            produceAmounts[j] = (int)(Math.random() * (bufferMax / 2)) + 1;
        }

        for (int j = 0; j < consumersCount; j++) {
            consumeAmounts[j] = (int)(Math.random() * (bufferMax / 2)) + 1;
        }

        for (int j = 0; j < producersCount; j++) {
            final int producerId = j;
            producers[j] = new Thread(() -> {
                for (produceLog[producerId] = 0;; produceLog[producerId]++) {
                    pc.produce(produceAmounts[producerId]);
                    try {
                        Thread.sleep(10);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }   
                }
            });
        }

        for (int j = 0; j < consumersCount; j++) {
            final int consumerId = j;
            consumers[j] = new Thread(() -> {
                for (consumeLog[consumerId] = 0;; consumeLog[consumerId]++) {
                    pc.consume(consumeAmounts[consumerId]);
                    try {
                        Thread.sleep(10);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }   
                    
                }
            });
        }

        for (Thread producer : producers) {
            producer.start();
        }

        for (Thread consumer : consumers) {
            consumer.start();
        }

        while (true) {
            for (int i = 0; i < producersCount; i++)
                System.out.print("+" + produceAmounts[i] + ": " + produceLog[i] + "  ");
            
            for (int i = 0; i < consumersCount; i++)
                System.out.print("-" + consumeAmounts[i] + ": " + consumeLog[i] + "  ");

            System.out.println();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
