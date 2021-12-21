package collections;

public interface NavigableMapTest<K, V> extends SortedMapTest<K, V> {

    MapTest.EntryTest<K,V>	ceilingEntry(K key);

    K ceilingKey(K key);

    NavigableMapTest<K, V>	descendingKeyMap();

    MapTest.EntryTest <K,V> firstEntry();

    MapTest.EntryTest<K,V>	floorEntry(K key);

    K	floorKey(K key);

    NavigableMapTest<K,V>	headMap(K toKey, boolean inclusive);

    MapTest.EntryTest<K,V>	higherEntry(K key);

    K	higherKey(K key);

    MapTest.EntryTest<K,V>	lastEntry();

    MapTest.EntryTest<K,V>	lowerEntry(K key);

    K	lowerKey(K key);

    MapTest.EntryTest<K,V>	pollFirstEntry();

    MapTest.EntryTest<K,V>	pollLastEntry();

    NavigableMapTest<K,V>	subMap(K fromKey, boolean fromInclusive, K toKey, boolean toInclusive);

    NavigableMapTest<K,V>	tailMap(K fromKey, boolean inclusive);

}
