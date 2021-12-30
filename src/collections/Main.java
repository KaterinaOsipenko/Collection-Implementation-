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
/*
        HashMapTest<String, Integer> map = new HashMapTest<>();
        map.put("Alina", 132);
        map.put("Kary", 145);
        map.put("Lina", 458);



        HashSetTest<Integer> set = new HashSetTest<>();
        set.add(122);
        set.add(148);
        set.add(12);


        SetTest<EntryTestClass<String, Integer>> test = map.entrySet();
       test.showSet();*/

        EntryTestClass<Integer, String> map  = new EntryTestClass<>(11, "Elena");
        System.out.println(map.getEntry() );
        TreeMapClass<Integer, String> mapClass = new TreeMapClass<>();
        System.out.println(mapClass.size());
        mapClass.put(11, "Kate");
        mapClass.put(18, "Lina");
        mapClass.put(10, "Mark");
        mapClass.put(25, "Mila");
        mapClass.put(2, "Roma");
        mapClass.put(100, "Elya");
        mapClass.showTree();

         mapClass.putIfAbsent(19, "Erik") ;
        mapClass.showTree();

        SortedMapTest<Integer, String> exam = mapClass.subMap(10, 10);
        exam.forEach((k, v) -> System.out.println(k + " " + v));





//        TreeMap<Integer, String> mapEx = new TreeMap<>();
//        mapEx.put(121, "Lena");
//        mapEx.put(185, "Alisa");
//        mapEx.put(2, "Becky");
//        mapEx.put(15, "Licka");
//        System.out.println(mapEx);
//        System.out.println(mapEx.pollFirstEntry());
//        System.out.println(mapEx);
//        System.out.println(mapEx.pollFirstEntry());
//        System.out.println(mapEx);



    }
}
