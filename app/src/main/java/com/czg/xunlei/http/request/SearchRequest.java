package com.czg.xunlei.http.request;

import com.czg.xunlei.http.response.Response;
import com.czg.xunlei.http.response.SearchResponse;
import com.czg.xunlei.model.XunLeiModel;

import java.util.List;

import okhttp3.Request;
import okhttp3.ResponseBody;

/**
 * @author ：czg
 * @class ：SearchRequest.class
 * @date ：2017/9/12.
 * @describe ：TODO(input describe)
 */
public class SearchRequest extends ApiRequest<List<XunLeiModel>> {
    private String content;
    private int page;//start 1
    private int type;//1 默认排序 2 创建时间 3 文件大小 4 相关度

    public SearchRequest(String content, int page, int type) {
        this.content = content;
        this.page = page;
        this.type = type;
    }//

    public String getType() {
        switch (type) {
            case 2:
                return "/time-";
            case 3:
                return "/size-";
            case 4:
                return "/rela-";
            default:
                return "/default-";
        }
    }
    @Override
    public Request getRequest() {

        Request request = new Request.Builder().get().url("http://360xunlei.com/c/" + content + getType() +page+ ".html").build();
        return request;
    }

    @Override
    public Response<List<XunLeiModel>> getResponse(ResponseBody responseBody) {
        return new SearchResponse(responseBody);
    }
}
