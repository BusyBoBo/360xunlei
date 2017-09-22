package com.czg.xunlei.model;

/**
 * @author ：czg
 * @class ：CarToonDetail.class
 * @date ：2017/9/22.
 * @describe ：TODO(input describe)
 */
public class CarToonDetail {
    private String url;
    private String name;
    private int page;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
