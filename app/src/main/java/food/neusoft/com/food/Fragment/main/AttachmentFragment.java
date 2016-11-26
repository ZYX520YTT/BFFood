package food.neusoft.com.food.Fragment.main;

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
import food.neusoft.com.food.adapter.AttachAdapter;
import food.neusoft.com.food.domian.AttachInfo;

/**
 * Created by 张宇翔 on 2016/11/21 22:47.
 * Email：1124751755@qq.com
 * 功能：附近的页面
 */

public class AttachmentFragment extends BaseFragment {

    private View view;

    @ViewInject(R.id.ls_show)
    private ListView ls_show;

    private List<AttachInfo> attachInfos;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_attach, container, false);
        ViewUtils.inject(this, view);
        Init();
        return view;
    }

    private void Init() {

        attachInfos=new ArrayList<>();
        for(int i=0;i<4;i++){
            AttachInfo attachInfo;
            if(i%3==0){
                attachInfo = new AttachInfo(R.drawable.pic_attach_one,
                        "重庆渝达老火锅(大业路店)",4.0f,"8折(预约专享)","¥50/人","火锅","1km");
            }else if(i%3==1){
                attachInfo=new AttachInfo(R.drawable.pic_attach_two,
                        "销魂掌(中海国际店)",4.0f,"8.8折(预约专享)","¥55/人","川菜","2.3km");
            }else{
                attachInfo=new AttachInfo(R.drawable.pic_attach_three,
                        "韩式自助烤肉(SM广场店)",4.0f,"8.5折(预约专享)","¥65/人","自助餐","5.0km");
            }
            attachInfos.add(attachInfo);
        }

        setupFragment();

    }

    private void setupFragment() {
        ls_show.setAdapter(new AttachAdapter(getContext(),attachInfos));
    }



}
