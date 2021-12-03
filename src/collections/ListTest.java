package collections;


public interface ListTest<E> extends CollectionTest<E> {

    void add(int index, E element);

    boolean addAll(int index, E[] e);

    E get(int index);

    int indexOf(E e);

    int lastIndexOf(E e);

    E remove(int index);

   // default void replaceAll(UnaryOperator<E> operator); !!

    E set(int index, E e);

    // default void	sort(Comparator<? super E> c); !!

    ListTest<E>	subList(int fromIndex, int toIndex);

    String toString();

    boolean removeElement(E e);
}
