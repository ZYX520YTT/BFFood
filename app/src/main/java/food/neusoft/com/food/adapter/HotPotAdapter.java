package food.neusoft.com.food.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

import food.neusoft.com.food.R;
import food.neusoft.com.food.domian.HotPotInfo;

/**
 * Created by 张宇翔 on 2016/11/23 17:06.
 * Email：1124751755@qq.com
 * 功能：火锅的Adapter
 */

public class HotPotAdapter extends BaseAdapter {

    private Context context;
    private List<HotPotInfo> hotPotInfos;

    public HotPotAdapter(Context context,List<HotPotInfo> hotPotInfos) {
        this.context=context;
        this.hotPotInfos=hotPotInfos;
    }

    @Override
    public int getCount() {
        return hotPotInfos.size();
    }

    @Override
    public HotPotInfo getItem(int i) {
        return hotPotInfos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view==null){
            view=View.inflate(context, R.layout.itme_hotpot,null);
            holder=new ViewHolder();
            holder.item_icon= (ImageView) view.findViewById(R.id.item_icon);
            holder.item_title= (TextView) view.findViewById(R.id.item_title);
            holder.item_rating= (RatingBar) view.findViewById(R.id.item_rating);
            holder.tv_money= (TextView) view.findViewById(R.id.tv_money);
            holder.item_name= (TextView) view.findViewById(R.id.item_name);
            holder.tv_price= (TextView) view.findViewById(R.id.tv_price);
            view.setTag(holder);
        }else{
            holder= (ViewHolder) view.getTag();
        }
        HotPotInfo hotPotInfo=getItem(i);
        holder.item_icon.setImageResource(hotPotInfo.getImageid());
        holder.item_title.setText(hotPotInfo.getStorename());
        holder.item_rating.setRating(hotPotInfo.getRating());
        holder.tv_money.setText(hotPotInfo.getMoney());
        holder.item_name.setText(hotPotInfo.getName());
        holder.tv_price.setText(hotPotInfo.getPrice());
        return view;
    }


    static class ViewHolder{
        public ImageView item_icon;
        public TextView item_title;
        public RatingBar item_rating;
        public TextView tv_money;
        public TextView item_name;
        public TextView tv_price;

    }
}
