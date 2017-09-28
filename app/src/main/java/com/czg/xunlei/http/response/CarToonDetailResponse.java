package com.czg.xunlei.http.response;

import com.czg.xunlei.model.CarToonDetail;
import com.czg.xunlei.utils.LogUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

import okhttp3.ResponseBody;

/**
 * @author ：czg
 * @class ：CarToonDetailResponse.class
 * @date ：2017/9/22.
 * @describe ：TODO(input describe)
 */
public class CarToonDetailResponse extends Response<CarToonDetail> {
    public CarToonDetailResponse(ResponseBody responseBody) {
        super(responseBody);
    }

    @Override
    public CarToonDetail getBody() throws IOException {
        String xml = mResponseBody.string();
        Document doc = Jsoup.parse(xml);
        CarToonDetail carToonDetail = null;
        Elements showpages = doc.getElementsByClass("showpage");
        Elements primary = doc.getElementsByClass("primary");
        if (showpages.size() > 0) {
            try {
                int page = 0;
                try {
                    String num = showpages.get(0).select("a").get(0).text().replace("共", "").replace("页", "").replace(":", "").trim();
                    page = Integer.parseInt(num);
                } catch (Exception e) {
                    page = 1;
                }


                carToonDetail = new CarToonDetail();
                carToonDetail.setPage(page);
                carToonDetail.setName(primary.select("img").attr("alt"));
                String attr = primary.select("img").attr("src");
                if (attr != null && attr.contains("tu1")) {
                    carToonDetail.setUrl(attr.replace("1.jpg", ""));
                } else {
                    carToonDetail.setPage(0);
                }

                LogUtils.e(carToonDetail.getUrl());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return carToonDetail;
    }
}
