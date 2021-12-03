package collections;

import java.util.ArrayList;
import java.util.Objects;

public class Main {

    public static void main(String[] args) {
        System.out.println("Implementation ArrayList, LinkedList, HashSet, TreeSet");
        /* ArrayList - class interface List
           LinkedList - class interface List and Deque
           HashSet - class interface Set
           TreeSet - class interface SortedSet
         */
        ArrayListTest<String> list = new ArrayListTest<>();
        list.add("Hanna");
        list.add("Elena");
        list.add("Joe");
        list.add("Irina");
        list.add("Pete");


        LinkedListTest <Integer> test = new LinkedListTest<>();
        test.add(1);
        test.add(2);
        test.add(100);
        test.add(2);
        test.add(5);
        test.add(2);
        test.add(7);


        test.printList();


        LinkedListTest<Integer> test2 = test.subList(1, 4);
        test2.printList();







    }
}
