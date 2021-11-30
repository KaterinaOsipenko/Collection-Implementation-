package collections;

public interface ListTest<E> extends Iterable<E> {

    boolean add(E e);
    void clear();
    E get(int index);
    int size();
    E set(int index, E e);
    String toString();
    boolean isEmpty();
    boolean contains(E e);
    int indexOf(E e);
    int lastIndexOf(E e);
    void add(int index, E element);
    E remove(int index);
    boolean remove(E e);

    boolean addAll(E[] e);

    boolean addAll(int index, E[] e);

    boolean removeAll(E[] e);

}
