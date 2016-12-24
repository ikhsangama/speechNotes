package ibmmobileappbuilder.adapters;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

public class TabItem {
    private Integer pageTitleRes;
    private Integer pageIconRes;
    private Class fragmentClass;

    public TabItem(@StringRes Integer pageTitleRes, @DrawableRes Integer pageIconRes, Class fragmentClass) {
        this.pageTitleRes = pageTitleRes;
        this.pageIconRes = pageIconRes;
        this.fragmentClass = fragmentClass;
    }

    public TabItem(Integer pageTypeRes, boolean isPageTitle, Class fragmentClass) {
        if(isPageTitle) {
            this.pageTitleRes = pageTypeRes;
        } else {
            this.pageIconRes = pageTypeRes;
        }

        this.fragmentClass = fragmentClass;
    }

    public Integer getPageTitleRes() {
        return pageTitleRes;
    }

    public Integer getPageIconRes() {
        return pageIconRes;
    }

    public Class getFragmentClass() {
        return fragmentClass;
    }
}
