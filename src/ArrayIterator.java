import java.util.Iterator;

public class ArrayIterator implements Iterator {

    private int[] arr;
    private int size;
    private int currIndex = 0;

    ArrayIterator(int[] arr, int size) {
        this.arr = arr;
        this.size = size;
    }

    @Override
    public boolean hasNext() {
        return currIndex < size;
    }

    @Override
    public Object next() {
        int res = arr[currIndex];
        currIndex++;
        return res;
    }
}