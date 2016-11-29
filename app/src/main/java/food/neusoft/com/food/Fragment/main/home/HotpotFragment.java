package food.neusoft.com.food.Fragment.main.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
import food.neusoft.com.food.Fragment.main.base.BaseFragment;
import food.neusoft.com.food.R;
import food.neusoft.com.food.activity.StroeActivity;
import food.neusoft.com.food.adapter.HotPotAdapter;
import food.neusoft.com.food.domian.HotPotInfo;
import food.neusoft.com.food.thread.HttpUtils;
import food.neusoft.com.food.thread.Url;
import food.neusoft.com.food.widget.pulltorefresh.PullToRefreshLayout;

/**
 * Created by 张宇翔 on 2016/11/22 19:14.
 * Email：1124751755@qq.com
 * 功能：
 */

public class HotpotFragment extends BaseFragment {


    private AsyncHttpResponseHandler hotpot_handler;
    private View view;

    @ViewInject(R.id.ls_show)
    private ListView ls_show;
    @ViewInject(R.id.refresh_view)
    private PullToRefreshLayout refresh_view;


    private List<HotPotInfo> hotPotInfos;

    public static String LOCAL;
    private boolean isLoadmore;

    private int count=10;//请求量
    private int firstIndex;//从第几条数据开始取
    private HotPotAdapter hotPotAdapter;


    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_hotpot,container,false);
        ViewUtils.inject(this,view);
        dohandler();
        InitData();
        return view;
    }

    private void InitData() {
        refresh_view.setOnRefreshListener(new MyListener());
        setupFragment();
    }


    private void setupFragment() {
        getFirst();
    }

    //下拉刷新
    private void getFirst(){
        isLoadmore=false;
        firstIndex=0;
        hotPotInfos=new ArrayList<>();
        RequestParams params=new RequestParams();
        params.put("count",count);
        params.put("firstIndex",firstIndex);
        params.put("marketAdress",LOCAL);
        HttpUtils.get(getContext(), Url.getHotpotMarket,params,hotpot_handler);

        hotPotAdapter = new HotPotAdapter(getContext(),hotPotInfos);
        ls_show.setAdapter(hotPotAdapter);


        ls_show.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(getContext(), StroeActivity.class);
                HotPotInfo hotPotInfo=hotPotInfos.get(i);
                intent.putExtra("marketNo",hotPotInfo.getMarketNo());
                intent.putExtra("type","火锅");
                intent.putExtra("storename",hotPotInfo.getMarketName());
                intent.putExtra("introduce",hotPotInfo.getMarketIntroduce());
                startActivity(intent);
            }
        });
    }

    //下拉加载更多
    private void getNext(){
        isLoadmore=true;
        RequestParams params=new RequestParams();
        params.put("count",count);
        params.put("firstIndex",firstIndex);
        params.put("marketAdress",LOCAL);
        HttpUtils.get(getContext(), Url.getHotpotMarket,params,hotpot_handler);
    }



    private void dohandler(){
        hotpot_handler=new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result=new String(responseBody);
                if(result.equals("ERROR")){
                    Toast.makeText(getContext(),"获取数据失败",Toast.LENGTH_SHORT).show();
                    if(isLoadmore){
                        refresh_view.loadmoreFinish(PullToRefreshLayout.FAIL);
                    }else{
                        refresh_view.refreshFinish(PullToRefreshLayout.FAIL);
                    }
                }else{
                    try {
                        JSONArray jsonArray=new JSONArray(result);
                        for(int i=0;i<jsonArray.length();i++){
                            JSONObject jsonObject=jsonArray.getJSONObject(i);
                            String bookIconPath=Url.getImgURL(jsonObject.getString("bookIconPath"));
                            String discountIconPath=Url.getImgURL(jsonObject.getString("discountIconPath"));
                            String marketAdress=jsonObject.getString("marketAdress");
                            String marketBigPicture=jsonObject.getString("marketBigPicture");
                            double marketDiscount=jsonObject.getDouble("marketDiscount");
                            double marketDistance=jsonObject.getDouble("marketDistance");
                            double marketHotLevel=jsonObject.getDouble("marketHotLevel");
                            String marketIconPath=Url.getImgURL(jsonObject.getString("marketIconPath"));
                            String marketIntroduce=jsonObject.getString("marketIntroduce");
                            String marketName=jsonObject.getString("marketName");
                            long marketNo=jsonObject.getLong("marketNo");
                            double marketPrice=jsonObject.getDouble("marketPrice");
                            String typeName=jsonObject.getString("typeName");
                            HotPotInfo hotPotInfo=new HotPotInfo(bookIconPath,typeName,marketPrice,marketNo,marketName,marketIntroduce
                            ,marketIconPath,marketHotLevel,marketDiscount,marketBigPicture,discountIconPath,marketAdress,marketDistance);

                            hotPotInfos.add(hotPotInfo);
                        }

                        hotPotAdapter.notifyDataSetChanged();
                        firstIndex+=count;
                        if(isLoadmore){
                            refresh_view.loadmoreFinish(PullToRefreshLayout.SUCCEED);
                        }else{
                            refresh_view.loadmoreFinish(PullToRefreshLayout.SUCCEED);
                        }

                    }catch (JSONException e) {
                        e.printStackTrace();
                        if(isLoadmore){
                            refresh_view.loadmoreFinish(PullToRefreshLayout.FAIL);
                        }else{
                            refresh_view.refreshFinish(PullToRefreshLayout.FAIL);
                        }
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(getContext(), R.string.toast_network_error1, Toast.LENGTH_SHORT).show();
            }
        };
    }


    //设置下拉上拉监听
    class MyListener implements PullToRefreshLayout.OnRefreshListener {

        @Override
        public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
            getFirst();
        }

        @Override
        public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
            getNext();
        }
    }

}
