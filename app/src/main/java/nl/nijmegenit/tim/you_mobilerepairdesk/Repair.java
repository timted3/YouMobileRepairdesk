package nl.nijmegenit.tim.you_mobilerepairdesk;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by timtr on 22-2-2018.
 */

public class Repair implements Parcelable {

    private String repairdescription;
    private int repaircost;


    public Repair(String repairdescription, int repaircost) {
        this.repairdescription = repairdescription;
        this.repaircost = repaircost;
    }


    protected Repair(Parcel in) {
        repairdescription = in.readString();
        repaircost = in.readInt();
    }

    public static final Creator<Repair> CREATOR = new Creator<Repair>() {
        @Override
        public Repair createFromParcel(Parcel in) {
            return new Repair(in);
        }

        @Override
        public Repair[] newArray(int size) {
            return new Repair[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(repairdescription);
        dest.writeInt(repaircost);
    }

    public String getRepairdescription() {
        return repairdescription;
    }

    public void setRepairdescription(String repairdescription) {
        this.repairdescription = repairdescription;
    }

    public int getRepaircost() {
        return repaircost;
    }

    public void setRepaircost(int repaircost) {
        this.repaircost = repaircost;
    }

    public static Creator<Repair> getCREATOR() {
        return CREATOR;
    }
}

