package collections;

public class EntryTestClass<K, V> implements EntryTest<K, V> {

    private K key;
    private V value;

    EntryTestClass(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public K getKey() {
        return this.key;
    }

    @Override
    public V getValue() {
        return this.value;
    }

    @Override
    public V setValue(V value) {
        V temp = this.value;
        this.value = value;
        return temp;
    }
}
