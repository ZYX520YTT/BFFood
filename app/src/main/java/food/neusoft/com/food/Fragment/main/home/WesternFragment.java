package food.neusoft.com.food.Fragment.main.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

import food.neusoft.com.food.Fragment.main.base.BaseFragment;
import food.neusoft.com.food.R;
import food.neusoft.com.food.adapter.WestAdapter;
import food.neusoft.com.food.domian.WestFoodInfo;

/**
 * Created by 张宇翔 on 2016/11/22 19:15.
 * Email：1124751755@qq.com
 * 功能：
 */

public class WesternFragment extends BaseFragment {
    private View view;

    @ViewInject(R.id.ls_show)
    private ListView ls_show;


    private List<WestFoodInfo> infos;


    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_west,container,false);
        ViewUtils.inject(this, view);
        Init();
        return view;
    }

    private void Init() {
        infos = new ArrayList<>();
        for(int i=0;i<4;i++){
            if(i%2==0){
                WestFoodInfo info=new WestFoodInfo("21km",R.drawable.view_privilige,"西餐",R.drawable.pic_west_one,"彼得西餐厅");
                infos.add(info);
            }else{
                WestFoodInfo info=new WestFoodInfo("15km",R.drawable.view_yu,"西餐",R.drawable.pic_west_two,"加路仕西餐厅");
                infos.add(info);
            }
        }



        setupFragment();
    }

    private void setupFragment() {
        ls_show.setAdapter(new WestAdapter(getContext(),infos));
    }
}
