package com.czg.xunlei.http.request;

import com.czg.xunlei.Config;
import com.czg.xunlei.http.response.Response;
import com.czg.xunlei.http.response.TypeResponse;
import com.czg.xunlei.model.TypeModel;

import java.util.List;

import okhttp3.Request;
import okhttp3.ResponseBody;

/**
 * @author ：czg
 * @class ：TypeRequest.class
 * @date ：2017/9/14.
 * @describe ：TODO(input describe)
 */
public class TypeRequest extends ApiRequest<List<TypeModel>> {


    @Override
    public Request getRequest() {
        Request request = new Request.Builder().get().url(Config.HOST + "genres.php").build();
        return request;
    }

    @Override
    public Response<List<TypeModel>> getResponse(ResponseBody responseBody) {
        return new TypeResponse(responseBody);
    }
}
