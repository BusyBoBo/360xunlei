package com.czg.xunlei.http.request;

import com.czg.xunlei.Config;
import com.czg.xunlei.http.response.Response;
import com.czg.xunlei.http.response.ThumbResponse;
import com.czg.xunlei.model.ThumbModel;

import java.util.List;

import okhttp3.Request;
import okhttp3.ResponseBody;

/**
 * Created by 78101 on 2017/9/18.
 */

public class HomeRequest extends ApiRequest<List<ThumbModel>> {
    private String api;
    private int page;

    public HomeRequest(String api, int page) {
        this.api = api;
        this.page = page;
    }

    @Override
    public Request getRequest() {
        Request request = new Request.Builder().get().url(Config.HOST + api + "?page=" + page).build();
        return request;
    }

    @Override
    public Response<List<ThumbModel>> getResponse(ResponseBody responseBody) {
        return new ThumbResponse(responseBody);
    }
}