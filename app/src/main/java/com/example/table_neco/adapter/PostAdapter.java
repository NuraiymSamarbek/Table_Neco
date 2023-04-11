package com.example.table_neco.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.table_neco.NewPost;
import com.example.table_neco.R;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolderData> {
    private List<NewPost> arrayPost;
    private Context context;
    private OnItemClickCustom onItemClickCustom;

    public PostAdapter(List<NewPost> arrayPost, Context context, OnItemClickCustom onItemClickCustom) {
        this.arrayPost = arrayPost;
        this.context = context;
        this.onItemClickCustom=onItemClickCustom;
    }

    @NonNull
    @Override
    public ViewHolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_ads,parent, false);
        return new ViewHolderData(view,onItemClickCustom);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderData holder, int position) {
        holder.setData(arrayPost.get(position));

    }

    @Override
    public int getItemCount() {
        return arrayPost.size();
    }

    public class ViewHolderData extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private TextView tvPriceTel, tvDisc, tvTitle;
        private ImageView imAds;
        private OnItemClickCustom onItemClickCustom;

        public ViewHolderData(@NonNull View itemView, OnItemClickCustom onItemClickCustom) {
            super(itemView);
            tvTitle=itemView.findViewById(R.id.tvTitle);
            tvPriceTel=itemView.findViewById(R.id.tvPriceTel);
            tvDisc=itemView.findViewById(R.id.tvDisc);
            imAds=itemView.findViewById(R.id.imAds);
            this.onItemClickCustom=onItemClickCustom;

            itemView.setOnClickListener(this);
        }
        public void setData(NewPost newPost)
        {
            tvTitle.setText(newPost.getTitle());
            String price_tel="Цена :"+newPost.getPrice()+" Телефон :"+newPost.getTel();
            tvPriceTel.setText(price_tel);
        }

        @Override
        public void onClick(View v) {
            onItemClickCustom.onItemSelected(getAdapterPosition());

        }
    }

    public interface OnItemClickCustom
    {
        public void onItemSelected(int position);
    }
    public void updateAdapter(List<NewPost> listData)
    {
        arrayPost.clear();
        arrayPost.addAll(listData);
        notifyDataSetChanged();
    }
}
