package com.lakshaysethi.driverlicencetestbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button continueButton;
    private Button showMatrixButton;
    private Button adminViewButton;
    private EditText licenceInputText;

    Controller controllerObj = new Controller();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //populate Slots
        controllerObj.populateSlotsArrayList();//IMP CHECK TODO LEARN REMEMBER CAUSION DO NOT

        //get reference to the widgets on the screen
        licenceInputText = findViewById(R.id.licenceInput);
        continueButton =(Button) findViewById(R.id.continueButton);
        showMatrixButton = (Button) findViewById(R.id.showMatrixButton);

        adminViewButton = (Button) findViewById(R.id.adminViewButton);



        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBookSlotActivity();
            }
        });


        showMatrixButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { {openMatrixActivity();} }
        });

        adminViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { { openAdminActivity();} }
        });

    }


    private void openAdminActivity() {
        //TODO
        Intent intent = new Intent(this,AdminViewActivity.class);
        //intent.putExtra("licenceNumber",licenceString);
        startActivity(intent);
    }

    private void openMatrixActivity() {
        Intent intent = new Intent(this,MatrixActivity.class);

        startActivity(intent);
    }

    public void openBookSlotActivity(){

        if(!licenceInputText.getText().toString().equals("")){
            Intent intent = new Intent(this, BookSlotActivity.class);
            startActivity(intent);
        }else{
            controllerObj.showToast(MainActivity.this,"Please Enter a Licence Number");
        }

    }





}


