package collections;

import jdk.swing.interop.SwingInterOpUtils;

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
/*
        HashMapTest<String, Integer> map = new HashMapTest<>();
        map.put("Alina", 132);
        map.put("Kary", 145);
        map.put("Lina", 458);



        HashSetTest<Integer> set = new HashSetTest<>();
        set.add(122);
        set.add(148);
        set.add(12);



*/

        TreeMapClass<Integer, String> mapClass = new TreeMapClass<>();
        System.out.println(mapClass.size());
        mapClass.put(11, "Kate");
        mapClass.put(18, "Lina");
        mapClass.put(10, "Mark");
        mapClass.put(25, "Mila");
        mapClass.put(2, "Roma");
        mapClass.put(100, "Elya");
        System.out.println(mapClass.size());

        TreeSetClass<Integer> tree = new TreeSetClass<>();
        tree.add(10);
        tree.add(12);
        tree.add(1);
        tree.add(54);
        tree.add(90);
        tree.showSet();
        System.out.println();
        NavigableSetTest<Integer> trees = (NavigableSetTest<Integer>) tree.tailSet(12,false);
        trees.showSet();
    }
}
