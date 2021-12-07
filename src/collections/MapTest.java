package collections;

import java.util.function.BiFunction;

public interface MapTest<K, V> {

    void clear();

     // V compute(K key, BiFunction<? super K,? super V,? extends V> remappingFunction);

     // V computeIfAbsent(K key, Function<? super K,? extends V> mappingFunction);

    // V computeIfPresent(K key, BiFunction<? super K,? super V,? extends V> remappingFunction);

    boolean	containsKey(K key);

    boolean	containsValue(V value);

    SetTest<MapTest.EntryTest<K,V>>	entrySet();

    boolean	equals(Object o);

    // void forEach(BiConsumer<? super K,? super V> action);

    V get(K key);

    V getOrDefault(K key, V value);

    int	hashCode();

    boolean	isEmpty();

    SetTest<K> keySet();

    // V merge(K key, V value, BiFunction<? super V,? super V,? extends V> remappingFunction);

    V put(K key, V value);

    void putAll(MapTest<? extends K,? extends V> m);

    V putIfAbsent(K key, V value);

    V remove(K key);

    boolean	remove(K key, V value);

    V replace(K key, V value);

    boolean	replace(K key, V oldValue, V newValue);

    void replaceAll(BiFunction<? super K,? super V,? extends V> function);

    int	size();

    CollectionTest<V> values();

    public interface EntryTest<K, V> {
        K getKey();

        V getValue();

        V setValue(V value);
    }
}
