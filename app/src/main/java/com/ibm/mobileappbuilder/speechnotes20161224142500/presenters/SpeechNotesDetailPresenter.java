
package com.ibm.mobileappbuilder.speechnotes20161224142500.presenters;

import com.ibm.mobileappbuilder.speechnotes20161224142500.R;
import com.ibm.mobileappbuilder.speechnotes20161224142500.ds.DatanotesDSSchemaItem;

import ibmmobileappbuilder.ds.CrudDatasource;
import ibmmobileappbuilder.ds.Datasource;
import ibmmobileappbuilder.mvp.presenter.BasePresenter;
import ibmmobileappbuilder.mvp.presenter.DetailCrudPresenter;
import ibmmobileappbuilder.mvp.view.DetailView;

public class SpeechNotesDetailPresenter extends BasePresenter implements DetailCrudPresenter<DatanotesDSSchemaItem>,
      Datasource.Listener<DatanotesDSSchemaItem> {

    private final CrudDatasource<DatanotesDSSchemaItem> datasource;
    private final DetailView view;

    public SpeechNotesDetailPresenter(CrudDatasource<DatanotesDSSchemaItem> datasource, DetailView view){
        this.datasource = datasource;
        this.view = view;
    }

    @Override
    public void deleteItem(DatanotesDSSchemaItem item) {
        datasource.deleteItem(item, this);
    }

    @Override
    public void editForm(DatanotesDSSchemaItem item) {
        view.navigateToEditForm();
    }

    @Override
    public void onSuccess(DatanotesDSSchemaItem item) {
                view.showMessage(R.string.item_deleted, true);
        view.close(true);
    }

    @Override
    public void onFailure(Exception e) {
        view.showMessage(R.string.error_data_generic, true);
    }
}
