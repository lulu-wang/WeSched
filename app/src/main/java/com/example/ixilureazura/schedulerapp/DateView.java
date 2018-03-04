package com.example.ixilureazura.schedulerapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DateFormatSymbols;
import java.util.ArrayList;

/**
 * Created by ixilureazura on 3/3/18.
 */

public class DateView extends Activity {

    private Button addEventButton;
    private TextView monthText;
    private TextView dateText;
    private Button backBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.date_layout);

        Intent intent = getIntent();
        int monthValue = intent.getIntExtra("monthInt", 0);
        int dateValue = intent.getIntExtra("dateInt", 0);

        monthText = (TextView) findViewById(R.id.month_text_view);
        dateText = (TextView) findViewById(R.id.date_text_view);
        backBtn = findViewById(R.id.back_button);

        String month = new DateFormatSymbols().getMonths()[monthValue];

        monthText.setText(month);
        dateText.setText(Integer.toString(dateValue));

        addEventButton = findViewById(R.id.addButton);
        addEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DateView.this, SecondScreen.class));
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DateView.this, MainActivity.class));
            }
        });

    }
}
