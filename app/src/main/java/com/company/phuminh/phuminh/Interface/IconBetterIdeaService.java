package com.company.phuminh.phuminh.Interface;

import com.company.phuminh.phuminh.Model.IconBetterIdead;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by THANHMAP on 12/13/2017.
 */

public interface IconBetterIdeaService {
    @GET
    Call<IconBetterIdead> getIconUrl(@Url String url);
}
