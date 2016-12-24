

package com.ibm.mobileappbuilder.speechnotes20161224142500;

import android.app.Application;
import ibmmobileappbuilder.injectors.ApplicationInjector;
import android.support.multidex.MultiDexApplication;
import ibmmobileappbuilder.cloudant.factory.CloudantDatabaseSyncerFactory;
import java.net.URI;


/**
 * You can use this as a global place to keep application-level resources
 * such as singletons, services, etc.
 */
public class MyApplication extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationInjector.setApplicationContext(this);
        //Syncing cloudant ds
        CloudantDatabaseSyncerFactory.instanceFor(
            "data_notes",
            URI.create("https://d474c95f-a46f-4ee2-8388-ed0e6c2a0462-bluemix:cd451352bbaa74569fc773726e43193b37bde09e1ed48cf4be4d1badd53396ea@d474c95f-a46f-4ee2-8388-ed0e6c2a0462-bluemix.cloudant.com/data_notes/")
        ).sync(null);
      }
}
