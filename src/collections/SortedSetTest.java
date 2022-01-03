package collections;

import java.util.Comparator;

public interface SortedSetTest<K> extends SetTest<K> {

    Comparator<? super K> comparator();

    K first();

    SortedSetTest<K> headSet(K toElement);

    K last();

    SortedSetTest<K> subSet(K fromElement, K toElement);

    SortedSetTest<K> tailSet(K fromElement);
}
