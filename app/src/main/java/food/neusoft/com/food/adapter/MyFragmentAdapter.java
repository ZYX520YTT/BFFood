package food.neusoft.com.food.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import food.neusoft.com.food.Fragment.main.base.BaseFragment;

/**
 * Created by 张宇翔 on 2016/11/22 19:20.
 * Email：1124751755@qq.com
 * 功能：FragmentPagerAdapter
 */

public class MyFragmentAdapter extends FragmentPagerAdapter {

    private List<BaseFragment> fs;

    public MyFragmentAdapter(FragmentManager fm,List<BaseFragment> fs) {
        super(fm);
        this.fs=fs;
    }

    @Override
    public Fragment getItem(int position) {
        return fs.get(position);
    }

    @Override
    public int getCount() {
        return fs.size();
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
