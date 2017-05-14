package com.yhpark.tosslabhomework.model.naver;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ppyh0 on 2017-05-14.
 */

public class NaverResult {
    @SerializedName("total")
    public int total;
    @SerializedName("start")
    public int start;
    @SerializedName("display")
    public int display;

    @SerializedName("items")
    public List<NaverItems> item;
}
