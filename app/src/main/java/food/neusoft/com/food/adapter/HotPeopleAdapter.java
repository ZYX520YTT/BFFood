package food.neusoft.com.food.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.lidroid.xutils.BitmapUtils;

import java.util.List;

import food.neusoft.com.food.R;
import food.neusoft.com.food.domian.HotMarketInfo;

/**
 * Created by 张宇翔 on 2016/11/23 11:43.
 * Email：1124751755@qq.com
 * 功能：热门的Adapter
 */

public class HotPeopleAdapter extends BaseAdapter {

    private Context context;
    private List<HotMarketInfo> hotpeoples;

    private BitmapUtils utils;

    public HotPeopleAdapter(Context context,List<HotMarketInfo> hotpeoples){
        this.context=context;
        this.hotpeoples=hotpeoples;
        utils=new BitmapUtils(context);
        utils.configDefaultLoadingImage(R.drawable.xc_loading);
    }


    @Override
    public int getCount() {
        return hotpeoples.size();
    }

    @Override
    public HotMarketInfo getItem(int i) {
        return hotpeoples.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view==null){
            view=View.inflate(context, R.layout.item_hotpeople,null);
            holder=new ViewHolder();
            holder.iv_hotpeople_image= (ImageView) view.findViewById(R.id.iv_hotpeople_image);
            view.setTag(holder);
        }else{
            holder= (ViewHolder) view.getTag();
        }
        utils.display(holder.iv_hotpeople_image,getItem(i).getMarketBigPicture());
        return view;
    }

    static class ViewHolder{
        public ImageView iv_hotpeople_image;
    }
}
