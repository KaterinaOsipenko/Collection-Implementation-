package collections;

import java.util.Comparator;

public interface SortedSetTest<E> extends SetTest<E> {

    Comparator<? super E> comparator();

    E first();

    SortedSetTest<E> headSet(E toElement);

    E last();

    SortedSetTest<E> subSet(E fromElement, E toElement);

    SortedSetTest<E> tailSet(E fromElement);
}
