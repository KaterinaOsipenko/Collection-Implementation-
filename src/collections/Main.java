package collections;

import java.lang.reflect.Array;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("Implementation ArrayList, LinkedList, HashSet, TreeSet");
//         ArrayList - class interface List
//           LinkedList - class interface List and Deque
//           HashSet - class interface Set
//           TreeSet - class interface SortedSet

       /* ArrayListTest<String> list = new ArrayListTest<>();
        list.add("Hanna");
        list.add("Elena");
        list.add("Joe");
        list.add("Irina");
        list.add("Pete");
        list.trimToSize();



     LinkedListTest <Integer> test = new LinkedListTest<>();
        test.add(1);
        test.add(2);
        test.add(100);
        test.add(2);
        test.add(5);
        test.add(2);
        test.add(7);*/

        HashMapTest<String, Integer> map = new HashMapTest<>();
        map.put("Alina", 132);
        map.put("Kary", 145);
        map.put("Lina", 458);



        HashSetTest<Integer> set = new HashSetTest<>();
        set.add(122);
        set.add(148);
        set.add(12);


        SetTest<EntryTestClass<String, Integer>> test = map.entrySet();
       test.showSet();

    }
}
