package com.lakshaysethi.driverlicencetestbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TableLayout;

public class HotspotMatrixActivity extends AppCompatActivity {


    TableLayout t1;

    TableLayout tl = (TableLayout) findViewById(R.id.hotspot_table);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotspot_matrix);
    }
}
