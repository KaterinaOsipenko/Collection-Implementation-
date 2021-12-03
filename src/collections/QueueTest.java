package collections;

public interface QueueTest<E> extends CollectionTest<E> {

    boolean add(E e);

    E element();

    boolean offer(E e);

    E peek();

    E poll();

    E remove();
}
