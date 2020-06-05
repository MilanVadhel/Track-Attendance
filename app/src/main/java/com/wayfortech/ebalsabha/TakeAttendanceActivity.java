package com.wayfortech.ebalsabha;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.wayfortech.ebalsabha.Database.DBManger;
import com.wayfortech.ebalsabha.Model.Attendance;

import java.util.Calendar;

public class TakeAttendanceActivity extends AppCompatActivity implements View.OnClickListener {

    private TextInputEditText todaysDate;
    private EditText attendanceNumber;
    private Button todaysDateTaker,addAttendance;
    private DBManger dbManger;
    private Attendance attendance;
    private Calendar calendar;
    private DatePickerDialog datePickerDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_attendance);
        initUI();
        getSupportActionBar().setTitle("Take Attendance");
        dbManger =new DBManger(this);
        dbManger.open();
        setonclicklistener();
        removeUIValues();
    }


    private void initUI()
    {
        todaysDate=findViewById(R.id.EditText_todaysDate);
        attendanceNumber=findViewById(R.id.EditText_attendanceNumber);
        todaysDateTaker=findViewById(R.id.Button_todaysDateTaker);
        addAttendance=findViewById(R.id.Button_addAttendance);
    }
    private void setonclicklistener() {
        this.todaysDateTaker.setOnClickListener(this);
        this.addAttendance.setOnClickListener(this);
    }
    private void removeUIValues()
    {
        todaysDate.setText("");
        attendanceNumber.setText("");
    }
    /*@Override
    protected void onResume() {
        super.onResume();
        attendanceDataSource.open();
    }*/

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbManger.close();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.Button_todaysDateTaker:
                takeTodaysDate();
                break;
            case R.id.Button_addAttendance:
                verifyAttendanceNumber();
                break;
        }
    }
    private void takeTodaysDate()
    {
        calendar=Calendar.getInstance();
        int day=calendar.get(Calendar.DAY_OF_MONTH);
        int month=calendar.get(Calendar.MONTH);
        int year=calendar.get(Calendar.YEAR);
        datePickerDialog=new DatePickerDialog(TakeAttendanceActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                todaysDate.setText(day + "/" + (month+1)+ "/" + year);
            }
        },day,month,year);
        datePickerDialog.show();

    }
    private void verifyAttendanceNumber()
    {
        String AID=attendanceNumber.getText().toString();
        String date=todaysDate.getText().toString();
        if(date.equalsIgnoreCase("") || AID.equalsIgnoreCase("")){
            todaysDate.setError("Fill Date");
            attendanceNumber.setError("Fill Attendance Number");
        }else
        {
            //get Name and Surname
            String CHILDFULLNAME= dbManger.getNameandSurname(AID);
            //if ok then put attendance or else cancel then set null name and surname
            openDialog(AID,date,CHILDFULLNAME);
        }

    }

    private void openDialog(final String cid,final String date,String CHILDFULLNAME)
    {
        if(CHILDFULLNAME.equalsIgnoreCase("") || CHILDFULLNAME.isEmpty() || CHILDFULLNAME==null || CHILDFULLNAME.startsWith("null"))
        {
            Toast.makeText(this, "Sorry you are not regesterd", Toast.LENGTH_LONG).show();
            attendanceNumber.setText("");
        }
        else
        {
            new AlertDialog.Builder(TakeAttendanceActivity.this)
                    .setTitle("Your Name")
                    .setMessage(CHILDFULLNAME)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            attendance=new Attendance(cid,date,"PRESENT");
                            dbManger.addAttendance(attendance);
                            Toast.makeText(TakeAttendanceActivity.this, "Thanks", Toast.LENGTH_LONG).show();
                            attendanceNumber.setText("");
                        }
                    }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(TakeAttendanceActivity.this, "Not Added", Toast.LENGTH_SHORT).show();
                    attendanceNumber.setText("");
                }
            }).show();
        }

    }

}
