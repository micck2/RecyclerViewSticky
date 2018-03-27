package com.micck.recyclerviewsticky;

import android.view.View;

/**
 * @author lilin on 2018/3/27 9:49
 */

public interface BaseStickyView {
    /**
     * 是否是吸顶view
     * @param view
     * @return
     */
    boolean isStickyView(View view);

    /**
     * 吸顶view的itemType
     * @return
     */
    int getStickyViewType();
}
