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

public class UpdateProfile extends AppCompatActivity implements View.OnClickListener {

    private TextInputEditText surname,name,fathername,mothername,dob,contactno,whatsappno,homeno,partno,societyname;
    private Button updateButton,dobtakerButton;
    private Calendar calendar;
    private DatePickerDialog datePickerDialog;
    private DBManger dbManger;
    private int cid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        initUI();
        setonclicklistener();
        setValues();
        getSupportActionBar().setTitle("Update Profile");
        dbManger=new DBManger(this);
        dbManger.open();
    }

    private void initUI() {
        surname=findViewById(R.id.Profile_childSurnameEditText);
        name=findViewById(R.id.Profile_childNameEditText);
        fathername=findViewById(R.id.Profile_childFatherNameEditText);
        mothername=findViewById(R.id.Profile_childMotherNameEditText);
        dob=findViewById(R.id.Profile_childDOBEditText);
        contactno=findViewById(R.id.Profile_childContactNoEditText);
        whatsappno=findViewById(R.id.Profile_childWhatsappNoEditText);
        homeno=findViewById(R.id.Profile_childHomeNoEditText);
        partno=findViewById(R.id.Profile_childPartNoEditText);
        societyname=findViewById(R.id.Profile_childSocietyNameEditText);
        dobtakerButton=findViewById(R.id.Profile_DOBTakerButton);
        updateButton=findViewById(R.id.Profile_updateChildInfoButton);
    }
    private void setonclicklistener()
    {
        this.updateButton.setOnClickListener(this);
        this.dobtakerButton.setOnClickListener(this);
    }
    private void setValues()
    {
        Child child= (Child) getIntent().getSerializableExtra("CHILDINFO");
        cid= child.getCid();
        surname.setText(child.getCsurname());
        name.setText(child.getCname());
        fathername.setText(child.getCfathername());
        mothername.setText(child.getCmothername());
        dob.setText(child.getCdob());
        contactno.setText(child.getCcontact());
        whatsappno.setText(child.getCwhatsapp());
        homeno.setText(child.getChomeno());
        partno.setText(child.getCpartno());
        societyname.setText(child.getCsocietyname());
    }

   /* protected void onResume() {
        super.onResume();
        addChildDatasource.open();
    }
*/
    /*@Override
    *//*protected void onPause() {
        super.onPause();
        addChildDatasource.close();
    }*/

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.Profile_DOBTakerButton:
                takedob();
                break;
            case R.id.Profile_updateChildInfoButton:
                updateChildInfo();
                break;
        }
    }

    private void takedob() {

        calendar= Calendar.getInstance();
        int day=calendar.get(Calendar.DAY_OF_MONTH);
        int month=calendar.get(Calendar.MONTH);
        int year=calendar.get(Calendar.YEAR);
        datePickerDialog=new DatePickerDialog(UpdateProfile.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                dob.setText(day + "/" + (month+1)+ "/" + year);
            }
        },day,month,year);
        datePickerDialog.show();
    }

    private void updateChildInfo() {
        Child child=new Child(
                surname.getText().toString(),
                name.getText().toString(),
                fathername.getText().toString(),
                mothername.getText().toString(),
                dob.getText().toString(),
                contactno.getText().toString(),
                whatsappno.getText().toString(),
                homeno.getText().toString(),
                partno.getText().toString(),
                societyname.getText().toString());
        dbManger.updateChildInfo(child,cid);
        Toast.makeText(this, "Successfully Update", Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbManger.close();
    }
}
