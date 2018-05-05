package com.trackmystack.popacosmin.trackmystack.Models;

/**
 * Created by Cosmin on 05-May-18.
 */

public class City implements DropdownElement{
    public int Id;
    public String Name;
    public int CountryId;

    @Override
    public String getName() {
        return Name;
    }
}
