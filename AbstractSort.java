public abstract class AbstractSort {
    private long count = 0;
    private long startTime = 0;
    private long endTime = 0;

    public abstract void sort(int[] arr);

    protected void startSort() {
        count = 0;
        startTime = System.nanoTime();
    }

    protected void endSort() {
        endTime = System.nanoTime();
    }

    protected void incrementCount() {
        count++;
    }

    public long getCount() {
        return count;
    }

    public long getTime() {
        return endTime - startTime;
    }
}

