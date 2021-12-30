package collections;

public class EntryTestClass<K, V> implements EntryTest<K, V>, MapTest.EntryTest<K, V>{

    private K key;
    private V value;
    EntryTestClass<K, V> next;
    EntryTestClass<K, V> left;
    EntryTestClass<K, V> right;
    EntryTestClass<K, V> parent;

    EntryTestClass() {

    }

    EntryTestClass(K key ) {
        this.key = key;

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

    public EntryTestClass<K, V> getEntry() {
        EntryTestClass<K, V> temp = new EntryTestClass<>(this.key, this.value);
        return temp;
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

    public void setLeft(EntryTestClass<K, V> entry) {
        this.left = entry;
    }

    public EntryTestClass<K, V> getLeft() {
        return this.left;
    }

    public void setRight(EntryTestClass<K, V> entry) {
        this.right = entry;
    }

    public EntryTestClass<K, V> getRight() {
        return this.right;
    }

    @Override
    public int hashcode() {
        return this.hashCode();
    }

    public  String toString() {
        return this.key.toString() + " " + this.value.toString();
    }

}
