package com.czg.xunlei.model;

/**
 * @author ：czg
 * @class ：ThumbModel.class
 * @date ：2017/9/14.
 * @describe ：TODO(input describe)
 */
public class ThumbModel {
    private String api;
    private String title;
    private String image;
    private String searchId;
    private float ratio;

    public float getRatio() {
        return ratio;
    }

    public void setRatio(float ratio) {
        this.ratio = ratio;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return "http:" + image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSearchId() {
        return searchId;
    }

    public void setSearchId(String searchId) {
        this.searchId = searchId;
    }
}
