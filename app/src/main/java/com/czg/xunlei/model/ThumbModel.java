package com.czg.xunlei.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author ：czg
 * @class ：ThumbModel.class
 * @date ：2017/9/14.
 * @describe ：TODO(input describe)
 */
@Entity
public class ThumbModel {
    @Id
    private Long id;
    @Property(nameInDb = "API")
    private String api;
    @Property(nameInDb = "TITLE")
    private String title;
    @Property(nameInDb = "IMAGE")
    private String image;
    @Property(nameInDb = "searchid")
    private String searchId;
    @Property(nameInDb = "RATIO")
    private Float ratio;
    @Generated(hash = 553348660)
    public ThumbModel(Long id, String api, String title, String image,
            String searchId, Float ratio) {
        this.id = id;
        this.api = api;
        this.title = title;
        this.image = image;
        this.searchId = searchId;
        this.ratio = ratio;
    }
    @Generated(hash = 973760115)
    public ThumbModel() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getApi() {
        return this.api;
    }
    public void setApi(String api) {
        this.api = api;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getImage() {
        return this.image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public String getSearchId() {
        return this.searchId;
    }
    public void setSearchId(String searchId) {
        this.searchId = searchId;
    }
    public Float getRatio() {
        return this.ratio;
    }
    public void setRatio(Float ratio) {
        this.ratio = ratio;
    }


}
