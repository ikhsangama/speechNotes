package ibmmobileappbuilder.navigation;

import android.support.v4.app.Fragment;

import java.util.List;

import ibmmobileappbuilder.adapters.TabItem;
import ibmmobileappbuilder.ui.TabActivity;

/**
 * Interface for Tab navigation fragments. Tab Navigation fragments are mainly used for the upper
 * navigation level, and are composed by sections, implemented as {@link
 * Fragment}.
 * Navigation between sections is up to concrete implementations.
 * Each concrete fragment may implement its own navigation pattern.
 * See {@link TabActivity}
 */
public interface TabNavigation {
    /**
     * Get the list of tabitem classes that implements each section
     *
     * @return a list of tabitem classes
     */
    List<TabItem> getTabItems();
}
