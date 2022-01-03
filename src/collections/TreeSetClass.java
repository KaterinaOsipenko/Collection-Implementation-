package collections;

import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class TreeSetClass<K> implements NavigableSetTest<K> {

    private transient TreeMapClass<K, Object> set;
    private static final Object PRESENT = new Object();

    TreeSetClass () {
        set = new TreeMapClass<>();
    }

    TreeSetClass(CollectionTest<? extends K> c) {
        // set = new TreeMapClass(c);
    }

    TreeSetClass(Comparator<? super K> comparator) {
        set = new TreeMapClass(comparator);
    }

    TreeSetClass(SortedSetTest<K> s) {
        set = new TreeMapClass((SortedMapTest) s);
    }


    @Override
    public boolean add(K e) {
        return this.set.put(e, PRESENT) != null;
    }

    @Override
    public boolean addAll(K[] c) {
        return false;
    }

    @Override
    public void clear() {
        this.set.clear();
    }

    @Override
    public Comparator<? super K> comparator() {
        return this.comparator();
    }

    @Override
    public K first() {
        return this.set.firstKey();
    }

    @Override
    public K ceiling(K k) {
        if(k == null) {
            throw new NullPointerException("This key is null!");
        }
        return this.set.ceilingKey(k);
    }

    @Override
    public NavigableSetTest<K> descendingSet() {
        NavigableSetTest<K> set = new TreeSetClass<K>();
        EntryTestClass<K, Object> entry = this.set.lastEntry();
        while (entry.parent != null) {
            set.add(entry.getKey());
            entry = entry.parent;
        }
        while (entry != null) {
            set.add(entry.getKey());
            if(entry.left != null) {
                set.add(entry.left.getKey());
            }
            if(entry.right != null) {
                set.add(entry.right.getKey());
            }
            entry = entry.left;
        }
        return set;
    }

    @Override
    public K floor(K k) {
        if(k == null) {
            throw new NullPointerException("This key is null!");
        }
        return this.set.floorKey(k);
    }

    @Override
    public SortedSetTest<K> headSet(K toElement) {
        if(toElement == null) {
            throw new NullPointerException("This key is null!");
        } else if(this.isEmpty()) {
            throw new NoSuchElementException("This tree is empty!");
        }
        SortedSetTest<K> setTest = new TreeSetClass<>();
        EntryTestClass<K, Object> entry = this.set.firstEntry();
        while (this.set.compare(entry.getKey(), toElement) != 0) {
            setTest.add(entry.getKey());
            if(this.set.compare(entry.getKey(), this.set.getRoot().getKey()) >= 0){
                entry = entry.right;
            } else {
                entry = entry.parent;
            }
        }
        return setTest;
    }

    @Override
    public NavigableSetTest<K> headSet(K toElement, boolean inclusive) {
        if (toElement == null) {
            throw new NullPointerException("This key is null!");
        } else if (this.isEmpty()) {
            throw new NoSuchElementException("This tree is empty!");
        }
        NavigableSetTest<K> setTest = new TreeSetClass<>();
        EntryTestClass<K, Object> entry = this.set.firstEntry();
        while (this.set.compare(entry.getKey(), toElement) != 0) {
            setTest.add(entry.getKey());
            if (this.set.compare(entry.getKey(), this.set.getRoot().getKey()) >= 0) {
                entry = entry.right;
            } else {
                entry = entry.parent;
            }
        }
        if(inclusive) {
            setTest.add(entry.getKey());
        }
        return setTest;
    }

    @Override
    public K higher(K e) {
        if(e == null) {
            throw new NullPointerException("This key is null!");
        }
        return this.set.higherKey(e);
    }

    @Override
    public K lower(K e) {
        if(e == null) {
            throw new NullPointerException("This key is null!");
        }
        return this.set.lowerKey(e);
    }

    @Override
    public K pollFirst() {
        return this.set.pollFirstEntry().getKey();
    }

    @Override
    public K pollLast() {
        return this.set.pollLastEntry().getKey();
    }

    @Override
    public NavigableSetTest<K> subSet(K fromElement, boolean fromInclusive, K toElement, boolean toInclusive) {
        if(fromElement == null || toElement == null) {
            throw new NullPointerException("This key is null!");
        } else if(this.set.compare(fromElement, toElement) == 1) {
            throw new IllegalArgumentException("This keys are unavailable!");
        }
        if(fromInclusive == toInclusive == true && this.set.compare(fromElement, toElement) == 0) {
            return null;
        } else {
            NavigableSetTest<K> setTest = new TreeSetClass<>();
            EntryTestClass<K, Object> entry;
            entry = this.set.firstEntry();
            while (this.set.compare(entry.getKey(), fromElement) != 0) {
                if (entry == this.set.getRoot() || this.set.compare(entry.getKey(), fromElement) == 1) {
                    entry = entry.right;
                } else {
                    entry = entry.parent;
                }
            }
            if (fromInclusive && !toInclusive) {
                return (NavigableSetTest<K>) this.subSet(fromElement, toElement);
            } else if (fromInclusive && toInclusive) {
                while (this.set.compare(entry.getKey(), toElement) == -1) {
                    setTest.add(entry.getKey());
                    if(entry == this.set.getRoot()) {
                        entry = entry.right;
                    } else if (this.set.compare(entry.getKey(), this.set.getRoot().getKey()) == 1) {
                        setTest.add(entry.getKey());
                        if (entry.left != null && entry != this.set.getRoot()) {
                            setTest.add(entry.left.getKey());
                        }
                        if (entry.right != null) {
                            setTest.add(entry.right.getKey());
                        }
                        entry = entry.right;
                    } else {
                        if (entry.parent != null) {
                            setTest.add(entry.parent.getKey());
                        }
                        entry = entry.parent;
                    }
                }
                setTest.add(entry.getKey());
                return setTest;
            } else if (fromInclusive == false && toInclusive) {
                while (this.set.compare(entry.getKey(), toElement) == -1) {
                    if(entry == this.set.getRoot()) {
                        entry = entry.right;
                    } else if (this.set.compare(entry.getKey(), this.set.getRoot().getKey()) == 1) {
                        if (entry.left != null && entry != this.set.getRoot()) {
                            setTest.add(entry.left.getKey());
                        }
                        setTest.add(entry.getKey());
                        entry = entry.right;
                    } else {
                        if (entry.parent != null) {
                            setTest.add(entry.parent.getKey());
                        }
                        entry = entry.parent;
                    }
                }
                setTest.add(entry.getKey());
                return setTest;
            } else {
                while (this.set.compare(entry.getKey(), toElement) != 0) {
                    if(entry == this.set.getRoot()) {
                        entry = entry.right;
                    } else if (this.set.compare(entry.getKey(), this.set.getRoot().getKey()) == 1) {
                       setTest.add(entry.getKey());
                        if (entry.left != null && entry != this.set.getRoot()) {
                            setTest.add(entry.left.getKey());
                        }
                        entry = entry.right;
                    } else {
                        if (entry.parent != null) {
                            setTest.add(entry.parent.getKey());
                        }
                        entry = entry.parent;
                    }
                }
                return setTest;
            }
        }
    }

    @Override
    public K last() {
        return this.set.lastKey();
    }

    @Override
    public SortedSetTest<K> subSet(K fromElement, K toElement) {
        if(fromElement == null || toElement == null) {
            throw new NullPointerException("This key is null!");
        } else if(this.set.compare(fromElement, toElement) == 1) {
            throw new IllegalArgumentException("This keys are unavailable!");
        }
        SortedSetTest<K> setTest = new TreeSetClass<>();
        EntryTestClass<K, Object> entry;
        if(this.set.compare(fromElement, toElement) == 0) {
            return null;
        } else {
            entry = this.set.firstEntry();
            while (this.set.compare(entry.getKey(), fromElement) != 0) {
                if (entry == this.set.getRoot() || this.set.compare(entry.getKey(), fromElement) == 1) {
                    entry = entry.right;
                } else {
                    entry = entry.parent;
                }
            }
            while (this.set.compare(entry.getKey(), toElement) != 0) {
                setTest.add(entry.getKey());
                if(entry == this.set.getRoot()) {
                    entry = entry.right;
                } else if (this.set.compare(entry.getKey(), this.set.getRoot().getKey()) == 1) {
                    setTest.add(entry.getKey());
                    if (entry.left != null && entry != this.set.getRoot()) {
                        setTest.add(entry.left.getKey());
                    }
                    if (entry.right != null) {
                        setTest.add(entry.right.getKey());
                    }
                    entry = entry.right;
                } else {
                    if (entry.parent.left != null && entry != this.set.getRoot()) {
                        setTest.add(entry.parent.left.getKey());
                    }
                    if (entry.parent != null) {
                        setTest.add(entry.parent.left.getKey());
                    }
                    entry = entry.parent;
                }
            }
        }
        return setTest;
    }

    @Override
    public SortedSetTest<K> tailSet(K fromElement) {
        if(fromElement == null) {
            throw new NoSuchElementException("This key is null!");
        }
        SortedSetTest<K> setTest = new TreeSetClass<>();
        EntryTestClass<K, Object> entry = this.set.getRoot();
        if(this.set.compare(entry.getKey(), fromElement) == 0) {
            while (entry != null) {
                setTest.add(entry.getKey());
                if (entry.left != null && entry != this.set.getRoot()) {
                    setTest.add(entry.left.getKey());
                }
                entry = entry.right;
            }
        } else {
            entry = this.set.firstEntry();
            while (this.set.compare(entry.getKey(), fromElement) != 0) {
                if (entry == this.set.getRoot() || this.set.compare(entry.getKey(), fromElement) == 1) {
                    entry = entry.right;
                } else {
                    entry = entry.parent;
                }
            }
            while (entry != null) {
                setTest.add(entry.getKey());
                if (this.set.compare(entry.getKey(), this.set.getRoot().getKey()) == 1) {
                    setTest.add(entry.getKey());
                    if (entry.left != null && entry != this.set.getRoot()) {
                        setTest.add(entry.left.getKey());
                    }
                    entry = entry.right;
                } else {
                    if (entry.parent.left != null && entry != this.set.getRoot()) {
                        setTest.add(entry.parent.left.getKey());
                    }
                    entry = entry.parent;
                }
            }
        }
        return setTest;
    }

    @Override
    public NavigableSetTest<K> tailSet(K fromElement, boolean inclusive) {
        if(fromElement == null) {
            throw new NullPointerException("This key is null!");
        } else if(this.isEmpty()) {
            throw new NoSuchElementException("This tree is empty!");
        }
        if(inclusive) {
            return (NavigableSetTest<K>) this.set.tailMap(fromElement);
        } else {
            NavigableSetTest<K> setTest = new TreeSetClass<>();
            EntryTestClass<K, Object> entry;
            entry = this.set.firstEntry();
            while (this.set.compare(entry.getKey(), fromElement) != 0) {
                if (entry == this.set.getRoot() || this.set.compare(entry.getKey(), fromElement) == 1) {
                    entry = entry.right;
                } else {
                    entry = entry.parent;
                }
            }
            while (entry != null) {
                if (entry == this.set.getRoot()) {
                    entry = entry.right;
                } else if (this.set.compare(entry.getKey(), this.set.getRoot().getKey()) == 1) {
                    if (entry.left != null && entry != this.set.getRoot()) {
                        setTest.add(entry.left.getKey());
                    }
                    if (entry.right != null) {
                        setTest.add(entry.right.getKey());
                    }
                    entry = entry.right;
                } else {
                    if (entry.parent.left != null && entry != this.set.getRoot()) {
                        setTest.add(entry.parent.left.getKey());
                    }
                    if (entry.parent != null) {
                        setTest.add(entry.parent.getKey());
                    }
                    entry = entry.parent;
                }
            }
            return setTest;
        }
    }

    @Override
    public boolean contains(K o) {
        if(o == null) {
            throw new NullPointerException("This key is null!");
        }
        return this.set.containsKey(o);
    }

    @Override
    public boolean isEmpty() {
        return this.set.isEmpty();
    }

    @Override
    public IteratorTest<K> iterator() {
        return this.set.keySet().iterator();
    }

    @Override
    public boolean removeElement(K o) {
        if(o == null) {
            throw new NullPointerException("This key is null!");
        }
        return this.set.remove(o) == null;
    }

    @Override
    public int size() {
        return this.set.size();
    }

    @Override
    public K[] toArray() {
        return this.set.keySet().toArray();
    }


    @Override
    public void showSet() {
        this.set.forEach((k, v) -> System.out.println(k));
    }
}
