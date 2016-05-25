package com.andevindo.recyclerview.Model;

/**
 * Created by heendher on 5/24/2016.
 */
public class Post {

    private String mTitle;
    private String mContent;
    private String mImage;
    private boolean mIsBookmarked;
    private boolean mIsLiked;

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        mImage = image;
    }

    public boolean isBookmarked() {
        return mIsBookmarked;
    }

    public void setBookmarked(boolean bookmarked) {
        mIsBookmarked = bookmarked;
    }

    public boolean isLiked() {
        return mIsLiked;
    }

    public void setLiked(boolean liked) {
        mIsLiked = liked;
    }
}
