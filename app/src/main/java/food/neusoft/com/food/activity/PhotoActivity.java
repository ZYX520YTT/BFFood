package food.neusoft.com.food.activity;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import java.io.File;
import java.io.FileNotFoundException;

import food.neusoft.com.food.Fragment.main.MineFragment;
import food.neusoft.com.food.R;
import food.neusoft.com.food.utils.RequestCode;
import food.neusoft.com.food.utils.Tools;

/**
 * Created by 张宇翔 on 2016/11/23 13:39.
 * Email：1124751755@qq.com
 * 功能：拍照或者选照片
 */

public class PhotoActivity extends BaseActivity implements View.OnClickListener{
    private Bitmap bitmap,bitmap1;
    private int code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//设置窗口没有标题
        setContentView(R.layout.activity_photo);
        if (getIntent()!=null) {
            code = getIntent().getIntExtra("code",0);
        }
        init();
    }
    private void init() {
        // TODO Auto-generated method stub
        ((Button)findViewById(R.id.btPicture)).setOnClickListener(this);
        ((Button)findViewById(R.id.btPhoto)).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.btPicture:
                getImage();
                break;
            case R.id.btPhoto:
                getPhoto();
                break;
            default:
                break;
        }
    }

    public void getImage() {
        Intent intent = new Intent();
		/* 开启Pictures画面Type设定为image */
        intent.setType("image/*");
		/* 使用Intent.ACTION_GET_CONTENT这个Action */
		/*
		 * 在Activity Action里面有一个“ACTION_GET_CONTENT”字符串常量，
		 * 该常量让用户选择特定类型的数据，并返回该数据的URI.
		 */
        intent.setAction(Intent.ACTION_GET_CONTENT);
		/* 取得相片后返回本画面 */
        startActivityForResult(intent, 1);
    }

    public void getPhoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Uri imageUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(),"foodupload.jpg"));
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(intent, 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null&&requestCode==1) {
            String sdStatus = Environment.getExternalStorageState();
            if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
//				Log.v("TestFile",
//						"SD card is not avaiable/writeable right now.");
                return;
            }
            Uri uri = data.getData();
            ContentResolver cr = this.getContentResolver();
            try {
                bitmap = BitmapFactory.decodeStream(cr
                        .openInputStream(uri));

                if (code == RequestCode.HEAD_IMAGE) {
                    bitmap1=Tools.getThumbnails(PhotoActivity.this,bitmap,R.dimen.head_height,R.dimen.head_height);
                }else {
                    bitmap1=Tools.getThumbnails(PhotoActivity.this,bitmap);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }else if (resultCode == RESULT_OK&&requestCode==2) {
            String sdStatus = Environment.getExternalStorageState();
            if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
                Log.v("TestFile",
                        "SD card is not avaiable/writeable right now.");
                return;
            }
//            BitmapFactory.Options bitmapFactoryOptions = new BitmapFactory.Options();
//            bitmapFactoryOptions.inPurgeable = true;
//            return BitmapFactory.decodeFile(/mnt/sdcard/YiZhai/cache/+bitmapName, bitmapFactoryOptions);
            BitmapFactory.Options bitmapFactoryOptions=new BitmapFactory.Options();
            bitmapFactoryOptions.inPurgeable=true;
            bitmap= BitmapFactory.decodeFile(Environment.getExternalStorageDirectory()+"/foodupload.jpg",bitmapFactoryOptions);
           if (code == RequestCode.HEAD_IMAGE) {
                bitmap1=Tools.getThumbnails(PhotoActivity.this,bitmap,R.dimen.head_height,R.dimen.head_height);
            }else{
               bitmap1=Tools.getThumbnails(PhotoActivity.this,bitmap);
           }

        }

        if (code == RequestCode.HEAD_IMAGE&&resultCode==RESULT_OK) {
            MineFragment.headimage = bitmap1;
            setResult(RequestCode.HEAD_IMAGE, data);
        }

        finish();
    }


    //这里很重要，为了防止出现OOM,这里Activity只要销毁，就回收内存
    @Override
    protected void onDestroy() {
        if (bitmap != null && !bitmap.isRecycled())
            bitmap.recycle();

        super.onDestroy();
    }
}
