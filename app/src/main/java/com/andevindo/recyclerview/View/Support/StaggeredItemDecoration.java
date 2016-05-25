package com.andevindo.recyclerview.View.Support;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.andevindo.recyclerview.R;

/**
 * Created by heendher on 5/25/2016.
 */
public class StaggeredItemDecoration extends RecyclerView.ItemDecoration {

    private int mSpanCount;
    private int mMargin;

    public StaggeredItemDecoration(Context context, int spanCount) {
        mSpanCount = spanCount;
        mMargin = (int) context.getResources().getDimension(R.dimen.list_margin);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.right = mMargin;
        outRect.bottom = mMargin;
        int spanIndex = ((StaggeredGridLayoutManager.LayoutParams)view.getLayoutParams()).getSpanIndex();
        if (parent.getChildAdapterPosition(view)<mSpanCount)
            outRect.top = mMargin;

        if ((spanIndex+1)%mSpanCount==1)
            outRect.left = mMargin;


    }
}
