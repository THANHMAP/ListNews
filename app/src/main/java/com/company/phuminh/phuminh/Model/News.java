package com.company.phuminh.phuminh.Model;

import java.util.List;

/**
 * Created by THANHMAP on 12/18/2017.
 */

public class News {
    private String status, source, sortBy;
    private List<Article> articles;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public List<Article> getArticle() {
        return articles;
    }

    public void setArticle(List<Article> article) {
        this.articles = article;
    }
}
