import java.util.Arrays;

public class Array<T> implements Iterable {

    private T[] arr;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    Array() {
        arr = (T[]) new Object[DEFAULT_CAPACITY];
    }

    Array(int capacity) {
        arr = (T[]) new Object[capacity];
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int indexOf(T item) {
        for (int i = 0; i < size; i++) {
            if (arr[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    public int lastIndexOf(T item) {
        for (int i = size - 1; i >= 0; i--) {
            if (arr[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    public T get(int index) {
        if (rangeCheck(index)) {
            return arr[index];
        }
        return null;
    }

    public T set(int index, T item) {
        T oldValue = null;
        if (rangeCheck(index)) {
            oldValue = arr[index];
            arr[index] = item;
        }
        return oldValue;
    }

    public boolean add(T item) {
        ensureCapacity(size + 1);
        arr[size] = item;
        size++;
        return true;
    }

    public boolean addAtPosition(int index, T item) {
        if (index >= size) {
            return false;
        }
        ensureCapacity(size + 1);
        copyArray(arr, index, arr, index + 1, size - index);
        arr[index] = item;
        size++;
        return true;
    }

    public boolean addAll(T arrToAdd[]) {
        ensureCapacity(size + arrToAdd.length);
        copyArray(arrToAdd, 0, arr, size, arrToAdd.length);
        size += arrToAdd.length;
        return true;
    }

    public boolean addAll(int index, T arrToAdd[]) {
        if (index > size || index < 0) {
            return false;
        }
        ensureCapacity(size + arrToAdd.length);

        int step = size - index;

        if (step > 0) {
            copyArray(arr, index, arr, index + arrToAdd.length, step);
            copyArray(arrToAdd, 0, arr, index, arr.length);
            size += arrToAdd.length;
        } else {
            addAll(arrToAdd);
        }
        return true;
    }

    public boolean remove(T item) {
        int foundIndex = indexOf(item);
        if (foundIndex == -1) {
            return false;
        }
        removeByIndex(foundIndex);
        return true;
    }

    public T removeByIndex(int index) {
        if(!rangeCheck(index)){
            return null;
        }
        T oldValue = arr[index];
        copyArray(arr, index + 1, arr, index, size - index);
        size--;
        return oldValue;
    }

    public boolean removeRange(int fromIndex, int toIndex){
        int step = size - toIndex;
        copyArray(arr, toIndex, arr, fromIndex, step);
        if(fromIndex < 0 || fromIndex >= size || toIndex > size || toIndex < fromIndex){
            return false;
        }
        size -= (toIndex - fromIndex);
        return true;
    }


    private void ensureCapacity(int minCapacity) {
        if (minCapacity - arr.length > 0)
            resizeCapacity(minCapacity);
    }

    private void resizeCapacity(int minCapacity) {
        int oldCapacity = arr.length;
        int newCapacity = oldCapacity + (oldCapacity * 2);
        if (newCapacity - minCapacity < 0)
            newCapacity = minCapacity;
        arr = copyArray(arr, newCapacity);
    }

    private T[] copyArray(T[] elementsArr, int newSize) {
        T[] newArr = (T[]) new Object[newSize];
        for (int i = 0; i < size; i++) {
            newArr[i] = elementsArr[i];
        }
        return newArr;
    }

    private void copyArray(T[] src, int srcPos, T[] dest, int destPos, int length) {
        if (Arrays.equals(src, dest)) {
            src = copyArray(src, src.length);
        }
        while (length + 1 >= 0 && destPos < dest.length && srcPos < src.length) {
            dest[destPos] = src[srcPos];
            destPos++;
            srcPos++;
            length--;
        }
    }

    private boolean rangeCheck(int index) {
        if (index >= size && index < 0){
            return false;
        }
        return true;
    }

    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.println(arr[i]);
        }
    }

    @Override
    public ArrayIterator iterator() {
        return new ArrayIterator(arr, size);
    }
}