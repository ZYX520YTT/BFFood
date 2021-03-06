package food.neusoft.com.food.Fragment.main;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;

import cz.msebera.android.httpclient.Header;
import food.neusoft.com.food.Fragment.main.base.BaseFragment;
import food.neusoft.com.food.NApplication;
import food.neusoft.com.food.R;
import food.neusoft.com.food.activity.MineCollectActivity;
import food.neusoft.com.food.activity.MineOrderActivity;
import food.neusoft.com.food.activity.MineShareActivity;
import food.neusoft.com.food.activity.PhotoActivity;
import food.neusoft.com.food.thread.HttpUtils;
import food.neusoft.com.food.thread.Url;
import food.neusoft.com.food.utils.BitmapUtil;
import food.neusoft.com.food.utils.RequestCode;
import food.neusoft.com.food.utils.UiUtils;
import food.neusoft.com.food.widget.roundhead.CircleImageView;

import static food.neusoft.com.food.NApplication.ImageUrl;
import static food.neusoft.com.food.NApplication.nickname;

/**
 * Created by 张宇翔 on 2016/11/21 22:47.
 * Email：1124751755@qq.com
 * 功能：我的页面
 */

public class MineFragment extends BaseFragment{

    private Context context;
    private AsyncHttpResponseHandler imageup_handler,get_personal_handler;
    private View view;

    public static Bitmap headimage;

    @ViewInject(R.id.iv_headimage)
    private CircleImageView iv_headimage;
    @ViewInject(R.id.tv_name)
    private TextView tv_name;
    @ViewInject(R.id.rlyt_my_order)
    private RelativeLayout rlyt_my_order;
    @ViewInject(R.id.rlyt_collect)
    private RelativeLayout rlyt_collect;
    @ViewInject(R.id.rlyt_share)
    private RelativeLayout rlyt_share;
    private BitmapUtils utils;


    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getContext();
    }


    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mine,container,false);
        ViewUtils.inject(this,view);
        dohandler();
        Init();
        getPersonalInfo();
        return view;
    }

    private void Init() {

        utils = new BitmapUtils(context);
        //用户名
        tv_name.setText(NApplication.user_number);



        ChangeUserInfo();




        iv_headimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = null;
                intent = new Intent(context,PhotoActivity.class);
                intent.putExtra("code", RequestCode.HEAD_IMAGE);
                startActivityForResult(intent, RequestCode.HEAD_IMAGE);
            }
        });

        //点击“我的预约”
        rlyt_my_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, MineOrderActivity.class));
            }
        });

        //点击"我的收藏"
        rlyt_collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, MineCollectActivity.class));
            }
        });

        //点击“我的分享”
        rlyt_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, MineShareActivity.class));
            }
        });


    }




    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // 根据返回码的不同，设置参数
        if (resultCode == RequestCode.HEAD_IMAGE) {
            if(headimage != null){
                savePicture(headimage);
                iv_headimage.setImageBitmap(headimage);
            }
        }
    }


    //切换用户信息,请求网络数据必须在子线程，刷新UI必须在主线程
    private void ChangeUserInfo(){
        UiUtils.runOnThread(new Runnable() {
            @Override
            public void run() {
                final Bitmap bitmap= BitmapUtil.getbitmap(ImageUrl);
                if(bitmap!=null){
                    UiUtils.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            iv_headimage.setImageBitmap(bitmap);
//                            BitmapUtil.saveBmpToSd(bitmap, Environment.getExternalStorageDirectory()+"/foodupload.jpg",100);
                        }
                    });
                }
            }
        });
        if(nickname!=null){
            tv_name.setText(nickname);
        }
    }


    /**
     * 上传我的头像
     * @param bitmap
     */
    private void savePicture(Bitmap bitmap)  {

        /**
         * bt.compress(Bitmap.CompressFormat.JPEG, 60, stream);

         byte[] bytes = stream.toByteArray();

         //将bitmap进行Base64编码

         String img = new String(Base64.encode(bytes, Base64.DEFAULT));

         */
//        ByteArrayOutputStream baos=new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.PNG,100,baos);
//        byte[] byte1=baos.toByteArray();
//        RequestParams params=new RequestParams();
//        params.put("userId",NApplication.user_number);
//        params.put("fileName","aa.jpeg");
//        params.put("byteArrayInputStream",new ByteArrayInputStream(byte1));
//        HttpUtils.post(getContext(), Url.uploadPhotos,params,imageup_handler);
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,60,stream);
        byte[] bytes =stream.toByteArray();
        String img = new String(Base64.encode(bytes, Base64.DEFAULT));//把图片转换成字符串

        RequestParams params=new RequestParams();
        params.put("userId",NApplication.user_number);
        params.put("fileName","aa.jpeg");
        params.put("upload",img);
        HttpUtils.post(context, Url.uploadPhotos,params,imageup_handler);
    }

    private void getPersonalInfo(){
        RequestParams params=new RequestParams();
        params.put("userId",NApplication.user_number);
        HttpUtils.get(context,Url.getUserInfo,params,get_personal_handler);
    }


    private void dohandler(){
        imageup_handler=new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result=new String(responseBody);
                if(result.equals("ERROR")){
                    Toast.makeText(context,"上传头像失败",Toast.LENGTH_SHORT).show();
                }else{
                    System.out.println("结果---------"+result+"---------结果");
                    Toast.makeText(context,"上传头像成功！",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(context, R.string.toast_network_error1, Toast.LENGTH_SHORT).show();
            }
        };


        get_personal_handler=new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result=new String(responseBody);
                if(result.equals("ERROR")){

                }else{

                    utils.configDefaultLoadingImage(R.drawable.ic_mine_personal);
                    try {
                        JSONObject jsonObject=new JSONObject(result);
                        String userNo=jsonObject.getString("userNo");
                        String userId=jsonObject.getString("userId");
                        String userAdress=jsonObject.getString("userAdress");
                        String userIconPath=jsonObject.getString("userIconPath");
                        String userName=jsonObject.getString("userName");
                        if(userIconPath!="null"){
                            utils.display(iv_headimage,Url.getImgURL(userIconPath));
                        }else{
                            iv_headimage.setImageResource(R.drawable.ic_mine_personal);
                        }
                        if(userName!="null"){//如果用户名不为空，就设置用户名，为空就设置用户的电话号码
                            tv_name.setText(userName);
                            System.out.println("名字地址："+userName);
                        }else{
                            tv_name.setText(userId);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(context, R.string.toast_network_error1, Toast.LENGTH_SHORT).show();
            }
        };
    }
}
