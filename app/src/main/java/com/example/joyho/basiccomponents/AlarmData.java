package com.example.joyho.basiccomponents;


public class AlarmData {
    private long id;
    private String time;
    private String index;
    private String value;

    public AlarmData(){
    }

    public AlarmData(String time, String index, String value){
        this.time = time;
        this.index = index;
        this.value = value;
    }

    public String getAlarmIndex(){return index;}
    public String getAlarmValue(){return value;}
    public String getAlarmTime(){return time;}
}
