package com.yhpark.tosslabhomework.request;

import com.yhpark.tosslabhomework.model.daum.DaumResult;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by ppyh0 on 2017-05-14.
 */

public interface DaumRequest {

    //     * @param result  한 페이지에 출력될 결과수
    //     * @param advance 상세 검색 기능 사용 여부
    //     * @param pageno  검색 결과 페이지 번호
    //     * @param sort    검색 결과의 정렬 순서
    //     * @param output  포맷

    /**
     * @param apikey  daumkakao application api key
     * @param q       검색을 원하는 질의어
     * @param options optional parameter
     * @return
     */
    @GET("/search/image")
    Call<DaumResult> getResult(
            @Query(value = "apikey", encoded = true) String apikey,
            @Query(value = "q", encoded = true) String q,
            @QueryMap(encoded = true) Map<String, String> options
    );
}
