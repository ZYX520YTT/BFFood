package food.neusoft.com.food;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 张宇翔 on 2016/11/21 22:32.
 * Email：1124751755@qq.com
 * 功能：Application
 */

public class NApplication extends Application {
	public static String user_number;//用户的手机号码
	public static String user_power;//用户的手机号码
	public static String ImageUrl;//用户用第三方登录时候的头像Url
	public static String nickname;//用户用第三方登录时候的昵称

	public static long share_marketNo=0;//分享的店铺的编号，默认是0


	private static NApplication application;
	private static int mainTid;
	private static Handler handler;

	private List AllAcivity;



	public static Map<Long,Boolean> MineCollects;//用户已进入主界面，就先查询出收藏的内容


	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
	}
	@Override
	public void onCreate() {
		super.onCreate();
		AllAcivity = new ArrayList<Map<String, String>>();
		application=this;
		mainTid = android.os.Process.myTid();
		handler=new Handler();
		MineCollects=new HashMap<>();//初始化我的收藏信息
	}
	
	

	public static Context getApplication() {
		return application;
	}
	public static int getMainTid() {
		return mainTid;
	}
	public static Handler getHandler() {
		return handler;
	}

	public   void addActivity(Activity activity) {
		try {
			AllAcivity.add(activity);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public  void destoryAllActivity() {
		try {
			for (int i = 0; i < AllAcivity.size(); i++) {
				((Activity) AllAcivity.get(i)).finish();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}