package org.dhruvnotes.StreamAPI;

import java.util.ArrayList;
import java.util.List;
import java.util.function.*;

public class LambdaExpression {
    public static void main(String[] args) {
        /*
        * lambda expression
         Lambda expression is an anonymous function ( no name, no return type, no access modifier )
        * */
        //Reference of function interface can be used to hold the lambda expression
        //Lambda expression are strictly use to implement functional interfaces, because of this only we are able to use lambda expression with
        //Comparator, Callable, Runnable

        Thread th1 = new Thread(()-> System.out.println("Hi"));
        //For above we don't need to create a class make it implement Runnable class then write return method, no not requried

        MathOperation addition = (a, b) -> (a+b);  //OR --  Integer::sum
        MathOperation subtraction = (a, b) -> (a-b);
        System.out.println(addition.operate(5,4));


        //Predicate is also FunctionInterface which returns boolean value , takes anything return boolean
        //Predicate pr = (int a) -> (a % 2 == 0);  You have to mention predicate type
        Predicate<Integer> pr = (Integer a) -> (a % 2 == 0);
        System.out.println(pr.test(5));
        //So we are holding application logic into variable that's what functional programming is

        Predicate<String> isWordStartingWithA = x -> x. toLowerCase().startsWith("a");
        Predicate<String> isWordEndingWithT = x -> x.toLowerCase().endsWith("t");
        Predicate<String> and = isWordStartingWithA.and(isWordEndingWithT);
        System.out.println(and.test("SA"));

        //Function Functional Interface --takes something return something
        Function<Integer, Integer> f1 = x -> x*2;
        Function<Integer, Integer> f2 = x -> x+2;
        Function<Integer, Integer> f3 = f1.andThen(f2);
        System.out.println(f3.apply(1));


        //Consumer ---Takes some return nothing
        Consumer<Integer> cs = a -> System.out.println(a);
        //System.out.println(cs.accept(5));   can not print void there is not return
        cs.accept(5);

        //Suppier  --Takes nothing , gives something
        Supplier<Integer> sp = () -> 5; //single line no need to use return keyword
        System.out.println(sp.get());

        //BiPredicate , BiConsumer, BiFunction we also have these
        //they take two inputs
        BiFunction<Integer, Integer, String> biFunction = (a, b) ->{
          if(a > b) return "First is greater than later";
          else return "First is smaller than latter";
        };
        System.out.println(biFunction.apply(5,4));


        //Method Reference --Use method without invoking
        List<String > students = new ArrayList<>(List.of("Dhruv", "dhruv", "dhruV"));
        //students.forEach(x -> System.out.println(x));
        students.forEach(System.out::println);
        // We gave the method as reference, we are saying go to System.out and use method println on it
        //It's shortcut for lambda expression
        //We can also use constructor reference here, it looks like
        //. x -> new Student(x)
        //.  Student::new




    }
}

interface MathOperation{
    int operate(int a, int b);
}
