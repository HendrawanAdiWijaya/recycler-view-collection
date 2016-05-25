package com.andevindo.recyclerview.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.andevindo.recyclerview.Model.Post;
import com.andevindo.recyclerview.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by heendher on 5/24/2016.
 */
public class StaggeredAdapter extends RecyclerView.Adapter<StaggeredViewHolder> implements StaggeredViewHolder.LocalStaggeredPresenter{

    private List<Post> mPostList;
    private Context mContext;
    private StaggeredPresenter mPresenter;

    public StaggeredAdapter(Context context) {
        mContext = context;
    }

    public void setData(List<Post> list) {
        mPostList = list;
        notifyDataSetChanged();
    }

    public void setPresenter(StaggeredPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public StaggeredViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_like_bookmark, parent, false);
        return new StaggeredViewHolder(view, mContext, mPresenter);
    }

    @Override
    public void onBindViewHolder(StaggeredViewHolder holder, int position) {
        holder.setLocalPresenter(this);
        holder.bindData(mPostList.get(position));
    }

    @Override
    public int getItemCount() {
        if (mPostList == null)
            return 0;
        else
            return mPostList.size();
    }

    @Override
    public void onLike(Post post) {
        int index = mPostList.indexOf(post);
        if (post.isLiked())
            post.setLiked(false);
        else
            post.setLiked(true);
        notifyItemChanged(index);
    }

    @Override
    public void onBookmark(Post post) {
        int index = mPostList.indexOf(post);
        if (post.isBookmarked())
            post.setBookmarked(false);
        else
            post.setBookmarked(true);
        notifyItemChanged(index);
    }


    public interface StaggeredPresenter {
        void onClick(Post post);

        void onLongClick(Post post);

        void onLike(Post post);

        void onBookmark(Post post);
    }
}

class StaggeredViewHolder extends RecyclerView.ViewHolder {

    private TextView mTitle;
    private ImageView mImage, mLike, mBookmark;
    private Context mContext;
    private StaggeredAdapter.StaggeredPresenter mPresenter;
    private LocalStaggeredPresenter mLocalPresenter;
    private Post mPost;

    public StaggeredViewHolder(View itemView, Context context, StaggeredAdapter.StaggeredPresenter presenter) {
        super(itemView);
        mPresenter = presenter;
        mContext = context;
        mTitle = (TextView) itemView.findViewById(R.id.title);
        mImage = (ImageView) itemView.findViewById(R.id.image);
        mLike = (ImageView) itemView.findViewById(R.id.like);
        mBookmark = (ImageView) itemView.findViewById(R.id.bookmark);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.onClick(mPost);
            }
        });
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mPresenter.onLongClick(mPost);
                return true;
            }
        });
        mLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLocalPresenter.onLike(mPost);
                mPresenter.onLike(mPost);
            }
        });
        mBookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLocalPresenter.onBookmark(mPost);
                mPresenter.onBookmark(mPost);
            }
        });
    }

    public void bindData(Post post) {
        mPost = post;
        mTitle.setText(post.getTitle());
        Picasso.with(mContext).load(post.getImage()).fit().centerCrop().into(mImage);
        if (post.isBookmarked())
            mBookmark.setImageResource(R.drawable.ic_bookmark_accent_24dp);
        else
            mBookmark.setImageResource(R.drawable.ic_bookmark_border_accent_24dp);

        if (post.isLiked())
            mLike.setImageResource(R.drawable.thumb_up_accent_24dp);
        else
            mLike.setImageResource(R.drawable.thumb_up_outline);
    }

    public void setLocalPresenter(LocalStaggeredPresenter localPresenter){
        mLocalPresenter = localPresenter;
    }

    public interface LocalStaggeredPresenter{
        void onLike(Post post);

        void onBookmark(Post post);
    }

}



