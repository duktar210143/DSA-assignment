// Question 7
// a)	Implement multi-threaded algorithm to multiply n*n matrix.

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class QN7a {
    private static int[][] matrix1 = {{1, 2}, {3, 4}};
    private static int[][] matrix2 = {{5, 6}, {7, 8}};
    private static int[][] result = new int[2][2];
    private static int n = 2;

    public static void main(String[] args) throws InterruptedException {
        int numThreads = Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                executor.execute(new MultiplyTask(i, j));
            }
        }

        executor.shutdown();
        executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);

        // print the result
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }

    static class MultiplyTask implements Runnable {
        private int i;
        private int j;

        MultiplyTask(int i, int j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public void run() {
            for (int k = 0; k < n; k++) {
                result[i][j] += matrix1[i][k] * matrix2[k][j];
            }
        }
    }
}
