package com.czg.xunlei.model;

import java.util.List;

/**
 * @author ：czg
 * @class ：CarToonModel.class
 * @date ：2017/9/22.
 * @describe ：TODO(input describe)
 */
public class CarToonModel {
    List<CarToonBean> mCarToonBeen;
    private List<CarToonPage> pages;

    public List<CarToonBean> getCarToonBeen() {
        return mCarToonBeen;
    }

    public void setCarToonBeen(List<CarToonBean> carToonBeen) {
        mCarToonBeen = carToonBeen;
    }

    public List<CarToonPage> getPages() {
        return pages;
    }

    public void setPages(List<CarToonPage> pages) {
        this.pages = pages;
    }

    public static class CarToonPage {

        String api;
        String page;

        public String getApi() {
            return api;
        }

        public void setApi(String api) {
            this.api = api;
        }

        public String getPage() {
            return page;
        }

        public void setPage(String page) {
            this.page = page;
        }
    }

    public static class CarToonBean {
        private String title;
        private String image;
        private String api;

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

    }
}
