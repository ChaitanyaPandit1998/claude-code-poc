import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Demonstration of the Producer-Consumer problem using BlockingQueue.
 *
 * This implementation uses Java's built-in ArrayBlockingQueue from java.util.concurrent
 * package, which handles all synchronization automatically. This is the modern,
 * industry-standard approach for implementing producer-consumer patterns.
 *
 * Key differences from manual synchronization approach:
 * - No need for synchronized, wait(), or notifyAll()
 * - Thread-safe operations built-in
 * - Automatic blocking when queue is full or empty
 * - Simpler and less error-prone
 */
public class ProducerConsumerBlocking {

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
 * Shared buffer with fixed capacity using ArrayBlockingQueue.
 *
 * ArrayBlockingQueue is a bounded blocking queue backed by an array.
 * It automatically handles all thread synchronization:
 * - put() blocks when queue is full
 * - take() blocks when queue is empty
 * - No need for manual synchronization
 */
class SharedBuffer {
    private final BlockingQueue<Integer> queue;
    private final int capacity;

    public SharedBuffer(int capacity) {
        this.queue = new ArrayBlockingQueue<>(capacity);
        this.capacity = capacity;
    }

    /**
     * Producer calls this to add items to the buffer.
     * Blocks automatically if buffer is full (no explicit wait needed).
     */
    public void produce(int item) throws InterruptedException {
        // Note: put() blocks silently when queue is full
        // Unlike the manual approach, there's no explicit "waiting" message
        queue.put(item);

        // Log after successful addition
        System.out.println(Thread.currentThread().getName() +
                         " produced: " + item + " | Buffer size: " + queue.size());
    }

    /**
     * Consumer calls this to remove items from the buffer.
     * Blocks automatically if buffer is empty (no explicit wait needed).
     */
    public int consume() throws InterruptedException {
        // Note: take() blocks silently when queue is empty
        // Unlike the manual approach, there's no explicit "waiting" message
        int item = queue.take();

        // Log after successful removal
        System.out.println(Thread.currentThread().getName() +
                         " consumed: " + item + " | Buffer size: " + queue.size());

        return item;
    }
}

/**
 * Producer thread that generates items and puts them in the buffer.
 * Identical to the original implementation - only the buffer's internal
 * synchronization mechanism has changed.
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
 * Identical to the original implementation - only the buffer's internal
 * synchronization mechanism has changed.
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
