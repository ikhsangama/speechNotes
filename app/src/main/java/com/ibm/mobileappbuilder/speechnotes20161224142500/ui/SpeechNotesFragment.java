package com.ibm.mobileappbuilder.speechnotes20161224142500.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.ibm.mobileappbuilder.speechnotes20161224142500.presenters.SpeechNotesPresenter;
import com.ibm.mobileappbuilder.speechnotes20161224142500.R;
import ibmmobileappbuilder.behaviors.FabBehaviour;
import ibmmobileappbuilder.behaviors.SelectionBehavior;
import ibmmobileappbuilder.ds.Datasource;
import ibmmobileappbuilder.ui.ListGridFragment;
import ibmmobileappbuilder.util.Constants;
import ibmmobileappbuilder.util.ViewHolder;
import java.util.List;
import ibmmobileappbuilder.ds.SearchOptions;
import ibmmobileappbuilder.ds.filter.Filter;
import java.util.Arrays;
import com.ibm.mobileappbuilder.speechnotes20161224142500.ds.DatanotesDSSchemaItem;
import ibmmobileappbuilder.ds.CloudantDatasource;
import ibmmobileappbuilder.cloudant.factory.CloudantDatastoresFactory;
import java.net.URI;
import ibmmobileappbuilder.mvp.view.CrudListView;
import ibmmobileappbuilder.ds.CrudDatasource;
import android.content.Intent;
import ibmmobileappbuilder.util.Constants;
import static ibmmobileappbuilder.util.NavigationUtils.generateIntentToAddOrUpdateItem;

/**
 * "SpeechNotesFragment" listing
 */
public class SpeechNotesFragment extends ListGridFragment<DatanotesDSSchemaItem> implements CrudListView<DatanotesDSSchemaItem> {

    private CrudDatasource<DatanotesDSSchemaItem> datasource;

    // "Add" button
    private FabBehaviour fabBehavior;

    public static SpeechNotesFragment newInstance(Bundle args) {
        SpeechNotesFragment fr = new SpeechNotesFragment();

        fr.setArguments(args);
        return fr;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setPresenter(new SpeechNotesPresenter(
            (CrudDatasource) getDatasource(),
            this
        ));
        // Multiple selection
        SelectionBehavior<DatanotesDSSchemaItem> selectionBehavior = new SelectionBehavior<>(
            this,
            R.string.remove_items,
            R.drawable.ic_delete_alpha);

        selectionBehavior.setCallback(new SelectionBehavior.Callback<DatanotesDSSchemaItem>() {
            @Override
            public void onSelected(List<DatanotesDSSchemaItem> selectedItems) {
                getPresenter().deleteItems(selectedItems);
            }
        });
        addBehavior(selectionBehavior);

        // FAB button
        fabBehavior = new FabBehaviour(this, R.drawable.ic_add_white, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().addForm();
            }
        });
        addBehavior(fabBehavior);
        
    }

    protected SearchOptions getSearchOptions() {
        SearchOptions.Builder searchOptionsBuilder = SearchOptions.Builder.searchOptions();
        return searchOptionsBuilder.build();
    }


    /**
    * Layout for the list itselft
    */
    @Override
    protected int getLayout() {
        return R.layout.fragment_list;
    }

    /**
    * Layout for each element in the list
    */
    @Override
    protected int getItemLayout() {
        return R.layout.speechnotes_item;
    }

    @Override
    protected Datasource<DatanotesDSSchemaItem> getDatasource() {
        if (datasource != null) {
            return datasource;
        }
       datasource = CloudantDatasource.cloudantDatasource(
              CloudantDatastoresFactory.create("data_notes"),
              URI.create("https://d474c95f-a46f-4ee2-8388-ed0e6c2a0462-bluemix:cd451352bbaa74569fc773726e43193b37bde09e1ed48cf4be4d1badd53396ea@d474c95f-a46f-4ee2-8388-ed0e6c2a0462-bluemix.cloudant.com/data_notes/"),
              DatanotesDSSchemaItem.class,
              getSearchOptions(),
              "judul", "notes");
        return datasource;
    }

    @Override
    protected void bindView(DatanotesDSSchemaItem item, View view, int position) {
        
        TextView title = ViewHolder.get(view, R.id.title);
        
        if (item.judul != null){
            title.setText(item.judul);
            
        }
    }

    @Override
    protected void itemClicked(final DatanotesDSSchemaItem item, final int position) {
        fabBehavior.hide(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                getPresenter().detail(item, position);
            }
        });
    }

    @Override
    public void showDetail(DatanotesDSSchemaItem item, int position) {
        // If we have forms, then we have to refresh when an item has been edited
        // Also with this we support list without details
        Bundle args = new Bundle();
        args.putInt(Constants.ITEMPOS, position);
        args.putParcelable(Constants.CONTENT, item);
        Intent intent = new Intent(getActivity(), SpeechNotesDetailActivity.class);
        intent.putExtras(args);

        if (!getResources().getBoolean(R.bool.tabletLayout)) {
            startActivityForResult(intent, Constants.DETAIL);
        } else {
            startActivity(intent);
        }
    }

    @Override
    public void showAdd() {
        startActivityForResult(generateIntentToAddOrUpdateItem(null,
                        0,
                        getActivity(),
                        SpeechNotesFormFormActivity.class
                ), Constants.MODE_CREATE
        );
    }

    @Override
    public void showEdit(DatanotesDSSchemaItem item, int position) {
        startActivityForResult(
                generateIntentToAddOrUpdateItem(item,
                        position,
                        getActivity(),
                        SpeechNotesFormFormActivity.class
                ), Constants.MODE_EDIT
        );
    }
}
