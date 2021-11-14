package com.example.android_crud_php_mysql_volley;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class PostViewActivity extends AppCompatActivity {

    private int postId, index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_view);

        viewPost();

    }

    private void viewPost() {
        TextView detail_tv_text = findViewById(R.id.detail_tv_text);
        TextView detail_tv_title = findViewById(R.id.detail_tv_title);

        Posts posts = getIntent().getParcelableExtra("posts");
        detail_tv_title.setText(posts.getTitle());
        detail_tv_text.setText(posts.getText());
    }
}