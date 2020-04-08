package com.lakshaysethi.driverlicencetestbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button continueButton;
    private EditText licenceInputText;
    private String licenceString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        licenceInputText = findViewById(R.id.licenceInput);
        licenceString =  licenceInputText.toString();

        continueButton =(Button) findViewById(R.id.button);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBookingActivity(licenceString);

            }
        });

    }


    public void openBookingActivity(String licenceString){
        Intent intent = new Intent(this,bookSlot.class);
        intent.putExtra("licenceNumber",licenceString);
        startActivity(intent);
    }
}


