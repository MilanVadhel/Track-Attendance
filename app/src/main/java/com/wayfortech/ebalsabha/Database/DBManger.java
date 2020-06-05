package com.wayfortech.ebalsabha.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.wayfortech.ebalsabha.Model.Attendance;
import com.wayfortech.ebalsabha.Model.Child;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.wayfortech.ebalsabha.Constants.MYTAG;

public class DBManger
{

    private Context context;
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase sqLiteDatabase;
    public DBManger(Context context)
    {
        this.context=context;
        databaseHelper=new DatabaseHelper(context);
        sqLiteDatabase=databaseHelper.getWritableDatabase();
    }

    //open database connection
    public void open()
    {
        sqLiteDatabase=databaseHelper.getWritableDatabase(); // for getting valid reference
        //because when config change(landscape to portrait and portrait to landscape and activity recreation) reference of db can distroye
    }

    //add child info
    public long addChild(Child child)
    {
        ContentValues childValues=child.getChildValues();
        long result=sqLiteDatabase.insert(TableCreator.TABLE_CHILDINFO,null,childValues);
        Log.d(MYTAG, "addChild: "+ result+ " Row inserted in childinfo table");
        return result;
    }

    //get all child's info for display in recyclerview
    public List<Child> getAllChild()
    {
        List<Child> childArrayList=new ArrayList<>();
        Cursor cursor=sqLiteDatabase.query(TableCreator.TABLE_CHILDINFO, TableCreator.COLUMN_ALLCOLUMNS,null,null,null,null,null);
        while(cursor.moveToNext())
        {
            Child child=new Child();
            child.setCid(Integer.parseInt(cursor.getString(cursor.getColumnIndex(TableCreator.COLUMN_CID))));
            child.setCsurname(cursor.getString(cursor.getColumnIndex(TableCreator.COLUMN_CSURNAME)));
            child.setCname(cursor.getString(cursor.getColumnIndex(TableCreator.COLUMN_CNAME)));
            child.setCfathername(cursor.getString(cursor.getColumnIndex(TableCreator.COLUMN_CFATHERNAME)));
            child.setCmothername(cursor.getString(cursor.getColumnIndex(TableCreator.COLUMN_CMOTHERNAME)));
            child.setCdob(cursor.getString(cursor.getColumnIndex(TableCreator.COLUMN_CDOB)));
            child.setCcontact(cursor.getString(cursor.getColumnIndex(TableCreator.COLUMN_CCONTACT)));
            child.setCwhatsapp(cursor.getString(cursor.getColumnIndex(TableCreator.COLUMN_CWHATSAPP)));
            child.setChomeno(cursor.getString(cursor.getColumnIndex(TableCreator.COLUMN_CHOMENO)));
            child.setCpartno(cursor.getString(cursor.getColumnIndex(TableCreator.COLUMN_CPARTNO)));
            child.setCsocietyname(cursor.getString(cursor.getColumnIndex(TableCreator.COLUMN_CSOCIETYNAME)));
            childArrayList.add(child);
        }
        cursor.close();
        return childArrayList;
    }

    //update child info
    public void updateChildInfo(Child child,int cid)
    {
        String where= TableCreator.COLUMN_CID+"=?";
        ContentValues valuesforupdate=child.getChildValues();
        sqLiteDatabase.update(TableCreator.TABLE_CHILDINFO,valuesforupdate,where,new String[]{String.valueOf(cid)});
        Log.d(MYTAG, "ChildInfo Updated");
    }

    //attendance add
    public void addAttendance(Attendance attendance)
    {
        ContentValues values=attendance.getValues();
        long result=0;
        try
        {
            //.insert() method returns result
            // -1 for error
            //else row number
            result=sqLiteDatabase.insert(TableCreator.TABLE_ATTENDANCE,null,values);
        }catch (Exception s)
        {
            Log.d(MYTAG, "Exception : "+s.getMessage());
        }

        if(result==-1)
        {
            Log.d(MYTAG, "Adding Fail");
        }
        else
        {
            Log.d(MYTAG, result+" Attendance Added");
        }

    }

