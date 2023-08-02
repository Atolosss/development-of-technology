package generics;

public class MyArrayList<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] data;
    private int size;

    public MyArrayList() {
        this.data = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    public void add(final T element) {
        if (size == data.length) {
            int newCapacity = data.length * 2;
            Object[] newData = new Object[newCapacity];
            System.arraycopy(data, 0, newData, 0, size);
            data = newData;
        }
        data[size++] = element;
    }

    public T get(final int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Мы вышли за границы");
        }
        return (T) data[index];
    }

    public void set(final int index,final T element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Мы вышли за границы");
        }
        data[index] = element;
    }

    public void remove(final int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of range: " + index);
        }
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        data[size - 1] = null;
        size--;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }



}
