
package com.ibm.mobileappbuilder.speechnotes20161224142500.ui;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.ibm.mobileappbuilder.speechnotes20161224142500.presenters.SpeechNotesDetailPresenter;
import com.ibm.mobileappbuilder.speechnotes20161224142500.R;
import ibmmobileappbuilder.behaviors.FabBehaviour;
import ibmmobileappbuilder.behaviors.ShareBehavior;
import ibmmobileappbuilder.mvp.presenter.DetailCrudPresenter;
import ibmmobileappbuilder.util.ColorUtils;
import ibmmobileappbuilder.util.Constants;
import ibmmobileappbuilder.ds.Datasource;
import ibmmobileappbuilder.ds.CrudDatasource;
import ibmmobileappbuilder.ds.SearchOptions;
import ibmmobileappbuilder.ds.filter.Filter;
import java.util.Arrays;
import com.ibm.mobileappbuilder.speechnotes20161224142500.ds.DatanotesDSSchemaItem;
import com.ibm.watson.developer_cloud.android.library.audio.StreamPlayer;
import com.ibm.watson.developer_cloud.text_to_speech.v1.TextToSpeech;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.Voice;

import ibmmobileappbuilder.ds.CloudantDatasource;
import ibmmobileappbuilder.cloudant.factory.CloudantDatastoresFactory;
import java.net.URI;

public class SpeechNotesDetailFragment extends ibmmobileappbuilder.ui.DetailFragment<DatanotesDSSchemaItem> implements ShareBehavior.ShareListener  {

    Button btnSpeech;
    StreamPlayer streamPlayer;
    String text_to_speech;

    private TextToSpeech initTextToSpeechService(){
        TextToSpeech service = new TextToSpeech();
        String username = "b79b07b3-3e60-41c4-99ac-8f81467222ee";
        String password = "DrMNemDDn5cO";
        service.setUsernameAndPassword(username, password);
        return service;
    }

    private class WatsonTask extends AsyncTask<String, Void, String>{
        @Override
        protected String doInBackground(String... textToSpeak){
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run(){
                    btnSpeech.setText("Sedang Mendengarkan Catatan");
                }
            });

            TextToSpeech textToSpeech = initTextToSpeechService();
            streamPlayer = new StreamPlayer();
            streamPlayer.playStream(textToSpeech.synthesize(text_to_speech, Voice.EN_ALLISON).execute());
            return "text to speech done";
        }

        @Override
        protected void onPostExecute(String result) {
            btnSpeech.setText("Dengarkan Note!");
        }
    }
    //
    private CrudDatasource<DatanotesDSSchemaItem> datasource;
    public static SpeechNotesDetailFragment newInstance(Bundle args){
        SpeechNotesDetailFragment fr = new SpeechNotesDetailFragment();
        fr.setArguments(args);

        return fr;
    }

    public SpeechNotesDetailFragment(){
        super();
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

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        // the presenter for this view
        setPresenter(new SpeechNotesDetailPresenter(
                (CrudDatasource) getDatasource(),
                this));
        // Edit button
        addBehavior(new FabBehaviour(this, R.drawable.ic_edit_white, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((DetailCrudPresenter<DatanotesDSSchemaItem>) getPresenter()).editForm(getItem());
            }
        }));
        addBehavior(new ShareBehavior(getActivity(), this));

    }

    // Bindings

    @Override
    protected int getLayout() {
        return R.layout.speechnotesdetail_detail;
    }

    @Override
    @SuppressLint("WrongViewCast")
    public void bindView(final DatanotesDSSchemaItem item, View view) {
        if (item.notes != null){
            
            TextView view0 = (TextView) view.findViewById(R.id.view0);
            view0.setText(item.notes);

            btnSpeech = (Button) view.findViewById(R.id.btnSpeech);
            btnSpeech.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        text_to_speech = item.notes;
                        WatsonTask task = new WatsonTask();
                        task.execute(new String[]{});
                    } catch (Exception e){
                        System.err.println(e);
                    }
                }
            });
        }
    }

    @Override
    protected void onShow(DatanotesDSSchemaItem item) {
        // set the title for this fragment
        getActivity().setTitle(item.judul);
    }

    @Override
    public void navigateToEditForm() {
        Bundle args = new Bundle();

        args.putInt(Constants.ITEMPOS, 0);
        args.putParcelable(Constants.CONTENT, getItem());
        args.putInt(Constants.MODE, Constants.MODE_EDIT);

        Intent intent = new Intent(getActivity(), SpeechNotesFormFormActivity.class);
        intent.putExtras(args);
        startActivityForResult(intent, Constants.MODE_EDIT);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.delete_menu, menu);

        MenuItem item = menu.findItem(R.id.action_delete);
        ColorUtils.tintIcon(item, R.color.textBarColor, getActivity());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_delete){
            ((DetailCrudPresenter<DatanotesDSSchemaItem>) getPresenter()).deleteItem(getItem());
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onShare() {
        DatanotesDSSchemaItem item = getItem();

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/plain");

        intent.putExtra(Intent.EXTRA_TEXT, (item.notes != null ? item.notes : "" ));
        intent.putExtra(Intent.EXTRA_SUBJECT, item.judul);
        startActivityForResult(Intent.createChooser(intent, getString(R.string.share)), 1);
    }
}
