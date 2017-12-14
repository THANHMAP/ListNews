package com.company.phuminh.phuminh.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.company.phuminh.phuminh.Common.Common;
import com.company.phuminh.phuminh.Interface.IconBetterIdeaService;
import com.company.phuminh.phuminh.Interface.ItemClickListener;
import com.company.phuminh.phuminh.Model.IconBetterIdead;
import com.company.phuminh.phuminh.Model.WebSite;
import com.company.phuminh.phuminh.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by THANHMAP on 12/13/2017.
 */

class ListSourceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    ItemClickListener itemClickListener;
    TextView source_name;
    CircleImageView source_image;
    public ListSourceViewHolder(View itemView) {
        super(itemView);
        source_image = itemView.findViewById(R.id.source_image);
        source_name = itemView.findViewById(R.id.source_name);

    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v, getAdapterPosition(), false);
    }
}

public class ListSourceAdapter extends RecyclerView.Adapter<ListSourceViewHolder>{

    private Context context;
    private WebSite webSite;
    private IconBetterIdeaService mService;
    public ListSourceAdapter(Context context, WebSite webSite) {
        this.context = context;
        this.webSite = webSite;
        mService = Common.getIconBetterIdeaService();
    }

    @Override
    public ListSourceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.source_layout, parent, false);
        return new ListSourceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ListSourceViewHolder holder, final int position) {
        StringBuilder stringBuilder =  new StringBuilder("https://icons.better-idea.org/allicon.json?url=");
        stringBuilder.append(webSite.getSourceList().get(position).getUrl());
        mService.getIconUrl(stringBuilder.toString())
                .enqueue(new Callback<IconBetterIdead>() {
                    @Override
                    public void onResponse(Call<IconBetterIdead> call, Response<IconBetterIdead> response) {
                        if(response.body().getIconList().size() > 0){
                            Picasso.with(context)
                                    .load(response.body().getIconList().get(0).getUrl())
                                    .into(holder.source_image);
                        }
                    }

                    @Override
                    public void onFailure(Call<IconBetterIdead> call, Throwable t) {

                    }
                });
        holder.source_name.setText(webSite.getSourceList().get(position).getName());
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int postion, boolean isLongClick) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return webSite.getSourceList().size();
    }
}
