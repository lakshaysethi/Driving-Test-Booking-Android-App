package com.lakshaysethi.driverlicencetestbooking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MatrixActivity extends AppCompatActivity {


    RecyclerView.LayoutManager recycLayoutmanager;
    RecyclerView dateRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrix);

        dateRecyclerView = (RecyclerView) findViewById(R.id.dateRecyclerView);
        recycLayoutmanager = new LinearLayoutManager(this);
        dateRecyclerView.setLayoutManager(recycLayoutmanager);


    }
}
