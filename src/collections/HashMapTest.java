package collections;

import java.util.function.BiFunction;

public class HashMapTest<K, V> implements MapTest<K, V> {

    private static final int DEFAULT_CAPACITY = 16;
    private int capacity;
    private double loadFactor;
    private int size;
    private EntryTestClass<K, V>[] values = new EntryTestClass[DEFAULT_CAPACITY];

    HashMapTest() {
        this.capacity = DEFAULT_CAPACITY;
        this.loadFactor = 0.75;
    }

    HashMapTest(int capacity) {
        this.capacity = capacity;
        this.loadFactor = 0.75;
    }

    HashMapTest(int capacity, double loadFactor) {
        this.capacity = capacity;
        this.loadFactor = loadFactor;
    }

    HashMapTest(MapTest<? extends K,? extends V> m) {

    }

    @Override
    public void clear() {

    }

    @Override
    public boolean containsKey(K key) {
        return false;
    }

    @Override
    public boolean containsValue(V value) {
        return false;
    }

    @Override
    public SetTest<EntryTest<K, V>> entrySet() {
        return null;
    }

    @Override
    public V get(K key) {
        return null;
    }

    @Override
    public V getOrDefault(K key, V value) {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public SetTest<K> keySet() {
        return null;
    }

    @Override
    public V put(K key, V value) {
        return null;
    }

    @Override
    public void putAll(MapTest<? extends K, ? extends V> m) {

    }

    @Override
    public V putIfAbsent(K key, V value) {
        return null;
    }

    @Override
    public V remove(K key) {
        return null;
    }

    @Override
    public boolean remove(K key, V value) {
        return false;
    }

    @Override
    public V replace(K key, V value) {
        return null;
    }

    @Override
    public boolean replace(K key, V oldValue, V newValue) {
        return false;
    }

    @Override
    public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function) {

    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public CollectionTest<V> values() {
        return null;
    }

    public int	hashCode() {
        int hash = 31;
        hash = hash * 31 + keySet().hashCode();
        hash = hash * 31 + value.hashCode();
    }
}
