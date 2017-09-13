package com.czg.xunlei.http.request;

import com.czg.xunlei.http.response.Response;

import okhttp3.Request;
import okhttp3.ResponseBody;

/**
 * @author ：czg
 * @class ：ApiRequest.class
 * @date ：2017/9/12.
 * @describe ：TODO(input describe)
 */
public abstract class ApiRequest<T> {
    public abstract Request getRequest();



    public abstract  Response<T> getResponse(ResponseBody responseBody);
}
