package collections;

import java.lang.ref.SoftReference;
import java.lang.reflect.Array;
import java.util.Arrays;

public class HashSetTest<E> implements SetTest<E> {


    private HashMapTest<E, Object> set = null;
    private static final double loadFactor = 0.75;
    private static final Object PRESENT = new Object();
    private static final int capacity = 16;

    public HashSetTest () {
        set = new HashMapTest<>(capacity);
    }

    public HashSetTest(int initialCapacity) {
        set = new HashMapTest<>(initialCapacity);
    }

    public HashSetTest(int initialCapacity, float loadFactor) {
        set = new HashMapTest<>(initialCapacity, loadFactor);
    }

    @Override
    public boolean add(E e) {
        return set.put(e, PRESENT) == null;
    }

    @Override
    public boolean addAll(E[] c) {
        for (int i = 0; i < c.length; i++) {
            set.put(c[i], PRESENT);
        }
        return true;
    }

    @Override
    public void clear() {
        this.set.clear();
    }

    @Override
    public boolean contains(E o) {
        return this.set.containsKey(o);
    }

    @Override
    public boolean isEmpty() {
        return this.set.size() == 0;
    }

    @Override
    public IteratorTest<E> iterator() {
        return new IteratorTest<>();
    }

    @Override
    public boolean removeElement(E o) {
        return this.set.remove(o) == null;
    }

    @Override
    public int size() {
        return this.set.size();
    }

    @Override
    public E[] toArray() {
        E[] obj = (E[]) new Object[this.set.size()];
        this.set.forEach((k, v) -> {
            Arrays.fill(obj, k);
        });
        return obj;
    }

    @Override
    public void showSet() {
        this.set.forEach((k, v) -> System.out.println(k));
    }



}
