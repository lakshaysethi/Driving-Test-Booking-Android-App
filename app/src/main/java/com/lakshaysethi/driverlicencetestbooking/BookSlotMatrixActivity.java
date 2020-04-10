package com.lakshaysethi.driverlicencetestbooking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class BookSlotMatrixActivity extends AppCompatActivity {



    RecyclerView date1RecyclerView;
    RecyclerView date2RecyclerView;
    RecyclerView date3RecyclerView;
    RecyclerView date4RecyclerView;
    RecyclerView date5RecyclerView;
    RecyclerView rvLdates;

    private TextView licenceNumberTextView;
    private Button viewMyBookingsButton;

    Controller c = new Controller();
    ArrayList<RecyclerView> rViewArrayList = new ArrayList<RecyclerView>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_slot_matrix);
        date1RecyclerView= (RecyclerView) findViewById(R.id.dateRecyclerView);
        date2RecyclerView= (RecyclerView) findViewById(R.id.date2RecyclerView);
        date3RecyclerView= (RecyclerView) findViewById(R.id.date3RecyclerView);
        date4RecyclerView= (RecyclerView) findViewById(R.id.date4RecyclerView);
        date5RecyclerView= (RecyclerView) findViewById(R.id.date5RecyclerView);
        rViewArrayList.add(date1RecyclerView);
        rViewArrayList.add(date2RecyclerView);
        rViewArrayList.add(date3RecyclerView);
        rViewArrayList.add(date4RecyclerView);
        rViewArrayList.add(date5RecyclerView);

        rvLdates = (RecyclerView) findViewById(R.id.date6RecyclerView);
        rvLdates.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<Pojoclasses.Slot> al = c.getArrayListOfUniqueDates(Controller.slotsList);
        rvLdates.setAdapter(new SlotAdapter(al,this,R.layout.date_and_day_view));

        int count = 0;
        for(RecyclerView rv : rViewArrayList ){
            rv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
            ArrayList<Pojoclasses.Slot> tmp = new ArrayList<Pojoclasses.Slot>(Controller.slotsList.subList(count,count+8));
            rv.setAdapter(new SlotAdapter(tmp,this,R.layout.slot_button));
            count +=8;
        }


        licenceNumberTextView = (TextView) findViewById(R.id.licenceTextView2);
        viewMyBookingsButton =(Button) findViewById(R.id.viewMyBookingsButton2);

        licenceNumberTextView.setText("Licence # " +Controller.licenceNumber);

        viewMyBookingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { {openViewMyBookingsActivity();} }
        });


    }


    private void openViewMyBookingsActivity() {
        Intent intent = new Intent(this,ViewMyBookingsActivity.class);
        //intent.putExtra("licenceNumber",licenceString);
        startActivity(intent);
    }
}
