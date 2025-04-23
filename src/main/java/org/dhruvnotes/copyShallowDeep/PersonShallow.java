package org.dhruvnotes.copyShallowDeep;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/*
* If you want to clone the objects using clone method, you must implement the Cloneabble marker interface
* Marker interface is interface is empty interface Just to let the JVM the task allocated to class and allow clone object on it
* Just like we use @Override on methods and it does notthing just checks if method is overriden correctly
* */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class PersonShallow implements Cloneable{
    String name;
    Address address;

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
