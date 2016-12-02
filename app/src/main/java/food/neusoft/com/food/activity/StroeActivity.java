package food.neusoft.com.food.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.BitmapUtils;
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
import food.neusoft.com.food.adapter.StoreAdapter;
import food.neusoft.com.food.domian.FoodInfo;
import food.neusoft.com.food.thread.HttpUtils;
import food.neusoft.com.food.thread.Url;
import food.neusoft.com.food.utils.Tools;
import food.neusoft.com.food.widget.roundhead.CircleImageView;

import static food.neusoft.com.food.NApplication.MineCollects;

public class StroeActivity extends BaseActivity {

    @ViewInject(R.id.rl_title)
    private RelativeLayout rl_title;
    @ViewInject(R.id.iv_back)
    private ImageView iv_back;
    @ViewInject(R.id.iv_heart)
    private CheckBox iv_heart;
    @ViewInject(R.id.iv_headimage)
    private CircleImageView iv_headimage;
    @ViewInject(R.id.tv_storename)
    private TextView tv_storename;
    @ViewInject(R.id.tv_introduce)
    private TextView tv_introduce;
    @ViewInject(R.id.ls_putshow)
    private ListView ls_putshow;
    @ViewInject(R.id.ls_hotshow)
    private ListView ls_hotshow;
    @ViewInject(R.id.iv_share)
    private ImageView iv_share;


    private long marketNo;
    private String type;

    private List<FoodInfo> putinfos,hotinfos;

    private AsyncHttpResponseHandler handler,collect_handler,removecollect_handler;
    private StoreAdapter putadapter;
    private StoreAdapter hotadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stroe);
        ViewUtils.inject(this);
        Intent intent=getIntent();
        marketNo=intent.getLongExtra("marketNo",0);
//        Toast.makeText(this,marketNo+"",Toast.LENGTH_SHORT).show();
        type = intent.getStringExtra("type");
        String storename=intent.getStringExtra("storename");
        String introduce=intent.getStringExtra("introduce");

        final String imagepath=intent.getStringExtra("imagepath");//图片的网络地址
//        food.neusoft.com.food.utils.LogUtils.d("结果:"+imagepath);
//        food.neusoft.com.food.utils.LogUtils.i("结果:"+imagepath);
//        food.neusoft.com.food.utils.LogUtils.e("结果:"+imagepath);
//        food.neusoft.com.food.utils.LogUtils.w("结果:"+imagepath);

        //更换背景图片
        if(type.equals("甜品")){
            rl_title.setBackgroundResource(R.drawable.tp_background);
        }else if(type.equals("饮品")){
            rl_title.setBackgroundResource(R.drawable.yp_background);
        }else if(type.equals("西餐")){
            rl_title.setBackgroundResource(R.drawable.xc_background);
        }else if(type.equals("火锅")){
            rl_title.setBackgroundResource(R.drawable.hg_background);
        }


        //设置头像
        BitmapUtils utils=new BitmapUtils(this);
        utils.configDefaultLoadingImage(R.drawable.ic_mine_personal);
        utils.display(iv_headimage,imagepath);

        if(storename!=null){
            tv_storename.setText(storename);//显示店名
        }
        if(introduce!=null){
            tv_introduce.setText(introduce);//显示店介绍
        }
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        //如果在集合中。桃心白变红，没在集合中，桃心变灰
        if(MineCollects.get(marketNo)!=null&&MineCollects.get(marketNo)==true){
            iv_heart.setChecked(true);
        }else{
            iv_heart.setChecked(false);
        }




        //对桃心进行监听，选中则为收藏。没选中则说明用户没收藏。切换时进行收藏或取消收藏
        iv_heart.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){//收藏
                    Collect(NApplication.user_number,marketNo);
                    MineCollects.put(marketNo,true);
                }else{//取消收藏
                    RemoveCollect(NApplication.user_number,marketNo);
                    MineCollects.put(marketNo,false);
                }
            }
        });

        //对分享按钮进行监听
        iv_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NApplication.share_marketNo=marketNo;
                Tools.showShare(StroeActivity.this,"好好吃","真的好吃",imagepath,"http://www.baiud.com");
            }
        });


        dohandler();
        Init();
    }
    private void Init(){
        putinfos=new ArrayList<>();
        hotinfos=new ArrayList<>();
        RequestParams params=new RequestParams();
        params.put("marketNo",marketNo);
        HttpUtils.get(this, Url.getFoods,params,handler);

        putadapter = new StoreAdapter(this,putinfos);
        hotadapter = new StoreAdapter(this,hotinfos);
         ls_hotshow.setAdapter(hotadapter);
        ls_putshow.setAdapter(putadapter);

    }


    //收藏店铺
    private void Collect(String userId,long marketNo){
        RequestParams params=new RequestParams();
        params.put("userId",userId);
        params.put("marketNo",marketNo);
        HttpUtils.post(this,Url.saveCollect,params,collect_handler);
    }
    //取消收藏
    private void RemoveCollect(String userId,long marketNo){
        RequestParams params=new RequestParams();
        params.put("userId",userId);
        params.put("marketNo",marketNo);
        HttpUtils.post(this,Url.removeCollect,params,removecollect_handler);
    }




    private void dohandler(){
        handler=new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result=new String(responseBody);
                if(result.equals("ERROR")){
                    Toast.makeText(StroeActivity.this,"获取数据失败",Toast.LENGTH_SHORT).show();
                }else{
                    try {
                        JSONArray jsonarray=new JSONArray(result);
                        for(int i=0;i<jsonarray.length();i++){
                            JSONObject jsonobject=jsonarray.getJSONObject(i);
                            String foodDiscount=jsonobject.getString("foodDiscount");
                            String foodHot=jsonobject.getString("foodHot");
                            String foodIconPath=Url.getImgURL(jsonobject.getString("foodIconPath"));
                            String foodIntroduce=jsonobject.getString("foodIntroduce");
                            String foodName=jsonobject.getString("foodName");
                            long foodNo=jsonobject.getLong("foodNo");
                            double foodPrice=jsonobject.getDouble("foodPrice");
                            FoodInfo foodInfo=new FoodInfo(foodName,foodIntroduce,foodHot,foodIconPath,foodDiscount,foodNo,foodPrice);
                            if(foodHot=="null"||foodHot=="true"){//是热卖
                                hotinfos.add(foodInfo);
                            }else{//不是热卖
                                putinfos.add(foodInfo);
                            }
                        }
                        putadapter.notifyDataSetChanged();
                        hotadapter.notifyDataSetChanged();

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(StroeActivity.this,"解析数据失败",Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(StroeActivity.this, R.string.toast_network_error1, Toast.LENGTH_SHORT).show();
            }
        };

        //收藏店铺
        collect_handler=new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result=new String(responseBody);
                if(result.equals("SUCCESS")){
                    Toast.makeText(StroeActivity.this,"收藏成功!",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(StroeActivity.this,"收藏失败",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(StroeActivity.this, R.string.toast_network_error1, Toast.LENGTH_SHORT).show();
            }
        };


        //取消收藏
        removecollect_handler=new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result=new String(responseBody);
                if(result.equals("SUCCESS")){
                    Toast.makeText(StroeActivity.this,"已经取消收藏",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(StroeActivity.this,"取消收藏失败",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(StroeActivity.this, R.string.toast_network_error1, Toast.LENGTH_SHORT).show();
            }
        };

    }



}
