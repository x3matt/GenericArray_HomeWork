import java.util.Iterator;

public class ArrayIterator<T> implements Iterator {

    private T[] arr;
    private int size;
    private int currIndex = 0;

    ArrayIterator(T[] arr, int size) {
        this.arr = arr;
        this.size = size;
    }

    @Override
    public boolean hasNext() {
        return currIndex < size;
    }

    @Override
    public Object next() {
        T res = arr[currIndex];
        currIndex++;
        return res;
    }
}