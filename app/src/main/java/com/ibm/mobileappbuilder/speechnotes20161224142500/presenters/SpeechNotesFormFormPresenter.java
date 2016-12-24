
package com.ibm.mobileappbuilder.speechnotes20161224142500.presenters;

import com.ibm.mobileappbuilder.speechnotes20161224142500.R;
import com.ibm.mobileappbuilder.speechnotes20161224142500.ds.DatanotesDSSchemaItem;

import java.util.List;

import ibmmobileappbuilder.ds.CrudDatasource;
import ibmmobileappbuilder.ds.Datasource;
import ibmmobileappbuilder.mvp.presenter.BaseFormPresenter;
import ibmmobileappbuilder.mvp.view.FormView;

public class SpeechNotesFormFormPresenter extends BaseFormPresenter<DatanotesDSSchemaItem> {

    private final CrudDatasource<DatanotesDSSchemaItem> datasource;

    public SpeechNotesFormFormPresenter(CrudDatasource<DatanotesDSSchemaItem> datasource, FormView<DatanotesDSSchemaItem> view){
        super(view);
        this.datasource = datasource;
    }

    @Override
    public void deleteItem(DatanotesDSSchemaItem item) {
        datasource.deleteItem(item, new OnItemDeletedListener());
    }

    @Override
    public void save(DatanotesDSSchemaItem item) {
        // validate
        if (validate(item)){
            datasource.updateItem(item, new OnItemUpdatedListener());
        } else {
            view.showMessage(R.string.correct_errors, false);
        }
    }

    @Override
    public void create(DatanotesDSSchemaItem item) {
        // validate
        if (validate(item)){
            datasource.create(item, new OnItemCreatedListener());
        } else {
            view.showMessage(R.string.correct_errors, false);
        }
    }

    private class OnItemDeletedListener extends ShowingErrorOnFailureListener {
        @Override
        public void onSuccess(DatanotesDSSchemaItem  item) {
                        view.showMessage(R.string.item_deleted, true);
            view.close(true);
        }
    }

    private class OnItemUpdatedListener extends ShowingErrorOnFailureListener {
        @Override
        public void onSuccess(DatanotesDSSchemaItem item) {
                        view.setItem(item);
            view.showMessage(R.string.item_updated, true);
            view.close(true);
        }
    }

    private class OnItemCreatedListener extends ShowingErrorOnFailureListener {
        @Override
        public void onSuccess(DatanotesDSSchemaItem item) {
                        view.setItem(item);
            view.showMessage(R.string.item_created, true);
            view.close(true);
        }
    }

    private abstract class ShowingErrorOnFailureListener implements Datasource.Listener<DatanotesDSSchemaItem > {
        @Override
        public void onFailure(Exception e) {
            view.showMessage(R.string.error_data_generic, true);
        }
    }

}
