package food.neusoft.com.food.Fragment.main;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

import food.neusoft.com.food.Fragment.main.base.BaseFragment;
import food.neusoft.com.food.Fragment.main.home.DessertFragment;
import food.neusoft.com.food.Fragment.main.home.DrinkFragment;
import food.neusoft.com.food.Fragment.main.home.HotFragment;
import food.neusoft.com.food.Fragment.main.home.HotpotFragment;
import food.neusoft.com.food.Fragment.main.home.WesternFragment;
import food.neusoft.com.food.R;
import food.neusoft.com.food.adapter.MyFragmentAdapter;
import food.neusoft.com.food.utils.Tools;

/**
 * Created by 张宇翔 on 2016/11/21 22:47.
 * Email：1124751755@qq.com
 * 功能：首页
 */

public class HomeFragment extends BaseFragment implements View.OnClickListener {

    private Context context;
    private View view;

    @ViewInject(R.id.rlyt_title)
    private RelativeLayout rlyt_title;
    @ViewInject(R.id.tv_local)
    private TextView tv_local;
    @ViewInject(R.id.iv_sign)
    private ImageView iv_sign;
    @ViewInject(R.id.iv_search)
    private ImageView iv_search;

    @ViewInject(R.id.llyt_title)
    private LinearLayout llyt_title;
    /**热门**/
    @ViewInject(R.id.rlyt_hot)
    private RelativeLayout rlyt_hot;
    @ViewInject(R.id.iv_hot)
    private ImageView iv_hot;
    @ViewInject(R.id.tv_hot)
    private TextView tv_hot;

    /**火锅**/
    @ViewInject(R.id.rlyt_hotpot)
    private RelativeLayout rlyt_hotpot;
    @ViewInject(R.id.iv_hotpot)
    private ImageView iv_hotpot;
    @ViewInject(R.id.tv_hotpot)
    private TextView tv_hotpot;

    /**西餐**/
    @ViewInject(R.id.rlyt_western)
    private RelativeLayout rlyt_western;
    @ViewInject(R.id.iv_western)
    private ImageView iv_western;
    @ViewInject(R.id.tv_western)
    private TextView tv_western;

    /**甜品**/
    @ViewInject(R.id.rlyt_dessert)
    private RelativeLayout rlyt_dessert;
    @ViewInject(R.id.iv_dessert)
    private ImageView iv_dessert;
    @ViewInject(R.id.tv_dessert)
    private TextView tv_dessert;

    /**饮品**/
    @ViewInject(R.id.rlyt_drink)
    private RelativeLayout rlyt_drink;
    @ViewInject(R.id.iv_drink)
    private ImageView iv_drink;
    @ViewInject(R.id.tv_drink)
    private TextView tv_drink;

    /**viewpager**/
    @ViewInject(R.id.viewPager)
    private ViewPager viewPager;



