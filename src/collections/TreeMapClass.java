package collections;

import java.util.Comparator;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

import static java.lang.Character.compare;

public class TreeMapClass<K, V> implements NavigableMapTest<K, V> {

    private Comparator<? super K> comparator = null;

    private EntryTestClass<K, V> root = null;

    private int size;

    TreeMapClass () {

    }

    TreeMapClass(Comparator<? super K> comparator) {
        this.comparator = comparator;
    }

    TreeMapClass(MapTest<? extends K,? extends V> m) {
        // putAll(m);
    }

    TreeMapClass(SortedMapTest<K,? extends V> m) {
        this.comparator = m.comparator();
    }

    @Override
    public EntryTest<K, V> ceilingEntry(K key) {
        return null;
    }

    @Override
    public K ceilingKey(K key) {
        return null;
    }

    @Override
    public NavigableMapTest<K, V> descendingKeyMap() {
        return null;
    }

    @Override
    public EntryTest<K, V> firstEntry() {
        return (EntryTest<K, V>) this.root;
    }

    @Override
    public EntryTest<K, V> floorEntry(K key) {
        return null;
    }

    @Override
    public K floorKey(K key) {
        return null;
    }

    @Override
    public NavigableMapTest<K, V> headMap(K toKey, boolean inclusive) {
        return null;
    }

    @Override
    public EntryTest<K, V> higherEntry(K key) {
        return null;
    }

    @Override
    public K higherKey(K key) {
        return null;
    }

    @Override
    public EntryTest<K, V> lastEntry() {
        while (this.root != null) {
            root = root.left;
            root = root.right;
        }
        return (EntryTest<K, V>) this.root;
    }

    @Override
    public EntryTest<K, V> lowerEntry(K key) {
        return null;
    }

    @Override
    public K lowerKey(K key) {
        return null;
    }

    @Override
    public EntryTest<K, V> pollFirstEntry() {
        return null;
    }

    @Override
    public EntryTest<K, V> pollLastEntry() {
        return null;
    }

    @Override
    public NavigableMapTest<K, V> subMap(K fromKey, boolean fromInclusive, K toKey, boolean toInclusive) {
        return null;
    }

    @Override
    public NavigableMapTest<K, V> tailMap(K fromKey, boolean inclusive) {
        return null;
    }

    @Override
    public Comparator<? super K> comparator() {
        return comparator;
    }

    @Override
    public K firstKey() {
        return this.root.getKey();
    }

    @Override
    public SortedMapTest<K, V> headMap(K toKey) {
        return null;
    }

    @Override
    public K lastKey() {
        return null;
    }

    @Override
    public SortedMapTest<K, V> subMap(K fromKey, K toKey) {
        return null;
    }

    @Override
    public SortedMapTest<K, V> tailMap(K fromKey) {
        return null;
    }

    @Override
    public void clear() {
        this.root = null;
        this.size = 0;
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
    public SetTest<EntryTestClass<K, V>> entrySet() {
        return null;
    }

    @Override
    public void forEach(BiConsumer<? super K, ? super V> action) {
        if (action == null) {
            throw new NullPointerException("This action is null");
        }

    }

    public void getRescursion(EntryTestClass<K, V> root) {
        if(root == null) {
            return;
        } else {
            System.out.println("Key: " + root.getKey() + " Value: " + root.getValue());
            getRescursion(root.left);
            getRescursion(root.right);
        }
    }

    public void showTree(){
       getRescursion(root);
    }

    @Override
    public V get(K key) {
        while (root != null) {
            int cmp = this.compare(root.getKey(), key);
            if (cmp == 0) {
                return root.getValue();
            } else if (cmp > 0) {
                if (root.left.getKey() == key) {
                    return root.left.getValue();
                }
                root = root.left;
            } else {
                if (root.right.getKey() == key) {
                    return root.right.getValue();
                }
                root = root.right;
            }
        }
        return null;
    }

    @Override
    public V getOrDefault(K key, V value) {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public SetTest<K> keySet() {
        return null;
    }

    @Override
    public V put(K key, V value) {
        EntryTestClass<K, V> temp = this.root;
        if(temp == null) {
            size++;
            root = new EntryTestClass<>(key, value);
            return null;
        }
        while (true) {
            int cmp = compare(key, temp.getKey());
            if (cmp == 0) {
                return temp.setValue(value);
            } else if (cmp < 0) {
                if(temp.left != null) {
                    temp = temp.left;

                } else {
                    size++;
                    temp.left = new EntryTestClass<>(key, value, temp);
                    return null;
                }
            } else {
                if(temp.right != null) {
                    temp = temp.right;
                } else {
                    size++;
                    temp.right = new EntryTestClass<>(key, value, temp);
                    return null;
                }
            }
        }
    }

    private int compare(K key, K key1) {
        return (this.comparator == null) ? ((Comparable</*-*/K>)key).compareTo(key1) : comparator.compare(key,key1);
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
        return this.size;
    }

    @Override
    public CollectionTest<V> values() {
        return null;
    }
}
