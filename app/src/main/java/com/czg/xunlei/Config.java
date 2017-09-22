package com.czg.xunlei;

import com.czg.xunlei.model.ApiModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 78101 on 2017/9/13.
 */

public class Config {
    public static final String THEME = "theme";
    public static final int THEME_LIGHT = 1;
    public static final int THEME_DARK = 2;
    public static final List<ApiModel> API = new ArrayList<>();

    static {
        API.add(new ApiModel("漫画", ""));
        API.add(new ApiModel("新话题", "vl_update.php"));
        API.add(new ApiModel("新发行", "vl_newrelease.php"));
        API.add(new ApiModel("新加入", "vl_newentries.php"));
        API.add(new ApiModel("最想要", "vl_mostwanted.php"));
        API.add(new ApiModel("高评价", "vl_bestrated.php"));
    }

    public static String HOST = "http://www.ja14b.com/cn/";


}
