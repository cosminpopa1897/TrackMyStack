package com.trackmystack.popacosmin.trackmystack.Fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;

import java.util.Calendar;

/**
 * Created by Cosmin on 05-May-18.
 */

public class DatePickerFragment extends DialogFragment
                implements DatePickerDialog.OnDateSetListener{

    DatePickerDialog.OnDateSetListener onDateSetListener;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void SetOnDateSetListener(DatePickerDialog.OnDateSetListener onDateSetListener){
        this.onDateSetListener = onDateSetListener;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        if(this.onDateSetListener != null){
            this.onDateSetListener.onDateSet(view,year,month,dayOfMonth);
        }
    }
}
