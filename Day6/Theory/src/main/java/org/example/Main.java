package org.example;

import java.util.*;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.


        Employee emp1 = new Employee("1001", "Vignesh", "ICC", 80000);
        Employee emp2 = new Employee("1002", "Raj", "Sales", 90000);
        Employee emp3 = new Employee("1003", "Soham", "Tech", 70000);

        // Hashmaps

        HashMap<Integer, Employee> map = new HashMap<>();
        map.put(101, emp1);
        map.put(102, emp2);
        map.put(103, emp3);

        System.out.print(map.get(101));

        //ArrayList

        List<Employee> employees = new ArrayList<>();
        employees.add(emp1);
        employees.add(emp2);
        employees.add(emp3);


        ListIterator<Employee> iterator = employees.listIterator();

        System.out.println("Foward traversal");
        while (iterator.hasNext()) {
            Employee emp = iterator.next();
            System.out.println(emp);
        }

        System.out.println("Backward traversal");
        while (iterator.hasPrevious()) {
            Employee emp = iterator.previous();
            System.out.println(emp);
        }

        //Hashset
        HashSet<String> set=new HashSet<>();
        // adding elements
        set.add("Apple");
        set.add("Banna");
        set.add("Cherry");

        System.out.println("HashSet contains: " + set);

        // Check if an element exists
        if (set.contains("Banana")) {
            System.out.println("Banana is in the set");
        }

        // Remove an element
        set.remove("Cherry");
        System.out.println("After removing Cherry: " + set);

        // Iterate over the Hashmap


        //Using Entry set
        Set<Map.Entry<Integer, Employee>> entrySet = map.entrySet();
        for (Map.Entry<Integer, Employee> entry : entrySet) {
            Integer key = entry.getKey();
            Employee value = entry.getValue();
            System.out.println("Key: " + key + ", Value: " + value);
        }


        //using Key set for iteration
        for (Integer key : map.keySet()) {
            Employee value = map.get(key);
            System.out.println("Key: " + key + ", Value: " + value);
        }

//       using for each
        map.forEach((key, value) -> {
            System.out.println("Key: " + key + ", Value: " + value);
        });

        //Box class
        Box<String> stringBox=new Box<>();
        stringBox.setValue("VK");
        System.out.println(stringBox.getValue());


//
//        String typee="Christmas";
//        Discount d=new ChristmasDiscount() ;
//        System.out.println(d.getDiscount());


    }


}