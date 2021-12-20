package collections;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;

public class HashMapTest<K, V> implements MapTest<K, V> {

    private static final int DEFAULT_CAPACITY = 16;
    private int capacity;
    private double loadFactor;
    private int size;
    private EntryTestClass<K, V>[] buckets;

    HashMapTest() {
        buckets = new EntryTestClass[DEFAULT_CAPACITY];
        this.capacity = DEFAULT_CAPACITY;
        this.loadFactor = 0.75;
    }

    HashMapTest(int capacity) {
        buckets = new EntryTestClass[capacity];
        this.capacity = capacity;
        this.loadFactor = 0.75;
    }

    HashMapTest(int capacity, double loadFactor) {
        buckets = new EntryTestClass[capacity];
        this.capacity = capacity;
        this.loadFactor = loadFactor;
    }

    HashMapTest(MapTest<? extends K, ? extends V> m) {

    }

    public EntryTestClass<K, V>[] getMap() {
        EntryTestClass<K, V>[] map = new EntryTestClass[this.buckets.length];
        map = this.buckets.clone();
        return map;
    }

    @Override
    public void clear() {
        for (EntryTestClass<K, V> bucket : buckets) {
            if (bucket != null) {
                size--;
            }
        }
    }

    @Override
    public boolean containsKey(K key) {
        if (key == null) {
            throw new NullPointerException("This key is null!");
        } else {
            for (EntryTestClass<K, V> bucket : this.buckets) {
                if (bucket != null) {
                    if (bucket.getKey() == key) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    @Override
    public boolean containsValue(V value) {
        for (EntryTestClass<K, V> bucket : this.buckets) {
            if (bucket != null) {
                if (bucket.getValue() == value) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public SetTest<EntryTestClass<K, V>> entrySet() {
        HashSetTest<EntryTestClass<K, V>> setTest = new HashSetTest<>();
        for (EntryTestClass<K, V> entry : this.buckets) {
            if(entry != null) {
                setTest.add(entry);
            }
        }
        return setTest;
    }

    @Override
    public V get(K key) {
        if (key == null) {
            throw new NullPointerException("This key is null!");
        } else {
            for (EntryTestClass<K, V> bucket : buckets) {
                if (bucket != null) {
                    if (bucket.getKey() == key) {
                        return bucket.getValue();
                    }
                }
            }
            return null;
        }
    }

    @Override
    public V getOrDefault(K key, V value) {
        if (key == null) {
            throw new NullPointerException("This key is null!");
        } else {
            for (EntryTestClass<K, V> bucket : buckets) {
                if (bucket != null) {
                    if (bucket.getKey() == key) {
                        return bucket.getValue();
                    }
                }
            }
            return value;
        }
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public HashSetTest<K> keySet() {
        HashSetTest<K> keys = new HashSetTest<>();
        this.forEach((key, value) -> keys.add(key));
        return keys;
    }

    @Override
    public V put(K key, V value) {
        if (key == null) {
            throw new NullPointerException("This key is null!");
        } else {
            EntryTestClass<K, V> entry = new EntryTestClass<>(key, value);
            int hash = entry.getKey().hashCode() % this.capacity;
            if (buckets[hash] == null) {
                buckets[hash] = entry;
                size++;
                return null;
            } else {
                while (buckets[hash].next != null) {
                    if (buckets[hash].hashCode() == entry.hashCode() && buckets[hash].getKey() == key) {
                        V temp = buckets[hash].getValue();
                        buckets[hash].setValue(value);
                        return temp;
                    }
                    buckets[hash] = buckets[hash].next;
                }
            }
            buckets[hash].next = entry;
            size++;
            return null;
        }
    }


    @Override
    public void putAll(MapTest<? extends K, ? extends V> m) {
        m.forEach(this::put);
    }

    @Override
    public V putIfAbsent(K key, V value) {
        V v = this.get(key);
        if (v == null) {
            this.put(key, value);
        }
        return  v;
    }

    @Override
    public V remove(K key) {
        for(int i = 0; i < buckets.length; i++) {
            if(buckets[i] != null) {
                if(buckets[i].getKey() == key) {
                    V temp = buckets[i].getValue();
                    buckets[i] = buckets[i + 1];
                    size--;
                    return temp;
                }
            }
        }
        return null;
    }

    @Override
    public boolean remove(K key, V value) {
        if (key == null || value == null) {
            throw new NullPointerException("The specified key or value is null");
        } else {
            for (int i = 0; i < this.buckets.length; i++) {
                if (this.buckets[i] != null) {
                    if ((this.buckets[i].getKey() == key) && (this.buckets[i].getValue().equals(value))) {
                        if (i != this.buckets.length - 1) {
                            this.buckets[i] = buckets[i + 1];
                        }
                        this.size--;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public V replace(K key, V value) {
        if (key == null || value == null) {
            throw new NullPointerException("The specified key or value is null");
        } else {
            if (this.containsKey(key)) {
                return this.put(key, value);
            } else {
                return null;
            }
        }
    }

    @Override
    public boolean replace(K key, V oldValue, V newValue) {
        if (key == null || oldValue == null || newValue == null ) {
            throw new NullPointerException("The specified key or value is null");
        } else {
            if (this.containsKey(key) && this.get(key) == oldValue) {
                this.put(key, newValue);
                return true;
            } else {
                return false;
            }
        }
    }

    // ?????????????????
    @Override
    public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function) {
        for(EntryTestClass<K, V> bucket : this.buckets) {
            if (bucket != null) {
                function.apply(bucket.getKey(), bucket.getValue());
            }
        }
    }

    @Override
    public void forEach(BiConsumer<? super K, ? super V> action) {
        if (action == null) {
            throw new NullPointerException("This action is null");
        } else {
            for (EntryTestClass<K, V> e : this.buckets) {
                if (e != null) {
                    action.accept(e.getKey(), e.getValue());
                }
            }
        }
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public CollectionTest<V> values() {
        ArrayListTest<V> temp = new ArrayListTest<>();
        forEach((key, value) -> temp.add(value));
        return temp;
    }

    public void showMap() {
        for (EntryTestClass<K, V> entry : this.buckets) {
            if (entry != null) {
                while(entry.next != null) {
                    System.out.println(entry.getKey() + " " + entry.getValue());
                    entry = entry.next;
                }
                System.out.println(entry.getKey() + " " + entry.getValue());
            }
        }
    }
}

