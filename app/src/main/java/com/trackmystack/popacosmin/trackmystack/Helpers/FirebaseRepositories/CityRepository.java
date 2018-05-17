package com.trackmystack.popacosmin.trackmystack.Helpers.FirebaseRepositories;

import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.trackmystack.popacosmin.trackmystack.Models.City;

import java.util.ArrayList;

/**
 * Created by Cosmin on 12-May-18.
 */

public class CityRepository {
    private DatabaseReference mDatabase;

    public CityRepository(DatabaseReference mDatabase){
        this.mDatabase = mDatabase;
    }

    public ArrayList<City> GetAllCities(){
        final ArrayList<City> cities = new ArrayList<>();
        this.mDatabase.child("City").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                City city = (City) dataSnapshot.getValue(City.class);
                cities.add(city);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
                return cities;
    }

    public ArrayList<City> getCitiesByCountyId(final int countyId){
        final ArrayList<City> cities = new ArrayList<>();
        this.mDatabase.child("City").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                City city = (City) dataSnapshot.getValue(City.class);
                if (city.getCountyId() == countyId)
                    cities.add(city);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return cities;

    }
}
