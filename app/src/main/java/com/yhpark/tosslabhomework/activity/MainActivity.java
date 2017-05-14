package com.yhpark.tosslabhomework.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.yhpark.tosslabhomework.R;
import com.yhpark.tosslabhomework.adapter.EngineListAdapter;
import com.yhpark.tosslabhomework.adapter.ImageListAdapter;
import com.yhpark.tosslabhomework.common.Constants;
import com.yhpark.tosslabhomework.model.SearchResult;
import com.yhpark.tosslabhomework.presenter.Presenter;
import com.yhpark.tosslabhomework.presenter.PresenterImpl;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnEditorAction;
import butterknife.OnItemSelected;
import butterknife.OnTextChanged;

public class MainActivity extends AppCompatActivity implements Presenter.View {
    private final String TAG = getClass().getSimpleName();

    @BindView(R.id.searchEngine)
    AppCompatSpinner searchEngine;
    @BindView(R.id.etSearch)
    EditText etSearch;
    @BindView(R.id.rvResult)
    RecyclerView rvResult;

    private final int GRID_COLUME = 3;

    private PresenterImpl presenter;

    private ImageListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //init presenter -S
        presenter = new PresenterImpl();
        presenter.setView(this);
        presenter.initRetrofit(Constants.ENGINE_NAVER);
        //init presenter -E

        //recycler ready
        searchEngine.setAdapter(new EngineListAdapter(this, 0, new Integer[]{R.drawable.naver, R.drawable.daum}));
        RecyclerView.LayoutManager manager = new GridLayoutManager(this, GRID_COLUME);
        rvResult.setLayoutManager(manager);
    }

    @OnEditorAction(R.id.etSearch)
    public boolean onEditorAction(TextView view, int actionId, KeyEvent keyEvent) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            presenter.getCall(etSearch.getText().toString(), getCurrentEngine(), true);
        }
        return false;
    }

    @OnTextChanged(R.id.etSearch)
    public void afterTextChanged(Editable s) {
        if (!s.equals("")) {
            presenter.getCall(s.toString(), getCurrentEngine(), true);
        } else {
            setImageList(null);
        }
    }

    @OnItemSelected(R.id.searchEngine)
    public void onItemSelected() {
        if (!"".equals(getCurrentKeyword())) {
            presenter.getCall(getCurrentKeyword(), getCurrentEngine(), true);
        }
    }

    @Override
    public void setImageList(List<SearchResult> list) {
        if (adapter != null) {
            adapter.addAll(list);
        } else {
            adapter = new ImageListAdapter(this, list);
            rvResult.setAdapter(adapter);
        }
    }

    /**
     * @return 현재 화면에 보여지는 검색어 string을 반환한다
     */
    @Override
    public String getCurrentKeyword() {
        return etSearch.getText().toString();
    }

    /**
     * @return 현재 화면에 보여지는/선택된 검색엔진을 반환한다
     */
    @Override
    public int getCurrentEngine() {
        return searchEngine.getSelectedItemPosition();
    }

    /**
     * onFailure() 발생 시 toast로 error 표시
     * list는 초기화한다 (임의진행)
     ** @param cause
     */
    @Override
    public void showError(String cause) {
        Toast.makeText(this, "casue: " + cause, Toast.LENGTH_SHORT).show();
        setImageList(null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.release();
    }
}
