package food.neusoft.com.food.thread;

import android.content.Context;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import food.neusoft.com.food.R;

/**
 * Created by 张宇翔 on 2016/11/22 08:23.
 * Email：1124751755@qq.com
 * 功能：1.期望替换所有的AsyncHttpResponseHandler
        2.以规整项目网络逻辑
 */

public abstract  class HttpHandler extends AsyncHttpResponseHandler {

    protected Context context;

    protected HttpHandler(Context context) {
        super();
        this.context = context;
    }

    protected abstract void handler200(JSONObject jsonObject) throws Exception;

    protected void handlerOther(JSONObject jsonObject) throws Exception{
        Toast.makeText(context, R.string.toast_network_error3, Toast.LENGTH_SHORT).show();
    }

    protected void catchException(){
        Toast.makeText(context, R.string.toast_network_error2, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
        try {
            JSONObject jsonObject = new JSONObject(new String(responseBody));
            if ("200".equals(jsonObject.getString("parameter"))) {
                    handler200(jsonObject);
            }else {
                handlerOther(jsonObject);
            }
        } catch (Exception e) {
            catchException();
            e.printStackTrace();
        }
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
        Toast.makeText(context, R.string.toast_network_error1, Toast.LENGTH_SHORT).show();
    }
}
