package ibmmobileappbuilder.ds.restds;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.Locale;

/**
 * Representation of a GeoJson point:
 * http://geojson.org/geojson-spec.html#point
 * [longitude, latitude]
 */
public class GeoPoint implements Parcelable {
    public static final int LONGITUDE_INDEX = 0;
    public static final int LATITUDE_INDEX = 1;

    @SerializedName("type")
    public String type = "Point";
    @SerializedName("coordinates")
    public double[] coordinates = new double[]{0, 0};

    public GeoPoint(){}

    public GeoPoint(double lon, double lat){
        this.coordinates[LONGITUDE_INDEX] = lon;
        this.coordinates[LATITUDE_INDEX] = lat;
    }

    public GeoPoint(double[] coords){
        this.coordinates = coords;
    }

    @Override
    public String toString() {
        return String.format(Locale.US,
                "%.8f, %.8f",
                coordinates[LATITUDE_INDEX],
                coordinates[LONGITUDE_INDEX]);
    }

    protected GeoPoint(Parcel in) {
        type = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(type);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<GeoPoint> CREATOR = new Parcelable.Creator<GeoPoint>() {
        @Override
        public GeoPoint createFromParcel(Parcel in) {
            return new GeoPoint(in);
        }

        @Override
        public GeoPoint[] newArray(int size) {
            return new GeoPoint[size];
        }
    };
}