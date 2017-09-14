package com.czg.xunlei.model;

import java.util.List;

/**
 * Created by 78101 on 2017/9/13.
 */

public class VideoDetailModel {
    private String title;
    private String image;
    private String searchId;
    private String date;
    private String length;
    private DirectorModel director;
    private List<TypeModel> types;
    private List<CastModel> casts;
    private MakerModel maker;
    private LabelModel label;
    private float ratio;

    public List<CastModel> getCasts() {
        return casts;
    }

    public void setCasts(List<CastModel> casts) {
        this.casts = casts;
    }

    public float getRatio() {
        return ratio;
    }

    public void setRatio(float ratio) {
        this.ratio = ratio;
    }

    public LabelModel getLabel() {
        return label;
    }

    public void setLabel(LabelModel label) {
        this.label = label;
    }

    public MakerModel getMaker() {
        return maker;
    }

    public void setMaker(MakerModel maker) {
        this.maker = maker;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return "http:"+image;
    }

    public String getSearchId() {
        return searchId;
    }

    public void setSearchId(String searchId) {
        this.searchId = searchId;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getLength() {
        return length;
    }

    public void setDirector(DirectorModel director) {
        this.director = director;
    }

    public DirectorModel getDirector() {
        return director;
    }

    public List<TypeModel> getTypes() {
        return types;
    }

    public void setTypes(List<TypeModel> types) {
        this.types = types;
    }

    @Override
    public String toString() {
        return "VideoDetailModel{" +
                "title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", searchId='" + searchId + '\'' +
                ", date='" + date + '\'' +
                ", length='" + length + '\'' +
                ", director='" + director + '\'' +
                '}';
    }
}
