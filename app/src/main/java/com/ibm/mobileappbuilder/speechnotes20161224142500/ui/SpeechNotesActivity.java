

package com.ibm.mobileappbuilder.speechnotes20161224142500.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.ibm.mobileappbuilder.speechnotes20161224142500.R;

import ibmmobileappbuilder.ui.BaseListingActivity;
/**
 * SpeechNotesActivity list activity
 */
public class SpeechNotesActivity extends BaseListingActivity {

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);

        if(isTaskRoot()) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        } else {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        
        setTitle(getString(R.string.speechNotesActivity));
    }

    @Override
    protected Class<? extends Fragment> getFragmentClass() {
        return SpeechNotesFragment.class;
    }

}
