package com.example.projectqrcode;

public class MyListData {

    String  regnum,rcowner,vehicle_type,chassisnum,id;

    public MyListData(String regnum, String rcowner, String vehicle_type, String chassisnum, String id) {
        this.regnum = regnum;
        this.rcowner = rcowner;
        this.vehicle_type = vehicle_type;
        this.chassisnum = chassisnum;
        this.id = id;
    }

    public String getRegnum() {
        return regnum;
    }

    public void setRegnum(String regnum) {
        this.regnum = regnum;
    }

    public String getRcowner() {
        return rcowner;
    }

    public void setRcowner(String rcowner) {
        this.rcowner = rcowner;
    }

    public String getVehicle_type() {
        return vehicle_type;
    }

    public void setVehicle_type(String vehicle_type) {
        this.vehicle_type = vehicle_type;
    }

    public String getChassisnum() {
        return chassisnum;
    }

    public void setChassisnum(String chassisnum) {
        this.chassisnum = chassisnum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

