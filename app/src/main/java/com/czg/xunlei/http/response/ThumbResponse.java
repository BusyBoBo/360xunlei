package com.czg.xunlei.http.response;

import android.text.TextUtils;

import com.czg.xunlei.model.ThumbModel;

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
 * @class ：ThumbResponse.class
 * @date ：2017/9/14.
 * @describe ：ThumbResponse
 */
public class ThumbResponse extends Response<List<ThumbModel>> {


    public ThumbResponse(ResponseBody responseBody) {
        super(responseBody);
    }

    @Override
    public List<ThumbModel> getBody() throws IOException {
        List<ThumbModel> thumbModels = new ArrayList<>();
        String xml = mResponseBody.string();
        Document doc = Jsoup.parse(xml);
        Elements videos = doc.getElementsByClass("video");
        for (Element video : videos) {
            ThumbModel thumbModel = new ThumbModel();
            String api = video.select("a").attr("href");
            thumbModel.setApi(api.subSequence(api.indexOf("v=") + 2, api.length()).toString());
            thumbModel.setTitle(video.select("a").attr("title"));
            String onerror = video.select("img").attr("onerror");
            if (!TextUtils.isEmpty(onerror)) {
                onerror = onerror.replaceAll("ThumbError", "");
                onerror = onerror.replaceAll("'", "");
                onerror = onerror.replaceAll("\\(", "");
                onerror = onerror.replaceAll("\\)", "");
                onerror = onerror.replaceAll("this", "");
                onerror = onerror.replaceAll(";", "");
                onerror = onerror.replaceAll(",", "");
                onerror = onerror.trim();
                if (onerror.contains("Error")) {
                    thumbModel.setImage("http:"+video.select("img").attr("src"));
                } else {
                    thumbModel.setImage("http:"+onerror);
                }


            } else {
                thumbModel.setImage("http:"+video.select("img").attr("src"));
            }
            try {
                ;
                float radio = Float.parseFloat(video.select("img").attr("width")) / Float.parseFloat(video.select("img").attr("height"));
                thumbModel.setRatio(radio);
            } catch (Exception ex) {
                thumbModel.setRatio(1f);
            }
            if (video.select("div").size() >= 2) {
                thumbModel.setSearchId(video.select("div").get(1).text());
            }
            thumbModels.add(thumbModel);
        }
        return thumbModels;
    }
}
