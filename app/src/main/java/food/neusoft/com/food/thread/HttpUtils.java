package food.neusoft.com.food.thread;

import android.content.Context;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.entity.StringEntity;

import food.neusoft.com.food.R;

/**
 * Created by 张宇翔 on 2016/11/22 08:18.
 * Email：1124751755@qq.com
 * 功能：HttpUtils
 */

public class HttpUtils {

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(Context context, String actionUrl, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(context, actionUrl, params, responseHandler);
    }

    public static void post(Context context, String actionUrl, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(context, actionUrl, params, responseHandler);
    }

    public static void postJson(Context context, String actionUrl, String json, AsyncHttpResponseHandler responseHandler){
        try {
            client.post(context, actionUrl, new StringEntity(json, "UTF-8"), "application/json", responseHandler);
        } catch (Exception e) {
            Toast.makeText(context, R.string.toast_network_error4, Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

}
