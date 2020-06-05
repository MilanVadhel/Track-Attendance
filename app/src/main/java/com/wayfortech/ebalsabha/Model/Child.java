package com.wayfortech.ebalsabha.Model;

import android.content.ContentValues;

import com.wayfortech.ebalsabha.Database.TableCreator;

import java.io.Serializable;

public class Child implements Serializable
{
    private String csurname,cname,cfathername,cmothername,cdob,ccontact,cwhatsapp,chomeno,cpartno,csocietyname;
    private int cid;

    public Child() {
    }

    public Child(String csurname, String cname,
                 String cfathername, String cmothername,
                 String cdob, String ccontact,
                 String cwhatsapp, String chomeno,
                 String cparthno, String csocietyname
                 ) {
        this.csurname = csurname;
        this.cname = cname;
        this.cfathername = cfathername;
        this.cmothername = cmothername;
        this.cdob = cdob;
        this.ccontact = ccontact;
        this.cwhatsapp = cwhatsapp;
        this.chomeno = chomeno;
        this.cpartno = cparthno;
        this.csocietyname = csocietyname;
    }

    public Child(String csurname, String cname,
                 String cfathername, String cmothername,
                 String cdob, String ccontact, String cwhatsapp,
                 String chomeno, String cpartno, String csocietyname, int cid) {
        this.csurname = csurname;
        this.cname = cname;
        this.cfathername = cfathername;
        this.cmothername = cmothername;
        this.cdob = cdob;
        this.ccontact = ccontact;
        this.cwhatsapp = cwhatsapp;
        this.chomeno = chomeno;
        this.cpartno = cpartno;
        this.csocietyname = csocietyname;
        this.cid = cid;
    }

    public String getCsurname() {
        return csurname;
    }

    public void setCsurname(String csurname) {
        this.csurname = csurname;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCfathername() {
        return cfathername;
    }

    public void setCfathername(String cfathername) {
        this.cfathername = cfathername;
    }

    public String getCmothername() {
        return cmothername;
    }

    public void setCmothername(String cmothername) {
        this.cmothername = cmothername;
    }

    public String getCdob() {
        return cdob;
    }

    public void setCdob(String cdob) {
        this.cdob = cdob;
    }

    public String getCcontact() {
        return ccontact;
    }

    public void setCcontact(String ccontact) {
        this.ccontact = ccontact;
    }

    public String getCwhatsapp() {
        return cwhatsapp;
    }

    public void setCwhatsapp(String cwhatsapp) {
        this.cwhatsapp = cwhatsapp;
    }

    public String getChomeno() {
        return chomeno;
    }

    public void setChomeno(String chomeno) {
        this.chomeno = chomeno;
    }

    public String getCpartno() {
        return cpartno;
    }

    public void setCpartno(String cparthno) {
        this.cpartno = cparthno;
    }

    public String getCsocietyname() {
        return csocietyname;
    }

    public void setCsocietyname(String csocietyname) {
        this.csocietyname = csocietyname;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    //create single object for adding child info in table
    public ContentValues getChildValues()
    {
        ContentValues childValues=new ContentValues(10);
        childValues.put(TableCreator.COLUMN_CSURNAME,this.csurname);
        childValues.put(TableCreator.COLUMN_CNAME,this.cname);
        childValues.put(TableCreator.COLUMN_CFATHERNAME,this.cfathername);
        childValues.put(TableCreator.COLUMN_CMOTHERNAME,this.cmothername);
        childValues.put(TableCreator.COLUMN_CDOB,this.cdob);
        childValues.put(TableCreator.COLUMN_CCONTACT,this.ccontact);
        childValues.put(TableCreator.COLUMN_CWHATSAPP,this.cwhatsapp);
        childValues.put(TableCreator.COLUMN_CHOMENO,this.chomeno);
        childValues.put(TableCreator.COLUMN_CPARTNO,this.cpartno);
        childValues.put(TableCreator.COLUMN_CSOCIETYNAME,this.csocietyname);
        return childValues;
    }
    @Override
    public String toString() {
        return "Child{" +
                "csurname='" + csurname + '\'' +
                ", cname='" + cname + '\'' +
                ", cfathername='" + cfathername + '\'' +
                ", cmothername='" + cmothername + '\'' +
                ", cdob='" + cdob + '\'' +
                ", ccontact='" + ccontact + '\'' +
                ", cwhatsapp='" + cwhatsapp + '\'' +
                ", chomeno='" + chomeno + '\'' +
                ", cparthno='" + cpartno + '\'' +
                ", csocietyname='" + csocietyname + '\'' +
                '}';
    }
}
