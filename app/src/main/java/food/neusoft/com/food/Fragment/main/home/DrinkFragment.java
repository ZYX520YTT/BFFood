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
import food.neusoft.com.food.adapter.DrinkAdapter;
import food.neusoft.com.food.domian.DrinkInfo;


/**
 * Created by 张宇翔 on 2016/11/22 19:15.
 * Email：1124751755@qq.com
 * 功能：
 */

public class DrinkFragment extends BaseFragment {
    private View view;

    @ViewInject(R.id.ls_show)
    private ListView ls_show;

    private List<DrinkInfo> drinkInfos;


    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_drink,container,false);
        ViewUtils.inject(this, view);
        Init();
        return view;
    }

    private void Init() {
        drinkInfos=new ArrayList<>();
        for(int i=0;i<8;i++){
            DrinkInfo drinkInfo;
            if(i%4==0){
                drinkInfo=new DrinkInfo(R.drawable.pic_drink_one,
                        "会员可享8折优惠","抹茶土司(春熙路店)",4.0f,R.drawable.view_privilige,R.drawable.view_yu);
            }else if(i%4==1){
                drinkInfo=new DrinkInfo(R.drawable.pic_drink_two,
                        "开业大酬宾","英伦摩点甜品(华阳路店)",3.0f,R.drawable.view_new,R.drawable.view_yu);
            }else if(i%4==2){
                drinkInfo=new DrinkInfo(R.drawable.pic_drink_three,
                        "会员可享8折优惠","沙茵屋牛奶布丁(春熙路店)",5.0f,R.drawable.view_privilige,R.drawable.view_yu);
            }else{
                drinkInfo=new DrinkInfo(R.drawable.pic_drink_four,
                        "会员可享8折优惠","泡芙蜜语(春熙路店)",3.0f,R.drawable.view_privilige,R.drawable.view_yu);
            }
            drinkInfos.add(drinkInfo);
        }

        setupFragment();
    }

    private void setupFragment() {
        ls_show.setAdapter(new DrinkAdapter(getContext(),drinkInfos));
    }
}
