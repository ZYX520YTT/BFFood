package food.neusoft.com.food.Fragment.main.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import food.neusoft.com.food.adapter.HotPeopleAdapter;
import food.neusoft.com.food.adapter.PhotoListAdapter;
import food.neusoft.com.food.domian.FoodPhotoInfo;
import food.neusoft.com.food.domian.HotMarketInfo;
import food.neusoft.com.food.thread.HttpUtils;
import food.neusoft.com.food.thread.Url;
import food.neusoft.com.food.widget.pulltorefresh.PullToRefreshLayout;

/**
 * Created by 张宇翔 on 2016/11/22 19:14.
 * Email：1124751755@qq.com
 * 功能：
 */

@SuppressLint("ValidFragment")
public class HotFragment extends BaseFragment {


    private AsyncHttpResponseHandler foundFood_handler;
    private AsyncHttpResponseHandler hotmarket_handler;
    private View view;

    @ViewInject(R.id.myimpager)
    private ViewPager myimpager;
    @ViewInject(R.id.ll_point)
    private LinearLayout ll_point;
    @ViewInject(R.id.ls_show)
    private ListView ls_show;
    @ViewInject(R.id.ls_show_hotpeople)
    private ListView ls_show_hotpeople;
    @ViewInject(R.id.refresh_view)
    private PullToRefreshLayout refresh_view;

    private List<Integer> imageId;


    private boolean isLoadmore;

    private int firstIndex;//页数
    private int count=10;//每次去10条数据




    private Handler handler;
    private List<FoodPhotoInfo> foodPhotoInfos;
    private List<HotMarketInfo> hotMarketInfos;



