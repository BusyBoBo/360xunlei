package com.czg.xunlei.model;

import java.util.List;

/**
 * @author ：czg
 * @class ：XunleiModel.class
 * @date ：2017/9/12.
 * @describe ：TODO(input describe)
 */
public class XunLeiModel {
    private String tittle;
    private List<String> subtitles;
    private String type;
    private String createTime;
    private String size;
    private String downloadCount;
    private String recentlyDownloaded;//最近下载
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(String downloadCount) {
        this.downloadCount = downloadCount;
    }

    public String getRecentlyDownloaded() {
        return recentlyDownloaded;
    }

    public void setRecentlyDownloaded(String recentlyDownloaded) {
        this.recentlyDownloaded = recentlyDownloaded;
    }

    public List<String> getSubtitles() {
        return subtitles;
    }

    public void setSubtitles(List<String> subtitles) {
        this.subtitles = subtitles;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    @Override
    public String toString() {
        return "XunLeiModel{" +
                "tittle='" + tittle + '\n' +
                ", subtitles=" + subtitles +
                ", type='" + type + '\n' +
                ", createTime='" + createTime + '\n' +
                ", size='" + size + '\n' +
                ", downloadCount='" + downloadCount + '\n' +
                ", recentlyDownloaded='" + recentlyDownloaded + '\n' +
                ", url='" + url + '\n' +
                '}';
    }
}
