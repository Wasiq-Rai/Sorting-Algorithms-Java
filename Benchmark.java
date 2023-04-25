import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Benchmark {
    private static final int DATA_SET_COUNT = 40;
    private static final int DATA_SET_SIZE_INCREMENT = 500;
    private static final int DATA_SET_SIZE_START = 500;
    private static final int DATA_SET_SIZE_END = 6000;

    public static void main(String[] args) {
        AbstractSort[] sorters = {new BubbleSort(), new MergeSort()};
        String[] fileNames = {"bubble_sort_results.txt", "merge_sort_results.txt"};

        // Warm-up the JVM
        for (AbstractSort sorter : sorters) {
            warmUpJVM(sorter);
        }

        for (int i = 0; i < sorters.length; i++) {
            AbstractSort sorter = sorters[i];
            String fileName = fileNames[i];
            try (FileWriter fw = new FileWriter(fileName)) {
                for (int n = DATA_SET_SIZE_START; n <= DATA_SET_SIZE_END; n += DATA_SET_SIZE_INCREMENT) {
                    fw.write(n + " ");
                    int[][] dataSets = generateDataSets(n);
                    for (int[] dataSet : dataSets) {
                        int[] data = dataSet.clone();
                        sorter.sort(data);
                        verifySorted(data);
                        fw.write(sorter.getCount() + " " + sorter.getTime() + " ");
                    }
                    fw.write("\n");
                }
            } catch (IOException e) {
                System.err.println("Error writing to file: " + fileName);
                e.printStackTrace();
            }
        }
    }

    private static void warmUpJVM(AbstractSort sorter) {
        int[] warmUpData = new int[1000];
        Random random = new Random();
        for (int i = 0; i < warmUpData.length; i++) {
            warmUpData[i] = random.nextInt();
        }
        for (int i = 0; i < 100; i++) {
            sorter.sort(warmUpData.clone());
        }
    }

    private static int[][] generateDataSets(int n) {
        int[][] dataSets = new int[DATA_SET_COUNT][n];
        Random random = new Random();
        for (int i = 0; i < DATA_SET_COUNT; i++) {
            for (int j = 0; j < n; j++) {
                dataSets[i][j] = random.nextInt();
            }
        }
        return dataSets;
    }

    private static void verifySorted(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] > arr[i]) {
                throw new IllegalStateException("Array not sorted");
            }
        }
    }
}


