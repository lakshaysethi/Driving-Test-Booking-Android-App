package com.lakshaysethi.driverlicencetestbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button continueButton;
    private Button showHotspotMatrixButton;
    private Button adminViewButton;
    private EditText licenceInputText;

    Controller controllerObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        controllerObj = new Controller();
        //populate Slots
        controllerObj.populateSlotsArrayList();//IMP CHECK TODO LEARN REMEMBER CAUSION DO NOT

        //get reference to the widgets on the screen
        licenceInputText = findViewById(R.id.licenceInput);
        continueButton =(Button) findViewById(R.id.continueButton);
        showHotspotMatrixButton = (Button) findViewById(R.id.showAllBookingsMatrixActivityBtn);
        adminViewButton = (Button) findViewById(R.id.adminViewButton);//disabled



        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { if(userHasEnteredLicenceNumber(licenceInputText.getText().toString())){ openBookSlotMatrixActivity(); } }});
        showHotspotMatrixButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {     openHotspotMatrixActivity();  }
        });
        adminViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { { if(userHasEnteredLicenceNumber(licenceInputText.getText().toString())){   openAdminActivity();  }} }
        });//depreciated



    }

    private void openHotspotMatrixActivity() {
        Intent intent = new Intent(this,HotspotMatrixActivity.class);
        //intent.putExtra("licenceNumber",licenceString);
        startActivity(intent);

    }

    private boolean userHasEnteredLicenceNumber(String licenceNumber) {
        if(!licenceNumber.equals("")){
            Controller.licenceNumber = licenceNumber;
            return true;
        }
        controllerObj.showToast(MainActivity.this,"Please Enter a Licence Number");
        return false;
    }

    private void openBookSlotMatrixActivity() {
        Intent intent = new Intent(this, BookSlotMatrixActivity.class);
        startActivity(intent);
    }

    public void openBookSlotActivity(){
        Intent intent = new Intent(this, BookSlotActivity.class);
        startActivity(intent);

    }//depriciated

    private void openAdminActivity() {
        //TODO
        Intent intent = new Intent(this,AdminViewActivity.class);
        //intent.putExtra("licenceNumber",licenceString);
        startActivity(intent);
    }//depriciated







}


