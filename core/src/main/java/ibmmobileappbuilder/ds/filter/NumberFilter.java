package ibmmobileappbuilder.ds.filter;

import android.os.Parcel;
import android.os.Parcelable;

public class NumberFilter implements IdentityFilter<Number>, Parcelable {

    private final Number value;
    private final String field;

    public NumberFilter(String field, Number value){
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

        return "\"" + field + "\":{\"$eq\":" + value + "}";
    }

    @Override
    public boolean applyFilter(Number fieldValue) {
        return (value == null) || value.equals(fieldValue);
    }

    @Override
    public Number getValue() {
        return value;
    }

    protected NumberFilter(Parcel in) {
        value = (Number) in.readValue(Number.class.getClassLoader());
        field = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(value);
        dest.writeString(field);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<NumberFilter> CREATOR = new Parcelable.Creator<NumberFilter>() {
        @Override
        public NumberFilter createFromParcel(Parcel in) {
            return new NumberFilter(in);
        }

        @Override
        public NumberFilter[] newArray(int size) {
            return new NumberFilter[size];
        }
    };
}
