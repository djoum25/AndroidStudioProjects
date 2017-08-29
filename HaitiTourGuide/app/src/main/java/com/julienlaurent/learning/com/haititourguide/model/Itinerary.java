package com.julienlaurent.learning.com.haititourguide.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by djoum on 8/28/17.
 */

public class Itinerary implements Parcelable {
   private String startDate;
    private String endDate;
    private String accommodationName;
    private String accomodationLocation;

    public Itinerary(String startDate, String endDate, String accommodationName, String accomodationLocation) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.accommodationName = accommodationName;
        this.accomodationLocation = accomodationLocation;
    }

    public Itinerary(String startDate, String endDate, String accommodationName) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.accommodationName = accommodationName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getAccommodationName() {
        return accommodationName;
    }

    public void setAccommodationName(String accommodationName) {
        this.accommodationName = accommodationName;
    }

    public String getAccomodationLocation() {
        return accomodationLocation;
    }

    public void setAccomodationLocation(String accomodationLocation) {
        this.accomodationLocation = accomodationLocation;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.startDate);
        dest.writeString(this.endDate);
        dest.writeString(this.accommodationName);
        dest.writeString(this.accomodationLocation);
    }

    protected Itinerary(Parcel in) {
        this.startDate = in.readString();
        this.endDate = in.readString();
        this.accommodationName = in.readString();
        this.accomodationLocation = in.readString();
    }

    public static final Parcelable.Creator<Itinerary> CREATOR = new Parcelable.Creator<Itinerary>() {
        @Override
        public Itinerary createFromParcel(Parcel source) {
            return new Itinerary(source);
        }

        @Override
        public Itinerary[] newArray(int size) {
            return new Itinerary[size];
        }
    };
}
