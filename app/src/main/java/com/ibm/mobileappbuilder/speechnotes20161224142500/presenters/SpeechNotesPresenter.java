
package com.ibm.mobileappbuilder.speechnotes20161224142500.presenters;

import com.ibm.mobileappbuilder.speechnotes20161224142500.R;
import com.ibm.mobileappbuilder.speechnotes20161224142500.ds.DatanotesDSSchemaItem;

import java.util.List;

import ibmmobileappbuilder.ds.CrudDatasource;
import ibmmobileappbuilder.ds.Datasource;
import ibmmobileappbuilder.mvp.presenter.BasePresenter;
import ibmmobileappbuilder.mvp.presenter.ListCrudPresenter;
import ibmmobileappbuilder.mvp.view.CrudListView;

public class SpeechNotesPresenter extends BasePresenter implements ListCrudPresenter<DatanotesDSSchemaItem>,
      Datasource.Listener<DatanotesDSSchemaItem>{

    private final CrudDatasource<DatanotesDSSchemaItem> crudDatasource;
    private final CrudListView<DatanotesDSSchemaItem> view;

    public SpeechNotesPresenter(CrudDatasource<DatanotesDSSchemaItem> crudDatasource,
                                         CrudListView<DatanotesDSSchemaItem> view) {
       this.crudDatasource = crudDatasource;
       this.view = view;
    }

    @Override
    public void deleteItem(DatanotesDSSchemaItem item) {
        crudDatasource.deleteItem(item, this);
    }

    @Override
    public void deleteItems(List<DatanotesDSSchemaItem> items) {
        crudDatasource.deleteItems(items, this);
    }

    @Override
    public void addForm() {
        view.showAdd();
    }

    @Override
    public void editForm(DatanotesDSSchemaItem item, int position) {
        view.showEdit(item, position);
    }

    @Override
    public void detail(DatanotesDSSchemaItem item, int position) {
        view.showDetail(item, position);
    }

    @Override
    public void onSuccess(DatanotesDSSchemaItem item) {
                view.showMessage(R.string.items_deleted);
        view.refresh();
    }

    @Override
    public void onFailure(Exception e) {
        view.showMessage(R.string.error_data_generic);
    }

}
