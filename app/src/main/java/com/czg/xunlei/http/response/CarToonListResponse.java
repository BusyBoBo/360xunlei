package com.czg.xunlei.http.response;

import com.czg.xunlei.model.CarToonModel;

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
 * @class ：CarToonResponse.class
 * @date ：2017/9/22.
 * @describe ：TODO(input describe)
 */
public class CarToonListResponse extends Response<CarToonModel> {

    public CarToonListResponse(ResponseBody responseBody) {
        super(responseBody);
    }

    @Override
    public CarToonModel getBody() throws IOException {
        CarToonModel carToonModel = new CarToonModel();

        List<CarToonModel.CarToonBean> carToonModels = new ArrayList<>();
        List<CarToonModel.CarToonPage> carToonPages = new ArrayList<>();
        String xml = mResponseBody.string();
        Document doc = Jsoup.parse(xml);
        Elements picBoxs = doc.getElementsByClass("picBox");
        for (Element picBox : picBoxs) {
            CarToonModel.CarToonBean toonModel = new CarToonModel.CarToonBean();
            toonModel.setApi(picBox.select("a").attr("href"));
            toonModel.setImage(picBox.select("img").attr("src"));
            toonModel.setTitle(picBox.text());
            carToonModels.add(toonModel);
        }

        Elements options = doc.select("option");
        for (Element option : options) {
            CarToonModel.CarToonPage carToonPage = new CarToonModel.CarToonPage();
            carToonPage.setApi(option.attr("value"));
            carToonPage.setPage(option.text());
            carToonPages.add(carToonPage);
        }
        carToonModel.setCarToonBeen(carToonModels);
        carToonModel.setPages(carToonPages);
        return carToonModel;
    }
}
