package com.czg.xunlei.model;

import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by czg on 2017/9/25.
 */
@Entity
public class CarToonBean {
    @Id
    private Long id;
    @Property(nameInDb = "TITLE")
    private String title;
    @Property(nameInDb = "IMAGE")
    private String image;
    @Property(nameInDb = "API")
    private String api;

    @Generated(hash = 676449377)
    public CarToonBean(Long id, String title, String image, String api) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.api = api;
    }

    @Generated(hash = 1931111479)
    public CarToonBean() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