    private List<BaseFragment> fragments;
    private MyFragmentAdapter adapter;
    private HotFragment hotFragment;
    private WesternFragment westernFragment;
    private HotpotFragment hotpotFragment;
    private DessertFragment dessertFragment;
    private DrinkFragment drinkFragment;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getContext();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        ViewUtils.inject(this, view);
        Init();
        return view;
    }

    private void Init() {


        fragments=new ArrayList<>();

        //点击展示地区
        iv_sign.setOnClickListener(this);
        tv_local.setOnClickListener(this);

        //点击搜索
        iv_search.setOnClickListener(this);



        rlyt_hot.setOnClickListener(this);
        rlyt_hotpot.setOnClickListener(this);
        rlyt_western.setOnClickListener(this);
        rlyt_dessert.setOnClickListener(this);
        rlyt_drink.setOnClickListener(this);


        hotFragment = new HotFragment();
        hotFragment.LOCAL=tv_local.getText().toString();
        fragments.add(hotFragment);


        hotpotFragment = new HotpotFragment();
        hotpotFragment.LOCAL=tv_local.getText().toString();
        fragments.add(hotpotFragment);


        westernFragment = new WesternFragment();
        westernFragment.LOCAL=tv_local.getText().toString();
        fragments.add(westernFragment);

        dessertFragment = new DessertFragment();
        dessertFragment.LOCAL=tv_local.getText().toString();
        fragments.add(dessertFragment);


        drinkFragment = new DrinkFragment();
        drinkFragment.LOCAL=tv_local.getText().toString();
        fragments.add(drinkFragment);


        setupFragment();
    }



    private void setupFragment(){
        FragmentManager fm=this.getChildFragmentManager();

        adapter = new MyFragmentAdapter(fm,fragments);
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                ChangLineAndColor(position);
            }
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_search:
//                Toast.makeText(getContext(),tv_local.getText().toString(),Toast.LENGTH_SHORT).show();
                hotFragment.LOCAL=tv_local.getText().toString();
                hotpotFragment.LOCAL=tv_local.getText().toString();
                westernFragment.LOCAL=tv_local.getText().toString();
                dessertFragment.LOCAL=tv_local.getText().toString();
                drinkFragment.LOCAL=tv_local.getText().toString();
                adapter.notifyDataSetChanged();
                break;
            case R.id.iv_sign:
            case R.id.tv_local:
                int size=tv_local.getWidth()+iv_search.getWidth()+
                (getResources().getDimensionPixelOffset(R.dimen.group_padding));
                Tools.showLocalList(context, tv_local, local, size);
                break;
            case R.id.rlyt_hot: /**热门**/
                viewPager.setCurrentItem(0,false);
                ChangLineAndColor(0);
                break;
            case R.id.rlyt_hotpot: /**火锅**/
                viewPager.setCurrentItem(1,false);
                ChangLineAndColor(1);
                break;
            case R.id.rlyt_western:/**西餐**/
                viewPager.setCurrentItem(2,false);
                ChangLineAndColor(2);
                break;
            case R.id.rlyt_dessert: /**甜品**/
                viewPager.setCurrentItem(3,false);
                ChangLineAndColor(3);
                break;
            case R.id.rlyt_drink:/**饮品**/
                viewPager.setCurrentItem(4,false);
                ChangLineAndColor(4);
                break;
            default:
                break;
        }
    }


    /**改变标题的颜色和下划线的颜色**/
    private void ChangLineAndColor(int postion){
        switch (postion){
            case 0:
                iv_hot.setVisibility(View.VISIBLE);
                tv_hot.setTextColor(getResources().getColor(R.color.maincolor_check));
                iv_hotpot.setVisibility(View.INVISIBLE);
                tv_hotpot.setTextColor(getResources().getColor(R.color.maincolor_nocheck));
                iv_western.setVisibility(View.INVISIBLE);
                tv_western.setTextColor(getResources().getColor(R.color.maincolor_nocheck));
                iv_dessert.setVisibility(View.INVISIBLE);
                tv_dessert.setTextColor(getResources().getColor(R.color.maincolor_nocheck));
                iv_drink.setVisibility(View.INVISIBLE);
                tv_drink.setTextColor(getResources().getColor(R.color.maincolor_nocheck));
                break;
            case 1:
                iv_hot.setVisibility(View.INVISIBLE);
                tv_hot.setTextColor(getResources().getColor(R.color.maincolor_nocheck));
                iv_hotpot.setVisibility(View.VISIBLE);
                tv_hotpot.setTextColor(getResources().getColor(R.color.maincolor_check));
                iv_western.setVisibility(View.INVISIBLE);
                tv_western.setTextColor(getResources().getColor(R.color.maincolor_nocheck));
                iv_dessert.setVisibility(View.INVISIBLE);
                tv_dessert.setTextColor(getResources().getColor(R.color.maincolor_nocheck));
                iv_drink.setVisibility(View.INVISIBLE);
                tv_drink.setTextColor(getResources().getColor(R.color.maincolor_nocheck));
                break;
            case 2:
                iv_hot.setVisibility(View.INVISIBLE);
                tv_hot.setTextColor(getResources().getColor(R.color.maincolor_nocheck));
                iv_hotpot.setVisibility(View.INVISIBLE);
                tv_hotpot.setTextColor(getResources().getColor(R.color.maincolor_nocheck));
                iv_western.setVisibility(View.VISIBLE);
                tv_western.setTextColor(getResources().getColor(R.color.maincolor_check));
                iv_dessert.setVisibility(View.INVISIBLE);
                tv_dessert.setTextColor(getResources().getColor(R.color.maincolor_nocheck));
                iv_drink.setVisibility(View.INVISIBLE);
                tv_drink.setTextColor(getResources().getColor(R.color.maincolor_nocheck));
                break;
            case 3:
                iv_hot.setVisibility(View.INVISIBLE);
                tv_hot.setTextColor(getResources().getColor(R.color.maincolor_nocheck));
                iv_hotpot.setVisibility(View.INVISIBLE);
                tv_hotpot.setTextColor(getResources().getColor(R.color.maincolor_nocheck));
                iv_western.setVisibility(View.INVISIBLE);
                tv_western.setTextColor(getResources().getColor(R.color.maincolor_nocheck));
                iv_dessert.setVisibility(View.VISIBLE);
                tv_dessert.setTextColor(getResources().getColor(R.color.maincolor_check));
                iv_drink.setVisibility(View.INVISIBLE);
                tv_drink.setTextColor(getResources().getColor(R.color.maincolor_nocheck));
                break;
            case 4:
                iv_hot.setVisibility(View.INVISIBLE);
                tv_hot.setTextColor(getResources().getColor(R.color.maincolor_nocheck));
                iv_hotpot.setVisibility(View.INVISIBLE);
                tv_hotpot.setTextColor(getResources().getColor(R.color.maincolor_nocheck));
                iv_western.setVisibility(View.INVISIBLE);
                tv_western.setTextColor(getResources().getColor(R.color.maincolor_nocheck));
                iv_dessert.setVisibility(View.INVISIBLE);
                tv_dessert.setTextColor(getResources().getColor(R.color.maincolor_nocheck));
                iv_drink.setVisibility(View.VISIBLE);
                tv_drink.setTextColor(getResources().getColor(R.color.maincolor_check));
                break;
            default:
                break;
        }
    }


}
