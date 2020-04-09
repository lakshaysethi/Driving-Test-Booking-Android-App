package com.lakshaysethi.driverlicencetestbooking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MatrixActivity extends AppCompatActivity {



    RecyclerView date1RecyclerView= (RecyclerView) findViewById(R.id.dateRecyclerView);
    RecyclerView date2RecyclerView= (RecyclerView) findViewById(R.id.date2RecyclerView);
    RecyclerView date3RecyclerView= (RecyclerView) findViewById(R.id.date3RecyclerView);
    RecyclerView date4RecyclerView= (RecyclerView) findViewById(R.id.date4RecyclerView);
    RecyclerView date5RecyclerView= (RecyclerView) findViewById(R.id.date5RecyclerView);

    ArrayList<RecyclerView> rViewArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrix);

        date1RecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        date1RecyclerView.setAdapter(new SlotAdapter(Controller.slotsList,this));

    }
}
