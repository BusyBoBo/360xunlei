package com.czg.xunlei.http.request;

import com.czg.xunlei.http.response.ResourceResponse;
import com.czg.xunlei.http.response.Response;

import okhttp3.Request;
import okhttp3.ResponseBody;

/**
 * @author ：czg
 * @class ：ResourceRequest.class
 * @date ：2017/9/13.
 * @describe ：TODO(input describe)
 */
public class ResourceRequest extends ApiRequest<String> {
    @Override
    public Request getRequest() {
        return new Request.Builder().get().url("http://www.javlibrary.com/cn/").build();
    }

    @Override
    public Response<String> getResponse(ResponseBody responseBody) {
        return new ResourceResponse(responseBody);
    }
}
