package ibmmobileappbuilder.ui;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.List;

import ibmmobileappbuilder.adapters.TabItem;
import ibmmobileappbuilder.adapters.TabPagerAdapter;
import ibmmobileappbuilder.core.R;
import ibmmobileappbuilder.navigation.TabNavigation;
import ibmmobileappbuilder.views.FixedScrollableCustomTabLayout;

public abstract class TabActivity extends AppCompatActivity implements TabNavigation {
    private ViewPager viewPager;
    private FixedScrollableCustomTabLayout tabLayout;
    private List<TabItem> tabItems;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.tab_activity);

        tabLayout = (FixedScrollableCustomTabLayout) findViewById(R.id.main_tab_layout);
        viewPager = (ViewPager) findViewById(R.id.main_view_pager);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        tabItems = getTabItems();

        setupTabs();
    }

    private void setupTabs() {
        viewPager.setAdapter(new TabPagerAdapter(getSupportFragmentManager(), this, tabItems));
		viewPager.setOffscreenPageLimit(tabItems.size());
        tabLayout.setupWithViewPager(viewPager);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            tabLayout.setTabTextColors(getResources().getColor(R.color.textBarColor, getTheme()),
                    getResources().getColor(R.color.textBarColor, getTheme()));
            tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.textBarColor, getTheme()));
        } else {
            tabLayout.setTabTextColors(getResources().getColor(R.color.textBarColor),
                    getResources().getColor(R.color.textBarColor));
            tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.textBarColor));
        }

        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            if(tabItems.get(i).getPageIconRes() != null) {
                tabLayout.getTabAt(i).setIcon(tabItems.get(i).getPageIconRes());
            }
        }
    }
}
