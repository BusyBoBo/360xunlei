package com.czg.xunlei.http.response;

import com.czg.xunlei.model.TypeModel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;

/**
 * @author ：czg
 * @class ：TypeResponse.class
 * @date ：2017/9/14.
 * @describe ：类型
 */
public class TypeResponse extends Response<List<TypeModel>> {
    public TypeResponse(ResponseBody responseBody) {
        super(responseBody);
    }

    @Override
    public List<TypeModel> getBody() throws IOException {
        List<TypeModel> typeModels = new ArrayList<>();
        String xml = mResponseBody.string();
        Document doc = Jsoup.parse(xml);
        Element rightcolumn = doc.getElementById("rightcolumn");
        Elements genreitems = rightcolumn.getElementsByClass("genreitem");
        for (Element genreitem : genreitems) {
            TypeModel typeModel = new TypeModel();
            typeModel.setName(genreitem.text());
            typeModel.setApi(genreitem.select("a").attr("href"));
            typeModels.add(typeModel);
        }
        return typeModels;
    }
}
