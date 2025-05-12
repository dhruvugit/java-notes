package org.dhruvnotes.copyShallowDeep;

public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {
        //********************Shallow copy using constructor********************
        PersonShallow2 ps1 = new PersonShallow2("Dhruv", new Address("TestLine", "TestLine2"));
        PersonShallow2 ps2 = new PersonShallow2(ps1);
        System.out.println(ps2);
        ps1.address.line1 = "New line changed";
        System.out.println("PS2 After line change in ps1: " + ps2);

        //*******************Deep Copy using constructor***********************
        //Just look at the class PersonDeep2 you'll see why it's deeep copy (it's easy)

        //********************Shallow Copy Using cloneable*******************
        PersonShallow personShallow = new PersonShallow("Ram", new Address("line11", "line22"));
        PersonShallow personShallow2 = (PersonShallow)personShallow.clone();
        System.out.println("Person Shallow2 before change :  " + personShallow2);
        personShallow.address.line1 = "Changed line";
        System.out.println("Person shallow 2 after change: " + personShallow2);
    }
}
