package collections;

import java.util.NoSuchElementException;

public class LinkedListTest<E> implements ListTest<E>, DequeTest<E>{

    private int size;
    private Node head;
    private Node tail;

    private class Node {
        private E value;
        private Node next;
        private Node prev;

        Node(E val) {
            this.value = val;
            this.next = null;
        }

        public Node() {
           this.next = null;
        }

        public void setValue(E val) {
            this.value = val;
        }

        public E getValue () {
            return this.value;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Node getNext() {
            return this.next;
        }
    }

    LinkedListTest() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    @Override
    public boolean add(E e) {
        Node current = head;
        if(current == null) {
            head = tail = new Node(e);
        } else {
            Node newNode = new Node(e);
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
        return true;
    }

    @Override
    public boolean addAll(E[] c) {
        for(E e : c) {
            add(e);
        }
        return true;
    }

    @Override
    public void add(int index, E e) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException("The index is out of range.");
        } else {
            Node current = new Node(e);
            if (index == 0) {
                current.next = head;
                head.prev = current;
                head = current;
                size++;
            } else if (index == this.size()) {
                add(e);
            } else {
                int count = 0;
                current = head;
                while (current != null && count != index) {
                    current = current.next;
                    count++;
                }
                Node temp = new Node(e);
                current.prev.next = temp;
                temp.prev = current.prev;
                current.prev = temp;
                temp.next = current;
            }
            size++;
        }
    }
    // CHECK IT
    @Override
    public boolean addAll(int index, E[] e) {
        if(index < 0 || index > size()) {
            throw new IndexOutOfBoundsException("The index is out of range.");
        } else {
            Node current = new Node();
            if (index == 0) {
                for(E el : e) {
                    add(el);
                }
                return true;
            } else if (index == this.size()) {
                addAll(e);
            } else {
                for (int i = 0; i < index - 1; i++) {
                    for(E el : e) {
                      add(i, el);
                    }
                    size++;
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public void addFirst(E e) {
        Node newNode = new Node();
        newNode.value = e;
        newNode.next = this.head;
        newNode.prev = null;
        if(head != null) {
            head.prev = newNode;
        } else {
            tail = newNode;
        }
        this.head = newNode;
        size++;
    }

    @Override
    public void addLast(E e) {
        if(this.isEmpty()) {
            addFirst(e);
        } else {
            Node newNode = new Node(e);
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
            size++;
        }
    }

    @Override
    public E get(int index) {
        if(index < 0 || index > size()) {
            throw new IndexOutOfBoundsException("The index is out of range.");
        } else if (size == 0){
            throw new NullPointerException("List is empty!");
        } else {
            int curIndex = index;
            Node current = head;
            while(current.getNext() != null && curIndex-- > 0) {
                current = current.getNext();
            }
            return current.getValue();
        }
    }

    @Override
    public E getFirst() {
        return this.head.value;
    }

    @Override
    public E getLast() {
        return tail.value;
    }

    @Override
    public boolean offerFirst(E e) {
        Node newNode = new Node(e);
        newNode.next = this.head;
        newNode.prev = null;
        if(head != null) {
            head.prev = newNode;
        } else {
            tail = newNode;
        }
        this.head = newNode;
        size++;
        return true;
    }

    @Override
    public boolean offerLast(E e) {
        if(this.isEmpty()) {
            addFirst(e);
        } else {
            Node newNode = new Node(e);
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
            size++;
        }
        return true;
    }

    @Override
    public boolean offer(E e) {
        Node current = head;
        if(current == null) {
            head = tail = new Node(e);
        } else {
            Node newNode = new Node(e);
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
        return true;
    }

    @Override
    public E peek() {
        Node root = head;
        if (this.isEmpty()) {
            return null;
        } else {
            head = root.next;
        }
        return root.value;
    }

    @Override
    public E peekFirst() {
        Node root = head;
        if (this.isEmpty()) {
            return null;
        } else {
            head = root.next;
        }
        return root.value;
    }

    @Override
    public E peekLast() {
        Node root = tail;
        if (this.isEmpty()) {
            return null;
        } else {
            Node temp = tail;
            tail = temp.prev;
            tail.next = null;
        }
        return root.value;
    }

    @Override
    public E poll() {
        Node root = head;
        if (this.isEmpty()) {
            return null;
        } else {
            removeFirst();
        }
        return root.value;
    }

    @Override
    public E pollFirst() {
        Node root = head;
        if (this.isEmpty()) {
            return null;
        } else {
            removeFirst();
        }
        return root.value;
    }

    @Override
    public E pollLast() {
        Node root = tail;
        if (this.isEmpty()) {
            return null;
        } else {
            removeLast();
        }
        return root.value;
    }

    @Override
    public E pop() {
        return null;
    }

    @Override
    public void push(E e) {

    }

    // Удаляет и возвращает первый элемент из этого списка.
    @Override
    public E removeFirst() {
        if(this.isEmpty()) {
            throw new NoSuchElementException("The list is empty!");
        } else {
            Node temp = head;
            head = temp.next;
            size--;
            return temp.value;
        }
    }

    // Удаляет и возвращает последний элемент из этого списка.
    @Override
    public E removeLast() {
        Node current = tail;
        if(this.isEmpty()) {
            throw new NoSuchElementException("The list is empty!");
        } else {
            tail = tail.prev;
            tail.next = null;
            size--;
        }
        return current.value;
    }

    // Удаляет первое вхождение указанного элемента в этом списке (при обходе списка от начала до конца).
    @Override
    public boolean removeFirstOccurrence(Object o) {
        int count = 0;
        boolean flag = false;
        if(this.isEmpty()) {
            throw new NoSuchElementException("The list is empty!");
        } else {
            Node current = head;
            while (current.next != null) {
                if (current.value == o && count == 0) {
                    if (current.prev == null) {
                        removeFirst();
                    } else {
                        current.next.prev = current.prev;
                        current.prev.next = current.next;
                        size--;
                    }
                    flag = true;
                    count++;
                }
                current = current.next;
                if (current == tail && current.value == o) {
                    removeLast();
                    flag = true;
                }
            }
        }
        return flag;
    }

    // Удаляет последнее вхождение указанного элемента в этом списке (при обходе списка от начала до конца).
    @Override
    public boolean removeLastOccurrence(Object o) {
        int count = 0;
        boolean flag = false;
        if(this.isEmpty()) {
            throw new NoSuchElementException("The list is empty!");
        } else {
            Node current = tail;
            while (current.prev != null) {
                if (current.value == o && count == 0) {
                    if (current.next == null) {
                        removeLast();
                    } else {
                        current.next.prev = current.prev;
                        current.prev.next = current.next;
                        size--;
                    }
                    flag = true;
                    count++;
                }
                current = current.prev;
                if (current == head && current.value == o) {
                    removeFirst();
                    flag = true;
                }
            }
        }
        return flag;
    }

    // Удаляет по индексу
    @Override
    public E remove(int index) {
        Node current = getByIndex(index);
        if(this.isEmpty()) {
            throw new NoSuchElementException("The list is empty!");
        } else if (index < 0 || index >= size()){
            throw new IndexOutOfBoundsException("The index is out of range.");
        } else {
            if (index == 0 && size == 1) {
                head = tail = null;
            } else if (index == 0 && size > 1) {
                current = head;
                head = current.next;
            } else if (index == size - 1) {
                tail = tail.prev;
                tail.next = null;
            } else {
                current.next.prev = current.prev;
                current.prev.next = current.next;
                current.next = null;
                current.prev = null;
            }
        }
        size--;
        return current.value;
    }

    // Удаляет голову
    @Override
    public E remove() {
        if(this.isEmpty()) {
            throw new NoSuchElementException("The list is empty!");
        } else {
            Node temp = head;
            head = temp.next;
            size--;
            return temp.value;
        }
    }

    // Remove specified element
    @Override
    public boolean removeElement(E e) {
        if(this.isEmpty()) {
            throw new NoSuchElementException("The list is enpty!");
        } else {
            Node temp = head;
            while (temp.next != null) {
                if (temp.next.value == e) {
                    temp.next = temp.next.next;
                    size--;
                    return true;
                }
                temp = temp.next;
                if (temp.value == e) {
                    tail = tail.prev;
                    tail.next = null;
                    size--;
                }
            }
        }
        return false;
    }

    private Node getByIndex(int index) {
        Node current = head;
        if (index != 0) {
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        }
        return current;
    }

    @Override
    public int indexOf(E e) {
        Node seekNode = new Node();
        seekNode.next = head;
        for (int i = 0; i < this.size(); i++) {
            if(seekNode.next != null) {
                if(e.equals(seekNode.next.value)){
                    return i;
                }
            }
            seekNode = seekNode.next;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(E e) {
        Node seekNode = new Node(e);
        seekNode.prev = tail;
        for (int i = this.size() - 1; i > 0; i--) {
            if (seekNode.prev != null) {
                if (e.equals(seekNode.prev.value)) {
                    return i;
                }
            }
            seekNode = seekNode.prev;
        }
        return -1;
    }

    @Override
    public E set(int index, E e) {
        if (index < 0 || index >= size()){
            throw new IndexOutOfBoundsException("The index is out of range.");
        } else {
            Node newNode = getByIndex(index);
            Node current = new Node();
            current.value = newNode.value;
            newNode.value = e;
            return current.value;
        }
    }

    @Override
    public LinkedListTest<E> subList(int fromIndex, int toIndex) {
        LinkedListTest<E> list = new LinkedListTest<>();
        Node start = getByIndex(fromIndex);
        Node finish = getByIndex(toIndex);
        while (start.next != finish.next) {
            list.add(start.value);
            start = start.next;
        }
        list.add(start.value);
        return list;
    }

    @Override
    public E element() {
        if (this.isEmpty()) {
            throw new NoSuchElementException("This list is empty!");
        } else {
            Node root = head;
            if (this.isEmpty()) {
                return null;
            } else {
                head = root.next;
            }
            return root.value;
        }
    }

    @Override
    public boolean contains(E o) {
        while (head.next != null) {
            if (head.value == o) {
                return true;
            }
            head = head.next;
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return (head == null);
    }

    @Override
    public void clear() {
        head.next = null;
        head = tail = null;
        size = 0;
    }

    @Override
    public IteratorTest<E> iterator() {
        return null;
    }

    @Override
    public IteratorTest<E> descendingIterator() {
        return null;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        while (head.next != null) {
            hash = (int) (31 * hash + (head.value == null ? 0 : head.value.hashCode()));
            head = head.next;
        }
        return hash;
    }

    @Override
    public E[] toArray() {
        E[] array = (E[])new Object[this.size()];
        int count = 0;
        Node temp = head;
        while (temp.next != null) {
            array[count] = temp.value;
            count++;
            temp = temp.next;
        }
        array[count++] = temp.value;
        return array;
    }

    public void printList() {
        if(this.isEmpty()) {
            System.out.println("List is empty!");
        } else {
            Node temp = head;
            while (temp.next != null) {
                System.out.print(temp.value + " ");
                temp = temp.next;
            }
            System.out.println(temp.value);
        }
    }
}
