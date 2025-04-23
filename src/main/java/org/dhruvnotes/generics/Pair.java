package org.dhruvnotes.generics;

//We can pass multiple genetic types to a class
public class Pair<K, V> {
    K key;
    V value;
    public void put(K key, V value){
        this.key = key;
        this.value = value;
    }
}
