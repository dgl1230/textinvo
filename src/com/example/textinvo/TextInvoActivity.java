package com.example.textinvo;

import java.text.DateFormatSymbols;
import java.util.Calendar;

import android.app.DatePickerDialog.OnDateSetListener;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class TextInvoActivity extends FragmentActivity implements OnDateSetListener{
	
	
	Button send;
	EditText phonenumber;
	Button dateButton;
	Button rsvpButton;
	EditText message;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_text_invo);
		
		send = (Button)findViewById(R.id.send_button);
		phonenumber = (EditText)findViewById(R.id.contact_edit_text);
		dateButton = (Button)findViewById(R.id.date_edit_text);
		rsvpButton = (Button)findViewById(R.id.rsvp_edit_text);
		message = (EditText)findViewById(R.id.message_edit_text);
		
		setCurrentDate(dateButton);
		dateButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				setUpDatePicker();
			}
		});
		
		setCurrentDate(rsvpButton);
		rsvpButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				setUpDatePicker();
				
			}
		});
		
		send.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String string_phone_number = phonenumber.getText().toString();
				String date = dateButton.getText().toString();
				String string_message = message.getText().toString();
				
				if (string_phone_number != null  && string_message != null){
					try{
						SmsManager smsManager = SmsManager.getDefault();
						String total_message = "Hi," + '\n' + "You've been invited to an event!" + '\n' + 
								"It takes place on " + date + '\n' + "Here's some info about the event: " +
								string_message;
						smsManager.sendTextMessage(string_phone_number, null, total_message, null, null);
						Toast.makeText(getApplicationContext(), "Your message has been sent!", Toast.LENGTH_SHORT).show();
								
					} catch (Exception error){
						Toast.makeText(getApplicationContext(), "You message could not be sent. Please try again later!",
								Toast.LENGTH_SHORT).show();
						error.printStackTrace();
					}
				}
				else{
					Toast.makeText(getApplicationContext(), "Please enter a valid phone number, date, and message.",
							Toast.LENGTH_SHORT).show();
					
					
				}
				
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.text_invo, menu);
		return true;
	}
	
	public void onDateSet(DatePicker view, int year, int month, int day) {
		dateButton.setText(getMonth(month + 1) + " " + day + "," + year);
    }
	
	public void setCurrentDate(Button d){
		final Calendar c = Calendar.getInstance();
	    int year = c.get(Calendar.YEAR);
	    int month = c.get(Calendar.MONTH);
	    int day = c.get(Calendar.DAY_OF_MONTH);
	    d.setText(getMonth(month + 1) + " " + day + "," + year);
	}
	
	public String getMonth(int month) {
	    return new DateFormatSymbols().getMonths()[month-1];
	} 
	
	public void setUpDatePicker(){
		FragmentManager fm = getSupportFragmentManager();
		DateSelection dialog = new DateSelection();
		dialog.show(fm, "date");
	}

}
