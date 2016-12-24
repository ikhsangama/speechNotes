package ibmmobileappbuilder.ds.filter;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * A filter that matches a field against a value list
 */
public class StringListFilter implements InFilter<String>, Parcelable {

    private String field;
    private List<String> values;

    public StringListFilter(String field, List<String> values) {
        this.field = field;
        this.values = values;
    }

    @Override
    public String getField() {
        return field;
    }

    @Override
    public String getQueryString() {
        if(values == null) return null;

        return "\"" + field + "\":{\"$in\":[\"" + TextUtils.join("\", \"", values) + "\"]}";
    }

    @Override
    public boolean applyFilter(String fieldValue) {
        return values == null || values.isEmpty() || values.contains(fieldValue);
    }

    @Override
    public List<String> getValues() {
        return values;
    }

    protected StringListFilter(Parcel in) {
        field = in.readString();
        if (in.readByte() == 0x01) {
            values = new ArrayList<String>();
            in.readList(values, String.class.getClassLoader());
        } else {
            values = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(field);
        if (values == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(values);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<StringListFilter> CREATOR = new Parcelable.Creator<StringListFilter>() {
        @Override
        public StringListFilter createFromParcel(Parcel in) {
            return new StringListFilter(in);
        }

        @Override
        public StringListFilter[] newArray(int size) {
            return new StringListFilter[size];
        }
    };
}
