
package com.ibm.mobileappbuilder.speechnotes20161224142500.ds;

import ibmmobileappbuilder.mvp.model.MutableIdentifiableBean;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class DatanotesDSSchemaItem implements Parcelable, MutableIdentifiableBean {

    private transient String cloudantIdentifiableId;

    @SerializedName("judul") public String judul;
    @SerializedName("notes") public String notes;

    @Override
    public void setIdentifiableId(String id) {
        this.cloudantIdentifiableId = id;
    }

    @Override
    public String getIdentifiableId() {
        return cloudantIdentifiableId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(cloudantIdentifiableId);
        dest.writeString(judul);
        dest.writeString(notes);
    }

    public static final Creator<DatanotesDSSchemaItem> CREATOR = new Creator<DatanotesDSSchemaItem>() {
        @Override
        public DatanotesDSSchemaItem createFromParcel(Parcel in) {
            DatanotesDSSchemaItem item = new DatanotesDSSchemaItem();
            item.cloudantIdentifiableId = in.readString();

            item.judul = in.readString();
            item.notes = in.readString();
            return item;
        }

        @Override
        public DatanotesDSSchemaItem[] newArray(int size) {
            return new DatanotesDSSchemaItem[size];
        }
    };
}

