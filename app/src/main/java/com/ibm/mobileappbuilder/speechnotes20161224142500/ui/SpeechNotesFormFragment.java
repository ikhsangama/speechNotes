
package com.ibm.mobileappbuilder.speechnotes20161224142500.ui;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import com.ibm.mobileappbuilder.speechnotes20161224142500.presenters.SpeechNotesFormFormPresenter;
import com.ibm.mobileappbuilder.speechnotes20161224142500.R;
import ibmmobileappbuilder.ds.CloudantDatasource;
import ibmmobileappbuilder.ui.FormFragment;
import ibmmobileappbuilder.views.TextWatcherAdapter;
import ibmmobileappbuilder.ds.Datasource;
import ibmmobileappbuilder.ds.CrudDatasource;
import ibmmobileappbuilder.ds.SearchOptions;
import ibmmobileappbuilder.ds.filter.Filter;
import java.util.Arrays;
import com.ibm.mobileappbuilder.speechnotes20161224142500.ds.DatanotesDSSchemaItem;
import ibmmobileappbuilder.ds.CloudantDatasource;
import ibmmobileappbuilder.cloudant.factory.CloudantDatastoresFactory;
import java.net.URI;

public class SpeechNotesFormFragment extends FormFragment<DatanotesDSSchemaItem> {

    private CrudDatasource<DatanotesDSSchemaItem> datasource;

    public static SpeechNotesFormFragment newInstance(Bundle args){
        SpeechNotesFormFragment fr = new SpeechNotesFormFragment();
        fr.setArguments(args);

        return fr;
    }

    public SpeechNotesFormFragment(){
        super();
    }

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);

        // the presenter for this view
        setPresenter(new SpeechNotesFormFormPresenter(
                (CrudDatasource) getDatasource(),
                this));

            }

    @Override
    protected DatanotesDSSchemaItem newItem() {
        return new DatanotesDSSchemaItem();
    }

    @Override
    protected int getLayout() {
        return R.layout.speechnotesform_form;
    }

    @Override
    @SuppressLint("WrongViewCast")
    public void bindView(final DatanotesDSSchemaItem item, View view) {
        
        bindString(R.id.judul, item.judul, new TextWatcherAdapter() {
            @Override
            public void afterTextChanged(Editable s) {
                item.judul = s.toString();
            }
        });
        
        
        bindString(R.id.notes, item.notes, new TextWatcherAdapter() {
            @Override
            public void afterTextChanged(Editable s) {
                item.notes = s.toString();
            }
        });
        
    }

    @Override
    public Datasource<DatanotesDSSchemaItem> getDatasource() {
      if (datasource != null) {
        return datasource;
      }
       datasource = CloudantDatasource.cloudantDatasource(
              CloudantDatastoresFactory.create("data_notes"),
              URI.create("https://d474c95f-a46f-4ee2-8388-ed0e6c2a0462-bluemix:cd451352bbaa74569fc773726e43193b37bde09e1ed48cf4be4d1badd53396ea@d474c95f-a46f-4ee2-8388-ed0e6c2a0462-bluemix.cloudant.com/data_notes/"),
              DatanotesDSSchemaItem.class,
              new SearchOptions(),
              "judul", "notes");
        return datasource;
    }
}
