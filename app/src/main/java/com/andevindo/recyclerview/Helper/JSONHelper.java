package com.andevindo.recyclerview.Helper;

import android.content.Context;
import android.widget.Toast;

import com.andevindo.recyclerview.Model.Post;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by heendher on 5/25/2016.
 */
public class JSONHelper {

    private static String loadJSONFromAsset(Context context) {
        String json = null;
        try {

            InputStream is = context.getAssets().open("post.json");

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        } catch (NullPointerException ex){
            ex.printStackTrace();
            return null;
        }
        return json;

    }

    public static List<Post> getData(Context context){
        List<Post> list = null;
        try {
            JSONArray jsonArray = new JSONArray(loadJSONFromAsset(context));
            list = new ArrayList<>(jsonArray.length());
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject json = jsonArray.getJSONObject(i);
                Post post = new Post();
                post.setTitle(json.getString("title"));
                post.setContent(json.getString("content"));
                post.setImage(json.getString("image"));
                list.add(post);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

}
