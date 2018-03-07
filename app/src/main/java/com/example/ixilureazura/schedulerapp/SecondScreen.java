package com.example.ixilureazura.schedulerapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by ixilureazura on 3/3/18.
 */

public class SecondScreen extends Activity {
    private Spinner hourDrop;
    private Spinner minuteDrop;
    private Spinner apmDrop;
    private Spinner monthDrop;
    private Spinner dayDrop;
    private Spinner yearDrop;
    private Spinner remindDrop;

    private String eventName;
    private String eventDesc;
    private String phoneNumber;

    private Button createEventBtn;
    private Button addPhoneNumberBtn;
    private Button backBtn;
    private int[] hoursArray = {01, 02, 03, 04, 05, 06, 07, 8, 9, 10, 11, 12};
    private int[] minutesArray = {00, 05, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55};
    private String[] apmArray = {"AM", "PM"};

    private int[] monthsArray = {01, 02, 03, 04, 05, 06, 07, 8, 9, 10, 11, 12};
    private int[] daysArray = {01, 02, 03, 04, 05, 06, 07, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21,
                                22, 23, 24, 25, 26, 27, 28, 29, 30};
    private int[] yearArray = {2017, 2018, 2019, 2020};

    private String[] remindTimes = {"0 minutes", "5 minutes", "10 minutes", "15 minutes", "20 minutes",
            "25 minutes", "30 minutes", "35 minutes", "40 minutes", "45 minutes", "50 minutes", "55 minutes", "1 hour"};

    private String hourDropSel;
    private String minuteDropSel;
    private String apmDropSel;
    private String monthsDropSel;
    private String daysDropSel;
    private String yearDropSel;
    private String remindDropSel;
    private ArrayList<String> phoneNumberArray;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);

        setupUI(findViewById(R.id.parent));

        hourDrop = findViewById(R.id.event_time_edit_hour);
        minuteDrop = findViewById(R.id.event_time_edit_minute);
        apmDrop = findViewById(R.id.event_time_edit_apm);
        monthDrop = findViewById(R.id.event_date_edit_month);
        dayDrop = findViewById(R.id.event_date_edit_day);
        yearDrop = findViewById(R.id.event_date_edit_year);
        remindDrop = findViewById(R.id.remind_edit_spinner);

        final EditText evName = (EditText) findViewById(R.id.event_name_edit_text);

        final EditText evDesc = (EditText) findViewById(R.id.event_details_edit_text);
        final EditText pN = (EditText) findViewById(R.id.phone_number_edit_text);

        createEventBtn = findViewById(R.id.create_event_button_view);
        addPhoneNumberBtn = findViewById(R.id.add_number_button);
        backBtn = findViewById(R.id.back_button);


        final ArrayList<String> hours = new ArrayList<>();
        final ArrayList<String> minutes = new ArrayList<>();
        final ArrayList<String> apm = new ArrayList<>();

        final ArrayList<String> months = new ArrayList<>();
        final ArrayList<String> days = new ArrayList<>();
        final ArrayList<String> years = new ArrayList<>();

        final ArrayList<String> remindTimeAL = new ArrayList<>();

        phoneNumberArray = new ArrayList<>();


        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SecondScreen.this, Screen1.class));
            }
        });

        createEventBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventDesc = evDesc.getText().toString();
                eventName = evName.getText().toString();
                //handles getting selected values

                //hour
                if (hourDrop.getSelectedItemPosition() + 1 < 10) {
                    hourDropSel = "0" + hourDrop.getSelectedItem().toString();
                } else {
                    hourDropSel = hourDrop.getSelectedItem().toString();
                }

                //minute
                if (minuteDrop.getSelectedItemPosition() < 1) {
                    minuteDropSel = "0" + minuteDrop.getSelectedItem().toString();
                } else {
                    minuteDropSel = minuteDrop.getSelectedItem().toString();
                }

                //apm
                apmDropSel = apmDrop.getSelectedItem().toString();

                //month
                if (monthDrop.getSelectedItemPosition() + 1 < 10) {
                    monthsDropSel = "0" + monthDrop.getSelectedItem().toString();
                } else {
                    monthsDropSel = monthDrop.getSelectedItem().toString();
                }

                //day
                if (dayDrop.getSelectedItemPosition() + 1 < 10) {
                    daysDropSel = "0" + dayDrop.getSelectedItem().toString();
                } else {
                    daysDropSel = dayDrop.getSelectedItem().toString();
                }

                //year
                yearDropSel = yearDrop.getSelectedItem().toString();

                //remind time
                remindDropSel = remindDrop.getSelectedItem().toString();
                int reminderInt = (remindTimeAL.indexOf(remindDropSel)) * 5;

                Event e = new Event (yearDropSel, monthsDropSel, daysDropSel, hourDropSel, minuteDropSel,
                        apmDropSel, eventName, eventDesc, reminderInt);
                phoneNumber = pN.getText().toString();
                if (!phoneNumberArray.contains(phoneNumber)) {
                    phoneNumberArray.add(phoneNumber);
                }
                e.setPhoneArray(phoneNumberArray);
                storeDataFB(e);
                phoneNumberArray.clear();
            }
        });

        addPhoneNumberBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneNumber = pN.getText().toString();
                if (!phoneNumberArray.contains(phoneNumber)) {
                    phoneNumberArray.add(phoneNumber);
                }
            }
        });

        //fill time section
        for (int h: hoursArray) {
            hours.add(Integer.toString(h));
        }
        for (int m: minutesArray) {
            minutes.add(Integer.toString(m));
        }
        for (String s: apmArray) {
            apm.add(s);
        }

        ArrayAdapter<String> adapter_hours = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, hours);
        adapter_hours.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hourDrop.setAdapter(adapter_hours);

        ArrayAdapter<String> adapter_minutes = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, minutes);
        adapter_minutes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        minuteDrop.setAdapter(adapter_minutes);


        ArrayAdapter<String> adapter_apm = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, apm);
        adapter_apm.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        apmDrop.setAdapter(adapter_apm);

        //end fill time section

        //fill date section
        for (int m: monthsArray) {
            months.add(Integer.toString(m));
        }
        for (int d: daysArray) {
            days.add(Integer.toString(d));
        }
        for (int y: yearArray) {
            years.add(Integer.toString(y));
        }

        ArrayAdapter<String> adapter_months = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, months);
        adapter_months.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monthDrop.setAdapter(adapter_months);


        ArrayAdapter<String> adapter_days = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, days);
        adapter_days.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dayDrop.setAdapter(adapter_days);

        ArrayAdapter<String> adapter_year = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, years);
        adapter_year.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearDrop.setAdapter(adapter_year);
        //end fill date section

        //fill reminder times section
        for (String s: remindTimes) {
            remindTimeAL.add(s);
        }

        ArrayAdapter<String> adapter_reminders = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, remindTimeAL);
        adapter_reminders.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        remindDrop.setAdapter(adapter_reminders);

    }

    public void storeDataFB (Event event) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("/events");
        DatabaseReference pushedPostRef = ref.push();
        String postId = pushedPostRef.getKey();
        ref.child(postId).setValue(event);
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }

    public void setupUI(View view) {

        // Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(SecondScreen.this);
                    return false;
                }
            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUI(innerView);
            }
        }
    }
}
