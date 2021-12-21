package collections;

public class EntryTestClass<K, V> implements EntryTest<K, V> {

    private K key;
    private V value;
    EntryTestClass<K, V> next;
    EntryTestClass<K, V> left;
    EntryTestClass<K, V> right;
    EntryTestClass<K, V> parent;

    EntryTestClass() {

    }

    EntryTestClass(K key, V value) {
        this.key = key;
        this.value = value;
    }

    EntryTestClass(K key, V value, EntryTestClass<K, V> parent) {
        this.key = key;
        this.value = value;
        this.parent = parent;
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
