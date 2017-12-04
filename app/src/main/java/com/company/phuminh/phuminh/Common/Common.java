package com.company.phuminh.phuminh.Common;

import com.company.phuminh.phuminh.Interface.NewService;
import com.company.phuminh.phuminh.Remote.RetrofitClient;

/**
 * Created by THANHMAP on 12/1/2017.
 */

public class Common {
    private static final String BASE_URL = "https://newsapi.org/";
    public static final String API_KEY = "3ed662fd303145aaa9bd93d2b15875f1";
    public static NewService getnewNewService(){
        return RetrofitClient.getClient(BASE_URL).create(NewService.class);
    }
}