    //get name and surname for verify attendance number
    public String getNameandSurname(String aid) {
        //select name, surname from attendance where cid=aid;
        String NAME=null;
        String SURNAME=null;
        String where= TableCreator.COLUMN_CID+"=?";
        Cursor cursor=sqLiteDatabase.query(TableCreator.TABLE_CHILDINFO,new String[]{TableCreator.COLUMN_CNAME, TableCreator.COLUMN_CSURNAME},where,new String[]{aid},null,null,null,null);
        while (cursor.moveToNext())
        {
            NAME=cursor.getString(cursor.getColumnIndex(TableCreator.COLUMN_CNAME));
            SURNAME=cursor.getString(cursor.getColumnIndex(TableCreator.COLUMN_CSURNAME));
        }
        return NAME+" "+SURNAME;
    }

    public ArrayList<Child> getDatWiseReport(String date)
    {
        /*//SELECT count(attendance.cid) from attendance WHERE attendance.date='26/1/1900'
        String selectedcolumns=TableCreator.COLUMN_AID;
        String where=TableCreator.COLUMN_DATE +" = ?";
        Cursor cursor=sqLiteDatabase.query(TableCreator.TABLE_ATTENDANCE,new String[]{selectedcolumns},where,new String[]{date},null,null,null);
        int total=cursor.getCount();
        Log.d(MYTAG, " Total:  "+total);
        cursor.close();
        return total;*/

        ArrayList<Child> presentChildList=new ArrayList<>();
        //SELECT childinfo.cid,childinfo.csurname,childinfo.cname FROM childinfo JOIN attendance on childinfo.cid=attendance.cid WHERE attendance.date='14/4/2020'
        String sql="SELECT childinfo.cid,childinfo.csurname,childinfo.cname FROM childinfo JOIN attendance on childinfo.cid=attendance.cid WHERE attendance.date='"+date+"'";
        Cursor cursor=sqLiteDatabase.rawQuery(sql,null);
        int total=cursor.getCount();
        while (cursor.moveToNext())
        {
            Child child=new Child();
            child.setCid(Integer.parseInt(cursor.getString(cursor.getColumnIndex(TableCreator.COLUMN_CID))));
            child.setCsurname(cursor.getString(cursor.getColumnIndex(TableCreator.COLUMN_CSURNAME)));
            child.setCname(cursor.getString(cursor.getColumnIndex(TableCreator.COLUMN_CNAME)));
            presentChildList.add(child);
        }
        Log.d(MYTAG, " Total:  "+total);
        cursor.close();
        return presentChildList;

    }

    public float getMonthWiseReport(String monthandyear)
    {
        //SELECT attendance.cid FROM attendance WHERE attendance.date like '%/1/1900'
        String selectedcolumns=TableCreator.COLUMN_AID;
        String where = TableCreator.COLUMN_DATE + " like ";
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT attendance.cid FROM attendance WHERE attendance.date like '%"+monthandyear+"'",null);
        //Cursor cursor=sqLiteDatabase.query(TableCreator.TABLE_ATTENDANCE,new String[]{selectedcolumns},where,new String[]{"'%"+monthandyear+"'"},null,null,null);
        float total=cursor.getCount();
        return total;
    }


    //close connection
    public void close()
    {
        sqLiteDatabase.close(); //for cloasing connection when activity is distroye
        //if you call close it will call all db connections
        //if we not call we getting db resource leak
    }

    public ArrayList<Child> getBirthdayBoys(int month) {
        ArrayList<Child> birthdayBoysList=new ArrayList<>();
        //Select name,fathername,surname,dob from childinfo where dob='%/month/%'
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT childinfo.cname,childinfo.cfathername,childinfo.csurname,childinfo.cdob FROM childinfo WHERE childinfo.cdob like '%"+"/"+month+"/"+"%'",null);
        while (cursor.moveToNext())
        {
            Child child=new Child();
            child.setCname(cursor.getString(cursor.getColumnIndex(TableCreator.COLUMN_CNAME)));
            child.setCfathername(cursor.getString(cursor.getColumnIndex(TableCreator.COLUMN_CFATHERNAME)));
            child.setCsurname(cursor.getString(cursor.getColumnIndex(TableCreator.COLUMN_CSURNAME)));
            child.setCdob(cursor.getString(cursor.getColumnIndex(TableCreator.COLUMN_CDOB)));
            birthdayBoysList.add(child);
        }
        cursor.close();
        return birthdayBoysList;
    }
}
