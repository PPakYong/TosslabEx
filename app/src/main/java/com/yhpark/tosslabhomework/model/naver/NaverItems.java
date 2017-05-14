package com.yhpark.tosslabhomework.model.naver;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ppyh0 on 2017-05-14.
 */

public class NaverItems {
    @SerializedName("title")
    public String title;
    @SerializedName("link")
    public String link;
    @SerializedName("thumbnail")
    public String thumbnail;
    @SerializedName("sizeheight")
    public String sizeheight;
    @SerializedName("sizewidth")
    public String sizewidth;
}