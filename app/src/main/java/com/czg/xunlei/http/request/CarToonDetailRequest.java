package com.czg.xunlei.http.request;

import com.czg.xunlei.http.response.CarToonDetailResponse;
import com.czg.xunlei.model.CarToonDetail;

import okhttp3.Request;
import okhttp3.ResponseBody;

/**
 * @author ：czg
 * @class ：CarToonDetailRequest.class
 * @date ：2017/9/22.
 * @describe ：TODO(input describe)
 */
public class CarToonDetailRequest extends ApiRequest<CarToonDetail> {
    private static final String HOST = "http://m.5dmh.net/";
    private final String url;

    public CarToonDetailRequest(String api) {
        url = HOST + api;
    }
    @Override
    public Request getRequest() {
        return new Request.Builder().get().url(url).build();
    }
    @Override
    public CarToonDetailResponse getResponse(ResponseBody responseBody) {
        return new CarToonDetailResponse(responseBody);
    }
}
