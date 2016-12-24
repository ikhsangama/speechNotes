package ibmmobileappbuilder.ds;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import ibmmobileappbuilder.ds.filter.Filter;

public class SearchOptions implements Parcelable {

    private String searchText;
    //Extract sortOptions class
    private String sortColumn;
    private Comparator sortComparator; // excluded for Parcelable
    private boolean sortAscending;
    private List<Filter> filters = new ArrayList<>();
    private List<Filter> fixedFilters = new ArrayList<>();     // set here filters users won't be able to change

    public SearchOptions() {
    }

    public SearchOptions(String searchText, String sortColumn, Comparator sortComparator,
                         boolean sortAscending) {
        this.searchText = searchText;
        this.sortColumn = sortColumn;
        this.sortComparator = sortComparator;
        this.sortAscending = sortAscending;
    }

    private SearchOptions(Builder builder) {
        this.searchText = builder.searchText;
        this.sortColumn = builder.sortColumn;
        this.sortComparator = builder.sortComparator;
        this.sortAscending = builder.sortAscending;
        this.filters = builder.filters;
        this.fixedFilters = builder.fixedFilters;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public Comparator getSortComparator() {
        return sortComparator;
    }

    public String getSortColumn() {
        return sortColumn;
    }


    public boolean isSortAscending() {
        return sortAscending;
    }

    public void addFilter(Filter filter) {
        if (this.filters == null) {
            this.filters = new ArrayList<>();
        }

        this.filters.add(filter);
    }

    public List<Filter> getFilters() {
        return this.filters;
    }

    public void setFilters(List<Filter> filters) {
        this.filters = filters;
    }

    public List<Filter> getFixedFilters() {
        return fixedFilters;
    }

    public static class Builder {
        private String searchText;
        private String sortColumn;
        private Comparator sortComparator;
        private boolean sortAscending;
        private List<Filter> filters = new ArrayList<>();
        private List<Filter> fixedFilters = new ArrayList<>();

        public static Builder searchOptions() {
            return new Builder();
        }

        public Builder withSearchText(String searchText) {
            this.searchText = searchText;
            return this;
        }

        public Builder withSortColumn(String sortColumn) {
            this.sortColumn = sortColumn;
            return this;
        }

        public Builder withSortComparator(Comparator sortComparator) {
            this.sortComparator = sortComparator;
            return this;
        }

        public Builder withSortAscending(boolean sortAscending) {
            this.sortAscending = sortAscending;
            return this;
        }

        public Builder withFilters(List<Filter> filters) {
            this.filters.clear();
            this.filters.addAll(filters);
            return this;
        }

        public Builder withFixedFilters(List<Filter> fixedFilters) {
            this.fixedFilters.clear();
            this.fixedFilters.addAll(fixedFilters);
            return this;
        }

        public SearchOptions build() {
            return new SearchOptions(this);
        }
    }

    protected SearchOptions(Parcel in) {
        searchText = in.readString();
        sortColumn = in.readString();
        sortAscending = in.readByte() != 0x00;

        if (in.readByte() == 0x01) {
            filters = new ArrayList<Filter>();
            in.readList(filters, Filter.class.getClassLoader());
        } else {
            filters = null;
        }
        if (in.readByte() == 0x01) {
            fixedFilters = new ArrayList<Filter>();
            in.readList(fixedFilters, Filter.class.getClassLoader());
        } else {
            fixedFilters = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(searchText);
        dest.writeString(sortColumn);
        dest.writeByte((byte) (sortAscending ? 0x01 : 0x00));

        if (filters == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(filters);
        }
        if (fixedFilters == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(fixedFilters);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<SearchOptions> CREATOR = new Parcelable.Creator<SearchOptions>() {
        @Override
        public SearchOptions createFromParcel(Parcel in) {
            return new SearchOptions(in);
        }

        @Override
        public SearchOptions[] newArray(int size) {
            return new SearchOptions[size];
        }
    };
}