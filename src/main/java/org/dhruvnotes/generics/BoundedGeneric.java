package org.dhruvnotes.generics;

/*
This is example of Upper Bound where T can be anything as Number or subclasses of Number
* */
public class BoundedGeneric<T extends Number> {
    T val;

    public T getValue(){
        return val;
    }
    public void setValue(T val){
        this.val = val;
    }

}
