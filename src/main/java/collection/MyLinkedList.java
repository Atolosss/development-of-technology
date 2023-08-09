package collection;

import java.util.NoSuchElementException;

public class MyLinkedList<T> implements MyList<T> {


    private Node<T> node;

    private int size;

    public MyLinkedList() {
        node = null;
        size = 0;
    }

    @Override
    public void add(final T element) {

        Node<T> newNode = new Node<>(element);
        if (isEmpty()) {
            node = newNode;
        } else {
            Node<T> current = node;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    @Override
    public T get(final int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        Node<T> current = node;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;

    }

    @Override
    public void set(final int index, final T element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        Node<T> current = node;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        current.data = element;
    }

    @Override
    public T remove(final int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        if (isEmpty()) {
            throw new NoSuchElementException("LinkedList is empty");
        }
        T data;
        if (index == 0) {
            data = node.data;
            node = node.next;
        } else {
            Node<T> current = node;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            data = (T) current.next.data;
            current.next = current.next.next;
        }
        size--;
        return data;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    final class Node<T> {
        private T data;
        private Node next;

        private Node(final T value) {
            this.data = value;
            this.next = null;
        }

        public T getData() {
            return data;
        }

        public void setData(final T value) {
            this.data = value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(final Node value) {
            this.next = value;
        }
    }
}



