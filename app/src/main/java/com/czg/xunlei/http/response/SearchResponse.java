package com.czg.xunlei.http.response;

import com.czg.xunlei.model.XunLeiModel;

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
 * @class ：SearchResponse.class
 * @date ：2017/9/12.
 * @describe ：TODO(input describe)
 */
public class SearchResponse extends Response<List<XunLeiModel>> {

    public SearchResponse(ResponseBody responseBody) {
        super(responseBody);
    }

    @Override
    public List<XunLeiModel> getBody() throws IOException {
        return parse(mResponseBody.string());
    }

    private List<XunLeiModel> parse(String xml) {
        Document doc = Jsoup.parse(xml);
        Element content = doc.getElementById("content");
        Elements links = content.getElementsByClass("search-item");
        List<XunLeiModel> models = new ArrayList<>();
        try {
            for (Element link : links) {
                XunLeiModel model = new XunLeiModel();
                Elements tittle = link.getElementsByClass("item-title");
                if (tittle.size() > 0) {
                    model.setTittle(tittle.get(0).text());
                }
                Elements tittle_list = link.getElementsByClass("item-list");
                ArrayList<String> subtitleArray = new ArrayList<>();
                for (Element subtitle : tittle_list) {
                    subtitleArray.add(subtitle.text());
                }
                model.setSubtitles(subtitleArray);

                Elements item_bar = link.getElementsByClass("item-bar");
                Elements 文件大小 = link.getElementsByClass("文件大小");
//            String linkHref = link.attr("href");
                //  LogUtils.e(item_bar.select("span")..text());
                Elements info = item_bar.select("span");
                Elements info2 = item_bar.select("a");
                model.setType(info.first().text());
                model.setCreateTime(info.select("b").get(0).text());
                model.setSize(info.select("b").get(1).text());
                model.setDownloadCount(info.select("b").get(2).text());
                model.setRecentlyDownloaded(info.select("b").get(3).text());
                model.setUrl(info2.attr("href"));
                models.add(model);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return models;

    }
}
