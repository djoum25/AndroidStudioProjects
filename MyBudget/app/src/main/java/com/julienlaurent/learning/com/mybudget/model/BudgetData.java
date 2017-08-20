package com.julienlaurent.learning.com.mybudget.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by djoum on 8/12/17.
 */

public class BudgetData implements Parcelable {
    private ArrayList<Budget> bills;
    private double salary1;
    private double salary2;
    private double salary3;
    private String budgetStartDate;
    private String budgetEndDate;

    public BudgetData() {
        bills = new ArrayList<>();
        bills.add(new Budget("Capital1(visas)"));
        bills.add(new Budget("Capital1(Master)"));
        bills.add(new Budget("Car Insurance"));
        bills.add(new Budget("City (costco)"));
        bills.add(new Budget("Water bill"));
        bills.add(new Budget("FPL"));
        bills.add(new Budget("Comcast"));
        bills.add(new Budget("Walmart"));
        bills.add(new Budget("Amex"));
        bills.add(new Budget("Haiti"));
    }

    public ArrayList<Budget> getBills() {
        return bills;
    }

    public double getSalary1() {
        return salary1;
    }

    public void setSalary1(double salary1) {
        this.salary1 = salary1;
    }

    public double getSalary2() {
        return salary2;
    }

    public void setSalary2(double salary2) {
        this.salary2 = salary2;
    }

    public double getSalary3() {
        return salary3;
    }

    public void setSalary3(double salary3) {
        this.salary3 = salary3;
    }

    public String getBudgetStartDate() {
        return budgetStartDate;
    }

    public void setBudgetStartDate(String budgetStartDate) {
        this.budgetStartDate = budgetStartDate;
    }

    public String getBudgetEndDate() {
        return budgetEndDate;
    }

    public void setBudgetEndDate(String budgetEndDate) {
        this.budgetEndDate = budgetEndDate;
    }


    public double totalIncome(){
      return getSalary1() + getSalary2() + getSalary3();
    }

    public double budgetTotal(){
       double total = 0;
        for (Budget bill: bills) {
            total += bill.getBillAmount();
        }
        return total;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.bills);
        dest.writeDouble(this.salary1);
        dest.writeDouble(this.salary2);
        dest.writeDouble(this.salary3);
        dest.writeString(this.budgetStartDate);
        dest.writeString(this.budgetEndDate);
    }

    protected BudgetData(Parcel in) {
        this.bills = in.createTypedArrayList(Budget.CREATOR);
        this.salary1 = in.readDouble();
        this.salary2 = in.readDouble();
        this.salary3 = in.readDouble();
        this.budgetStartDate = in.readString();
        this.budgetEndDate = in.readString();
    }

    public static final Parcelable.Creator<BudgetData> CREATOR = new Parcelable.Creator<BudgetData>() {
        @Override
        public BudgetData createFromParcel(Parcel source) {
            return new BudgetData(source);
        }

        @Override
        public BudgetData[] newArray(int size) {
            return new BudgetData[size];
        }
    };
}
