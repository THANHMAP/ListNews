package com.company.phuminh.phuminh.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.company.phuminh.phuminh.Common.ISO8601DateParser;
import com.company.phuminh.phuminh.DetailActicleActivity;
import com.company.phuminh.phuminh.Interface.ItemClickListener;
import com.company.phuminh.phuminh.ListNewActivity;
import com.company.phuminh.phuminh.R;
import com.github.curioustechizen.ago.RelativeTimeTextView;
import com.company.phuminh.phuminh.Model.Article;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by THANHMAP on 12/18/2017.
 */

class ListNewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    ItemClickListener itemClickListener;
    TextView article_title;
    RelativeTimeTextView article_time;
    CircleImageView article_image;

    public ListNewsViewHolder(View itemView) {
        super(itemView);
        article_image = itemView.findViewById(R.id.actical_image);
        article_title = itemView.findViewById(R.id.actical_title);
        article_time = itemView.findViewById(R.id.actical_time);

        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v, getAdapterPosition(), false);
    }
}

public class ListNewAdapter extends RecyclerView.Adapter<ListNewsViewHolder> {

    private List<Article> articleList;
    private Context context;

    public ListNewAdapter(List<Article> articleList, Context context) {
        this.articleList = articleList;
        this.context = context;
    }

    @Override
    public ListNewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.new_layout, parent, false);
        return new ListNewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListNewsViewHolder holder, final int position) {
        Picasso.with(context)
                .load(articleList.get(position).getUrlToImage())
                .into(holder.article_image);
        if(articleList.get(position).getTitle().length() > 65){
            holder.article_title.setText(articleList.get(position).getTitle().substring(0,65) +"...");
        }else holder.article_title.setText(articleList.get(position).getTitle());
        Date date = null;
        if(articleList.get(position).getPublishedAt() != null){
            try {

                date = ISO8601DateParser.parse(articleList.get(position).getPublishedAt());
            }catch (ParseException ex){
                ex.printStackTrace();
            }
            holder.article_time.setReferenceTime(date.getTime());

        }

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int postion, boolean isLongClick) {
                Intent intent = new Intent(context, DetailActicleActivity.class);
                intent.putExtra("webURL", articleList.get(position).getUrl());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }
}
