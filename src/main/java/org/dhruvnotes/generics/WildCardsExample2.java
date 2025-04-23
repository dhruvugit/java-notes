package org.dhruvnotes.generics;

import java.util.ArrayList;
import java.util.List;

public class WildCardsExample2 {
    public static void main(String[] args) {
        List<? super Object> list = new ArrayList<>();
        list.add(4);
        list.add("Hi");
        System.out.println(list);
    }
}
