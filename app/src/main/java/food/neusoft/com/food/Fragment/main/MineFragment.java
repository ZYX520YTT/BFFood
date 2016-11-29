package food.neusoft.com.food.Fragment.main;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import cz.msebera.android.httpclient.Header;
import food.neusoft.com.food.Fragment.main.base.BaseFragment;
import food.neusoft.com.food.NApplication;
import food.neusoft.com.food.R;
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

    private AsyncHttpResponseHandler imageup_handler;
    private View view;

    public static Bitmap headimage;

    @ViewInject(R.id.iv_headimage)
    private CircleImageView iv_headimage;
    @ViewInject(R.id.tv_name)
    private TextView tv_name;


    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mine,container,false);
        ViewUtils.inject(this,view);
        dohandler();
        Init();
        return view;
    }

    private void Init() {

        //用户名
        tv_name.setText(NApplication.user_number);



        ChangeUserInfo();




        iv_headimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = null;
                intent = new Intent(getContext(),PhotoActivity.class);
                intent.putExtra("code", RequestCode.HEAD_IMAGE);
                startActivityForResult(intent, RequestCode.HEAD_IMAGE);
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
                System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
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
    private void savePicture(Bitmap bitmap){
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,baos);
        byte[] byte1=baos.toByteArray();
        RequestParams params=new RequestParams();
        params.put("userId", NApplication.user_number);
        params.put("fileName","aa.jpeg");
        params.put("fileInputStream",new ByteArrayInputStream(byte1));
        HttpUtils.post(getContext(), Url.uploadPhotos,params,imageup_handler);
    }


    private void dohandler(){
        imageup_handler=new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result=new String(responseBody);
                if(result.equals("ERROR")){
                    Toast.makeText(getContext(),"上传头像失败",Toast.LENGTH_SHORT).show();
                }else{
                    System.out.println("结果---------"+result+"---------结果");
                    Toast.makeText(getContext(),"上传头像成功！",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(getContext(), R.string.toast_network_error1, Toast.LENGTH_SHORT).show();
            }
        };
    }
}
