package food.neusoft.com.food.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;

import java.util.List;

import food.neusoft.com.food.R;
import food.neusoft.com.food.domian.OrderInfo;
import food.neusoft.com.food.utils.TimeChose;

/**
 * Created by 张宇翔 on 2016/11/23 17:06.
 * Email：1124751755@qq.com
 * 功能：预订的Adapter
 */

public class OrderAdapter extends BaseAdapter {

    private Context context;
    private List<OrderInfo> orderInfos;
    private BitmapUtils butils;
    private BitmapUtils yutils;


    public OrderAdapter(Context context, List<OrderInfo> orderInfos) {
        this.context=context;
        this.orderInfos=orderInfos;
        butils=new BitmapUtils(context);
        yutils=new BitmapUtils(context);
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
            holder.iv_order= (ImageView) view.findViewById(R.id.iv_order);
            holder.item_title= (TextView) view.findViewById(R.id.item_title);
            holder.item_rating= (RatingBar) view.findViewById(R.id.item_rating);
            holder.tv_money= (TextView) view.findViewById(R.id.tv_money);
            holder.item_name= (TextView) view.findViewById(R.id.item_name);
            holder.tv_price= (TextView) view.findViewById(R.id.tv_price);
            holder.tv_distance= (TextView) view.findViewById(R.id.tv_distance);
            holder.iv_hui= (ImageView) view.findViewById(R.id.iv_hui);
            view.setTag(holder);
        }else{
            holder= (ViewHolder) view.getTag();
        }
        OrderInfo orderInfo=getItem(i);
        butils.display(holder.item_icon,orderInfo.getMarketIconPath());
        yutils.display(holder.iv_order,orderInfo.getBookIconPath());
        holder.item_title.setText(orderInfo.getMarketName());
        holder.item_rating.setRating((float) orderInfo.getMarketHotLevel());
        holder.tv_money.setText("¥"+orderInfo.getMarketPrice()+"/人");
        holder.item_name.setText(orderInfo.getTypeName());
        holder.tv_distance.setText(orderInfo.getMarketDistance()+"km");
        yutils.display(holder.iv_hui,orderInfo.getDiscountIconPath());
        holder.tv_price.setText(orderInfo.getMarketIntroduce());
        OnClick(holder,i);
        return view;
    }


    static class ViewHolder{
        public ImageView item_icon;
        public ImageView iv_order;
        public TextView item_title;
        public RatingBar item_rating;
        public TextView tv_money;
        public TextView item_name;
        public TextView tv_price;
        public TextView tv_distance;
        public ImageView iv_hui;

    }


    private void OnClick(ViewHolder holder,final  int position){
        holder.iv_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OrderInfo orderInfo=orderInfos.get(position);
                TimeChose timeChose=new TimeChose(context, (Activity) context);
                timeChose.DiaLog(orderInfo.getMarketNo());
            }
        });
    }
}
