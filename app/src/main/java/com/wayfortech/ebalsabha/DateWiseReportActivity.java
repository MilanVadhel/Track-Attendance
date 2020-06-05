package com.wayfortech.ebalsabha;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.wayfortech.ebalsabha.Adapters.PresentBoysAdapter;
import com.wayfortech.ebalsabha.Database.DBManger;
import com.wayfortech.ebalsabha.Model.Child;

import java.util.ArrayList;
import java.util.Calendar;

public class DateWiseReportActivity extends AppCompatActivity  implements View.OnClickListener {

    private EditText reportDate,totalChild;
    private RecyclerView prsentedBoysView;
    private Button datetakerBtnforReport,reportGeneratorBtn;
    private Calendar calendar;
    private DatePickerDialog datePickerDialog;
    private DBManger dbManger;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_wise_report);
        initUI();
        setonclicklistener();
        getSupportActionBar().setTitle("Date Wise Report");
        dbManger=new DBManger(this);
        dbManger.open();

    }


    private void initUI() {
        reportDate=findViewById(R.id.Report_date);
        totalChild=findViewById(R.id.Edittext_TotalChild);
        datetakerBtnforReport=findViewById(R.id.Button_dateforReport);
        reportGeneratorBtn=findViewById(R.id.Button_GenerateDateWise);
        prsentedBoysView=findViewById(R.id.presentBoysRecyclerview);
    }

    private void setonclicklistener() {
        this.datetakerBtnforReport.setOnClickListener(this);
        this.reportGeneratorBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.Button_dateforReport:
                takedate();
                break;
            case R.id.Button_GenerateDateWise:
                generatereport();
                break;
        }
    }



    private void takedate() {
        calendar= Calendar.getInstance();
        int day=calendar.get(Calendar.DAY_OF_MONTH);
        int month=calendar.get(Calendar.MONTH);
        int year=calendar.get(Calendar.YEAR);
        datePickerDialog=new DatePickerDialog(DateWiseReportActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                reportDate.setText(day + "/" + (month+1)+ "/" + year);
            }
        },day,month,year);
        datePickerDialog.show();
    }

    private void generatereport() {
        ArrayList<Child> presentCHilds=new ArrayList<Child>();
        presentCHilds=dbManger.getDatWiseReport(reportDate.getText().toString());
        totalChild.setText(String.valueOf(presentCHilds.size()));
        setRecyclerView(presentCHilds);
    }

    private void setRecyclerView(ArrayList<Child> presentChildList)
    {
        prsentedBoysView.setLayoutManager(new LinearLayoutManager(this));
        prsentedBoysView.setAdapter(new PresentBoysAdapter(this,presentChildList));
    }
}
