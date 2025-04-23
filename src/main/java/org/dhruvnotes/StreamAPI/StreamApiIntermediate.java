package org.dhruvnotes.StreamAPI;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class StreamApiIntermediate {
    public static void main(String[] args) {
        //Process collections of data in functional and declarative manner
        //Enable easy parallelism
        /*
        * Ways to create stream
        * Arrays.stream(arr)
        * Stream.of(any iteratble)
        * list.stream()
        * */
        //Generate infinite number as given starting point and period
        Stream.iterate(5,  x -> x + 3).limit(5).forEach(System.out::println);

        /* list1.stream() to process each item inside the list.
        Stream.of(list1) if you want to create a stream of a single object which is the list itself.*/
        List<String> list1 = new ArrayList<>(List.of("Aditi", "Dhruv", "Ram"));
        list1.stream().filter(a -> a.startsWith("A")).forEach(System.out::println);

        //Map --If you want to change something in the elements
        list1.stream().map(String::toUpperCase).forEach(System.out::println);

        //Sorted
        list1.stream().sorted((a, b) -> (b.length() - a.length())).forEach(System.out::println); //sort in decreasing length

        //Limit
        System.out.println(Stream.iterate(5, x -> x+2).limit(1000).count());;

        //skip
        System.out.println(Stream.iterate(5, x -> x+2).skip(5).limit(1000).count());
        System.out.println(Stream.iterate(5, x -> x+2).limit(1000).skip(5).count());
    }
}
