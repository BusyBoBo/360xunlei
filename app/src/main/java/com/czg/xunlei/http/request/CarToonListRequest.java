package com.czg.xunlei.http.request;

import android.text.TextUtils;

import com.czg.xunlei.http.response.CarToonListResponse;
import com.czg.xunlei.http.response.Response;
import com.czg.xunlei.model.CarToonModel;

import okhttp3.Request;
import okhttp3.ResponseBody;

/**
 * @author ：czg
 * @class ：CarToonRequest.class
 * @date ：2017/9/22.
 * @describe ：TODO(input describe)
 */
public class CarToonListRequest extends ApiRequest<CarToonModel> {
    private static final String HOST = "http://m.5dmh.net/";
    private final String mUrl;

    //http://m.5dmh.net/shaonvmanhua/list_7_2.html
    public CarToonListRequest(String api, String page) {
        mUrl = HOST + api + (TextUtils.isEmpty(page) ? "" : "/" + page);
    }

    @Override
    public Request getRequest() {
        return new Request.Builder().get().url(mUrl).build();
    }

    @Override
    public Response getResponse(ResponseBody responseBody) {
        return new CarToonListResponse(responseBody);
    }
}
