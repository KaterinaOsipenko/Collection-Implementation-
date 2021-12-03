package collections;

public interface DequeTest<E> extends QueueTest<E> {

    void addFirst(E e);

    void addLast(E e);

    IteratorTest<E>	descendingIterator();

    E getFirst();

    E getLast();

    boolean	offerFirst(E e);

    boolean	offerLast(E e);

    E peekFirst();

    E peekLast();

    E pollFirst();

    E pollLast();

    E pop();

    void push(E e);

    E removeFirst();

    boolean	removeFirstOccurrence(Object o);

    E removeLast();

    boolean	removeLastOccurrence(Object o);
}
