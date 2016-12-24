package ibmmobileappbuilder.ds.filter;

import android.os.Parcel;
import android.os.Parcelable;

public class StringFilter implements ContainsFilter, Parcelable {

    private final String value;
    private final String field;

    public StringFilter(String field, String value){
        this.field = field;
        this.value = value;
    }

    @Override
    public String getField() {
        return field;
    }

    @Override
    public String getQueryString() {
        if(value == null) return null;

        return "\"" + field + "\":{\"$regex\":\"" + value + "\",\"$options\":\"i\"}";
    }

    @Override
    public boolean applyFilter(String fieldValue) {
        return (value == null) ||
                (fieldValue != null && fieldValue.toLowerCase().contains(value.toLowerCase()));
    }

    @Override
    public String getValue() {
        return value;
    }

    protected StringFilter(Parcel in) {
        value = in.readString();
        field = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(value);
        dest.writeString(field);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<StringFilter> CREATOR = new Parcelable.Creator<StringFilter>() {
        @Override
        public StringFilter createFromParcel(Parcel in) {
            return new StringFilter(in);
        }

        @Override
        public StringFilter[] newArray(int size) {
            return new StringFilter[size];
        }
    };
}
