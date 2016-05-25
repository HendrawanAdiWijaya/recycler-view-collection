package com.andevindo.recyclerview.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.andevindo.recyclerview.Model.Post;
import com.andevindo.recyclerview.R;

import java.util.List;

/**
 * Created by heendher on 5/24/2016.
 */
public class TextOnlyAdapter extends RecyclerView.Adapter<TextOnlyViewHolder> {

    private List<Post> mPostList;
    private Context mContext;
    private TextOnlyPresenter mPresenter;

    public TextOnlyAdapter(Context context) {
        mContext = context;
    }

    public void setData(List<Post> list){
        mPostList = list;
        notifyDataSetChanged();
    }

    public void setPresenter(TextOnlyPresenter presenter){
        mPresenter = presenter;
    }

    @Override
    public TextOnlyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_text_only, parent, false);
        return new TextOnlyViewHolder(view, mPresenter);
    }

    @Override
    public void onBindViewHolder(TextOnlyViewHolder holder, int position) {
        holder.bindData(mPostList.get(position));
    }

    @Override
    public int getItemCount() {
        if (mPostList == null)
            return 0;
        else
            return mPostList.size();
    }

    public interface TextOnlyPresenter {
        void onClick(Post post);
        void onLongClick(Post post);
    }
}

class TextOnlyViewHolder extends RecyclerView.ViewHolder {

    private TextView mTitle, mContent;
    private TextOnlyAdapter.TextOnlyPresenter mPresenter;
    private Post mPost;

    public TextOnlyViewHolder(View itemView, TextOnlyAdapter.TextOnlyPresenter presenter) {
        super(itemView);
        mPresenter = presenter;
        mTitle = (TextView)itemView.findViewById(R.id.title);
        mContent = (TextView)itemView.findViewById(R.id.content);
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
    }
}
