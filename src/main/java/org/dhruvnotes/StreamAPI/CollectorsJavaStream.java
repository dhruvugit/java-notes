package org.dhruvnotes.StreamAPI;

import java.util.*;
import java.util.stream.Collectors;

public class CollectorsJavaStream {
    public static void main(String[] args) {
        //Collectors is utility class which provide various methods

        //We already know how to collect to a list or set, let see something else


        //Collecting to a specific collection
        //-----toCollection takes a supplier it means takes nothing return something
        List<String> names = new ArrayList<>(List.of("Dhruv", "ram", "govind"));
        ArrayDeque<String> collect = names.stream().collect(Collectors.toCollection(() -> new ArrayDeque<>()));

        //Joining Strings ---If no separater provided we will get a single word with all chars joined
        String concatenatedString = names.stream().map(String::toUpperCase).collect(Collectors.joining(", "));
        System.out.println("Concatenated String of names list is : " + concatenatedString);

        //Summarzizing Data
        //Gives count sum min avg max
        List<Integer> list5 = List.of(5,2,5,2,47,8,95,2);
        IntSummaryStatistics stats = list5.stream().collect(Collectors.summarizingInt(x -> x));
        System.out.println("Summary : " + stats.toString());
        //or if you want just a particular stat that we can also do
        double avg = list5.stream().collect(Collectors.averagingInt(x -> x));
        System.out.println("Avg is : " + avg);
        //simple count
        Long count = list5.stream().collect(Collectors.counting());
        System.out.println("Count using collector : "  + count);


        //Grouping Elements
        List<String> words1 = List.of("java", "OS", "HI", "CP", "Multithreading", "SpringBot");
        //Inside Grouping by we tell the logic of sepration suppose length, so our elements will be separated by length and key will be lengh only
        System.out.println(words1.stream().collect(Collectors.groupingBy(a -> a.length())));
        //this will group our elements and then do some operation on that group which is joining
        System.out.println(words1.stream().collect(Collectors.groupingBy(String::length, Collectors.joining())));
        //Each length as key what is count in it
        System.out.println(words1.stream().collect(Collectors.groupingBy(String::length, Collectors.counting())));

        // 8. Grouping Elements
        List<String> words = Arrays.asList("hello", "world", "java", "streams", "collecting");
        System.out.println(words.stream().collect(Collectors.groupingBy(String::length)));
        System.out.println(words.stream().collect(Collectors.groupingBy(String::length, Collectors.joining(", "))));
        System.out.println(words.stream().collect(Collectors.groupingBy(String::length, Collectors.counting())));
        TreeMap<Integer, Long> treeMap = words.stream().collect(Collectors.groupingBy(String::length, TreeMap::new, Collectors.counting()));
        System.out.println(treeMap);

        // 9. Partitioning Elements
        //  Partitions elements into two groups (true and false) based on a predicate
        System.out.println(words.stream().collect(Collectors.partitioningBy(x -> x.length() > 5)));

        // 10. Mapping and Collecting
        // Applies a mapping function before collecting
        System.out.println(words.stream().collect(Collectors.mapping(x -> x.toUpperCase(), Collectors.toList())));

        // 11. toMap

        // Example 1: Collecting Names by Length
        List<String> l1 = Arrays.asList("Anna", "Bob", "Alexander", "Brian", "Alice");
        System.out.println(l1.stream().collect(Collectors.groupingBy(String::length)));

        // Example 2: Counting Word Occurrences
        String sentence = "hello world hello java world";
        //Grouping criteria is word itself here that's why we did x->x
        System.out.println(Arrays.stream(sentence.split(" ")).collect(Collectors.groupingBy(x -> x, Collectors.counting())));

        //Patition creates two partition one comes under true key another into false key and it's quite obvous we give a boolean condition for partition
        // Example 3: Partitioning Even and Odd Numbers
        List<Integer> l2 = Arrays.asList(1, 2, 3, 4, 5, 6);
        System.out.println(l2.stream().collect(Collectors.partitioningBy(x -> x % 2 == 0)));

        // Example 4: Summing Values in a Map
        Map<String, Integer> items = new HashMap<>();
        items.put("Apple", 10);
        items.put("Banana", 20);
        items.put("Orange", 15);
        System.out.println(items.values().stream().reduce(Integer::sum));
        System.out.println(items.values().stream().collect(Collectors.summingInt(x -> x)));

        // Example 5: Creating a Map from Stream Elements
        List<String> fruits = Arrays.asList("Apple", "Banana", "Cherry");
        System.out.println(fruits.stream().collect(Collectors.toMap(x -> x.toUpperCase(), x -> x.length())));

        // Example 6:
        List<String> words2 = Arrays.asList("apple", "banana", "apple", "orange", "banana", "apple");
        System.out.println(words2.stream().collect(Collectors.toMap(k -> k, v -> 1, (x, y) -> x + y)));;
    }
}
