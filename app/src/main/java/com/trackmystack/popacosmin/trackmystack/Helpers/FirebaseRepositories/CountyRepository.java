package com.trackmystack.popacosmin.trackmystack.Helpers.FirebaseRepositories;

import android.widget.ArrayAdapter;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.trackmystack.popacosmin.trackmystack.Models.Country;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cosmin on 12-May-18.
 */

public class CountyRepository {
    private DatabaseReference mDatabaseReference;

    public CountyRepository(DatabaseReference mDatabaseReference){
        this.mDatabaseReference = mDatabaseReference;
    }

    public ArrayList<Country> getCounties(){
        final ArrayList<Country> countries = new ArrayList<Country>();
         this.mDatabaseReference.child("County").addChildEventListener(new ChildEventListener() {
             @Override
             public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Country country =  dataSnapshot.getValue(Country.class);
                 countries.add(country);
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
        return countries;
    }
}
