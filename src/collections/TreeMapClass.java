package collections;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

public class TreeMapClass<K, V> implements NavigableMapTest<K, V>, Comparable<K> {

    private Comparator<? super K> comparator;

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

    TreeMapClass(SortedMapTest<K, V> m) {
        this.comparator = m.comparator();
    }

    @Override
    public EntryTest<K, V> ceilingEntry(K key) {
        if (key == null) {
            throw new NullPointerException("This key is null");
        }
        if(this.containsKey(key)) {
            EntryTestClass<K, V> entry = this.root;
            while (entry != null) {
                if (compare(entry.getKey(), key) == 0) {
                    return entry;
                } else if (compare(entry.getKey(), key) < 0) {
                    entry = entry.right;
                } else {
                    entry = entry.left;
                }
            }
        } else {
            if (compare(this.root.getKey(), key) < 0) {
                EntryTestClass<K, V> entry = this.root;
                while (entry != null) {
                    if (compare(entry.getKey(), key) > 0) {
                        return entry;
                    } else {
                        entry = entry.right;
                    }
                }
            } else {
                EntryTestClass<K, V> entry = this.firstEntry();
                while (entry != null) {
                    if (compare(entry.getKey(), key) > 0) {
                        return entry;
                    } else {
                        entry = entry.parent;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public K ceilingKey(K key) {
        if(key == null) {
            throw new NullPointerException("This key is null!");
        }
        return this.ceilingEntry(key).getKey();
    }

    @Override
    public void clear() {
        this.root = null;
        this.size = 0;
    }

    @Override
    public Comparator<? super K> comparator() {
        return comparator;
    }

    @Override
    public boolean containsKey(K key) {
        if(key == null) {
            throw new NullPointerException("This key is null!");
        }
        EntryTestClass<K, V> entry = this.root;
        while (entry != null) {
            if (compare(entry.getKey(), key) == 0) {
                return true;
            } else if (compare(entry.getKey(), key) < 0) {
                entry = entry.right;
            } else {
                entry = entry.left;
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(V value) {
        EntryTestClass<K, V> entry = this.root;
        while (entry != null) {
            if (entry.getValue() == value) {
                return true;
            } else {
                entry = entry.left;
            }
        }
        entry = this.root;
        while (entry != null) {
            if (entry.getValue() == value) {
                return true;
            } else {
                entry = entry.right;
            }
        }
        return false;
    }

    @Override
    public NavigableMapTest<K, V> descendingKeyMap() {
        NavigableMapTest<K, V> map = new TreeMapClass<K, V>();
        EntryTestClass<K, V> entry = this.lastEntry();
        while (entry.parent != null) {
            map.put(entry.getKey(), entry.getValue());
            entry = entry.parent;
        }
        while (entry != null) {
            map.put(entry.getKey(), entry.getValue());
            if(entry.right != null) {
                map.put(entry.right.getKey(), entry.right.getValue());
            }
            entry = entry.left;
        }
        return map;
    }

    @Override
    public SetTest<EntryTestClass<K, V>> entrySet() {
        SetTest<EntryTestClass<K, V>> set = new HashSetTest<>();
        EntryTestClass<K, V> entry = this.firstEntry();
        while (entry.parent != null) {
            set.add(entry);
            if(entry.right != null) {
                set.add(entry.right);
            }
            entry = entry.parent;
        }
        entry = this.root;
        while (entry != null) {
            set.add(entry);
            if(entry.left != null && entry != this.root) {
                set.add(entry.left);
            }
            entry = entry.right;
        }
        return set;
    }

    @Override
    public EntryTestClass<K, V> firstEntry() {
        EntryTestClass<K, V> entry = this.root;
        while(entry.left != null) {
            entry = entry.left;
        }
        return entry;
    }

    @Override
    public K firstKey() {
        if(this.isEmpty()) {
            throw new NoSuchElementException("This tree is empty!");
        }
        return this.firstEntry().getKey();
    }

    @Override
    public EntryTest<K, V> floorEntry(K key) {
        if(key == null) {
            throw new NullPointerException("This key is null!");
        }
        if(this.containsKey(key)) {
            EntryTestClass<K, V> entry = this.root;
            while (entry != null) {
                if (compare(entry.getKey(), key) == 0) {
                    return entry;
                } else if (compare(entry.getKey(), key) < 0) {
                    entry = entry.right;
                } else {
                    entry = entry.left;
                }
            }
        } else {
            if (compare(this.root.getKey(), key) < 0) {
                EntryTestClass<K, V> entry = this.lastEntry();
                while (entry != null) {
                    if (compare(entry.getKey(), key) < 0) {
                        return entry;
                    } else {
                        entry = entry.parent;
                    }
                }
            } else {
                EntryTestClass<K, V> entry = this.root;
                while (entry != null) {
                    if (compare(entry.getKey(), key) < 0) {
                        return entry;
                    } else {
                        entry = entry.left;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public K floorKey(K key) {
        if(key == null) {
            throw new NullPointerException("This key is null!");
        }
        return this.floorEntry(key).getKey();
    }

    @Override
    public void forEach(BiConsumer<? super K, ? super V> action) {
        if (action == null) {
            throw new NullPointerException("This action is null");
        } else if (this.root == null) {
            throw new NullPointerException("This tree is NULL!");
        }
        if (this.root.right == null && this.root.left == null) {
            action.accept(this.root.getKey(), this.root.getValue());
        }

        EntryTestClass<K, V> entry = this.firstEntry();

        while (entry.parent != null) {
            action.accept(entry.getKey(), entry.getValue());
            if(entry.right != null) {
                action.accept(entry.right.getKey(), entry.right.getValue());
            }
            entry = entry.parent;
        }
        entry = this.root;
        while (entry != null) {
            action.accept(entry.getKey(), entry.getValue());
            if(entry.left != null && entry != this.root) {
                action.accept(entry.left.getKey(), entry.left.getValue());
            }
            entry = entry.right;
        }
    }

    @Override
    public V get(K key) {
        if(key == null) {
            throw new NullPointerException("This key is null!");
        }
        EntryTestClass<K, V> entry = this.root;
        while (entry != null) {
            int cmp = this.compare(entry.getKey(), key);
            if (cmp == 0) {
                return entry.getValue();
            } else if (cmp > 0) {
                if (entry.getKey() == key) {
                    return entry.getValue();
                }
                entry = entry.left;
            } else {
                if (entry.getKey() == key) {
                    return entry.getValue();
                }
                entry = entry.right;
            }
        }
        return null;
    }

    @Override
    public SortedMapTest<K, V> headMap(K toKey) {
        SortedMapTest<K, V> map = new TreeMapClass<>();
        if(toKey == null) {
            throw new NullPointerException("This key is null!");
        } else if(this.isEmpty()) {
            throw new NoSuchElementException("This tree is empty!");
        }
        EntryTestClass<K, V> entry = this.firstEntry();
        while (compare(entry.getKey(), toKey) != 0) {
            map.put(entry.getKey(), entry.getValue());
            if(compare(entry.getKey(), this.root.getKey()) >= 0){
                entry = entry.right;
            } else {
                entry = entry.parent;
            }
        }
        return map;
    }

    @Override
    public NavigableMapTest<K, V> headMap(K toKey, boolean inclusive) {
        NavigableMapTest <K, V> map = new TreeMapClass<>();
        if(toKey == null) {
            throw new NullPointerException("This key is null!");
        } else if(this.isEmpty()) {
            throw new NoSuchElementException("This tree is empty!");
        }
        EntryTestClass<K, V> entry = this.firstEntry();
        while (compare(entry.getKey(), toKey) != 0) {
            map.put(entry.getKey(), entry.getValue());
            if (compare(entry.getKey(), this.root.getKey()) >= 0) {
                entry = entry.right;
            } else {
                entry = entry.parent;
            }
        }
        if(inclusive) {
            map.put(entry.getKey(), entry.getValue());
        }
        return map;
    }

    @Override
    public EntryTest<K, V> higherEntry(K key) {
        if(key == null) {
            throw new NullPointerException("This key is null!");
        }
        return this.ceilingEntry(key);
    }

    @Override
    public K higherKey(K key) {
        if(key == null) {
            throw new NullPointerException("This key is null!");
        }
        return this.higherEntry(key).getKey();
    }

    @Override
    public SetTest<K> keySet() {
        SetTest<K> set = new HashSetTest<>();
        this.forEach((k, v) -> set.add(k));
        return set;
    }

    @Override
    public EntryTestClass<K, V> lastEntry() {
        EntryTestClass p = root;
        if (p != null)
            while (p.right != null)
                p = p.right;
        return p;
    }

    @Override
    public K lastKey() {
        if (this.isEmpty()) {
            throw new NoSuchElementException("This tree is empty!");
        }
        return this.lastEntry().getKey();
    }

    @Override
    public EntryTest<K, V> lowerEntry(K key) {
        if(key == null) {
            throw new NullPointerException("This key is null!");
        }
        return this.floorEntry(key);
    }

    @Override
    public K lowerKey(K key) {
        if(key == null) {
            throw new NullPointerException("This key is null!");
        }
        return this.floorEntry(key).getKey();
    }

    @Override
    public EntryTest<K, V> pollFirstEntry() {
        EntryTestClass<K, V> entry = this.firstEntry();
        if(this.root == null) {
            System.out.println("This tree is empty!");
            return null;
        } else {
            remove(entry.getKey());
            return entry;
        }
    }

    @Override
    public EntryTest<K, V> pollLastEntry() {
        EntryTestClass<K, V> entry = this.lastEntry();
        if(this.root == null) {
            System.out.println("This tree is empty!");
            return null;
        } else {
            remove(entry.getKey());
            return entry;
        }
    }

    @Override
    public V put(K key, V value) {
        if(key == null) {
            throw new NullPointerException("This key is null!");
        }
        EntryTestClass<K, V> entry = root;
        int result = 0;
        while(entry != null ) {
            this.root.parent = entry;
            result = ((Comparable<K>) entry.getKey()).compareTo(key);
            if (result == 0) {
                entry.setValue(value);
                this.root.parent = null;
                return value;
            } else if (result > 0) {
                entry = entry.getLeft();
            } else {
                entry = entry.getRight();
            }
        }
        EntryTestClass<K, V> temp = new EntryTestClass<>(key, value, root);
        if (this.root == null) {
            this.root = temp;
        } else if (result > 0) {
            this.root.parent.setLeft(temp);
            temp.parent = this.root.parent;
        } else {
            this.root.parent.setRight(temp);
            temp.parent = this.root.parent;
        }
        this.root.parent = null;
        this.size++;
        return value;
    }

    @Override
    public void putAll(MapTest<? extends K, ? extends V> m) {

    }

    @Override
    public V remove(K key) {
        if(key == null) {
            throw new NullPointerException("This key is null!");
        }
        if(!this.containsKey(key)) {
            System.out.println("This tree hasn`t this key.");
        } else {
            EntryTestClass<K, V> entry = this.root;
            while (entry != null) {
                if (compare(entry.getKey(), key) == 0) {
                    if(entry.left != null) {
                        entry.left.parent = entry.parent;
                        entry.parent.left = entry.left;
                    } else if(entry.right != null) {
                        entry.right.parent = entry.parent;
                        entry.parent.right = entry.right;
                    } else {
                        entry.parent.left = null;
                        entry.parent.right = null;
                    }
                    V temp = entry.getValue();
                    return temp;
                } else if (compare(entry.getKey(), key) < 0) {
                    entry = entry.right;
                } else {
                    entry = entry.left;
                }
            }
        }
        return null;
    }

    @Override
    public boolean remove(K key, V value) {
        if(key == null) {
            throw new NullPointerException("This key is null!");
        }
        if(!this.containsKey(key)) {
            System.out.println("This tree hasn`t this key.");
        } else {
            EntryTestClass<K, V> entry = this.root;
            while (entry != null) {
                if (compare(key, entry.getKey()) == 0 && value == entry.getValue()) {
                    this.remove(key);
                    return true;
                } else if (compare(key, entry.getKey()) > 0) {
                    entry = entry.right;
                } else {
                    entry = entry.left;
                }
            }
        }
        return false;
    }

    @Override
    public V replace(K key, V value) {
        if(key == null) {
            throw new NullPointerException("This key is null!");
        }
        if(this.containsKey(key)) {
            return this.put(key, value);
        }
        return null;
    }

    @Override
    public boolean replace(K key, V oldValue, V newValue) {
        if(key == null) {
            throw new NullPointerException("This key is null!");
        }
        if(this.containsKey(key) && this.get(key) == oldValue) {
            this.put(key, newValue);
            return true;
        }
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
    public NavigableMapTest<K, V> subMap(K fromKey, boolean fromInclusive, K toKey, boolean toInclusive) {
        if(fromKey == null || toKey == null) {
            throw new NullPointerException("This key is null!");
        } else if(this.compare(fromKey, toKey) == 1) {
            throw new IllegalArgumentException("This keys are unavailable!");
        }
        if(fromInclusive == toInclusive == true && compare(fromKey, toKey) == 0) {
            return null;
        } else {
            NavigableMapTest<K, V> map = new TreeMapClass<>();
            EntryTestClass<K, V> entry;
            entry = this.firstEntry();
            while (compare(entry.getKey(), fromKey) == -1) {
                if (entry == this.root || compare(entry.getKey(), fromKey) == 1) {
                    entry = entry.right;
                } else {
                    entry = entry.parent;
                }
            }
            if (fromInclusive && !toInclusive) {
                return (NavigableMapTest<K, V>) this.subMap(fromKey, toKey);
            } else if (fromInclusive && toInclusive) {
                while (compare(entry.getKey(), toKey) == -1) {
                    map.put(entry.getKey(), entry.getValue());
                    if(entry == this.root) {
                        entry = entry.right;
                    } else if (compare(entry.getKey(), this.root.getKey()) == 1) {
                        map.put(entry.getKey(), entry.getValue());
                        if (entry.left != null && entry != this.root) {
                            map.put(entry.left.getKey(), entry.left.getValue());
                        }
                        if (entry.right != null) {
                            map.put(entry.right.getKey(), entry.right.getValue());
                        }
                        entry = entry.right;
                    } else {
                        if (entry.parent != null) {
                            map.put(entry.parent.getKey(), entry.parent.getValue());
                        }
                        entry = entry.parent;
                    }
                }
                map.put(entry.getKey(), entry.getValue());
                return map;
            } else if (toInclusive) {
                while (compare(entry.getKey(), toKey) == -1) {
                    if(entry == this.root) {
                        map.put(entry.getKey(), entry.getValue());
                        entry = entry.right;
                    } else if (compare(entry.getKey(), this.root.getKey()) == 1) {
                        if (entry.left != null && entry != this.root) {
                            map.put(entry.left.getKey(), entry.left.getValue());
                        }
                        if (entry.right != null) {
                            map.put(entry.right.getKey(), entry.right.getValue());
                        }
                        entry = entry.right;
                    } else {
                        if (entry.parent != null) {
                            map.put(entry.parent.getKey(), entry.parent.getValue());
                        }
                        entry = entry.parent;
                    }
                }
                map.put(entry.getKey(), entry.getValue());
                return map;
            } else {
                while (compare(entry.getKey(), toKey) != 0) {
                    if(entry == this.root) {
                        map.put(entry.getKey(), entry.getValue());
                        entry = entry.right;
                    } else if (compare(entry.getKey(), this.root.getKey()) == 1) {
                        map.put(entry.getKey(), entry.getValue());
                        if (entry.left != null && entry != this.root) {
                            map.put(entry.left.getKey(), entry.left.getValue());
                        }
                        if (entry.right != null) {
                            map.put(entry.right.getKey(), entry.right.getValue());
                        }
                        entry = entry.right;
                    } else {
                        if (entry.parent != null) {
                            map.put(entry.parent.getKey(), entry.parent.getValue());
                        }
                        entry = entry.parent;
                    }
                }
                return map;
            }
        }
    }

    @Override
    public SortedMapTest<K, V> subMap(K fromKey, K toKey) {
        if(fromKey == null || toKey == null) {
            throw new NullPointerException("This key is null!");
        } else if(this.compare(fromKey, toKey) == 1) {
            throw new IllegalArgumentException("This keys are unavailable!");
        }
        SortedMapTest<K, V> map = new TreeMapClass<>();
        EntryTestClass<K, V> entry;
        if(compare(fromKey, toKey) == 0) {
            return null;
        } else {
            entry = this.firstEntry();
            while (compare(entry.getKey(), fromKey) != 0) {
                if (entry == this.root || compare(entry.getKey(), fromKey) == 1) {
                    entry = entry.right;
                } else {
                    entry = entry.parent;
                }
            }
            while (compare(entry.getKey(), toKey) != 0) {
                map.put(entry.getKey(), entry.getValue());
                if(entry == this.root) {
                    entry = entry.right;
                } else if (compare(entry.getKey(), this.root.getKey()) == 1) {
                    map.put(entry.getKey(), entry.getValue());
                    if (entry.left != null && entry != this.root) {
                        map.put(entry.left.getKey(), entry.left.getValue());
                    }
                    if (entry.right != null) {
                        map.put(entry.right.getKey(), entry.right.getValue());
                    }
                    entry = entry.right;
                } else {
                    if (entry.parent.left != null && entry != this.root) {
                        map.put(entry.parent.left.getKey(), entry.parent.left.getValue());
                    }
                    if (entry.parent != null) {
                        map.put(entry.parent.getKey(), entry.parent.getValue());
                    }
                    entry = entry.parent;
                }
            }
        }
        return map;
    }

    @Override
    public SortedMapTest<K, V> tailMap(K fromKey) {
        if(fromKey == null) {
            throw new NoSuchElementException("This key is null!");
        }
        SortedMapTest<K, V> map = new TreeMapClass<>();
        EntryTestClass<K, V> entry = this.root;
        if(compare(entry.getKey(), fromKey) == 0) {
            while (entry != null) {
                map.put(entry.getKey(), entry.getValue());
                if (entry.left != null && entry != this.root) {
                    map.put(entry.left.getKey(), entry.left.getValue());
                }
                entry = entry.right;
            }
        } else {
            entry = this.firstEntry();
            while (compare(entry.getKey(), fromKey) != 0) {
                if (entry == this.root || compare(entry.getKey(), fromKey) == 1) {
                    entry = entry.right;
                } else {
                    entry = entry.parent;
                }
            }
            while (entry != null) {
                map.put(entry.getKey(), entry.getValue());
                if (compare(entry.getKey(), this.root.getKey()) == 1) {
                    map.put(entry.getKey(), entry.getValue());
                    if (entry.left != null && entry != this.root) {
                        map.put(entry.left.getKey(), entry.left.getValue());
                    }
                    entry = entry.right;
                } else {
                    if (entry.parent.left != null && entry != this.root) {
                        map.put(entry.parent.left.getKey(), entry.parent.left.getValue());
                    }
                    entry = entry.parent;
                }
            }
        }
        return map;
    }

    @Override
    public NavigableMapTest<K, V> tailMap(K fromKey, boolean inclusive) {
        if(fromKey == null) {
            throw new NullPointerException("This key is null!");
        } else if(this.isEmpty()) {
            throw new NoSuchElementException("This tree is empty!");
        }
        if(inclusive) {
            return (NavigableMapTest<K, V>) this.tailMap(fromKey);
        } else {
            NavigableMapTest<K, V> map = new TreeMapClass<>();
            EntryTestClass<K, V> entry = this.root;
            entry = this.firstEntry();
            while (compare(entry.getKey(), fromKey) != 0) {
                if (entry == this.root || compare(entry.getKey(), fromKey) == 1) {
                    entry = entry.right;
                } else {
                    entry = entry.parent;
                }
            }
            while (entry != null) {
                if (entry == this.root) {
                    entry = entry.right;
                } else if (compare(entry.getKey(), this.root.getKey()) == 1) {
                    if (entry.left != null && entry != this.root) {
                        map.put(entry.left.getKey(), entry.left.getValue());
                    }
                    if (entry.right != null) {
                        map.put(entry.right.getKey(), entry.right.getValue());
                    }
                    entry = entry.right;
                } else {
                    if (entry.parent.left != null && entry != this.root) {
                        map.put(entry.parent.left.getKey(), entry.parent.left.getValue());
                    }
                    if (entry.parent != null) {
                        map.put(entry.parent.getKey(), entry.parent.getValue());
                    }
                    entry = entry.parent;
                }
            }
            return map;
        }
    }

    @Override
    public CollectionTest<V> values() {
        ArrayListTest<V> temp = new ArrayListTest<>();
        EntryTestClass<K, V> entry = this.root;
        while (entry != null) {
            temp.add(entry.getValue());
            entry = entry.left;
        }
        entry = this.root.right;
        while (entry != null) {
            temp.add(entry.getValue());
            entry = entry.right;
        }
        temp.trimToSize();
        return temp;
    }

    @Override
    public V getOrDefault(K key, V value) {
        if(key == null) {
            throw new NullPointerException("This key is null!");
        }
        if(this.containsKey(key)) {
            return get(key);
        }
        return value;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public V putIfAbsent(K key, V value) {
        if(this.containsKey(key)) {
            return this.get(key);
        } else {
            return put(key, value);
        }
    }

    @Override
    public int compareTo(K o) {
        return this.root.getKey().toString().length() - o.toString().length();
    }

    public EntryTestClass<K, V> getRoot() {
        return this.root;
    }

    public void showTree(){
        this.forEach((k, v) -> System.out.println("[" + k + "; " + v + "]"));
    }

    protected int compare(K key, K key1) {
        return (comparator == null) ? ((Comparable<K>) key).compareTo(key1) : comparator.compare(key, key1);
    }
}
