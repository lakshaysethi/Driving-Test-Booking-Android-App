package com.lakshaysethi.driverlicencetestbooking;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import com.lakshaysethi.driverlicencetestbooking.Pojoclasses.Slot;

public class HotspotMatrixActivity extends AppCompatActivity {


    TableLayout t1;
    private Controller c = new Controller();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotspot_matrix);

        TableLayout hotspotTable = (TableLayout) findViewById(R.id.hotspot_table);

        TableRow tableRowTop = new TableRow(this);
        tableRowTop.setBackgroundColor(Color.rgb(229, 209, 240));
        hotspotTable.addView(tableRowTop);

        int startHour = 900;
        ArrayList<TextView> headtvAl = new ArrayList<TextView>();
        for(int i=0;i<9;i++){

            TextView tv = new TextView(this);
            if(i==0){
                tv.setText("Days/Time Slots");
                tv.setBackgroundColor(Color.WHITE);
            }else{
                tv.setText("  "+c.getStringHourFromInt(startHour)+"-"+c.getStringHourFromInt(startHour+100)+" ");
                startHour+=100;
            }
            tableRowTop.addView(tv);
            headtvAl.add(tv);

        }

        ArrayList<TableRow> tableRowArrayList = new ArrayList<>();

        int numberOfCellsNeeded = 5*9;
        int count =0;
        for(int i=0; i<numberOfCellsNeeded;i++){

            if(i%9==0){//if first cell of the row so make a new row
                TableRow tr = new TableRow(this);//we need  5
                tableRowArrayList.add(tr);
            }
            //we need 45
            TextView tv = new TextView(this);

            if(i%9==0){
                tv.setText(c.getArrayListOfUniqueDates(Controller.slotsList).get(i/9).getDateString());
                tv.setBackgroundColor(Color.rgb(229, 209, 240));
                count++;
            }else{
                Slot s1 = Controller.slotsList.get(i-count);
                int numberOfBookings = 10-s1.remainingTimes;
                tv.setText(Integer.toString(numberOfBookings));
                setColor(tv,numberOfBookings);

            }

            tableRowArrayList.get(i/9).addView(tv);


        }

        for(TableRow tr:tableRowArrayList){
            hotspotTable.addView(tr);
        }
    }

    private void setColor(TextView tv, int numberOfBookings) {
        switch (numberOfBookings){
            case 1:tv.setBackgroundColor(Color.rgb(117, 235, 229)); break;
            case 2:tv.setBackgroundColor(Color.rgb(143, 235, 117)); break;
            case 3:tv.setBackgroundColor(Color.rgb(143, 235, 117)); break;
            case 4:tv.setBackgroundColor(Color.rgb(255, 233, 87)); break;
            case 5:tv.setBackgroundColor(Color.rgb(255, 233, 87)); break;
            case 6:tv.setBackgroundColor(Color.rgb(255, 233, 87)); break;
            case 7:tv.setBackgroundColor(Color.rgb(255, 233, 87)); break;
            case 8:tv.setBackgroundColor(Color.rgb(255, 87, 87)); break;
            case 9:tv.setBackgroundColor(Color.rgb(255, 87, 87)); break;
            case 10:tv.setBackgroundColor(Color.rgb(255, 87, 87)); break;


        }

    }
}
