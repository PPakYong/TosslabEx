package com.yhpark.tosslabhomework.presenter;

import android.support.test.runner.AndroidJUnit4;

import com.yhpark.tosslabhomework.common.Constants;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by ppyh0 on 2017-05-14.
 */
@RunWith(AndroidJUnit4.class)
public class PresenterImplTest {
    private static final String keyword = "토스랩";

    private Presenter.View testView;
    private PresenterImpl testPresenter;

    private Retrofit retrofit;

    @Before
    public void setUp() throws Exception {
        testView = Mockito.mock(Presenter.View.class);
        testPresenter = new PresenterImpl();
        testPresenter.setView(testView);
    }

    @Test
    public void testEmptyKeyword() {
        testPresenter.getCall("", Constants.ENGINE_NAVER, false);
        Mockito.verify(testView).setImageList(null);
    }

    @Test
    public void testInitRetrofit() {
        retrofit = testPresenter.initRetrofit(Constants.ENGINE_DAUM);
        Assert.assertEquals(retrofit.baseUrl().toString(), Constants.daumURL);

        retrofit = testPresenter.initRetrofit(Constants.ENGINE_NAVER);
        Assert.assertEquals(retrofit.baseUrl().toString(), Constants.naverURL);
    }

    @Test
    public void testSearchFromNaver() throws IOException {
        Call call = testPresenter.getCall(keyword, Constants.ENGINE_NAVER, false);
        Response response = call.execute();

        Assert.assertEquals(response.code(), 200);
        Assert.assertNotNull(testPresenter.getNaverResults(response));
    }

    @Test
    public void tesSearchFromDaum() throws IOException {
        Call call = testPresenter.getCall(keyword, Constants.ENGINE_DAUM, false);
        Response response = call.execute();

        Assert.assertEquals(response.code(), 200);
        Assert.assertNotNull(testPresenter.getDaumResults(response));
    }
}