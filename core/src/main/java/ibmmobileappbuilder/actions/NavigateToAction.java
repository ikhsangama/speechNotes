package ibmmobileappbuilder.actions;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import ibmmobileappbuilder.analytics.model.AnalyticsInfo;

import static ibmmobileappbuilder.analytics.model.AnalyticsInfo.Builder.analyticsInfo;

public class NavigateToAction implements Action {
    private String parameterName;
    private Class target;
    private Parcelable item;

    public NavigateToAction(String parameterName, Class target, Parcelable item) {
        this.parameterName = parameterName;
        this.target = target;
        this.item = item;
    }

    @Override
    public void execute(@NonNull Context context) {
        Intent intent = new Intent(context, target);

        if (item != null) {
            Bundle args = new Bundle();
            args.putParcelable(parameterName, item);
            intent.putExtras(args);
            context.startActivity(intent);
        } else {
            throw new IllegalStateException("You must set the parcelable item first");
        }
    }

    @NonNull
    @Override
    public AnalyticsInfo getAnalyticsInfo() {
        return analyticsInfo()
                .withAction("Navigate to")
                .withTarget(target.getName())
                .build();
    }
}
