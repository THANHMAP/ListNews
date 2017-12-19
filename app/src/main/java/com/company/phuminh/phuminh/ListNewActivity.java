package com.company.phuminh.phuminh;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.company.phuminh.phuminh.Adapter.ListNewAdapter;
import com.company.phuminh.phuminh.Common.Common;
import com.company.phuminh.phuminh.Interface.NewService;
import com.company.phuminh.phuminh.Model.Article;
import com.company.phuminh.phuminh.Model.News;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.github.florent37.diagonallayout.DiagonalLayout;
import com.squareup.picasso.Picasso;

import java.util.List;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListNewActivity extends AppCompatActivity {

    KenBurnsView kenBurnsView;
    AlertDialog dialog;
    NewService mService;
    DiagonalLayout diagonalLayout;
    TextView topAuthor, topTitle;
    SwipeRefreshLayout swipeRefreshLayout;
    String source = "", sortBy ="", webHostURL = "";
    ListNewAdapter adapter;
    RecyclerView lstNews;
    RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_new);
        kenBurnsView = findViewById(R.id.top_image);
        topAuthor = findViewById(R.id.topAuthor);
        topTitle =  findViewById(R.id.topTitle);
        mService = Common.getnewNewService();
        dialog = new SpotsDialog(this);
        swipeRefreshLayout =  findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadNews(source, true);
            }
        });
        diagonalLayout =  findViewById(R.id.diagonalLayout);
        lstNews = findViewById(R.id.lstNews);
        lstNews.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        lstNews.setLayoutManager(layoutManager);
        diagonalLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListNewActivity.this, DetailActicleActivity.class);
                intent.putExtra("webURL", webHostURL);
                startActivity(intent);
            }
        });
        if(getIntent() != null){
            source = getIntent().getStringExtra("source");
            sortBy = getIntent().getStringExtra("sortBy");
            if(!source.isEmpty() && !sortBy.isEmpty()){
                loadNews(source, false);
            }
        }
    }

    private void loadNews(String source, boolean b) {
        if(!b){
            dialog.show();
            mService.getNewsArticle(Common.getAPIUrl(source, sortBy, Common.API_KEY)).enqueue(new Callback<News>() {
                @Override
                public void onResponse(Call<News> call, Response<News> response) {
                    dialog.dismiss();
                    Picasso.with(getBaseContext())
                            .load(response.body().getArticle().get(0).getUrlToImage())
                    .into(kenBurnsView);
                    topTitle.setText(response.body().getArticle().get(0).getTitle());
                    topAuthor.setText(response.body().getArticle().get(0).getAuthor());
                    webHostURL = response.body().getArticle().get(0).getUrl();

                    List<Article> articles = response.body().getArticle();
                    articles.remove(0);

                    adapter = new ListNewAdapter(articles, getBaseContext());
                    adapter.notifyDataSetChanged();
                    lstNews.setAdapter(adapter);
                }

                @Override
                public void onFailure(Call<News> call, Throwable t) {
                    dialog.dismiss();
                }
            });
        }else {
            dialog.show();
            mService.getNewsArticle(Common.getAPIUrl(source, sortBy, Common.API_KEY)).enqueue(new Callback<News>() {
                @Override
                public void onResponse(Call<News> call, Response<News> response) {
                    dialog.dismiss();
                    Picasso.with(getBaseContext())
                            .load(response.body().getArticle().get(0).getUrlToImage())
                            .into(kenBurnsView);
                    topTitle.setText(response.body().getArticle().get(0).getTitle());
                    topAuthor.setText(response.body().getArticle().get(0).getAuthor());
                    webHostURL = response.body().getArticle().get(0).getUrl();

                    List<Article> articles = response.body().getArticle();
                    articles.remove(0);

                    adapter = new ListNewAdapter(articles, getBaseContext());
                    adapter.notifyDataSetChanged();
                    lstNews.setAdapter(adapter);
                }

                @Override
                public void onFailure(Call<News> call, Throwable t) {
                    dialog.dismiss();
                }
            });
            swipeRefreshLayout.setRefreshing(false);
        }

    }

}
