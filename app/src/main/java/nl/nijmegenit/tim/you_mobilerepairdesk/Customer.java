package nl.nijmegenit.tim.you_mobilerepairdesk;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by timtr on 9-3-2018.
 */

public class Customer implements Parcelable {

    private String Name;
    private String Postcode;
    private String Housenumber;
    private int Cellphone;
    private String Email;
    //Telefoon toevoegen?? DB?

    public Customer(String name, String postcode, String housenumber, int cellphone, String email) {
        Name = name;
        Postcode = postcode;
        Housenumber = housenumber;
        Cellphone = cellphone;
        Email = email;
    }

    protected Customer(Parcel in) {
        Name = in.readString();
        Postcode = in.readString();
        Housenumber = in.readString();
        Cellphone = in.readInt();
        Email = in.readString();
    }

    public static final Creator<Customer> CREATOR = new Creator<Customer>() {
        @Override
        public Customer createFromParcel(Parcel in) {
            return new Customer(in);
        }

        @Override
        public Customer[] newArray(int size) {
            return new Customer[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Name);
        dest.writeString(Postcode);
        dest.writeString(Housenumber);
        dest.writeInt(Cellphone);
        dest.writeString(Email);
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPostcode() {
        return Postcode;
    }

    public void setPostcode(String postcode) {
        Postcode = postcode;
    }

    public String getHousenumber() {
        return Housenumber;
    }

    public void setHousenumber(String housenumber) {
        Housenumber = housenumber;
    }

    public int getCellphone() {
        return Cellphone;
    }

    public void setCellphone(int cellphone) {
        Cellphone = cellphone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public static Creator<Customer> getCREATOR() {
        return CREATOR;
    }
}
