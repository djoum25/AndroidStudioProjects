package com.julienlaurent.learning.com.mybudget.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Budget implements Parcelable{
    private String itemName;
    private double billAmount;
    private String dueDate;
    private String billStatus;
    private String billNotes;

    public Budget(String itemName, String dueDate) {
        this.itemName = itemName;
        this.dueDate = dueDate;
    }

    public Budget(String itemName) {
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(double billAmount) {
        this.billAmount = billAmount;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getBillStatus() {
        return billStatus;
    }

    public void setBillStatus(String billStatus) {
        this.billStatus = billStatus;
    }

    public String getBillNotes() {
        return billNotes;
    }

    public void setBillNotes(String billNotes) {
        this.billNotes = billNotes;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.itemName);
        dest.writeDouble(this.billAmount);
        dest.writeString(this.dueDate);
        dest.writeString(this.billStatus);
        dest.writeString(this.billNotes);
    }

    protected Budget(Parcel in) {
        this.itemName = in.readString();
        this.billAmount = in.readDouble();
        this.dueDate = in.readString();
        this.billStatus = in.readString();
        this.billNotes = in.readString();
    }

    public static final Creator<Budget> CREATOR = new Creator<Budget>() {
        @Override
        public Budget createFromParcel(Parcel source) {
            return new Budget(source);
        }

        @Override
        public Budget[] newArray(int size) {
            return new Budget[size];
        }
    };
}
