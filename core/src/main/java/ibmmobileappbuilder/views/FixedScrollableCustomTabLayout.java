package ibmmobileappbuilder.views;

import android.content.Context;
import android.content.res.Configuration;
import android.support.design.widget.TabLayout;
import android.util.AttributeSet;

public class FixedScrollableCustomTabLayout extends TabLayout {
    private static int MAX_TABS_FIXED = 3;

    public FixedScrollableCustomTabLayout(Context context) {
        super(context);
    }

    public FixedScrollableCustomTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FixedScrollableCustomTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		
		if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            MAX_TABS_FIXED = 3;
        } else {
            MAX_TABS_FIXED = 5;
        }
		
        try {
            if (getTabCount() == 0)
                return;
            if(getTabCount() <= MAX_TABS_FIXED){
                setTabMode(TabLayout.MODE_FIXED);
                setTabGravity(TabLayout.GRAVITY_FILL);
            }else{
                setTabMode(TabLayout.MODE_SCROLLABLE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}