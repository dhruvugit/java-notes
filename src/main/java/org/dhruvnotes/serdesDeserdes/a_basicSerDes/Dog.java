package org.dhruvnotes.serdesDeserdes.a_basicSerDes;

import lombok.ToString;

import java.io.Serializable;

@ToString
public class Dog implements Serializable { //If you won't implement serializable you will get NotSerialzableException at runtime
    int i = 10;
    transient int j = 20;  //keep primitive datatype as transient will result in default primitive value after deser
    Integer I = 30;
    transient Integer J = 40; //keeping Wrapper as transient will result null in after deser
    transient String str = "testStr";
    final String str2 = "test2";
    transient final String str3 = "test3"; //no impact after deser same "test3" will be there even after transient
    transient static String str4 = "test4"; //no impact same value will be their even with transient
}
