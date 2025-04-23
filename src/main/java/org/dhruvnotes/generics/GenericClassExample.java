package org.dhruvnotes.generics;

public class GenericClassExample {
    public static void main(String[] args) {
        Print<Integer> integerPrint = new Print<>();
        integerPrint.setPrintValue(5);
        System.out.println(integerPrint.getPrintValue());
        //integerPrint.setPrintValue("Hii");  will give compile error

        //***********Parent Generic, Subclass Non Generic
        PrintChild printChild = new PrintChild();
        //printChild.setPrintValue(5);     this will give compile error as ParentChild is of type String
        printChild.setPrintValue("Hi");
        System.out.println(printChild.getPrintValue());

        //***********Parent generic, Sub class Generic
        ParentChild2<String> parentChild2 = new ParentChild2<>();
        parentChild2.setPrintValue("HIII");
        //parentChild2.setPrintValue(5); //will give compile error
    }
}

