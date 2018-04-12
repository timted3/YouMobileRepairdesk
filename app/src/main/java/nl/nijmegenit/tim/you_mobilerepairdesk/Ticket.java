package nl.nijmegenit.tim.you_mobilerepairdesk;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by timtr on 9-3-2018.
 */

public class Ticket implements Parcelable {

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public ArrayList<Repair> getRepairs() {
        return repairs;
    }

    public void setRepairs(ArrayList<Repair> repairs) {
        this.repairs = repairs;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(int ticket_id) {
        this.ticket_id = ticket_id;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    private Customer customer;
    private Phone phone;
    private ArrayList<Repair> repairs;
    private String status;
    private int ticket_id;
    private int employee_id;


    public Ticket(Customer customer, Phone phone, ArrayList<Repair> repairs, String status, int ticket_id, int employee_id) {
        this.customer = customer;
        this.phone = phone;
        this.repairs = repairs;
        this.status = status;
        this.ticket_id = ticket_id;
        this.employee_id = employee_id;
    }

    protected Ticket(Parcel in) {
        customer = in.readParcelable(Customer.class.getClassLoader());
        phone = in.readParcelable(Phone.class.getClassLoader());
        repairs = in.createTypedArrayList(Repair.CREATOR);
        status = in.readString();
        ticket_id = in.readInt();
        employee_id = in.readInt();
    }

    public static final Creator<Ticket> CREATOR = new Creator<Ticket>() {
        @Override
        public Ticket createFromParcel(Parcel in) {
            return new Ticket(in);
        }

        @Override
        public Ticket[] newArray(int size) {
            return new Ticket[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(customer, flags);
        dest.writeParcelable(phone, flags);
        dest.writeTypedList(repairs);
        dest.writeString(status);
        dest.writeInt(ticket_id);
        dest.writeInt(employee_id);
    }
}

