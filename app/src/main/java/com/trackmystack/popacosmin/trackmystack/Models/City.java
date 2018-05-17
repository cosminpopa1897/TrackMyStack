package com.trackmystack.popacosmin.trackmystack.Models;

/**
 * Created by Cosmin on 05-May-18.
 */

public class City implements DropdownElement{


    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getCountyId() {
        return CountyId;
    }

    public void setCountyId(int countyId) {
        CountyId = countyId;
    }

    private int Id;
    private String Name;
    private int CountyId;

    @Override
    public String getName() {
        return Name;
    }
}
