package food.neusoft.com.food.Fragment.main;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import food.neusoft.com.food.Fragment.main.base.BaseFragment;
import food.neusoft.com.food.R;
import food.neusoft.com.food.activity.PhotoActivity;
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
        Init();
        return view;
    }

    private void Init() {

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
//                savePicture(headimage);
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
}
