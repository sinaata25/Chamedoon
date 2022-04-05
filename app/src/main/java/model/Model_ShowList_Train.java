package model;

import android.os.Parcel;
import android.os.Parcelable;

public class Model_ShowList_Train implements Parcelable {

    String id;
    String orogin;
    String distanition;
    String start_time;
    String end_time;
    String date;
    String type;
    String capacity;
    String coupe_capacity;
    String company;
    String price;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrogin() {
        return orogin;
    }

    public void setOrogin(String orogin) {
        this.orogin = orogin;
    }

    public String getDistanition() {
        return distanition;
    }

    public void setDistanition(String distanition) {
        this.distanition = distanition;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getCoupe_capacity() {
        return coupe_capacity;
    }

    public void setCoupe_capacity(String coupe_capacity) {
        this.coupe_capacity = coupe_capacity;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.orogin);
        dest.writeString(this.distanition);
        dest.writeString(this.start_time);
        dest.writeString(this.end_time);
        dest.writeString(this.date);
        dest.writeString(this.type);
        dest.writeString(this.capacity);
        dest.writeString(this.coupe_capacity);
        dest.writeString(this.company);
        dest.writeString(this.price);
    }

    public Model_ShowList_Train() {
    }

    protected Model_ShowList_Train(Parcel in) {
        this.id = in.readString();
        this.orogin = in.readString();
        this.distanition = in.readString();
        this.start_time = in.readString();
        this.end_time = in.readString();
        this.date = in.readString();
        this.type = in.readString();
        this.capacity = in.readString();
        this.coupe_capacity = in.readString();
        this.company = in.readString();
        this.price = in.readString();
    }

    public static final Parcelable.Creator<Model_ShowList_Train> CREATOR = new Parcelable.Creator<Model_ShowList_Train>() {
        @Override
        public Model_ShowList_Train createFromParcel(Parcel source) {
            return new Model_ShowList_Train(source);
        }

        @Override
        public Model_ShowList_Train[] newArray(int size) {
            return new Model_ShowList_Train[size];
        }
    };
}
