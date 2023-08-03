package collection;

public interface MyList<T> {
    void add(T element);

    T get(int index);

    void set(int index, T element);

    void remove(int index);

    int size();

    boolean isEmpty();
}
