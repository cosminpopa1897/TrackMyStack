package com.trackmystack.popacosmin.trackmystack;

import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.trackmystack.popacosmin.trackmystack.Helpers.SqLiteHelper;
import com.trackmystack.popacosmin.trackmystack.Navigation.Navigator;

/**
 * Created by Cosmin on 10-Dec-17.
 */

public abstract class BaseActivity extends AppCompatActivity {
    public Navigator Navigator;
    protected int currentMenuButtonId;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        this.Navigator = new Navigator();
        this.setCurrentMenuButtonId();
    }

    public abstract  void setCurrentMenuButtonId();

    public int getActivity(){
        return this.currentMenuButtonId;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        MenuItem item = menu.findItem(this.currentMenuButtonId);
        item.setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Navigator.MapActivities(this, item);
        return true;
    }

}
