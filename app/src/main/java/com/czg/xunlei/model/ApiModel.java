package com.czg.xunlei.model;

/**
 * Created by 78101 on 2017/9/18.
 */

public class ApiModel {
    private String api;
    private String name;

    public ApiModel(String name, String api) {
        this.name = name;
        this.api = api;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
