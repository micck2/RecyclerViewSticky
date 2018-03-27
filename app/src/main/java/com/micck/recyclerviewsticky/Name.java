package com.micck.recyclerviewsticky;

/**
 * @author lilin on 2018/3/27 10:27
 */

public class Name {
    public String name;
    public int itemType;

    public Name(String name, int itemType) {
        this.name = name;
        this.itemType = itemType;
    }

    public Name(String name) {
        this(name,0);
    }
}
