package collections;

public class HashSetTest<E> implements SetTest<E> {

    private int size;

    public HashSetTest () {

    }

    public HashSetTest (E e) {

    }

    public HashSetTest(int initialCapacity) {

    }

    public HashSetTest(int initialCapacity, float loadFactor) {

    }

    @Override
    public boolean add(E e) {
        return false;
    }

    @Override
    public boolean addAll(E[] c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean contains(E o) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public IteratorTest<E> iterator() {
        return null;
    }

    @Override
    public boolean removeElement(E o) {
        return false;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public E[] toArray() {
        return null;
    }
}
