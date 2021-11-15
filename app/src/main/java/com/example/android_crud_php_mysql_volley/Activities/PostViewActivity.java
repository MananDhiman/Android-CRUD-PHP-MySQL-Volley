package com.example.android_crud_php_mysql_volley.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.example.android_crud_php_mysql_volley.Posts;
import com.example.android_crud_php_mysql_volley.R;
import com.example.android_crud_php_mysql_volley.Variable;

public class PostViewActivity extends AppCompatActivity {

    private ImageView imageView;
    private int postId, index;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_view);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        viewPost();

    }

    private void viewPost() {

        TextView detail_tv_text = findViewById(R.id.detail_tv_text);
        TextView detail_tv_title = findViewById(R.id.detail_tv_title);
        this.imageView = (ImageView) findViewById(R.id.item_iv_image);

        Posts posts = getIntent().getParcelableExtra("posts");

        detail_tv_title.setText(posts.getTitle());
        detail_tv_text.setText(posts.getText());
        postId = posts.getId();
        index = posts.getPosition();

        loadImage(posts);

    }

    private void loadImage(Posts posts) {

    }

}