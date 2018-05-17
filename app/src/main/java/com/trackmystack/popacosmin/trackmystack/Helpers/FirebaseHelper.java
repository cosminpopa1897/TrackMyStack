package com.trackmystack.popacosmin.trackmystack.Helpers;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.trackmystack.popacosmin.trackmystack.Helpers.FirebaseRepositories.CityRepository;
import com.trackmystack.popacosmin.trackmystack.Helpers.FirebaseRepositories.CountyRepository;

/**
 * Created by Cosmin on 12-May-18.
 */

public class FirebaseHelper {
    private DatabaseReference mDatabase;
    public CountyRepository countyRepository;
    public CityRepository cityRepository;
    private static FirebaseHelper firebaseHelper = null;

    private FirebaseHelper(){
        mDatabase = FirebaseDatabase.getInstance().getReference();
        this.countyRepository = new CountyRepository(mDatabase);
        this.cityRepository = new CityRepository(mDatabase);
    }

    public static synchronized FirebaseHelper getInstance(){
        if (firebaseHelper == null)
            firebaseHelper = new FirebaseHelper();
        return firebaseHelper;
    }
}
