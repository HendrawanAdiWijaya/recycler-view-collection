package com.andevindo.recyclerview.View.Support;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.andevindo.recyclerview.R;

/**
 * Created by heendher on 5/25/2016.
 */
public class LinearItemDecoration extends RecyclerView.ItemDecoration {

    private int mMargin;

    public LinearItemDecoration(Context context) {
        mMargin = (int) context.getResources().getDimension(R.dimen.list_margin);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.left = mMargin;
        outRect.right = mMargin;
        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.top = mMargin;
            outRect.bottom = mMargin;
        } else {
            outRect.bottom = mMargin;
        }
    }
}
