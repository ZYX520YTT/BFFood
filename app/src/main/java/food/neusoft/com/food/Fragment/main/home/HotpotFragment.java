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
import food.neusoft.com.food.adapter.HotPotAdapter;
import food.neusoft.com.food.domian.HotPotInfo;

/**
 * Created by 张宇翔 on 2016/11/22 19:14.
 * Email：1124751755@qq.com
 * 功能：
 */

public class HotpotFragment extends BaseFragment {
    private View view;

    @ViewInject(R.id.ls_show)
    private ListView ls_show;

    private List<HotPotInfo> hotPotInfos;


    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_hotpot,container,false);
        ViewUtils.inject(this,view);
        InitData();
        return view;
    }

    private void InitData() {

        hotPotInfos=new ArrayList<>();
        for(int i=0;i<4;i++){
            HotPotInfo hotPotInfo;
            if(i%3==0){
                hotPotInfo = new HotPotInfo(R.drawable.pic_hotpot_one,
                        "重庆渝达老火锅(大业路店)",4.0f,"火锅","¥50/人","8折(预约专享)");
            }else if(i%3==1){
                hotPotInfo=new HotPotInfo(R.drawable.pic_hotpot_two,
                        "渝龙老火锅(广场路店)",3.0f,"火锅","¥40/人","8折(预约专享)");
            }else{
                hotPotInfo=new HotPotInfo(R.drawable.pic_hotpot_three,
                        "熊氏土灶老火锅(水岸庭院店)",5.0f,"火锅","¥60/人","8折(预约专享)");
            }
            hotPotInfos.add(hotPotInfo);
        }

        setupFragment();



    }

    private void setupFragment() {
        ls_show.setAdapter(new HotPotAdapter(getContext(),hotPotInfos));
    }


}
