package ibmmobileappbuilder.behaviors;

import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import ibmmobileappbuilder.actions.LogoutAction;
import ibmmobileappbuilder.core.R;
import ibmmobileappbuilder.util.SecurePreferences;

/**
 * Add 3 dots menu to logout
 */
public class LogoutBehavior extends NoOpBehavior {
    private SecurePreferences mSharedPreferences;
    private Class loginActivity;
    private Activity activity;

    public LogoutBehavior(SecurePreferences mSharedPreferences, Class loginActivity, Activity activity) {
        this.mSharedPreferences = mSharedPreferences;
        this.loginActivity = loginActivity;
        this.activity = activity;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        MenuItem item = menu.add(R.string.log_out);

        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                new LogoutAction(mSharedPreferences, loginActivity, activity).execute(activity);
                return true;
            }
        });
    }
}
