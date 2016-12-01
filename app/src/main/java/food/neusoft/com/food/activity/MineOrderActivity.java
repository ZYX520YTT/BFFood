package food.neusoft.com.food.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import food.neusoft.com.food.NApplication;
import food.neusoft.com.food.R;
import food.neusoft.com.food.adapter.MineOrderAdapter;
import food.neusoft.com.food.domian.MineOrderInfo;
import food.neusoft.com.food.thread.HttpUtils;
import food.neusoft.com.food.thread.Url;

public class MineOrderActivity extends BaseActivity {

    private AsyncHttpResponseHandler mineorder_handler;
    private int count=10;
    private int firstIndex=0;


    private List<MineOrderInfo> mineOrderInfos;

    @ViewInject(R.id.iv_back)
    private ImageView iv_back;
    @ViewInject(R.id.ls_show)
    private ListView ls_show;
    private MineOrderAdapter mineOrderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mineorder);
        ViewUtils.inject(this);
        dohandler();
        Init();
    }
    private void Init(){
        //退出当前界面
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mineOrderInfos=new ArrayList<>();

        RequestParams params=new RequestParams();
        params.put("userId", NApplication.user_number);
        params.put("count",count);
        params.put("firstIndex",firstIndex);
        HttpUtils.get(this, Url.getMyOrder,params,mineorder_handler);


        mineOrderAdapter = new MineOrderAdapter(this,mineOrderInfos);
        ls_show.setAdapter(mineOrderAdapter);
    }

    private void dohandler(){
        mineorder_handler=new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result=new String(responseBody);
                if(result.equals("ERROR")){
                    Toast.makeText(MineOrderActivity.this,"获取数据失败",Toast.LENGTH_SHORT).show();
                }else{
                    try {
                        JSONArray jsonArray=new JSONArray(result);
                        for(int i=0;i<jsonArray.length();i++){
                            JSONObject jsonObject=jsonArray.getJSONObject(i);
                            String orderDay=jsonObject.getString("orderDay");
                            String orderState=jsonObject.getString("orderState");
                            JSONObject jsonObject1=jsonObject.getJSONObject("market");
                            String bookIconPath=Url.getImgURL(jsonObject1.getString("bookIconPath"));
                            double marketDistance=jsonObject1.getDouble("marketDistance");
                            double marketHotLevel=jsonObject1.getDouble("marketHotLevel");
                            double marketPrice=jsonObject1.getDouble("marketPrice");
                            String marketIconPath=Url.getImgURL(jsonObject1.getString("marketIconPath"));
                            String marketName=jsonObject1.getString("marketName");
                            JSONObject jsonObject2=jsonObject1.getJSONObject("foodType");
                            String typeName=jsonObject2.getString("typeName");
                            MineOrderInfo mineOrderInfo=new MineOrderInfo(bookIconPath,typeName,orderState,orderDay
                            ,marketName,marketIconPath,marketHotLevel,marketDistance,marketPrice);
                            mineOrderInfos.add(mineOrderInfo);
                        }
                        mineOrderAdapter.notifyDataSetChanged();

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(MineOrderActivity.this,"获取数据失败",Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(MineOrderActivity.this, "网络连接错误，请检查网络设置后重试。",
                        Toast.LENGTH_SHORT).show();
            }
        };
    }

}
