package com.example.notemanagement.ui.note;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import com.example.notemanagement.R;

import java.util.Date;

public class DatePickerFragment extends DialogFragment{


    DatePickerDialog.OnDateSetListener onDateSet;
    private boolean isModal = false;

    public static DatePickerFragment newInstance()
    {
        DatePickerFragment frag = new DatePickerFragment();
        frag.isModal = true; // WHEN FRAGMENT IS CALLED AS A DIALOG SET FLAG
        return frag;
    }
    public DatePickerFragment(){}
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(),R.style.FullScreenDialog, onDateSet, year, month, day);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(isModal) // AVOID REQUEST FEATURE CRASH
        {
            return super.onCreateView(inflater, container, savedInstanceState);
        }
        else {
            View rootView = inflater.inflate(R.layout.add_note_dialog, container, false);
            return rootView;
        }
    }

    public void setCallBack(DatePickerDialog.OnDateSetListener onDate) {
        onDateSet = onDate;
    }
    /*@Override
    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
        Context context = null;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.add_note_dialog, null, false);
        txtPlanDate = view.findViewById(R.id.txtSelectPlanDate);
        Date curentTime = java.util.Calendar.getInstance().getTime();
        if(year <= curentTime.getYear() || monthOfYear+1 <= curentTime.getMonth() || dayOfMonth <= curentTime.getDay())
        {
            Toast.makeText(getContext(),"The plan date must be after the current date!",Toast.LENGTH_SHORT).show();
        }
        else{
            monthOfYear++;
            txtPlanDate.setText(dayOfMonth + "/"+ monthOfYear  + "/" + year);
        }

    }*/
}
