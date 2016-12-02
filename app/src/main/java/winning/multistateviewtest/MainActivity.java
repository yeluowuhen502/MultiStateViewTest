package winning.multistateviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

public class MainActivity extends AppCompatActivity {
    private MultiStateView mMultiStateView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMultiStateView = (MultiStateView) findViewById(R.id.mMultiStateView);
        if (!CommonUtil.isNetworkAvailable(this)) {
            mMultiStateView.setViewState(MultiStateView.STATE_NETWORK);
            return;
        }
        mMultiStateView.setOnRetryClickListener(new MultiStateView.OnRetryClickListener() {
            @Override
            public void onRetry() {
                getDataFromServer();
            }
        });

//        mMultiStateView.setViewState(MultiStateView.STATE_LOADING);
//        mMultiStateView.setViewState(MultiStateView.STATE_CONTENT);
//        mMultiStateView.setViewState(MultiStateView.STATE_EMPTY, "暂无入库单");
        getDataFromServer();

    }

    public void getDataFromServer() {
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.GET, "http://www.baidu.com", new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> info) {
                Log.d("infooo", "hahah");
                mMultiStateView.setViewState(MultiStateView.STATE_ERROR, "暂无入库单");

            }

            @Override
            public void onFailure(HttpException e, String s) {
                Log.d("infooo", s);
            }
        });
    }
}
