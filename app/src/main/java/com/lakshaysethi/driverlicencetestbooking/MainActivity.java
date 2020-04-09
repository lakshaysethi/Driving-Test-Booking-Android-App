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
        controllerObj.populateSlotsArrayList();

        //get reference to the widgets on the screen
        licenceInputText = findViewById(R.id.licenceInput);
        continueButton =(Button) findViewById(R.id.continueButton);
        showMatrixButton = (Button) findViewById(R.id.showMatrixButton);

        adminViewButton = (Button) findViewById(R.id.adminViewButton);



        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkLicenceAndWelcomeUser(licenceInputText.getText().toString())){
                    System.out.println("opening booking activity");
                    openBookingActivity();
                }else{
                    System.out.println("Please Enter a Licence Number");
                    controllerObj.showToast(MainActivity.this,"Please Enter a Licence Number");
                }
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

    public boolean checkLicenceAndWelcomeUser(String licenceNumber) {

        /*
        * what does this function do?
        * it checks if the user is old user or not and shows a toast message according to that
        *
        * it also returns true or false if user was able to sign in or not so
        *
        * it sets the variable currentUser in the Controller class
        *
        * */


    if(licenceNumber.equals("")){

        //licence is not vlaid:
        return false;
    }else{
        if(controllerObj.isOldUser(licenceNumber)) {
            controllerObj.showToast(this, "Welcome Back! :)");
        }else{
            controllerObj.currentUser = controllerObj.addNewUser(licenceNumber);
            controllerObj.showToast(this, "Welcome New User! ");

        }
        controllerObj.setCurrentUser(licenceNumber);
        return true;
    }


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

    public void openBookingActivity(){
        Intent intent = new Intent(this, BookSlotActivity.class);
        //intent.putExtra("licenceNumber",licenceString);
        startActivity(intent);
    }





}


