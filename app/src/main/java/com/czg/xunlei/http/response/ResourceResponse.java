package com.czg.xunlei.http.response;

import java.io.IOException;

import okhttp3.ResponseBody;

/**
 * @author ：czg
 * @class ：ResourceResponse.class
 * @date ：2017/9/13.
 * @describe ：TODO(input describe)
 */
public class ResourceResponse extends Response<String> {
    public ResourceResponse(ResponseBody responseBody) {
        super(responseBody);
    }

    @Override
    public String getBody() throws IOException {
        return mResponseBody.string();
    }

}
