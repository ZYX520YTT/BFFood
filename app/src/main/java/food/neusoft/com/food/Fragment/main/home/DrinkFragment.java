package food.neusoft.com.food.Fragment.main.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import food.neusoft.com.food.adapter.DrinkAdapter;
import food.neusoft.com.food.domian.DrinkInfo;
import food.neusoft.com.food.thread.HttpUtils;
import food.neusoft.com.food.thread.Url;
import food.neusoft.com.food.widget.pulltorefresh.PullToRefreshLayout;


/**
 * Created by 张宇翔 on 2016/11/22 19:15.
 * Email：1124751755@qq.com
 * 功能：
 */

public class DrinkFragment extends BaseFragment {

    private Context context;
    private AsyncHttpResponseHandler drink_handler;
    private View view;

    @ViewInject(R.id.ls_show)
    private ListView ls_show;
    @ViewInject(R.id.refresh_view)
    private PullToRefreshLayout refresh_view;


    private List<DrinkInfo> drinkInfos;

    public static String LOCAL;
    private boolean isLoadmore;

    private int count=10;//请求量
    private int firstIndex;//从第几条数据开始取
    private DrinkAdapter drinkAdapter;


    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getContext();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_drink,container,false);
        ViewUtils.inject(this, view);
        dohandler();
        Init();
        return view;
    }

    private void Init() {
        refresh_view.setOnRefreshListener(new MyListener());
        setupFragment();
    }

    private void setupFragment() {
        getFirst();
    }

    private void getFirst(){
        isLoadmore=false;
        firstIndex=0;
        drinkInfos=new ArrayList<>();
        RequestParams params=new RequestParams();
        params.put("count",count);
        params.put("firstIndex",firstIndex);
        params.put("marketAdress",LOCAL);
        HttpUtils.get(context, Url.getDrinkMarket,params,drink_handler);

        drinkAdapter = new DrinkAdapter(context,drinkInfos);
        ls_show.setAdapter(drinkAdapter);



    }

    private void getNext(){
        isLoadmore=true;
        RequestParams params=new RequestParams();
        params.put("count",count);
        params.put("firstIndex",firstIndex);
        params.put("marketAdress",LOCAL);
        HttpUtils.get(context, Url.getDrinkMarket,params,drink_handler);
    }

    private void dohandler(){
        drink_handler=new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result=new String(responseBody);
                if(result.equals("ERROR")){
                    Toast.makeText(context,"获取数据失败",Toast.LENGTH_SHORT).show();
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
                            String marketBigPicture=Url.getImgURL(jsonObject.getString("marketBigPicture"));
                            double marketDiscount=jsonObject.getDouble("marketDiscount");
                            double marketDistance=jsonObject.getDouble("marketDistance");
                            double marketHotLevel=jsonObject.getDouble("marketHotLevel");
                            String marketIconPath=Url.getImgURL(jsonObject.getString("marketIconPath"));
                            String marketIntroduce=jsonObject.getString("marketIntroduce");
                            String marketName=jsonObject.getString("marketName");
                            long marketNo=jsonObject.getLong("marketNo");
                            double marketPrice=jsonObject.getDouble("marketPrice");
                            String typeName=jsonObject.getString("typeName");
                            DrinkInfo drinkInfo=new DrinkInfo(bookIconPath,typeName,marketPrice,
                            marketNo,marketName,marketIntroduce,marketIconPath,
                            marketHotLevel,marketDistance,marketDiscount,marketBigPicture,
                                    marketAdress,discountIconPath);
                            drinkInfos.add(drinkInfo);
                        }
                        drinkAdapter.notifyDataSetChanged();
                        firstIndex+=count;
                        if(isLoadmore){
                            refresh_view.loadmoreFinish(PullToRefreshLayout.SUCCEED);
                        }else{
                            refresh_view.loadmoreFinish(PullToRefreshLayout.SUCCEED);
                        }

                    } catch (JSONException e) {
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
                Toast.makeText(context, R.string.toast_network_error1, Toast.LENGTH_SHORT).show();
                if(isLoadmore){
                    refresh_view.loadmoreFinish(PullToRefreshLayout.FAIL);
                }else{
                    refresh_view.refreshFinish(PullToRefreshLayout.FAIL);
                }
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
