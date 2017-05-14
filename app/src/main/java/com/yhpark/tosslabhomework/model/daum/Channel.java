package com.yhpark.tosslabhomework.model.daum;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ppyh0 on 2017-05-14.
 */

public class Channel{
    @SerializedName("title")
    public String title; //검색 제목
    @SerializedName("link")
    public String link; //서비스 URL
    @SerializedName("description")
    public String description; //검색 결과의 간략한 소개
    @SerializedName("lastBuildDate")
    public String lastBuildDate; //검색 시간
    @SerializedName("totalCount")
    public int totalCount;    //전체 검색 결과의 수(추정치)
    @SerializedName("pageCount")
    public int pageCount;    //보여줄 수 있는 문서의 수(추정치)
    @SerializedName("result")
    public int result;    //한 페이지에 출력될 결과수
    @SerializedName("item")
    public List<DaumItems> item;  //한 페이지에 출력될 결과수
}
