package com.yhpark.tosslabhomework.presenter;

import com.yhpark.tosslabhomework.model.SearchResult;

import java.util.List;

/**
 * Created by ppyh0 on 2017-05-14.
 */

public interface Presenter{
    public interface View {
        void setImageList(List<SearchResult> list);
        String getCurrentKeyword();
        int getCurrentEngine();
        void showError(String cause);
    }
}
