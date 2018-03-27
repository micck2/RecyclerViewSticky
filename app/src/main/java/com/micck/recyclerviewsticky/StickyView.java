package com.micck.recyclerviewsticky;

import android.view.View;

/**
 * @author lilin on 2018/3/27 9:51
 */

public class StickyView implements BaseStickyView {
    @Override
    public boolean isStickyView(View view) {
        return (boolean) view.getTag();
    }

    @Override
    public int getStickyViewType() {
        return 0;
    }
}
