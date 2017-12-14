package com.company.phuminh.phuminh;

import android.graphics.pdf.PdfDocument;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.company.phuminh.phuminh.Adapter.ListSourceAdapter;
import com.company.phuminh.phuminh.Common.Common;
import com.company.phuminh.phuminh.Interface.NewService;
import com.company.phuminh.phuminh.Model.WebSite;
import com.google.gson.Gson;

import dmax.dialog.SpotsDialog;
import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView listWebsite;
    RecyclerView.LayoutManager layoutManager;
    NewService newService;
    ListSourceAdapter sourceAdapter;
    SpotsDialog alertDialog;
    SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Init cache
        Paper.init(this);
        // Init Service
        newService = Common.getnewNewService();
        //Init View
        swipeRefreshLayout = findViewById(R.id.swipeRefresh);
        listWebsite = findViewById(R.id.list_source);
        listWebsite.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        listWebsite.setLayoutManager(layoutManager);

        alertDialog = new SpotsDialog(this);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadWebSiteSource(true);
            }
        });
        loadWebSiteSource(false);
    }

    private void loadWebSiteSource(boolean b) {
        if(!b){
            String cache = Paper.book().read("cache");
            if(cache != null && !cache.isEmpty())// have cache
            {
                WebSite webSite = new Gson().fromJson(cache, WebSite.class); // convert cache form json to object
                sourceAdapter = new ListSourceAdapter(this, webSite); // get data
                sourceAdapter.notifyDataSetChanged();
                listWebsite.setAdapter(sourceAdapter);
            }else {
                alertDialog.show();
                newService.getSources().enqueue(new Callback<WebSite>() {
                    @Override
                    public void onResponse(Call<WebSite> call, Response<WebSite> response) {
                        sourceAdapter = new ListSourceAdapter(getBaseContext(), response.body());
                        sourceAdapter.notifyDataSetChanged();
                        listWebsite.setAdapter(sourceAdapter);

                        // save cache adapter
                        Paper.book().write("cache", new Gson().toJson(response.body()));
                        // dismiss refesh
                        swipeRefreshLayout.setRefreshing(true);
                    }

                    @Override
                    public void onFailure(Call<WebSite> call, Throwable t) {

                    }
                });
            }
        }else // if swipe refes
        {
            alertDialog.show();
            newService.getSources().enqueue(new Callback<WebSite>() {
                @Override
                public void onResponse(Call<WebSite> call, Response<WebSite> response) {
                    sourceAdapter = new ListSourceAdapter(getBaseContext(), response.body());
                    sourceAdapter.notifyDataSetChanged();
                    listWebsite.setAdapter(sourceAdapter);

                    // save cache adapter
                    Paper.book().write("cache", new Gson().toJson(response.body()));

                }

                @Override
                public void onFailure(Call<WebSite> call, Throwable t) {

                }
            });
        }
    }

}
