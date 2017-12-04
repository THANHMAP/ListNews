package com.company.phuminh.phuminh.Interface;

import com.company.phuminh.phuminh.Model.WebSite;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by THANHMAP on 12/1/2017.
 */

public interface NewService {
    @GET("v2/sources?apiKey={3ed662fd303145aaa9bd93d2b15875f1}")
    Call<WebSite> getSources();
}
