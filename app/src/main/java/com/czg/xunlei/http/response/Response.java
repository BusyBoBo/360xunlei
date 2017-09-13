package com.czg.xunlei.http.response;

import java.io.IOException;

import okhttp3.ResponseBody;

/**
 * @author ：czg
 * @class ：Response.class
 * @date ：2017/9/12.
 * @describe ：TODO(input describe)
 */
public abstract class Response<T> {
    protected final ResponseBody mResponseBody;


    public Response(ResponseBody responseBody) {
        this.mResponseBody = responseBody;
    }

    public abstract T getBody() throws IOException;

}
