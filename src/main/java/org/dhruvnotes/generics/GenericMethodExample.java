package org.dhruvnotes.generics;



public class GenericMethodExample {
    public <K,V> void printValue(Pair<K, V> pair1, Pair<K, V> pair2){
        //method body
    }

    public <T> void setValue(T busObject){
        //methodBody
    }

    public <K, V, X, Y, T, A, B> void printThreeValues(Pair<K, V> pair1, Pair<X, Y> pair2, T value, Pair<B, A> pair3){

    }

}
