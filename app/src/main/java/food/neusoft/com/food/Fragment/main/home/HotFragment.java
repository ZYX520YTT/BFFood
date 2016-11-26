package food.neusoft.com.food.Fragment.main.home;

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

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

import food.neusoft.com.food.Fragment.main.base.BaseFragment;
import food.neusoft.com.food.R;
import food.neusoft.com.food.adapter.HotPeopleAdapter;
import food.neusoft.com.food.adapter.PhotoListAdapter;
import food.neusoft.com.food.domian.FoodPhotoInfo;

/**
 * Created by 张宇翔 on 2016/11/22 19:14.
 * Email：1124751755@qq.com
 * 功能：
 */

public class HotFragment extends BaseFragment {
    private View view;

    @ViewInject(R.id.myimpager)
    private ViewPager myimpager;
    @ViewInject(R.id.ll_point)
    private LinearLayout ll_point;
    @ViewInject(R.id.ls_show)
    private ListView ls_show;
    @ViewInject(R.id.ls_show_hotpeople)
    private ListView ls_show_hotpeople;

    private List<Integer> imageId;


    private Handler handler;
    private List<FoodPhotoInfo> foodPhotoInfos;
    private List<Integer> hotpeoples;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_hot, container, false);
        ViewUtils.inject(this, view);
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


        InitFood();//初始化“发现美食”

        InitHot();//初始化热门商家


        setupFragment();


    }

    private void InitHot() {

        hotpeoples = new ArrayList<>();
        for(int i=0;i<5;i++){
            hotpeoples.add(R.drawable.title1);
        }


    }

    private void InitFood() {

        foodPhotoInfos = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            int id = 0;
            String desc = "";
            if (i == 0) {
                id = R.drawable.food1;
                desc = getResources().getString(R.string.hot_food1);
            } else if (i == 1) {
                id = R.drawable.food2;
                desc = getResources().getString(R.string.hot_food2);
            } else if (i == 2) {
                id = R.drawable.food3;
                desc = getResources().getString(R.string.hot_food3);
            } else if (i == 3) {
                id = R.drawable.food4;
                desc = getResources().getString(R.string.hot_food4);
            } else if (i == 4) {
                id = R.drawable.food5;
                desc = getResources().getString(R.string.hot_food5);
            } else if (i == 5) {
                id = R.drawable.food6;
                desc = getResources().getString(R.string.hot_food6);
            }
            FoodPhotoInfo foodPhotoInfo = new FoodPhotoInfo(id, desc);
            foodPhotoInfos.add(foodPhotoInfo);
        }
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


        //发现美食
        ls_show.setAdapter(new PhotoListAdapter(getContext(),foodPhotoInfos));

        //热门商家
        ls_show_hotpeople.setAdapter(new HotPeopleAdapter(getContext(),hotpeoples));

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


}
