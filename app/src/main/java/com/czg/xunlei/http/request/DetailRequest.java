package com.czg.xunlei.http.request;

import com.czg.xunlei.Config;
import com.czg.xunlei.http.response.DetailResponse;
import com.czg.xunlei.http.response.Response;
import com.czg.xunlei.model.VideoDetailModel;

import okhttp3.Request;
import okhttp3.ResponseBody;

/**
 * Created by 78101 on 2017/9/13.
 */

public class DetailRequest extends ApiRequest<VideoDetailModel> {
    private String id;

    public DetailRequest(String id) {
        this.id = id;
    }

    @Override
    public Request getRequest() {
        Request request = new Request.Builder().get().url(Config.HOST+"?v="+id).build();
        return request;
    }

    @Override
    public Response<VideoDetailModel> getResponse(ResponseBody responseBody) {
        return new DetailResponse(responseBody);
    }
}
