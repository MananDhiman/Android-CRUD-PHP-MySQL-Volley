package com.example.android_crud_php_mysql_volley;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

    private Context context;
    private View view;
    private ArrayList<Posts> postsArrayList;

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Posts posts = postsArrayList.get(position);
        holder.item_tv_title.setText(posts.getTitle());
        holder.item_tv_text.setText(posts.getText());

        holder.item_tv_readMore.setOnClickListener(v -> {
            showDetail(posts, position);
        });
    }

    private void showDetail(Posts posts, int position) {
        Intent intentShowDetail = new Intent(context, PostViewActivity.class);
        posts.setPosition(position);
        intentShowDetail.putExtra("posts",posts);
        context.startActivity(intentShowDetail);
    }

    public PostsAdapter(Context context, ArrayList<Posts> postsArrayList){
        this.context = context;
        this.postsArrayList = postsArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(context).inflate(R.layout.item_post,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return postsArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView item_tv_title, item_tv_text,item_tv_readMore;
        LinearLayout itemLayout;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            item_tv_title = itemView.findViewById(R.id.item_tv_title);
            item_tv_text = itemView.findViewById(R.id.item_tv_text);
            item_tv_readMore = itemView.findViewById(R.id.readMore);
            itemLayout = itemView.findViewById(R.id.item_layout_post);
        }
    }
}
