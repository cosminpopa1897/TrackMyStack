package com.trackmystack.popacosmin.trackmystack.Models;

/**
 * Created by Cosmin on 05-May-18.
 */

public class Country implements DropdownElement{
    private int id;
    private String name;

    @Override
    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
