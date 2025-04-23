package org.dhruvnotes.copyShallowDeep;


class PersonDeep implements Cloneable {
    String name;
    Address address;

    public Object clone() throws CloneNotSupportedException {
        PersonDeep cloned = (PersonDeep) super.clone();
        Address clonedAddress = new Address();
        clonedAddress.setLine1(this.address.getLine1());
        clonedAddress.setLine2(this.address.getLine2());
        cloned.address = clonedAddress;
        return cloned;
    }
}