import java.util.LinkedList;
import java.util.Queue;

/**
 * Demonstration of the Producer-Consumer problem using wait() and notify().
 *
 * The Producer-Consumer problem is a classic synchronization problem where:
 * - Producers generate data and put it in a shared buffer
 * - Consumers take data from the buffer and process it
 * - The buffer has a limited capacity
 * - Producers must wait if buffer is full
 * - Consumers must wait if buffer is empty
 */
public class ProducerConsumer {

    public static void main(String[] args) {
        SharedBuffer buffer = new SharedBuffer(5);

        // Create producer thread
        Thread producer = new Thread(new Producer(buffer), "Producer");

        // Create multiple consumer threads
        Thread consumer1 = new Thread(new Consumer(buffer), "Consumer-1");
        Thread consumer2 = new Thread(new Consumer(buffer), "Consumer-2");

        // Start all threads
        producer.start();
        consumer1.start();
        consumer2.start();

        // Let threads run for a while
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Interrupt threads to stop them
        producer.interrupt();
        consumer1.interrupt();
        consumer2.interrupt();

        System.out.println("\nProducer-Consumer demo completed!");
    }
}

/**
 * Shared buffer with fixed capacity.
 * Uses synchronized methods and wait/notify for thread coordination.
 */
class SharedBuffer {
    private final Queue<Integer> queue;
    private final int capacity;

    public SharedBuffer(int capacity) {
        this.queue = new LinkedList<>();
        this.capacity = capacity;
    }

    /**
     * Producer calls this to add items to the buffer.
     * Waits if buffer is full.
     */
    public synchronized void produce(int item) throws InterruptedException {
        // Wait while buffer is full
        while (queue.size() == capacity) {
            System.out.println(Thread.currentThread().getName() +
                             " waiting... Buffer is FULL");
            wait();
        }

        // Add item to buffer
        queue.add(item);
        System.out.println(Thread.currentThread().getName() +
                         " produced: " + item + " | Buffer size: " + queue.size());

        // Notify waiting consumers
        notifyAll();
    }

    /**
     * Consumer calls this to remove items from the buffer.
     * Waits if buffer is empty.
     */
    public synchronized int consume() throws InterruptedException {
        // Wait while buffer is empty
        while (queue.isEmpty()) {
            System.out.println(Thread.currentThread().getName() +
                             " waiting... Buffer is EMPTY");
            wait();
        }

        // Remove item from buffer
        int item = queue.poll();
        System.out.println(Thread.currentThread().getName() +
                         " consumed: " + item + " | Buffer size: " + queue.size());

        // Notify waiting producers
        notifyAll();

        return item;
    }
}

/**
 * Producer thread that generates items and puts them in the buffer.
 */
class Producer implements Runnable {
    private final SharedBuffer buffer;
    private int itemCounter = 0;

    public Producer(SharedBuffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                int item = ++itemCounter;
                buffer.produce(item);

                // Simulate time taken to produce
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " stopped.");
        }
    }
}

/**
 * Consumer thread that takes items from the buffer and processes them.
 */
class Consumer implements Runnable {
    private final SharedBuffer buffer;

    public Consumer(SharedBuffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                int item = buffer.consume();

                // Simulate time taken to consume/process
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " stopped.");
        }
    }
}
