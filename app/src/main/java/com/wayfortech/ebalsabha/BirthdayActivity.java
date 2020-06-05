package com.wayfortech.ebalsabha;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.wayfortech.ebalsabha.Adapters.BirthdayAdapter;
import com.wayfortech.ebalsabha.Database.DBManger;
import com.wayfortech.ebalsabha.Model.Child;
import com.wayfortech.ebalsabha.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import static com.wayfortech.ebalsabha.Constants.MYTAG;

public class BirthdayActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DBManger dbManger;
    private ArrayList<Child> childArrayList;
    private ArrayList<String> monthList;
    private Spinner spinner;
    private String[] months=new String[]{"January","February","March","April","May","June","July","August","September","October","November","December"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birthday);
        getSupportActionBar().setTitle("Birthday Boys");
        initUI();
        dbManger=new DBManger(this);
        dbManger.open();
        childArrayList=new ArrayList<>();
        setSpinner();
        //get month systematically
    }

    private void initUI() {
        recyclerView=findViewById(R.id.birthDayBoysRecyclerView);
        spinner=findViewById(R.id.monthSelector);

    }

    private void setSpinner() {
        monthList=new ArrayList<>();
        monthList.addAll(Arrays.asList(months));
        ArrayAdapter<String> monthAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,monthList);
        spinner.setAdapter(monthAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int selectedmonth=adapterView.getSelectedItemPosition();
                childArrayList=dbManger.getBirthdayBoys(selectedmonth+1);
                setRecyclerView();
                Log.d(MYTAG, "Selected Item Number: "+selectedmonth);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }




    private void setRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new BirthdayAdapter(this,childArrayList));
    }

   /* private int getCurrentMonth() {
        Calendar calendar=Calendar.getInstance();
        int month=calendar.get(Calendar.MONTH);
        return month+1;
    }
*/



}
