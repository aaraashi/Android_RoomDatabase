package com.example.hyongdonjeong_juheekim_comp304lab4;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Test")
public class Test {
    @PrimaryKey(autoGenerate = true)
    private int testId;
    private int patientId;
    private String nurseId;
    private int BPL;
    private int BPH;
    private double temperature;
    private double weight;
    private double  height;

    public Test(int testId, int patientId, String nurseId, int BPL, int BPH, double temperature, double weight, double height, double ESL, double ESR) {
        this.testId = testId;
        this.patientId = patientId;
        this.nurseId = nurseId;
        this.BPL = BPL;
        this.BPH = BPH;
        this.temperature = temperature;
        this.weight = weight;
        this.height = height;
    }

    public Test() {

    }

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getNurseId() {
        return nurseId;
    }

    public void setNurseId(String nurseId) {
        this.nurseId = nurseId;
    }

    public int getBPL() {
        return BPL;
    }

    public void setBPL(int BPL) {
        this.BPL = BPL;
    }

    public int getBPH() {
        return BPH;
    }

    public void setBPH(int BPH) {
        this.BPH = BPH;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

}
