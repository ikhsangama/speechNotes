package ibmmobileappbuilder.ds.filter;

import android.os.Parcel;
import android.os.Parcelable;

public class StringIdentityFilter implements IdentityFilter<String>, Parcelable {

    private String field;
    private String value;

    public StringIdentityFilter(String field, String value){
        this.field = field;
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String getField() {
        return field;
    }

    @Override
    public String getQueryString() {
        return "\"" + field + "\":{\"$eq\":" + value + "}";
    }

    @Override
    public boolean applyFilter(String fieldValue) {
        return (value == null) || (fieldValue != null && fieldValue.equalsIgnoreCase(value));
    }

    protected StringIdentityFilter(Parcel in) {
        field = in.readString();
        value = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(field);
        dest.writeString(value);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<StringIdentityFilter> CREATOR = new Parcelable.Creator<StringIdentityFilter>() {
        @Override
        public StringIdentityFilter createFromParcel(Parcel in) {
            return new StringIdentityFilter(in);
        }

        @Override
        public StringIdentityFilter[] newArray(int size) {
            return new StringIdentityFilter[size];
        }
    };
}
