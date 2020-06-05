package com.wayfortech.ebalsabha;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.wayfortech.ebalsabha.Database.DBManger;
import com.wayfortech.ebalsabha.Model.Child;

import java.util.Calendar;

public class AddNewChildActivity extends AppCompatActivity implements View.OnClickListener {

    private DBManger dbManger;
    private Child child;
    private TextInputEditText childSurname,childName,
    childFathername,childMothername,childDOB,
    childContactNo,childWhatsappNo,childHomeNo,childPartNo,
    childSocietyName;
    private Button dobTakerBtn,addChildInfoBtn;
    private DatePickerDialog datePickerDialog;
    private Calendar calendar;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_child);
        initUI();
        clearUI();
        setonclicklistener();
        getSupportActionBar().setTitle("Add New Child");
        createDatBases();
    }


    private void initUI()
    {
        childSurname=findViewById(R.id.EditText_childSurname);
        childName=findViewById(R.id.EditText_childName);
        childFathername=findViewById(R.id.EditText_childFatherName);
        childMothername=findViewById(R.id.EditText_childMotherName);
        childDOB=findViewById(R.id.EditText_childDOB);
        childContactNo=findViewById(R.id.EditText_childContactNo);
        childWhatsappNo=findViewById(R.id.EditText_childWhatsappNo);
        childHomeNo=findViewById(R.id.EditText_childHomeNo);
        childPartNo=findViewById(R.id.EditText_childPartNo);
        childSocietyName=findViewById(R.id.EditText_childSocietyName);
        dobTakerBtn=findViewById(R.id.Button_dobTaker);
        addChildInfoBtn=findViewById(R.id.Button_addChildInfo);
    }
    private void clearUI()
    {
        childSurname.setText("");
        childName.setText("");
        childFathername.setText("");
        childMothername.setText("");
        childDOB.setText("");
        childContactNo.setText("");
        childWhatsappNo.setText("");
        childHomeNo.setText("");
        childPartNo.setText("");
        childSocietyName.setText("");
    }
    private void setonclicklistener() {
        this.dobTakerBtn.setOnClickListener(this);
        this.addChildInfoBtn.setOnClickListener(this);
    }

    private void createDatBases()
    {
        dbManger=new DBManger(this);
        dbManger.open();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbManger.close();
    }
    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.Button_dobTaker:
                takeDOB();
                break;
            case R.id.Button_addChildInfo:
                addChildInfo();
                break;
        }
    }

    private void takeDOB()
    {
        calendar=Calendar.getInstance();
        int day=calendar.get(Calendar.DAY_OF_MONTH);
        int month=calendar.get(Calendar.MONTH);
        int year=calendar.get(Calendar.YEAR);
        datePickerDialog=new DatePickerDialog(AddNewChildActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                childDOB.setText(day + "/" + (month+1)+ "/" + year);
            }
        },day,month,year);
        datePickerDialog.show();
    }
    private void addChildInfo()
    {
        if(childSurname.getText().toString().equalsIgnoreCase("") || childName.getText().toString().equalsIgnoreCase("") ||
        childFathername.getText().toString().equalsIgnoreCase("") || childMothername.getText().toString().equalsIgnoreCase(""))
        {
            childSurname.setError("Fill required");
            childName.setError("Fill required");
            childFathername.setError("Fill required");
            childMothername.setError("Fill required");
        }else{
            child=new Child(
                    childSurname.getText().toString(),
                    childName.getText().toString(),
                    childFathername.getText().toString(),
                    childMothername.getText().toString(),
                    childDOB.getText().toString(),
                    childContactNo.getText().toString(),
                    childWhatsappNo.getText().toString(),
                    childHomeNo.getText().toString(),
                    childPartNo.getText().toString(),
                    childSocietyName.getText().toString()
            );
            long cidnumber=dbManger.addChild(child);
            Toast.makeText(this, "Added Successfully: "+cidnumber, Toast.LENGTH_SHORT).show();
            clearUI();
        }

    }


}
