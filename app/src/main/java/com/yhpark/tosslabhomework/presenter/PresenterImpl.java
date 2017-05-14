package com.yhpark.tosslabhomework.presenter;

import com.yhpark.tosslabhomework.common.Constants;
import com.yhpark.tosslabhomework.model.SearchResult;
import com.yhpark.tosslabhomework.model.daum.DaumItems;
import com.yhpark.tosslabhomework.model.daum.DaumResult;
import com.yhpark.tosslabhomework.model.naver.NaverItems;
import com.yhpark.tosslabhomework.model.naver.NaverResult;
import com.yhpark.tosslabhomework.request.DaumRequest;
import com.yhpark.tosslabhomework.request.NaverRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ppyh0 on 2017-05-14.
 */

public class PresenterImpl implements Presenter, Callback {
    private final String TAG = getClass().getSimpleName();

    private Presenter.View view;
    private Retrofit retrofit;

    public void setView(View view) {
        this.view = view;
    }

    private int searchEngine = Constants.ENGINE_NAVER;
    private String keyword = "";

    public Call getCall(final String keyword, int searchEngine, boolean startImmediately) {
        Call call = null;

        this.keyword = keyword;
        this.searchEngine = searchEngine;

        initRetrofit(searchEngine);

        Map<String, String> options = new HashMap<>();

        if (!"".equals(keyword)) {
            switch (searchEngine) {
                //case Constants.ENGINE_NAVER:
                default:
                    NaverRequest naverRequest = retrofit.create(NaverRequest.class);
                    options.put("display", "100");
                    call = naverRequest.getResult(
                            keyword,
                            options
                    );
                    break;
                case Constants.ENGINE_DAUM:
                    DaumRequest daumRequest = retrofit.create(DaumRequest.class);
                    options.put("result", "20");
                    options.put("output", "json");
                    call = daumRequest.getResult(
                            Constants.DAUM_API_KEY,
                            keyword,
                            options
                    );
                    break;
            }

            if (startImmediately) {
                call.enqueue(this);
            }
        } else {
            view.setImageList(null);
        }

        return call;
    }

    public void initRetrofit() {
        initRetrofit(searchEngine);
    }

    public Retrofit initRetrofit(int engine) {
        //default engine is naver.
        String url = Constants.naverURL;

        switch (engine) {
            default:
                url = Constants.naverURL;
                break;
            case Constants.ENGINE_DAUM:
                url = Constants.daumURL;
                break;
        }

        if (retrofit != null) {
            retrofit = retrofit.newBuilder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(url)
                    .build();
        } else {
            retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(url)
                    .build();
        }

        return retrofit;
    }

    @Override
    public void onResponse(Call call, Response response) {

        switch (searchEngine) {
            //case Constants.ENGINE_NAVER:
            default:
                if (keyword.equals(view.getCurrentKeyword())) {
                    view.setImageList(getNaverResults(response));
                }
                break;
            case Constants.ENGINE_DAUM:
                if (keyword.equals(view.getCurrentKeyword())) {
                    view.setImageList(getDaumResults(response));
                }
                break;
        }
    }

    @Override
    public void onFailure(Call call, Throwable t) {
        view.showError(t.getCause().toString());
    }


    public List<SearchResult> getNaverResults(Response response) {
        final List<SearchResult> imageUrls = new ArrayList<>();
        for (NaverItems naverItems : ((NaverResult) response.body()).item) {
            SearchResult result = new SearchResult();
            result.setImg_url(naverItems.link);
            result.setThumbnail_url(naverItems.thumbnail);
            result.setTitle(naverItems.title);

            imageUrls.add(result);
        }
        return imageUrls;
    }


    public List<SearchResult> getDaumResults(Response response) {
        final List<SearchResult> imageUrls = new ArrayList<>();
        for (DaumItems daumItems : ((DaumResult) response.body()).channel.item) {
            SearchResult result = new SearchResult();
            result.setImg_url(daumItems.image);
            result.setThumbnail_url(daumItems.thumbnail);
            result.setTitle(daumItems.title);
            result.setLink_url(daumItems.link);

            imageUrls.add(result);
        }

        return imageUrls;
    }
}
