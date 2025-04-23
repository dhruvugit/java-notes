package org.dhruvnotes.interfaceAbstractClass;

interface MyInterface {
    // Abstract method (must be overridden)
    void abstractMethod();

    // Default method (optional to override)
    default void defaultMethod() {
        System.out.println("Default Implementation");
    }

    // Static method (cannot be overridden)
    static void staticMethod() {
        System.out.println("Static method in interface");
    }

    // Private method (only usable within interface)
    private void helper() {
        System.out.println("Private helper");
    }

    default void useHelper() {
        helper();
    }
}
