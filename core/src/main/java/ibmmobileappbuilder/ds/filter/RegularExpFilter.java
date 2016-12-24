package ibmmobileappbuilder.ds.filter;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.regex.Pattern;

/**
 * A filter that matches a field against a regular expression
 */
public class RegularExpFilter implements Filter<String>, Parcelable {

    private String field;
    private String value;

    public RegularExpFilter(String field, String value) {
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

        StringBuilder sb = new StringBuilder();
        //searches.add("{\"" + col + "\":{\"$regex\":\"" + st + "\",\"$options\":\"i\"}}");
        sb.append("\"")
                .append(field)
                .append("\":{\"$regex\":\"")
                .append(value)
                .append("\",\"$options\":\"i\"}");

        return sb.toString();
    }

    @Override
    public boolean applyFilter(String fieldValue) {
        Pattern p = Pattern.compile(value);
        return p.matcher(fieldValue).matches();
    }

    protected RegularExpFilter(Parcel in) {
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
    public static final Parcelable.Creator<RegularExpFilter> CREATOR = new Parcelable.Creator<RegularExpFilter>() {
        @Override
        public RegularExpFilter createFromParcel(Parcel in) {
            return new RegularExpFilter(in);
        }

        @Override
        public RegularExpFilter[] newArray(int size) {
            return new RegularExpFilter[size];
        }
    };
}
