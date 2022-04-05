package model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;

public class Model_Showlist_Bus implements Parcelable {

   public String id,origin,distination,date,capacity,company,type,price,time,origin_terminal,destination_terminal,distance,chairs;
    public String getId() {
        return id;
    }

    public String getOrigin_terminal() {
        return origin_terminal;
    }

    public void setOrigin_terminal(String origin_terminal) {
        this.origin_terminal = origin_terminal;
    }

    public String getChairs() {
        return chairs;
    }

    public void setChairs(String chairs) {
        this.chairs = chairs;
    }

    public static Creator<Model_Showlist_Bus> getCREATOR() {
        return CREATOR;
    }

    public String getDestination_terminal() {
        return destination_terminal;
    }

    public void setDestination_terminal(String destination_terminal) {
        this.destination_terminal = destination_terminal;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }



    public void setId(String id) {
        this.id = id;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDistination() {
        return distination;
    }

    public void setDistination(String distination) {
        this.distination = distination;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Model_Showlist_Bus() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.origin);
        dest.writeString(this.distination);
        dest.writeString(this.date);
        dest.writeString(this.capacity);
        dest.writeString(this.company);
        dest.writeString(this.type);
        dest.writeString(this.price);
        dest.writeString(this.time);
        dest.writeString(this.origin_terminal);
        dest.writeString(this.destination_terminal);
        dest.writeString(this.distance);
        dest.writeString(this.chairs);
    }

    protected Model_Showlist_Bus(Parcel in) {
        this.id = in.readString();
        this.origin = in.readString();
        this.distination = in.readString();
        this.date = in.readString();
        this.capacity = in.readString();
        this.company = in.readString();
        this.type = in.readString();
        this.price = in.readString();
        this.time = in.readString();
        this.origin_terminal = in.readString();
        this.destination_terminal = in.readString();
        this.distance = in.readString();
        this.chairs = in.readString();
    }

    public static final Creator<Model_Showlist_Bus> CREATOR = new Creator<Model_Showlist_Bus>() {
        @Override
        public Model_Showlist_Bus createFromParcel(Parcel source) {
            return new Model_Showlist_Bus(source);
        }

        @Override
        public Model_Showlist_Bus[] newArray(int size) {
            return new Model_Showlist_Bus[size];
        }
    };
}
