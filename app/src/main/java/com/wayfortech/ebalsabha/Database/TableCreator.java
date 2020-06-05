package com.wayfortech.ebalsabha.Database;


import static com.wayfortech.ebalsabha.Constants.TABLE_ADDCHILDINFO;

public class TableCreator
{
    /*****************************For Childinfo table**************************************************/
    public static final String TABLE_CHILDINFO="childinfo";
    public static final String COLUMN_CID="cid";
    public static final String COLUMN_CSURNAME="csurname";
    public static final String COLUMN_CNAME="cname";
    public static final String COLUMN_CFATHERNAME="cfathername";
    public static final String COLUMN_CMOTHERNAME="cmothername";
    public static final String COLUMN_CDOB="cdob";
    public static final String COLUMN_CCONTACT="ccontact";
    public static final String COLUMN_CWHATSAPP="cwhatsapp";
    public static final String COLUMN_CHOMENO="chomeno";
    public static final String COLUMN_CPARTNO="cpartno";
    public static final String COLUMN_CSOCIETYNAME="csocietyname";

    public static final String[] COLUMN_ALLCOLUMNS=new String[]
            { COLUMN_CID,COLUMN_CSURNAME,
                    COLUMN_CNAME,COLUMN_CFATHERNAME,COLUMN_CMOTHERNAME,COLUMN_CDOB,
                    COLUMN_CCONTACT,COLUMN_CWHATSAPP,COLUMN_CHOMENO,COLUMN_CPARTNO,COLUMN_CSOCIETYNAME };

    public static final String SQL_CREATE_CHILD_INFO_TABLE=
            "CREATE TABLE "+ TABLE_ADDCHILDINFO + "(" +
                    COLUMN_CID          + " INTEGER PRIMARY KEY AUTOINCREMENT DEFAULT 1, "+
                    COLUMN_CSURNAME     + " TEXT, "+
                    COLUMN_CNAME        + " TEXT, "+
                    COLUMN_CFATHERNAME  + " TEXT, "+
                    COLUMN_CMOTHERNAME  + " TEXT, "+
                    COLUMN_CDOB         + " TEXT, "+
                    COLUMN_CCONTACT     + " TEXT, "+
                    COLUMN_CWHATSAPP    + " TEXT, "+
                    COLUMN_CHOMENO      + " TEXT, "+
                    COLUMN_CPARTNO      + " TEXT, "+
                    COLUMN_CSOCIETYNAME + " TEXT"+
                    ");";

    public static final String SQL_DELETE_CHILDINFO_TABLE="DROP TABLE IF EXISTS "+TABLE_CHILDINFO;




    /****************************************For Attendance Table************************************************/
    public static final String TABLE_ATTENDANCE="attendance";
    public static final String COLUMN_ID="id";
    public static final String COLUMN_AID="cid";
    public static final String COLUMN_DATE="date";
    public static final String COLUMN_STATUS="status";
    public static final String SQL_CREATE_ATTENDANCE_TABLE=
            "CREATE TABLE "+ TABLE_ATTENDANCE  +"("+
                    COLUMN_ID       +   " INTEGER PRIMARY KEY AUTOINCREMENT DEFAULT 1, " +
                    COLUMN_AID      +   " TEXT, "+
                    COLUMN_DATE     +   " TEXT, "+
                    COLUMN_STATUS   +   " TEXT);";
    public static final String SQL_DELETE_ATTENDANCE_TABLE="DROP TABLE IF EXISTS "+TABLE_ATTENDANCE;

}
