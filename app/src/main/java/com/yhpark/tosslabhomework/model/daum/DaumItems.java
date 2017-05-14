package com.yhpark.tosslabhomework.model.daum;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ppyh0 on 2017-05-14.
 */

public class DaumItems {
    @SerializedName("item")
    public String item; //	개별 검색 결과 정보
    @SerializedName("title")
    public String title; //개별 검색 결과의 제목
    @SerializedName("link")
    public String link; //개별 검색 결과의 link url
    @SerializedName("image")
    public String image; //이미지 URL
    @SerializedName("thumbnail")
    public String thumbnail; //썸네일 URL
    @SerializedName("width")
    public String width; //이미지의 가로 크기
    @SerializedName("height")
    public String height; //이미지의 세로 크기
    @SerializedName("pubDate")
    public String pubDate; //등록일
    @SerializedName("cpname")
    public String cpname; //컨텐츠 제공처
}
