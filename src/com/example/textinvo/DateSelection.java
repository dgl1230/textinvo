package com.example.textinvo;

import java.util.Calendar;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class DateSelection extends DialogFragment  {
	

	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState){
		 final Calendar c = Calendar.getInstance();
	     int year = c.get(Calendar.YEAR);
	     int month = c.get(Calendar.MONTH);
	     int day = c.get(Calendar.DAY_OF_MONTH);
	     return new DatePickerDialog(getActivity(), (TextInvoActivity)getActivity(), year, month, day);
	}
	
	
	

}
