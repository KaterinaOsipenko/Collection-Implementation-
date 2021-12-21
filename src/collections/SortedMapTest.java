package collections;

import java.util.Comparator;

public interface SortedMapTest<K, V> extends MapTest<K, V> {

    Comparator<? super K> comparator();

    K firstKey();

    SortedMapTest<K,V>	headMap(K toKey);

    K lastKey();

    SortedMapTest<K,V> subMap(K fromKey, K toKey);

    SortedMapTest<K,V>	tailMap(K fromKey);


}
