package com.company.phuminh.phuminh.Model;

import java.util.List;

/**
 * Created by THANHMAP on 12/1/2017.
 */

public class WebSite {
    public String status;
    public List<Source> sourceList;

    public WebSite() {
    }

    public WebSite(String status, List<Source> sourceList) {
        this.status = status;
        this.sourceList = sourceList;
    }
}
