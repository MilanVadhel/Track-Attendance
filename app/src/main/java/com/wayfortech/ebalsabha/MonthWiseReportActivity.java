package com.wayfortech.ebalsabha;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.wayfortech.ebalsabha.Database.DBManger;

public class MonthWiseReportActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText month,year,totalofmonth,averageofmoth;
    private Button reportGeneratorBtn;
    private DBManger dbManger;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month_wise_report);
        initUI();
        setonclicklistener();
        getSupportActionBar().setTitle("Month Wise Report");
        dbManger=new DBManger(this);
        dbManger.open();
    }

    private void initUI()
    {
        month=findViewById(R.id.Report_month);
        year=findViewById(R.id.Report_year);
        reportGeneratorBtn=findViewById(R.id.Button_monthReportGeneratorBtn);
        totalofmonth=findViewById(R.id.Edittext_TotalChildForMonth);
        averageofmoth=findViewById(R.id.Edittext_AverageForMonth);
    }
    private void setonclicklistener() {
        this.reportGeneratorBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.Button_monthReportGeneratorBtn:
                generatReportOfMonth();
                break;
        }

    }

    private void generatReportOfMonth()
    {
        String monthforreport=month.getText().toString();
        String yearforreport=year.getText().toString();
        String monthandyear="/"+monthforreport+"/"+yearforreport;
        float totalchild=dbManger.getMonthWiseReport(monthandyear);
        float averagechild=totalchild/4;
        totalofmonth.setText(String.valueOf(totalchild));
        averageofmoth.setText(String.valueOf(averagechild));
    }
}
