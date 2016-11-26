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
import food.neusoft.com.food.domian.OrderInfo;

/**
 * Created by 张宇翔 on 2016/11/23 17:06.
 * Email：1124751755@qq.com
 * 功能：预订的Adapter
 */

public class OrderAdapter extends BaseAdapter {

    private Context context;
    private List<OrderInfo> orderInfos;

    public OrderAdapter(Context context, List<OrderInfo> orderInfos) {
        this.context=context;
        this.orderInfos=orderInfos;
    }

    @Override
    public int getCount() {
        return orderInfos.size();
    }

    @Override
    public OrderInfo getItem(int i) {
        return orderInfos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view==null){
            view=View.inflate(context, R.layout.item_order,null);
            holder=new ViewHolder();
            holder.item_icon= (ImageView) view.findViewById(R.id.item_icon);
            holder.item_title= (TextView) view.findViewById(R.id.item_title);
            holder.item_rating= (RatingBar) view.findViewById(R.id.item_rating);
            holder.tv_money= (TextView) view.findViewById(R.id.tv_money);
            holder.item_name= (TextView) view.findViewById(R.id.item_name);
            holder.tv_price= (TextView) view.findViewById(R.id.tv_price);
            holder.tv_distance= (TextView) view.findViewById(R.id.tv_distance);
            view.setTag(holder);
        }else{
            holder= (ViewHolder) view.getTag();
        }
        OrderInfo orderInfo=getItem(i);
        holder.item_icon.setImageResource(orderInfo.getImageid());
        holder.item_title.setText(orderInfo.getStorename());
        holder.item_rating.setRating(orderInfo.getRating());
        holder.tv_money.setText(orderInfo.getMoney());
        holder.item_name.setText(orderInfo.getName());
        holder.tv_price.setText(orderInfo.getPrice());
        holder.tv_distance.setText(orderInfo.getDistance());
        return view;
    }


    static class ViewHolder{
        public ImageView item_icon;
        public TextView item_title;
        public RatingBar item_rating;
        public TextView tv_money;
        public TextView item_name;
        public TextView tv_price;
        public TextView tv_distance;

    }
}
