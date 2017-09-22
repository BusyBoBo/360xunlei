package com.czg.xunlei.http.response;

import android.text.TextUtils;

import com.czg.xunlei.model.CastModel;
import com.czg.xunlei.model.DirectorModel;
import com.czg.xunlei.model.LabelModel;
import com.czg.xunlei.model.MakerModel;
import com.czg.xunlei.model.TypeModel;
import com.czg.xunlei.model.VideoDetailModel;
import com.czg.xunlei.utils.LogUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;

/**
 * Created by 78101 on 2017/9/13.
 */

public class DetailResponse extends Response<VideoDetailModel> {
    public DetailResponse(ResponseBody responseBody) {
        super(responseBody);
    }

    @Override
    public VideoDetailModel getBody() throws IOException {
        VideoDetailModel detailModel = new VideoDetailModel();
        String xml = mResponseBody.string();
        Document doc = Jsoup.parse(xml);
        LogUtils.xml(xml);
        detailModel.setTitle(doc.getElementById("video_title").text());
        Element video_jacket_img = doc.getElementById("video_jacket_img");
        detailModel.setImage(video_jacket_img.html());
        String width = video_jacket_img.attr("width");
        String height = video_jacket_img.attr("height");
        try {
            float radio = Float.parseFloat(width) / Float.parseFloat(height);
            detailModel.setRatio(radio);
        } catch (Exception ex) {
            detailModel.setRatio(1);
        }

        Element video_jacket = doc.getElementById("video_jacket");
        Elements img = video_jacket.select("img");
        detailModel.setImage(img.attr("src"));

        Element video_jacket_info = doc.getElementById("video_jacket_info");
        Element video_id = video_jacket_info.getElementById("video_id");
        Elements searchId = video_id.getElementsByClass("text");
        detailModel.setSearchId(searchId.text());

        Element video_date = video_jacket_info.getElementById("video_date");
        detailModel.setDate(video_date.getElementsByClass("text").text());

        Element video_length = video_jacket_info.getElementById("video_length");
        detailModel.setLength(video_length.getElementsByClass("text").text());

        Element video_director = video_jacket_info.getElementById("video_director");
        String director_name = video_director.getElementsByClass("text").text();
        if (!TextUtils.isEmpty(director_name)) {
            String api = video_director.select("a").attr("href");
            DirectorModel director = new DirectorModel();
            director.setApi(api);
            director.setName(director_name);
            detailModel.setDirector(director);
        }

        Element video_maker = video_jacket_info.getElementById("video_maker");
        String video_maker_name = video_maker.getElementsByClass("text").text();
        if (!TextUtils.isEmpty(video_maker_name)) {
            String api = video_maker.select("a").attr("href");
            MakerModel maker = new MakerModel();
            maker.setApi(api);
            maker.setName(video_maker_name);
            detailModel.setMaker(maker);
        }


        Element video_label = video_jacket_info.getElementById("video_label");
        String video_label_name = video_label.getElementsByClass("text").text();
        if (!TextUtils.isEmpty(video_label_name)) {
            String api = video_label.select("a").attr("href");
            LabelModel label = new LabelModel();
            label.setApi(api);
            label.setName(video_label_name);
            detailModel.setLabel(label);
        }
        Element video_genre = video_jacket_info.getElementById("video_genres");
        Elements video_genres = video_genre.select("span");
        ArrayList<TypeModel> typeModels = new ArrayList<>();
        if (!video_genres.isEmpty()) {
            for (Element genre : video_genres) {
                TypeModel typeModel = new TypeModel();
                typeModel.setName(genre.text());
                typeModel.setApi(genre.select("a").attr("href"));
                typeModels.add(typeModel);
            }
        }
        detailModel.setTypes(typeModels);


        Element video_cast = video_jacket_info.getElementById("video_cast");
        Elements video_casts = video_cast.getElementsByClass("star");
        ArrayList<CastModel> castModels = new ArrayList<>();
        if (!video_casts.isEmpty()) {
            for (Element genre : video_casts) {
                CastModel castModel = new CastModel();
                castModel.setName(genre.text());
                castModel.setApi(genre.select("a").attr("href"));
                castModels.add(castModel);
            }
        }
        detailModel.setCasts(castModels);

        return detailModel;
    }
}
