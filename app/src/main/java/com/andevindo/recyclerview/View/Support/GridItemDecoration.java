package com.andevindo.recyclerview.View.Support;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.andevindo.recyclerview.R;

/**
 * Created by heendher on 5/25/2016.
 */
public class GridItemDecoration extends RecyclerView.ItemDecoration {

    private int mSpanCount;
    private int mMargin;

    public GridItemDecoration(Context context, int spanCount) {
        mSpanCount = spanCount;
        mMargin = (int) context.getResources().getDimension(R.dimen.list_margin);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.right = mMargin;
        outRect.bottom = mMargin;
        if (parent.getChildAdapterPosition(view)<mSpanCount)
            outRect.top = mMargin;

        if ((parent.getChildAdapterPosition(view)+1)%mSpanCount==1)
            outRect.left = mMargin;
    }
}
