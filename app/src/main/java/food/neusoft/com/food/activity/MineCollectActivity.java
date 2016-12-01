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
import food.neusoft.com.food.adapter.MineCollectAdapter;
import food.neusoft.com.food.domian.MineCollectInfo;
import food.neusoft.com.food.thread.HttpUtils;
import food.neusoft.com.food.thread.Url;

public class MineCollectActivity extends BaseActivity {

    private AsyncHttpResponseHandler minecollect_handler;
    @ViewInject(R.id.iv_back)
    private ImageView iv_back;
    @ViewInject(R.id.ls_show)
    private ListView ls_show;

    private List<MineCollectInfo> mineCollectInfos;
    private MineCollectAdapter mineCollectAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minecollect);
        ViewUtils.inject(this);
        dohandler();
        Init();
    }

    private void Init() {
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



        mineCollectInfos=new ArrayList<>();

        RequestParams params=new RequestParams();
        params.put("userId", NApplication.user_number);
        HttpUtils.get(this, Url.getMyCollect,params,minecollect_handler);


        mineCollectAdapter = new MineCollectAdapter(this,mineCollectInfos);
        ls_show.setAdapter(mineCollectAdapter);
    }

    private void dohandler() {

        minecollect_handler=new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result=new String(responseBody);
                if(result.equals("ERROR")){
                    Toast.makeText(MineCollectActivity.this,"获取数据失败",Toast.LENGTH_SHORT).show();
                }else{
                    try {
                        JSONArray jsonArray=new JSONArray(result);
                        for(int i=0;i<jsonArray.length();i++){
                            JSONObject jsonObject=jsonArray.getJSONObject(i);
                            String date=jsonObject.getString("date");
                            JSONObject jsonObject1=jsonObject.getJSONObject("market");
                            String bookIconPath=Url.getImgURL(jsonObject1.getString("bookIconPath"));
                            String discountIconPath=Url.getImgURL(jsonObject1.getString("discountIconPath"));
                            double marketDiscount=jsonObject1.getDouble("marketDiscount");
                            double marketDistance=jsonObject1.getDouble("marketDistance");
                            double marketHotLevel=jsonObject1.getDouble("marketHotLevel");
                            String marketIconPath=Url.getImgURL(jsonObject1.getString("marketIconPath"));
                            String marketIntroduce=jsonObject1.getString("marketIntroduce");
                            String marketName=jsonObject1.getString("marketName");
                            long marketNo=jsonObject1.getLong("marketNo");
                            double marketPrice=jsonObject1.getDouble("marketPrice");
                            String newIconPath=Url.getImgURL(jsonObject1.getString("newIconPath"));
                            JSONObject jsonObject2=jsonObject1.getJSONObject("foodType");
                            String typeName=jsonObject2.getString("typeName");
                            MineCollectInfo mineCollectInfo=new MineCollectInfo(bookIconPath,typeName,newIconPath,marketPrice,
                                    marketNo,marketName,marketIntroduce,marketIconPath,marketHotLevel,marketDistance,marketDiscount
                            ,discountIconPath,date);
                            mineCollectInfos.add(mineCollectInfo);
                        }
                        mineCollectAdapter.notifyDataSetChanged();

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(MineCollectActivity.this,"获取数据失败",Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(MineCollectActivity.this, "网络连接错误，请检查网络设置后重试。",
                        Toast.LENGTH_SHORT).show();
            }
        };
    }


}
