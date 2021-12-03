package collections;

public interface CollectionTest<E> extends Iterable<E>{

    boolean add(E e);

    boolean addAll(E[] c);

    void clear();

    boolean	contains(E o);

    // boolean	containsAll(CollectionTest<?> c);

    boolean	equals(Object o);

    int	hashCode();

    boolean	isEmpty();

    IteratorTest<E>	iterator();

    // default Stream<E> parallelStream();

    boolean	removeElement(E o);

   // boolean removeAll(CollectionTest<?> c);

    // default boolean removeIf(Predicate<? super E> filter);

    // boolean	retainAll(CollectionTest<?> c);

    int	size();

    // default Spliterator<E> spliterator();

    // default Stream<E> stream();

    E[] toArray();

}
