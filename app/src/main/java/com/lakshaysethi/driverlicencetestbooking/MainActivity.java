package com.lakshaysethi.driverlicencetestbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button continueButton;
    private Button showMatrixButton;
    private Button viewMyBookingsButton;
    private Button adminViewButton;
    private EditText licenceInputText;
    Controller controllerObj = new Controller();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //read from data base and make static

        //get reference to the widgets on the screen
        licenceInputText = findViewById(R.id.licenceInput);
        continueButton =(Button) findViewById(R.id.continueButton);
        showMatrixButton = (Button) findViewById(R.id.showMatrixButton);
        viewMyBookingsButton =(Button) findViewById(R.id.viewMyBookingsButton);
        adminViewButton = (Button) findViewById(R.id.adminViewButton);



            continueButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(welcomeUser()){
                        openBookingActivity();
                    }else{
                        showToast(MainActivity.this,"Please Enter a Licence Number");
                    }
                }
            });


            showMatrixButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) { {openMatrixActivity();} }
            });
            viewMyBookingsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) { {openViewMyBookingsActivity();} }
            });
            adminViewButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) { { openAdminActivity();} }
            });

        }


public boolean welcomeUser(){
    if(licenceInputText.getText().toString().equals("")){
        return false;
    }
        Pojoclasses.User u1 = controllerObj.getCurrentUser(licenceInputText.getText().toString());

    if(u1!=null){

        if (Controller.usersStaticList.contains(u1)) {
            //ofcourse it is inside the static list  you just put it there 
            showToast(this,"Welcome Back! :)");
        }else{
            showToast(this,"Welcome New User! you have been added to the Database :)");
        }
        return true;
    }
    return false;
}

    private void showToast(Context c, String s) {
        Toast t1 = Toast.makeText(c,s,Toast.LENGTH_SHORT);
        t1.show();
    }


    private void openAdminActivity() {
        //TODO
        Intent intent = new Intent(this,ViewMyBookingsActivity.class);
        //intent.putExtra("licenceNumber",licenceString);
        startActivity(intent);
    }

    private void openViewMyBookingsActivity() {
        Intent intent = new Intent(this,ViewMyBookingsActivity.class);
        //intent.putExtra("licenceNumber",licenceString);
        startActivity(intent);
    }

    private void openMatrixActivity() {
        Intent intent = new Intent(this,MatrixActivity.class);
        //intent.putExtra("licenceNumber",licenceString);
        startActivity(intent);
    }

    public void openBookingActivity(){
        Intent intent = new Intent(this,BookSlot.class);
        //intent.putExtra("licenceNumber",licenceString);
        startActivity(intent);
    }





}


