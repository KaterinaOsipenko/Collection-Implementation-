package collections;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Consumer;

public class ArrayListTest<E> implements ListTest<E> {

    private static final int DEFAULT_CAPACITY = 10;

    private E[] values;
    private int size;
    private int point = 0;


    ArrayListTest() {
        this.values = (E[]) new Object[DEFAULT_CAPACITY];
    }

    ArrayListTest(int capacity) {
        if (capacity >= 0) {
            this.values = (E[]) new Object[capacity];
        } else {
            throw new IllegalStateException("Capacity can't be less than 0!");
        }
    }
/*
    ArrayListTest(E[] array) {
        if (array != null) {
            this.values = (E[]) new Object[array.length];
            this.values = array.clone();
        } else {
            throw new NullPointerException("Array can`t be empty!");
        }
    }*/

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= this.size()) {
            throw new IndexOutOfBoundsException("The index is out of range.");
        } else {
            return this.values[index];
        }
    }

    @Override
    public E set(int index, E e) {
        if (index < 0 || index >= this.size()) {
            throw new IndexOutOfBoundsException("The index is out of range.");
        } else {
            return values[index] = e;
        }
    }

    @Override
    public boolean add(E e) {
        if (this.size() == values.length) {
            ensureCapacity(this.size() + 1);
        }
        values[this.size()] = e;
        this.size++;
        return true;
    }

    @Override
    public void add(int index, E e) {
        if (index < 0 || index >= this.size()) {
            throw new IndexOutOfBoundsException("The index is out of range.");
        } else {
            ensureCapacity(this.size() + 1);
            if (this.size() - index >= 0) System.arraycopy(values, index, values, index + 1, this.size() - index);
            values[index] = e;
            size++;
        }
    }

    @Override
    public void clear() {
        this.size = 0;
        ensureCapacity(DEFAULT_CAPACITY);
    }

    @Override
    public boolean contains(E e) {
        for (E el : values) {
            if(el == e) return true;
        }
        return false;
    }

    @Override
    public boolean remove(E e) {
        for(int i = 0; i <= this.size() - 1; i++) {
            if (values[i] == e) {
                for (int j = i; i <= this.size(); i++) {
                    if (i == this.size() - 1) {
                        break;
                    } else {
                        values[i] = values[i + 1];
                    }
                }
                this.size--;
            }
        }
        trimToSize();
        return true;
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= this.size()) {
            throw new IndexOutOfBoundsException("The index is out of range.");
        } else {
            E removeEl = values[index];
            for (int i = index; i <= this.size(); i++) {
                if (index == this.size() - 1) {
                    break;
                } else {
                    values[i] = values[i + 1];
                }
            }
            this.size--;
            return removeEl;
        }
    }

    @Override
    public String toString() {
        trimToSize();
        return Arrays.toString(values);
    }

    @Override
    public boolean isEmpty() {
        return (this.size() == 0);
    }

    @Override
    public int indexOf(E e) {
        for (int i = 0; i < this.size(); i++) if (values[i] == e) return i;
        return -1;
    }

    @Override
    public int lastIndexOf(E e) {
        for (int i = size(); i > -1; i--) {
            if (values[i] == e) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean addAll(E[] e) {
        if (e == null) {
            throw new NullPointerException("Array can`t be empty!");
        } else {
            int temp = this.size();
            ensureCapacity(this.size() + e.length);
            this.size += e.length;
            int j = 0;
            for (int i = temp; i < this.size() ; i++) {
                values[i] = e[j];
                j++;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(int index, E[] e) {
        if (e == null) {
            throw new NullPointerException("Array can`t be empty!");
        } else if (index < 0 || index >= this.size()) {
            throw new IndexOutOfBoundsException("The index is out of range.");
        } else {
            int temp = this.size();
            ensureCapacity(this.size() + e.length);
            this.size += e.length;
            E[] old = (E[]) new Object[temp - index];
            int k = 0;
            for (int i = index; i < temp; i++) {
                old[k] = values[i];
                k++;
            }
            int j = 0;
            for (int i = index; i < index + e.length; i++) {
                values[i] = e[j];
                j++;
            }
            int c = 0;
            for (int i = index + e.length; i < this.size() ; i++) {
                values[i] = old[c];
                c++;
            }
            return true;
        }
    }

    @Override
    public boolean removeAll(E[] e) {
        if (e == null) {
            throw new NullPointerException("Array can`t be empty!");
        } else {
            for(E elem : e)
                if (this.contains(elem)) remove(elem);
                else {
                    return false;
                }
        }
        return true;
    }

    @Override
    public Iterator<E> iterator() {
        return new IteratorTest<>(values);
    }

    public void ensureCapacity(int minCapacity) {
        if (minCapacity < this.size) return;
        E[] newArray = values;
        values = (E[]) new Object[minCapacity];
        for (int i = 0; i < this.size(); i++) {
            values[i] = newArray[i];
        }
    }

    public void trimToSize() {
        ensureCapacity(size());
    }

    protected void removeRange(int fromIndex, int toIndex) {
       if (fromIndex < 0 || fromIndex >= size() || toIndex > size() || toIndex < fromIndex) {
           throw new IndexOutOfBoundsException("FromIndex or toIndex is out of range");
       } else if (toIndex==fromIndex) {
           return;
       } else {
           for (int i = fromIndex; i <= toIndex; i++) {
               if (toIndex == this.size() - 1) {
                   break;
               } else {
                   values[i] = values[i + 1];
               }
           }

       }
        this.size -= (toIndex - (fromIndex - 1));
        trimToSize();
    }

    public void forEach(Consumer<? super E> action) {
        for(E e : this) {
            action.accept(e);
        }
    }

}
