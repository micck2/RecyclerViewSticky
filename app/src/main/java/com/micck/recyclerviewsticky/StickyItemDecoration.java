package com.micck.recyclerviewsticky;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * @author lilin on 2018/3/27 9:56
 */

public class StickyItemDecoration extends RecyclerView.ItemDecoration {
    private StickyView mStickyView;
    private LinearLayoutManager mLayoutManager;
    /**
     * 吸附的itemView
     */
    private View mStickyItemView;

    /**
     * 吸附itemView 距离顶部
     */
    private int mStickyItemViewMarginTop;

    /**
     * 吸附itemView 高度
     */
    private int mStickyItemViewHeight;
    /**
     * 滚动过程中当前的UI是否可以找到吸附的view
     */
    private boolean mCurrentUIFindStickView;
    private RecyclerView.Adapter<RecyclerView.ViewHolder> mAdapter;
    private RecyclerView.ViewHolder mViewHolder;

    private List<Integer> mStickyPositionList = new ArrayList<>();
    /**
     * 绑定数据的position
     */
    private int mBindDataPosition = -1;

    private Paint mPaint;

    public StickyItemDecoration() {
        mStickyView = new StickyView();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);

        mLayoutManager = (LinearLayoutManager) parent.getLayoutManager();
        mCurrentUIFindStickView = false;
        for (int i = 0,size = parent.getChildCount(); i < size; i++) {
            View view = parent.getChildAt(i);

            //判断是否是需要吸附效果的View
            if (mStickyView.isStickyView(view)) {
                //当前UI当中是否找到了需要吸附的View，此时设置为true
                mCurrentUIFindStickView = true;
                getStickyViewHolder(parent);
                cacheStickyViewPosition(i);
                //如果当前吸附的view距离顶部小于等于0，然后给吸附的View绑定数据，计算View的宽高
                if (view.getTop() <= 0) {
                    bindDataForStickyView(mLayoutManager.findFirstVisibleItemPosition(), parent.getMeasuredWidth());
                } else {
                    //如果大于0，从position缓存中取得当前的position，然后绑定数据，计算View的宽高
                    if (mStickyPositionList.size() > 0) {
                        if (mStickyPositionList.size() == 1) {
                            bindDataForStickyView(mStickyPositionList.get(0), parent.getMeasuredWidth());
                        } else {
                            int currentPosition = getStickyViewPositionOfRecyclerView(i);
                            int indexOfCurrentPosition = mStickyPositionList.lastIndexOf(currentPosition);
                            bindDataForStickyView(mStickyPositionList.get(indexOfCurrentPosition - 1), parent.getMeasuredWidth());
                        }
                    }
                }
                //计算吸附的View距离顶部的高度
                if (view.getTop() > 0 && view.getTop() <= mStickyItemViewHeight) {
                    mStickyItemViewMarginTop = mStickyItemViewHeight - view.getTop();
                } else {
                    mStickyItemViewMarginTop = 0;
                }

                drawStickyItemView(c);
                break;
            }
        }
        //如果在当前的列表视图中没有找到需要吸附的View
        if (!mCurrentUIFindStickView) {
            mStickyItemViewMarginTop = 0;
            //如果已经滑动到底部了，就绑定最后一个缓存的position的View，这种情况一般出现在快速滑动列表的时候吸附View出现错乱，所以需要绑定一下
            if (mLayoutManager.findFirstVisibleItemPosition() + parent.getChildCount() == parent.getAdapter().getItemCount()) {
                bindDataForStickyView(mStickyPositionList.get(mStickyPositionList.size() - 1), parent.getMeasuredWidth());
            }
            drawStickyItemView(c);
        }
    }

    /**
     * 给StickyView绑定数据
     * @param position
     * @param measuredWidth
     */
    private void bindDataForStickyView(int position, int measuredWidth) {
        if (mBindDataPosition == position) {
            return;
        }

        mBindDataPosition = position;
        mAdapter.onBindViewHolder(mViewHolder, mBindDataPosition);
        measureLayoutStickyItemView(measuredWidth);
        mStickyItemViewHeight = mViewHolder.itemView.getBottom() - mViewHolder.itemView.getTop();
    }

    /**
     * 计算布局吸附的itemView
     * @param measuredWidth
     */
    private void measureLayoutStickyItemView(int measuredWidth) {
        if (!mStickyItemView.isLayoutRequested()) {
            return;
        }
        int widthSpec = View.MeasureSpec.makeMeasureSpec(measuredWidth, View.MeasureSpec.EXACTLY);
        int heightSpec;
        ViewGroup.LayoutParams layoutParams = mStickyItemView.getLayoutParams();
        if (layoutParams != null && layoutParams.height > 0) {
            heightSpec = View.MeasureSpec.makeMeasureSpec(layoutParams.height, View.MeasureSpec.EXACTLY);
        } else {
            heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        }

        mStickyItemView.measure(widthSpec, heightSpec);
        mStickyItemView.layout(0,0,mStickyItemView.getMeasuredWidth(),mStickyItemView.getMeasuredHeight());
    }

    /**
     * 得到吸顶viewHolder
     * @param parent
     */
    private void getStickyViewHolder(RecyclerView parent) {
        if (mAdapter != null) {
            return;
        }
        mAdapter = parent.getAdapter();
        mViewHolder = mAdapter.onCreateViewHolder(parent, mStickyView.getStickyViewType());
        mStickyItemView = mViewHolder.itemView;
    }

    /**
     * 缓存吸附的view position
     * @param i
     */
    private void cacheStickyViewPosition(int i) {
        int position = getStickyViewPositionOfRecyclerView(i);
        if (!mStickyPositionList.contains(position)) {
            mStickyPositionList.add(position);
        }
    }

    /**
     * 得到吸附view在RecyclerView中 的position
     * @param i
     * @return
     */
    private int getStickyViewPositionOfRecyclerView(int i) {
        return mLayoutManager.findFirstVisibleItemPosition() + i;
    }

    /**
     * 绘制吸附的itemView
     * @param canvas
     */
    private void drawStickyItemView(Canvas canvas) {
        int saveCount = canvas.save();
        canvas.translate(0, -mStickyItemViewMarginTop);
        mStickyItemView.draw(canvas);
        canvas.restoreToCount(saveCount);
    }
}
