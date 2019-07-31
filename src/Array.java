import java.util.Arrays;
import java.util.Iterator;

public class Array implements Iterable {

    private int[] arr;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    Array() {
        arr = new int[DEFAULT_CAPACITY];
    }

    Array(int capacity) {
        arr = new int[capacity];
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int indexOf(int number) {
        for (int i = 0; i < size; i++) {
            if (arr[i] == number) {
                return i;
            }
        }
        return -1;
    }

    public int lastIndexOf(int number) {
        for (int i = size - 1; i >= 0; i--) {
            if (arr[i] == number) {
                return i;
            }
        }
        return -1;
    }

    public int get(int index) {
        if (rangeCheck(index)) {
            return arr[index];
        }
        return -1;
    }

    public int set(int index, int number) {
        int oldValue = -1;
        if (rangeCheck(index)) {
            oldValue = arr[index];
            arr[index] = number;
        }
        return oldValue;
    }

    public boolean add(int number) {
        ensureCapacity(size + 1);
        arr[size] = number;
        size++;
        return true;
    }

    public boolean addAtPosition(int index, int number) {
        if (index >= size) {
            return false;
        }
        ensureCapacity(size + 1);
        copyArray(arr, index, arr, index + 1, size - index);
        arr[index] = number;
        size++;
        return true;
    }

    public boolean addAll(int arrToAdd[]) {
        ensureCapacity(size + arrToAdd.length);
        copyArray(arrToAdd, 0, arr, size, arrToAdd.length);
        size += arrToAdd.length;
        return true;
    }

    public boolean addAll(int index, int arrToAdd[]) {
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

    public boolean remove(int number) {
        int foundIndex = indexOf(number);
        if (foundIndex == -1) {
            return false;
        }
        removeByIndex(foundIndex);
        return true;
    }

    public int removeByIndex(int index) {
        if(!rangeCheck(index)){
            return -1;
        }
        int oldValue = arr[index];
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

    private int[] copyArray(int[] elementsArr, int newSize) {
        int[] newArr = new int[newSize];
        for (int i = 0; i < size; i++) {
            newArr[i] = elementsArr[i];
        }
        return newArr;
    }

    private void copyArray(int[] src, int srcPos, int[] dest, int destPos, int length) {
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
    public Iterator iterator() {
        return new ArrayIterator(arr, size);
    }
}