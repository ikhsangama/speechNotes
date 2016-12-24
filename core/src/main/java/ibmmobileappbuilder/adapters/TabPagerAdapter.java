package ibmmobileappbuilder.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class TabPagerAdapter extends FragmentPagerAdapter {
    private List<TabItem> tabItems;
    private Context context;

    public TabPagerAdapter(FragmentManager fm, Context context, List<TabItem> tabItems) {
        super(fm);
        this.context = context;
        this.tabItems = tabItems;
    }

    @Override
    public Fragment getItem(int position) {
        Class fragmentClass = tabItems.get(position).getFragmentClass();
        Fragment fragment;

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (InstantiationException e) {
            throw new IllegalStateException("Fragment result must not be null.");
        } catch (IllegalAccessException e) {
            throw new IllegalStateException("Fragment result must not be null.");
        }

        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabItems.get(position).getPageTitleRes() != null ? context.getString(tabItems.get(position).getPageTitleRes()) : "";
    }

    @Override
    public int getCount() {
        return tabItems.size();
    }
}
