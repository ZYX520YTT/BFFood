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
import food.neusoft.com.food.adapter.OrderAdapter;
import food.neusoft.com.food.domian.OrderInfo;

/**
 * Created by 张宇翔 on 2016/11/21 22:47.
 * Email：1124751755@qq.com
 * 功能：预约页面
 */

public class OrderFragment extends BaseFragment {
    private View view;

    @ViewInject(R.id.ls_show)
    private ListView ls_show;

    private List<OrderInfo> orderInfos;


    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_order,container,false);
        ViewUtils.inject(this, view);
        Init();
        return view;
    }

    private void Init() {
        orderInfos=new ArrayList<>();
        for(int i=0;i<4;i++){
            OrderInfo orderInfo;
            if(i%3==0){
                orderInfo = new OrderInfo(R.drawable.re_pic_one,
                        "泰普西餐店(大业店路)",4.0f,"8折(预约专享)","¥50/人","西餐","1km");
            }else if(i%3==1){
                orderInfo=new OrderInfo(R.drawable.re_pic_two,
                        "销魂掌(中海国际店)",4.0f,"8.8折(预约专享)","¥55/人","川菜","2.3km");
            }else{
                orderInfo=new OrderInfo(R.drawable.re_pic_three,
                        "韩式自助烤肉(SM广场店)",4.0f,"8.5折(预约专享)","¥65/人","自助餐","5.0km");
            }
            orderInfos.add(orderInfo);
        }

        setupFragment();
    }

    private void setupFragment() {
        ls_show.setAdapter(new OrderAdapter(getContext(),orderInfos));
    }


}
