package com.wayfortech.ebalsabha.Model;

import android.content.ContentValues;

import com.wayfortech.ebalsabha.Database.TableCreator;

public class Attendance
{
    private int id;
    private String cid,date,status;

    public Attendance() {
    }

    public Attendance(String cid, String date, String status) {
        this.cid = cid;
        this.date = date;
        this.status = status;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    //create single object for adding attendance in table
    public ContentValues getValues()
    {
        ContentValues contentValues=new ContentValues(3);
        contentValues.put(TableCreator.COLUMN_AID,this.cid);
        contentValues.put(TableCreator.COLUMN_DATE,this.date);
        contentValues.put(TableCreator.COLUMN_STATUS,this.status);
        return contentValues;
    }
    @Override
    public String toString() {
        return "Attendance{" +
                "cid='" + cid + '\'' +
                ", date='" + date + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

}
