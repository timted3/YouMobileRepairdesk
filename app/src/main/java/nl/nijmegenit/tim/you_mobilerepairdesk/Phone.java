package nl.nijmegenit.tim.you_mobilerepairdesk;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by timtr on 9-3-2018.
 */

public class Phone implements Parcelable {

        private int imei;
        private String serialnumber;
        private String brand;
        private String model;
        private boolean cameraWorks;
        private boolean buttonWorks;
        private boolean microphoneWorks;
        private boolean networkWorks;
        private boolean powerbuttonWorks;
        private boolean speakersWorks;
        private boolean volumebuttonWorks;
        private boolean chargeconnectionWorks;

    protected Phone(Parcel in) {
        imei = in.readInt();
        serialnumber = in.readString();
        brand = in.readString();
        model = in.readString();
        cameraWorks = in.readByte() != 0;
        buttonWorks = in.readByte() != 0;
        microphoneWorks = in.readByte() != 0;
        networkWorks = in.readByte() != 0;
        powerbuttonWorks = in.readByte() != 0;
        speakersWorks = in.readByte() != 0;
        volumebuttonWorks = in.readByte() != 0;
        chargeconnectionWorks = in.readByte() != 0;
    }

        public static final Parcelable.Creator<Phone> CREATOR = new Parcelable.Creator<Phone>() {
            @Override
            public Phone createFromParcel(Parcel in) {
                return new Phone(in);
            }

            @Override
            public Phone[] newArray(int size) {
                return new Phone[size];
            }
        };

    public Phone(int imei, String serialnumber, String brand, String model, boolean cameraWorks, boolean buttonWorks, boolean microphoneWorks, boolean networkWorks, boolean powerbuttonWorks, boolean speakersWorks, boolean volumebuttonWorks, boolean chargeconnectionWorks) {
        this.imei = imei;
        this.serialnumber = serialnumber;
        this.brand = brand;
        this.model = model;
        this.cameraWorks = cameraWorks;
        this.buttonWorks = buttonWorks;
        this.microphoneWorks = microphoneWorks;
        this.networkWorks = networkWorks;
        this.powerbuttonWorks = powerbuttonWorks;
        this.speakersWorks = speakersWorks;
        this.volumebuttonWorks = volumebuttonWorks;
        this.chargeconnectionWorks = chargeconnectionWorks;
    }


    public Phone() {
    }

    public Phone(int imei, String serialnumber, String brand, String model) {
        this.imei = imei;
        this.serialnumber = serialnumber;
        this.brand = brand;
        this.model = model;
    }

        @Override
        public int describeContents() {
        return 0;
    }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(imei);
        parcel.writeString(serialnumber);
        parcel.writeString(brand);
        parcel.writeString(model);
        parcel.writeByte((byte) (cameraWorks ? 1 : 0));
        parcel.writeByte((byte) (buttonWorks ? 1 : 0));
        parcel.writeByte((byte) (microphoneWorks ? 1 : 0));
        parcel.writeByte((byte) (networkWorks ? 1 : 0));
        parcel.writeByte((byte) (powerbuttonWorks ? 1 : 0));
        parcel.writeByte((byte) (speakersWorks ? 1 : 0));
        parcel.writeByte((byte) (volumebuttonWorks ? 1 : 0));
        parcel.writeByte((byte) (chargeconnectionWorks ? 1 : 0));

    }

    public int getImei() {
        return imei;
    }

    public void setImei(int imei) {
        this.imei = imei;
    }

    public String getSerialnumber() {
        return serialnumber;
    }

    public void setSerialnumber(String serialnumber) {
        this.serialnumber = serialnumber;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public boolean isCameraWorks() {
        return cameraWorks;
    }

    public void setCameraWorks(boolean cameraWorks) {
        this.cameraWorks = cameraWorks;
    }

    public boolean isButtonWorks() {
        return buttonWorks;
    }

    public void setButtonWorks(boolean buttonWorks) {
        this.buttonWorks = buttonWorks;
    }

    public boolean isMicrophoneWorks() {
        return microphoneWorks;
    }

    public void setMicrophoneWorks(boolean microphoneWorks) {
        this.microphoneWorks = microphoneWorks;
    }

    public boolean isNetworkWorks() {
        return networkWorks;
    }

    public void setNetworkWorks(boolean networkWorks) {
        this.networkWorks = networkWorks;
    }

    public boolean isPowerbuttonWorks() {
        return powerbuttonWorks;
    }

    public void setPowerbuttonWorks(boolean powerbuttonWorks) {
        this.powerbuttonWorks = powerbuttonWorks;
    }

    public boolean isSpeakersWorks() {
        return speakersWorks;
    }

    public void setSpeakersWorks(boolean speakersWorks) {
        this.speakersWorks = speakersWorks;
    }

    public boolean isVolumebuttonWorks() {
        return volumebuttonWorks;
    }

    public void setVolumebuttonWorks(boolean volumebuttonWorks) {
        this.volumebuttonWorks = volumebuttonWorks;
    }

    public boolean isChargeconnectionWorks() {
        return chargeconnectionWorks;
    }

    public void setChargeconnectionWorks(boolean chargeconnectionWorks) {
        this.chargeconnectionWorks = chargeconnectionWorks;
    }


    @Override
    public String toString() {
        return "Phone{" +
                "imei=" + imei +
                ", serialnumber='" + serialnumber + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", cameraWorks=" + cameraWorks +
                ", buttonWorks=" + buttonWorks +
                ", microphoneWorks=" + microphoneWorks +
                ", networkWorks=" + networkWorks +
                ", powerbuttonWorks=" + powerbuttonWorks +
                ", speakersWorks=" + speakersWorks +
                ", volumebuttonWorks=" + volumebuttonWorks +
                ", chargeconnectionWorks=" + chargeconnectionWorks +
                '}';
    }
}
