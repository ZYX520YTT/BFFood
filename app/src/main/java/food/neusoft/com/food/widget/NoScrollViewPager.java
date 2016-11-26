package food.neusoft.com.food.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
/**
 * Created by 张宇翔 on 2016/11/21 22:30.
 * Email：1124751755@qq.com
 * 功能：不能滑动的ViewPager
 */
public class NoScrollViewPager extends ViewPager{

	public NoScrollViewPager(Context context) {
		super(context);
	}

	public NoScrollViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		return false;
	}

	/**
	 * 重写onTouchEvent事件,什么都不用做
	 */
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		return false;
	}

}
