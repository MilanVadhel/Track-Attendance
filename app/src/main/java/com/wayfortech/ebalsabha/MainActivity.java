package com.wayfortech.ebalsabha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton addNewChildBtn,takeAttendanceBtn,generateReportBtn,updateProfile,birthDayBoys;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        setOnClickListener();

    }

    private void initUI()
    {
        this.addNewChildBtn=findViewById(R.id.Button_addNewChild);
        this.takeAttendanceBtn=findViewById(R.id.Button_takeAttendance);
        this.updateProfile=findViewById(R.id.Button_updateProfile);
        this.generateReportBtn=findViewById(R.id.Button_reportGenerate);
        this.birthDayBoys=findViewById(R.id.Button_birthday);
    }
    private void setOnClickListener()
    {
        addNewChildBtn.setOnClickListener(this);
        takeAttendanceBtn.setOnClickListener(this);
        updateProfile.setOnClickListener(this);
        generateReportBtn.setOnClickListener(this);
        birthDayBoys.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.Button_addNewChild:
                startActivity(new Intent(MainActivity.this,AddNewChildActivity.class));
                break;
            case R.id.Button_takeAttendance:
                startActivity(new Intent(MainActivity.this,TakeAttendanceActivity.class));
                break;
            case R.id.Button_updateProfile:
                startActivity(new Intent(MainActivity.this, ChildListActivity.class));
                break;
            case R.id.Button_reportGenerate:
                startActivity(new Intent(MainActivity.this,ReportGenerationActivity.class));
                break;
            case R.id.Button_birthday:
                startActivity(new Intent(MainActivity.this, BirthdayActivity.class));
                break;

        }
    }
}
