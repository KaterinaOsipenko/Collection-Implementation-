package collections;

import java.util.Comparator;

public class TreeSetTest<E> implements SortedSetTest<E> {



    @Override
    public Comparator<? super E> comparator() {
        return null;
    }

    @Override
    public E first() {
        return null;
    }

    @Override
    public SortedSetTest<E> headSet(E toElement) {
        return null;
    }

    @Override
    public E last() {
        return null;
    }

    @Override
    public SortedSetTest<E> subSet(E fromElement, E toElement) {
        return null;
    }

    @Override
    public SortedSetTest<E> tailSet(E fromElement) {
        return null;
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
        return false;
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
        return 0;
    }

    @Override
    public E[] toArray() {
        return null;
    }

    @Override
    public void showSet() {

    }
}
