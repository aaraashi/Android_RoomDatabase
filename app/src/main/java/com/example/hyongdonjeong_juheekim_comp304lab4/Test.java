package com.example.hyongdonjeong_juheekim_comp304lab4;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Test {
    @PrimaryKey(autoGenerate = true)
    private int testId;
    private int patientId;
    private int nurseId;
    private int BPL;
    private int BPH;
    private double temperature;
    private double weight;
    private double  height;
    // eye sight left and right
    private double ESL;
    private double ESR;

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

    public int getNurseId() {
        return nurseId;
    }

    public void setNurseId(int nurseId) {
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

    public double getESL() {
        return ESL;
    }

    public void setESL(double ESL) {
        this.ESL = ESL;
    }

    public double getESR() {
        return ESR;
    }

    public void setESR(double ESR) {
        this.ESR = ESR;
    }
}
