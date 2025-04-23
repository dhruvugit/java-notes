package org.dhruvnotes.StreamAPI;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamApiTerminal {
    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<>(List.of(1, 2, 3, 5, 5, 2, 3));
        List<Integer> finalList1 = list1.stream().distinct().collect(Collectors.toList());
        Set<Integer> finalSet1 = list1.stream().collect(Collectors.toSet());
        System.out.println(finalList1);
        System.out.println(finalSet1);

        /*
        Stream.toList() returns an immutable list.
        Use toList() if you want a simple, immutable list from a stream (Java 16+).
        For mutable lists, stick with collect(Collectors.toList()).
        * */
        //forEach
        list1.stream().forEach(System.out::println);

        //reduce -- combine elements to produce single result uses BinaryOperator for it, BinaryOperator extends BinaryFunction
        //in which the BinaryOperator have same input and output values so we write it as BinaryOperator<Integer>
        //it returns Optiona<Integer> or if you want you can direclty store in int
        List<Integer> list3 = list1.stream().reduce((a, b) -> a + b).map(List::of).orElse(List.of());
        Optional<Integer> reducedResult = list1.stream().reduce((a, b) -> a + b);
        System.out.println("Reduced Result : " + reducedResult);

        //count

        //anyMatch , allMatch, noneMatch
        boolean b1 = list1.stream().anyMatch(x -> x %2 == 0);
        System.out.println("At least one is even : " + b1);

        boolean b2 = list1.stream().allMatch(x -> x%2 == 0);
        System.out.println("All are even : " + b2);
        //none match is just opposite of allMatch
        boolean b3 = list1.stream().noneMatch(x -> x%2 == 0);
        System.out.println("All failed non of them match with the condition gave: " + b3);

        //findFirst, findAny
        //these all findFirst, allMatch, anyMatch are shortciruit operations as soon as they found matching result they stop processing further
        System.out.println(list1.stream().findFirst().get());


        //Q1 Square and Sort
        List<Integer> l1 = List.of(4,2,4,21,24,9);
        System.out.println("List of squared and sorted stream: " + l1.stream().map(x -> x*x).sorted().toList());

        //Q2 Summing all values
        List<Integer> l2 = List.of(1,2,3,4,5,6,7,8,9,10);
        System.out.println("Total sum is : " + l2.stream().reduce((a, b) -> (a+b)).get()); //Used get as reduces gives Optional<T>


        //Counting Occurence of a particular character in string using stream
        String input = "Hi there! how's it all going there  ?"; //input.chars() gives IntStream
        long count = input.chars().filter(c -> c == 't').count();
        System.out.println("Count of char 't' is : " +count);

        //Count occurences of all the chars
        Map<Character, Long> freqMap = input.chars()
                .mapToObj(a -> (char)a) //can't simply use .map(a->.    as we need to convert it to wrapper class
                .collect(
                        Collectors.groupingBy(Function.identity(), Collectors.counting())
                );

        System.out.println("Final freq map is : " + freqMap);

        //forEach  -- Terminal Operation
        //peek ---Intermediate Operation


        //min/max
        Stream.of(3,2,4,5,2,1,3,66,3,32,345).max(Comparator.naturalOrder());
        System.out.println(Stream.of(3,2,4,5,2,1,3,66,3,32,345).max((a, b) -> b - a)); //doing b-a reversed what max was suposed to do
        //so max min are just like comparotor passing only


        //flatMap
        //Handles a stream where each element of stream itself is a collection
        //Flatten the nested strucuture like list of list and all so that can deal with single elements
        List<List<String>> listOfLists = Arrays.asList(
                Arrays.asList("apple", "banana"),
                Arrays.asList("orange", "kiwi"),
                Arrays.asList("pear", "grape")
        );
        System.out.println(listOfLists.get(1).get(1));
        //Print all elements in my nested all at once
        System.out.println("Full flatten list is : " + listOfLists.stream().flatMap(x -> x.stream()).toList());


        //List of sentences break all words into single list and do uppercase on them
        List<String> sentences = Arrays.asList(
                "The quick brown fox jumps over the lazy dog.",
                "Java is a powerful, object-oriented programming language.",
                "I love solving coding problems on weekends.",
                "Artificial intelligence is transforming the world.",
                "She sells seashells by the seashore.",
                "This sentence has exactly seven words.",
                "Let's build something amazing together!",
                "Unit tests help ensure code quality.",
                "Always comment your code where necessary.",
                "Practice makes a developer perfect."
        );
        //After doing flatMap we need to passfuther a stream that's why after split we got a array and we converted that back to a strem
        List<String> finalList = sentences.stream()
                .flatMap(sentence -> Arrays.stream(sentence.split(" "))
                        .map(String::toUpperCase))
                .collect(Collectors.toList());

        System.out.println("Final flatten list is : " + finalList);

        //Stream can not be resued once the terminal operation is used



    }
}
