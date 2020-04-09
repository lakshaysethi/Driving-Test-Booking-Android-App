package com.lakshaysethi.driverlicencetestbooking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;

import java.util.ArrayList;

public class MatrixActivity extends AppCompatActivity {



    RecyclerView date1RecyclerView;
    RecyclerView date2RecyclerView;
    RecyclerView date3RecyclerView;
    RecyclerView date4RecyclerView;
    RecyclerView date5RecyclerView;
    RecyclerView rvLdates;

    ArrayList<RecyclerView> rViewArrayList = new ArrayList<RecyclerView>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrix);
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
        rvLdates.setAdapter(new SlotAdapter(getArrayListOfUniqueDates(Controller.slotsList),this,R.layout.date_and_day_view));

        int count = 0;
        for(RecyclerView rv : rViewArrayList ){
            rv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
            ArrayList<Pojoclasses.Slot> tmp = new ArrayList<Pojoclasses.Slot>(Controller.slotsList.subList(count,count+8));
            rv.setAdapter(new SlotAdapter(tmp,this,R.layout.slot_button));

            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int width = displayMetrics.widthPixels;

            rv.setMinimumWidth(width-rvLdates.getWidth());
            System.out.println("width of the recycler"+rv.getWidth());
            (new Controller()).showToast(this,"width of the recycler"+rv.getWidth());
            count +=8;
        }
    }

    private ArrayList<Pojoclasses.Slot> getArrayListOfUniqueDates(ArrayList<Pojoclasses.Slot> slotsList) {
        ArrayList<Pojoclasses.Slot> al = new ArrayList<Pojoclasses.Slot>();
        int count=0;
        for(int i=0;i<5;i++){
            al.add(slotsList.get(count));
            count+=8;
        }
        return  al;

    }
}