    public static String LOCAL;
    private PhotoListAdapter photoadapter;
    private HotPeopleAdapter hotPeopleAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_hot, container, false);
        ViewUtils.inject(this, view);
        dohandler();
        Init();
        return view;
    }

    private void Init() {
        imageId = new ArrayList<>();
        imageId.add(R.drawable.pic_one);
        imageId.add(R.drawable.pic_two);
        imageId.add(R.drawable.pic_three);


        //初始化小圆点
        InitPoint();

        //默认选中第一张图片
        ChosePhoto(0);

        refresh_view.setOnRefreshListener(new MyListener());

        getFirst();

        setupFragment();


    }

    //热门商家
    private void  InitHot() {

//        Toast.makeText(getContext(),"我是:"+LOCAL,Toast.LENGTH_SHORT).show();
        firstIndex=0;
        hotMarketInfos = new ArrayList<>();
        RequestParams params=new RequestParams();
        params.put("count",count);//默认每次加载10条数据
        params.put("marketAdress",LOCAL);
        params.put("firstIndex",firstIndex);
        HttpUtils.get(getContext(),Url.getHotMarket,params,hotmarket_handler);
    }

    //发现美食
    private void InitFood() {

        foodPhotoInfos = new ArrayList<>();
        RequestParams params=new RequestParams();
        params.put("count",6);//总共6条数据
        HttpUtils.get(getContext(), Url.getHotFood,params,foundFood_handler);
    }

    //下拉刷新
    private void getFirst(){
        isLoadmore=false;

        InitFood();//初始化“发现美食”

        InitHot();//初始化热门商家


        photoadapter = new PhotoListAdapter(getContext(),foodPhotoInfos);

        ls_show.setAdapter(photoadapter);


        hotPeopleAdapter = new HotPeopleAdapter(getContext(),hotMarketInfos);

        ls_show_hotpeople.setAdapter(hotPeopleAdapter);

    }


    //下拉加载更多
    private void getNext(){
        isLoadmore=true;
        RequestParams params=new RequestParams();
        params.put("count",count);//默认每次加载10条数据
        params.put("marketAdress",LOCAL);
        params.put("firstIndex",firstIndex);
        HttpUtils.get(getContext(),Url.getHotMarket,params,hotmarket_handler);
//      Toast.makeText(getContext(),"firstIndex:"+firstIndex,Toast.LENGTH_SHORT).show();
    }





    private void setupFragment() {

        myimpager.setAdapter(new MyPagerAdapter());
        myimpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                ChosePhoto(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //设置图片轮播
        if (handler == null) {
            handler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    int current = myimpager.getCurrentItem();
                    if (current < imageId.size() - 1) {
                        current++;
                    } else {
                        current = 0;
                    }
                    myimpager.setCurrentItem(current);
                    handler.sendEmptyMessageDelayed(0, 2000);
                }
            };
            handler.sendEmptyMessageDelayed(0, 2000);
        }

    }

    private void ChosePhoto(int postion) {
        for (int i = 0; i < imageId.size(); i++) {
            if (i == postion) {
                ((ImageView) ll_point.getChildAt(i)).setImageResource(R.drawable.icon_slider_light);
            } else {
                ((ImageView) ll_point.getChildAt(i)).setImageResource(R.drawable.icon_slider);
            }
        }
    }


    //初始化小圆点
    private void InitPoint() {
        for (int i = 0; i < imageId.size(); i++) {
            ImageView view = new ImageView(getContext());
            view.setImageResource(R.drawable.icon_slider);
            if (i != 0) {
                view.setPadding(getResources().getDimensionPixelOffset(R.dimen.point_margin), 0, 0, 0);
            }
            ll_point.addView(view);
        }
    }


    //顶部轮播图
    class MyPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return imageId.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView image = new ImageView(getContext());
            image.setScaleType(ImageView.ScaleType.FIT_XY);
            image.setImageResource(imageId.get(position));
            container.addView(image);
            image.setOnTouchListener(new TopNewsTouchListener());
            return image;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }


    //对轮播图监听
    class TopNewsTouchListener implements View.OnTouchListener {

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            switch (motionEvent.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    handler.removeCallbacksAndMessages(null);
                    break;
                case MotionEvent.ACTION_CANCEL:
                    handler.sendEmptyMessageDelayed(0, 2000);
                    break;
                case MotionEvent.ACTION_UP:
                    handler.sendEmptyMessageDelayed(0, 2000);
                    break;
                default:
                    break;
            }
            return true;
        }
    }



    private void dohandler(){
        foundFood_handler=new AsyncHttpResponseHandler() {
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
                            double foodDiscount=jsonObject.getDouble("foodDiscount");
                            boolean foodHot=jsonObject.getBoolean("foodHot");
                            String foodIconPath= Url.getImgURL(jsonObject.getString("foodIconPath"));
                            String foodIntroduce=jsonObject.getString("foodIntroduce");
                            String foodName=jsonObject.getString("foodName");
                            long foodNo=jsonObject.getLong("foodNo");
                            FoodPhotoInfo foodPhotoInfo=new FoodPhotoInfo(foodDiscount,foodHot,foodIconPath,foodName,foodIntroduce,foodNo);
                            foodPhotoInfos.add(foodPhotoInfo);

                        }
                        //发现美食
                        photoadapter.notifyDataSetChanged();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(getContext(), R.string.toast_network_error1, Toast.LENGTH_SHORT).show();
            }
        };

        hotmarket_handler=new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result=new String(responseBody);
                if(result.equals("ERROR")){
                    Toast.makeText(getContext(),"获取数据失败",Toast.LENGTH_SHORT).show();
                }else{
                    try {
                        JSONArray jsonArray=new JSONArray(result);
                        for(int i=0;i<jsonArray.length();i++){
                            JSONObject jsonobject=jsonArray.getJSONObject(i);
                            String marketAdress=jsonobject.getString("marketAdress");
                            String marketBigPicture=Url.getImgURL(jsonobject.getString("marketBigPicture"));
                            double marketDiscount=jsonobject.getDouble("marketDiscount");
                            double marketDistance=jsonobject.getDouble("marketDistance");
                            double marketHotLevel=jsonobject.getDouble("marketHotLevel");
                            String marketIconPath=Url.getImgURL(jsonobject.getString("marketIconPath"));
                            String marketIntroduce=jsonobject.getString("marketIntroduce");
                            String marketName=jsonobject.getString("marketName");
                            long marketNo=jsonobject.getLong("marketNo");
                            double marketPrice=jsonobject.getDouble("marketPrice");
                            HotMarketInfo hotmarketinfo=new HotMarketInfo(marketAdress,marketPrice,marketNo,marketName,marketIntroduce
                            ,marketIconPath,marketDistance,marketDiscount,marketBigPicture,marketHotLevel);
                            hotMarketInfos.add(hotmarketinfo);

                        }

                        //热门商家
                        hotPeopleAdapter.notifyDataSetChanged();
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
                Toast.makeText(getContext(), R.string.toast_network_error1, Toast.LENGTH_SHORT).show();
            }
        };
    }




    //监听下拉和上拉
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
