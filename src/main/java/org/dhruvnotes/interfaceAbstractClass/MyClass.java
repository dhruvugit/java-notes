package org.dhruvnotes.interfaceAbstractClass;


class MyClass implements MyInterface {
    @Override
    public void abstractMethod() {
        System.out.println("Implemented abstract method");
    }

    @Override
    public void defaultMethod() {
        System.out.println("Overridden default method");
    }

    // Cannot override static or private methods
    //Overriding default method is optional and overriding abstract ones is must.
}
