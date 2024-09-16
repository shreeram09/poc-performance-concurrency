import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.ArrayList;
import java.util.List;
import java.time.*;

public class ConcurrencyJavaVirtualThreads {

    public static void main(String[] args) throws Exception {
        int numWorkers = 10;

        // Create a Virtual Thread executor
        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();

        List<Future<Long>> results = new ArrayList<>();

        long startTime = System.nanoTime();
        // Submit workers using virtual threads
        for (int i = 0; i < numWorkers; i++) {
            final int start = i * 100_000 + 1;
            final int end = (i + 1) * 100_000;
            results.add(executor.submit(() -> sumRange(start, end)));
        }

        // Aggregate the results
        long totalSum = 0;
        for (Future<Long> result : results) {
            totalSum += result.get();
        }

        long endTime = System.nanoTime();
        long timeElapsed = (endTime - startTime) / 1_000_000;
        // Print the total sum
        System.out.println("Total sum: " + totalSum);
        System.out.println("Total time consumed: " + timeElapsed + " ms");

        // Shutdown the virtual thread executor
        executor.shutdown();
    }

    // Method to sum numbers in a range
    private static long sumRange(int start, int end) {
        long sum = 0;
        for (int i = start; i <= end; i++) {
            sum += i;
        }
        return sum;
    }
}
