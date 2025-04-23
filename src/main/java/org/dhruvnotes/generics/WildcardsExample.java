package org.dhruvnotes.generics;

import java.util.*;

class WildcardsExample {
    public static void main(String[] args) {
        // 1️⃣ Unbounded Wildcard (List<?>)
        List<?> unknownList = new ArrayList<>(List.of(1, 2.5, "hello"));
        printList(unknownList); // ✅ Can read as Object, cannot add elements

        // 2️⃣ Upper Bounded Wildcard (List<? extends Number>)
        List<Integer> intList = Arrays.asList(10, 20, 30);
        List<Double> doubleList = Arrays.asList(2.5, 3.6);
        sumOfNumbers(intList); // ✅ Works for List<Integer>
        sumOfNumbers(doubleList); // ✅ Works for List<Double>

        // 3️⃣ Lower Bounded Wildcard (List<? super Integer>)
        List<Number> numberList = new ArrayList<>();
        addNumbers(numberList); // ✅ Can add Integer values
        List<Object> objectList = new ArrayList<>();
        addNumbers(objectList); // ✅ Can add Integer values

        // 4️⃣ Mixing Wildcards
        List<Number> numbers = new ArrayList<>(List.of(1, 2.5, 3));
        copyList(numbers, objectList); // ✅ Can copy from Number to Object

        // Print final lists
        System.out.println("Number List: " + numberList);
        System.out.println("Object List: " + objectList);
    }

    // ✅ 1. Unbounded Wildcard (?)
    static void printList(List<?> list) {
        for (Object item : list) { // Can read as Object
            System.out.print(item + " ");
        }
        System.out.println();
        // list.add(10); ❌ Cannot add (except null)
    }

    // ✅ 2. Upper Bounded Wildcard (? extends Number)
    static void sumOfNumbers(List<? extends Number> list) {
        double sum = 0;
        for (Number num : list) { // ✅ Can read as Number
            sum += num.doubleValue();
        }
        System.out.println("Sum: " + sum);
        // list.add(10); ❌ Cannot add elements
    }

    // ✅ 3. Lower Bounded Wildcard (? super Integer)
    static void addNumbers(List<? super Integer> list) {
        list.add(10); // ✅ Can add Integer
        list.add(20);
        // Number n = list.get(0); ❌ Cannot read as Number
    }

    // ✅ 4. Mixing Wildcards
    static void copyList(List<? extends Number> src, List<? super Number> dest) {
        for (Number num : src) {
            dest.add(num); // ✅ Allowed because dest is super of Number
        }
    }
}
