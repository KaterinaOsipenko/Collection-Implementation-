package collections;

public interface NavigableSetTest<K> extends SortedSetTest<K> {

    K ceiling(K k);

    NavigableSetTest<K>	descendingSet();

    K floor(K k);

    SortedSetTest<K> headSet(K toElement);

    NavigableSetTest<K>	headSet(K toElement, boolean inclusive);

    K	higher(K e);

    K	lower(K e);

    K	pollFirst();

    K	pollLast();

    NavigableSetTest<K>	subSet(K fromElement, boolean fromInclusive, K toElement, boolean toInclusive);

    SortedSetTest<K>	subSet(K fromElement, K toElement);

    SortedSetTest<K>	tailSet(K fromElement);

    NavigableSetTest<K>	tailSet(K fromElement, boolean inclusive);
}
