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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Source> getSourceList() {
        return sourceList;
    }

    public void setSourceList(List<Source> sourceList) {
        this.sourceList = sourceList;
    }
}
