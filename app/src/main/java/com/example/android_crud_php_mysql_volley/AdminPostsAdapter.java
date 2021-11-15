package com.example.android_crud_php_mysql_volley;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.android_crud_php_mysql_volley.Activities.AdminActivity;
import com.example.android_crud_php_mysql_volley.Activities.PostViewActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AdminPostsAdapter extends RecyclerView.Adapter<AdminPostsAdapter.ViewHolder>{

    private Context context;
    private View view;
    private ArrayList<Posts> postsArrayList;

    @NonNull
    @Override
    public AdminPostsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(context).inflate(R.layout.item_post,parent,false);

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull AdminPostsAdapter.ViewHolder holder, int position) {

        final Posts posts = postsArrayList.get(position);
        holder.item_tv_title.setText(posts.getTitle());
        holder.item_tv_text.setText(posts.getText());

        Glide.with(context)
                .load(Variable.base + "images/" + posts.getImage())
                .apply(new RequestOptions().centerCrop())
                .into(holder.item_iv_image);


        holder.item_tv_readMore.setOnClickListener(v -> {
            showDetail(posts, position);
        });

        holder.itemLayout.setOnClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(context, holder.itemLayout);
            popupMenu.inflate(R.menu.item_menu);
            popupMenu.setOnMenuItemClickListener(item -> {
                switch (item.getItemId()){
                    case R.id.item_menu_update:
                        //Toast.makeText(context, "Update", Toast.LENGTH_LONG).show();

                        updatePost(posts, position);

                        break;
                    case R.id.item_menu_delete:
                        //Toast.makeText(context, "Delete", Toast.LENGTH_LONG).show();

                        deletePost(posts.getId(), position, posts.getImage());
                        break;
                }
                return false;
            });
            popupMenu.show();
        });

    }

    private void deletePost(int id, int position, String image) {
        StringRequest request = new StringRequest(Request.Method.POST, Variable.delete_post, response -> {
            AdminActivity.postsArrayList.remove(position);
            AdminActivity.recyclerView.getAdapter().notifyItemRemoved(position);
            AdminActivity.recyclerView.getAdapter().notifyItemRangeChanged(position, postsArrayList.size());
        },error -> {

        }){
            @Override
            protected Map<String, String> getParams(){
                HashMap<String, String> map = new HashMap<>();
                map.put("id", id+"");
                map.put("image",image);
                return map;
            }
        };
        Volley.newRequestQueue(context).add(request);
    }

    private void updatePost(Posts posts, int position) {
    }

    private void showDetail(Posts posts, int position) {
        Intent intentShowDetail = new Intent(context, PostViewActivity.class);
        posts.setPosition(position);
        intentShowDetail.putExtra("posts",posts);
        context.startActivity(intentShowDetail);
    }

    public AdminPostsAdapter(Context context, ArrayList<Posts> postsArrayList){
        this.context = context;
        this.postsArrayList = postsArrayList;
    }

    @Override
    public int getItemCount() {
        return postsArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView item_tv_title, item_tv_text,item_tv_readMore;
        ImageView item_iv_image;
        LinearLayout itemLayout;

        public ViewHolder(@NonNull View view){
            super(view);
            item_tv_title = view.findViewById(R.id.item_tv_title);
            item_tv_text = view.findViewById(R.id.item_tv_title);
            item_tv_readMore = view.findViewById(R.id.readMore);
            item_iv_image = view.findViewById(R.id.item_iv_image);
            itemLayout = view.findViewById(R.id.item_layout_post);
        }
    }

}