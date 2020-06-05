package com.wayfortech.ebalsabha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.wayfortech.ebalsabha.Database.DBManger;
import com.wayfortech.ebalsabha.Model.Child;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.wayfortech.ebalsabha.Constants.MYTAG;

public class ReportGenerationActivity extends AppCompatActivity implements View.OnClickListener {

    Button allChildData,getDateWiseReportBtn,getMonthWiseReportBtn;
    DBManger dbManger;
    public CellStyle cellStyle;
    public static File file=null;
    public static Workbook workbook;
    public static Sheet sheet=null;
    String[] colnames=new String[]{"CID","SURNAME","NAME","FATHERNAME","MOTHERNAME","DOB","CONTACTNO","WHATSAPPNO","HOMENO","PARTNO","SOCIETYNAME"};
    Row row=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_generation);
        initUI();
        setonclicklistener();
        getSupportActionBar().setTitle("Generate Report");
        dbManger=new DBManger(this);
        dbManger.open();
    }
    
    private void initUI() {
        allChildData=findViewById(R.id.Button_allchildDataReport);
        getDateWiseReportBtn=findViewById(R.id.Button_getDateWiseReport);
        getMonthWiseReportBtn=findViewById(R.id.Button_getMonthWiseReport);
    }

    private void setonclicklistener() {
        this.allChildData.setOnClickListener(ReportGenerationActivity.this);
        this.getDateWiseReportBtn.setOnClickListener(ReportGenerationActivity.this);
        this.getMonthWiseReportBtn.setOnClickListener(ReportGenerationActivity.this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.Button_allchildDataReport:
                generateReport();
                break;
            case R.id.Button_getDateWiseReport:
                generateDateWiseReport();
                break;
            case R.id.Button_getMonthWiseReport:
                generateMonthWiseReport();
        }
    }


    private void generateReport() {
        List<Child> childList=new ArrayList<>();
        childList=dbManger.getAllChild();
        FileOutputStream fileOutputStream=null;
        Cell cell=null;

        workbook=new HSSFWorkbook();
        cellStyle=workbook.createCellStyle();
        cellStyle.setFillForegroundColor(HSSFColor.LIGHT_BLUE.index);
        //Creating excel sheet

        sheet=workbook.createSheet("Purshottamnagar Balsabha Information");
        //getExternalFilesDir() which is created as private file and deleted when app is uninstalled
        //getExternalStoragePublicDirectory() which is created as public file and not deleted when app is uninstalled
        file=new File(this.getExternalFilesDir(null),"ebalsabha.xls");

            row=sheet.createRow(0);
            for(int j=0;j<colnames.length;j++)
            {
                cell=row.createCell(j);
                cell.setCellValue(colnames[j]);
                cell.setCellStyle(cellStyle);
                sheet.setColumnWidth(j,(10*200));
                try {
                    fileOutputStream=new FileOutputStream(file);
                    workbook.write(fileOutputStream);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            Log.d(MYTAG, "Header Inserted");



        for(int i=0;i<childList.size();i++)
        {
            row=sheet.createRow(i+1);
            // column 1 for row 1
            cell=row.createCell(0);
            cell.setCellValue(childList.get(i).getCid());
            cell.setCellStyle(cellStyle);

            // column 2 for row 1
            cell=row.createCell(1);
            cell.setCellValue(childList.get(i).getCsurname());
            cell.setCellStyle(cellStyle);

            // column 3 for row 1
            cell=row.createCell(2);
            cell.setCellValue(childList.get(i).getCname());
            cell.setCellStyle(cellStyle);

            // column 4 for row 1
            cell=row.createCell(3);
            cell.setCellValue(childList.get(i).getCfathername());
            cell.setCellStyle(cellStyle);

            // column 5 for row 1
            cell=row.createCell(4);
            cell.setCellValue(childList.get(i).getCmothername());
            cell.setCellStyle(cellStyle);

            // column 6 for row 1
            cell=row.createCell(5);
            cell.setCellValue(childList.get(i).getCdob());
            cell.setCellStyle(cellStyle);

            // column 7 for row 1
            cell=row.createCell(6);
            cell.setCellValue(childList.get(i).getCcontact());
            cell.setCellStyle(cellStyle);

            // column 8 for row 1
            cell=row.createCell(7);
            cell.setCellValue(childList.get(i).getCwhatsapp());
            cell.setCellStyle(cellStyle);

            // column 9 for row 1
            cell=row.createCell(8);
            cell.setCellValue(childList.get(i).getChomeno());
            cell.setCellStyle(cellStyle);

            // column 10 for row 1
            cell=row.createCell(9);
            cell.setCellValue(childList.get(i).getCpartno());
            cell.setCellStyle(cellStyle);

            // column 11 for row 1
            cell=row.createCell(10);
            cell.setCellValue(childList.get(i).getCsocietyname());
            cell.setCellStyle(cellStyle);


            try {
                fileOutputStream=new FileOutputStream(file);
                workbook.write(fileOutputStream);
                Log.d(MYTAG, "Header Inserted");
            } catch (Exception e) {
                e.printStackTrace();
            }


            Log.d(MYTAG,childList.get(i).getCname()+" Added in sheet");

        }
        Toast.makeText(this, "File Generated", Toast.LENGTH_LONG).show();
        Log.d(MYTAG, "All Data Added in Sheet ");



    }

    private void generateDateWiseReport() {
        startActivity(new Intent(ReportGenerationActivity.this,DateWiseReportActivity.class));
    }

    private void generateMonthWiseReport() {
        startActivity(new Intent(ReportGenerationActivity.this,MonthWiseReportActivity.class));
    }


}
