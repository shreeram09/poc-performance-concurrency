import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class JavaConcurrency {

    public static void main(String[] args) throws Exception {
        int numWorkers = 10;
        ExecutorService executor = Executors.newFixedThreadPool(numWorkers);
        List<Future<Long>> results = new ArrayList<>();

        long startTime = System.nanoTime();
        // Submitting workers to the thread pool
        for (int i = 0; i < numWorkers; i++) {
            final int start = i * 100_000 + 1;
            final int end = (i + 1) * 100_000;
            results.add(executor.submit(new SumWorker(start, end)));
        }

        long totalSum = 0;
        for (Future<Long> result : results) {
            totalSum += result.get();
        }

        long endTime = System.nanoTime();
        long timeElapsed = (endTime - startTime) / 1_000_000;
        System.out.println("Total sum: " + totalSum);
        System.out.println("Total time consumed: " + timeElapsed + " ms");
        executor.shutdown();
    }

    static class SumWorker implements Callable<Long> {
        private final int start, end;

        public SumWorker(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public Long call() {
            long sum = 0;
            for (int i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        }
    }
}
