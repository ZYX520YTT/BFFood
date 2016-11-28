package food.neusoft.com.food.Fragment.main.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张宇翔 on 2016/11/22 13:04.
 * Email：1124751755@qq.com
 * 功能：基础Fragment
 */

public class BaseFragment extends Fragment {

    public static List<String> local;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        local=new ArrayList<>();
        local.add("锦江区");
        local.add("青羊区");
        local.add("金牛区");
        local.add("武侯区");
        local.add("成华区");
        local.add("龙泉驿");
        local.add("青白江");
        local.add("新都区");
        local.add("温江区");
        local.add("高新区");
    }
}
