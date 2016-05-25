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
public class WithImageAdapter extends RecyclerView.Adapter<WithImageViewHolder> {

    private List<Post> mPostList;
    private Context mContext;
    private WithImagePresenter mPresenter;

    public WithImageAdapter(Context context) {
        mContext = context;
    }

    public void setData(List<Post> list){
        mPostList = list;
        notifyDataSetChanged();
    }

    public void setPresenter(WithImagePresenter presenter){
        mPresenter = presenter;
    }

    @Override
    public WithImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_with_image, parent, false);
        return new WithImageViewHolder(view, mContext, mPresenter);
    }

    @Override
    public void onBindViewHolder(WithImageViewHolder holder, int position) {
        holder.bindData(mPostList.get(position));
    }

    @Override
    public int getItemCount() {
        if (mPostList == null)
            return 0;
        else
            return mPostList.size();
    }

    public interface WithImagePresenter {
        void onClick(Post post);
        void onLongClick(Post post);
    }
}

class WithImageViewHolder extends RecyclerView.ViewHolder {

    private TextView mTitle, mContent;
    private ImageView mImage;
    private Context mContext;
    private WithImageAdapter.WithImagePresenter mPresenter;
    private Post mPost;

    public WithImageViewHolder(View itemView, Context context, WithImageAdapter.WithImagePresenter presenter) {
        super(itemView);
        mPresenter = presenter;
        mContext = context;
        mTitle = (TextView)itemView.findViewById(R.id.title);
        mContent = (TextView)itemView.findViewById(R.id.content);
        mImage = (ImageView)itemView.findViewById(R.id.image);
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
    }

    public void bindData(Post post) {
        mPost = post;
        mTitle.setText(post.getTitle());
        mContent.setText(post.getContent());
        Picasso.with(mContext).load(post.getImage()).fit().centerCrop().into(mImage);
    }
}

