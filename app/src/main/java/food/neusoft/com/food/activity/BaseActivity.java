package food.neusoft.com.food.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;

/**
 * Created by 张宇翔 on 2016/11/21 22:30.
 * Email：1124751755@qq.com
 * 功能：基础Activity
 */

public class BaseActivity extends FragmentActivity {

    public static int ScreenWidth;//屏幕高度
    public static int ScreenHeight;//屏幕宽度


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        ScreenWidth=metric.widthPixels;
        ScreenHeight=metric.heightPixels;
    }





}
