package com.czg.xunlei.http.request;

import com.czg.xunlei.http.response.ResourceResponse;
import com.czg.xunlei.http.response.Response;

import okhttp3.Request;
import okhttp3.ResponseBody;

/**
 * @author ：czg
 * @class ：ResourceRequest.class
 * @date ：2017/9/13.
<<<<<<< HEAD
 * @describe ：ResourceRequest
=======
 * @describe ：TODO(input describe)
>>>>>>> 8b879dcb41530da740b49be92ba66a553c1a6292
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
