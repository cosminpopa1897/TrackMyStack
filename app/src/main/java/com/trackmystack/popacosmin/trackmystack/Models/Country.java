package com.trackmystack.popacosmin.trackmystack.Models;

/**
 * Created by Cosmin on 05-May-18.
 */

public class Country implements DropdownElement{
    public int Id;
    public String Name;

    @Override
    public String getName() {
        return Name;
    }
}
