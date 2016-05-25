package com.andevindo.recyclerview.View.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.andevindo.recyclerview.Adapter.LikeBookmarkAdapter;
import com.andevindo.recyclerview.Adapter.StaggeredAdapter;
import com.andevindo.recyclerview.Helper.JSONHelper;
import com.andevindo.recyclerview.Model.Post;
import com.andevindo.recyclerview.R;
import com.andevindo.recyclerview.View.Support.GridItemDecoration;
import com.andevindo.recyclerview.View.Support.StaggeredItemDecoration;

/**
 * A simple {@link Fragment} subclass.
 */
public class StaggeredLayoutManagerFragment extends Fragment implements StaggeredAdapter.StaggeredPresenter{

    private RecyclerView mRecyclerView;
    private StaggeredAdapter mAdapter;
    private StaggeredItemDecoration mStaggeredItemDecoration;

    public StaggeredLayoutManagerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recycler_view, container, false);

        mStaggeredItemDecoration = new StaggeredItemDecoration(getContext(), 2);
        mRecyclerView = (RecyclerView)view.findViewById(R.id.recycler);
        mAdapter = new StaggeredAdapter(getContext());
        mAdapter.setPresenter(this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.addItemDecoration(mStaggeredItemDecoration);
        mAdapter.setData(JSONHelper.getData(getContext()));
        return view;
    }

    @Override
    public void onClick(Post post) {
        Toast.makeText(getContext(), "Click di post tentang " + post.getTitle(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLongClick(Post post) {
        Toast.makeText(getContext(), "Long click di post tentang " + post.getTitle(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLike(Post post) {
        if (post.isLiked())
            Toast.makeText(getContext(), "Like", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getContext(), "Dislike", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBookmark(Post post) {
        if (post.isBookmarked())
            Toast.makeText(getContext(), "Bookmark", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getContext(), "Unbookmark", Toast.LENGTH_SHORT).show();
    }
}
