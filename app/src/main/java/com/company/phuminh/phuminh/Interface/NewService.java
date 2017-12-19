package com.company.phuminh.phuminh.Interface;

import com.company.phuminh.phuminh.Model.News;
import com.company.phuminh.phuminh.Model.WebSite;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by THANHMAP on 12/1/2017.
 */

public interface NewService {
    @GET("v1/sources?language=en")
    Call<WebSite> getSources();

    @GET
    Call<News> getNewsArticle(@Url String url);
}
