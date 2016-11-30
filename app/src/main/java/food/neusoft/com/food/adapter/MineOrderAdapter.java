package food.neusoft.com.food.adapter;

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
import food.neusoft.com.food.domian.MineOrderInfo;

/**
 * Created by 张宇翔 on 2016/11/30 20:52.
 * Email：1124751755@qq.com
 * 功能：
 */

public class MineOrderAdapter extends BaseAdapter {

    private Context context;
    private List<MineOrderInfo> mineOrderInfos;

    private BitmapUtils butils;
    private BitmapUtils yutils;

    public MineOrderAdapter(Context context,List<MineOrderInfo> mineOrderInfos){
        this.context=context;
        this.mineOrderInfos=mineOrderInfos;
        butils=new BitmapUtils(context);
        yutils=new BitmapUtils(context);
    }

    @Override
    public int getCount() {
        return mineOrderInfos.size();
    }

    @Override
    public MineOrderInfo getItem(int i) {
        return mineOrderInfos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view==null){
            view=View.inflate(context, R.layout.item_mineorder,null);
            holder=new ViewHolder();
            holder.item_icon= (ImageView) view.findViewById(R.id.item_icon);
            holder.iv_order= (ImageView) view.findViewById(R.id.iv_order);
            holder.item_title= (TextView) view.findViewById(R.id.item_title);
            holder.tv_time= (TextView) view.findViewById(R.id.tv_time);
            holder.item_rating= (RatingBar) view.findViewById(R.id.item_rating);
            holder.tv_money= (TextView) view.findViewById(R.id.tv_money);
            holder.item_name= (TextView) view.findViewById(R.id.item_name);
            holder.tv_distance= (TextView) view.findViewById(R.id.tv_distance);
            holder.tv_orderinfo= (TextView) view.findViewById(R.id.tv_orderinfo);
            view.setTag(holder);
        }else{
            holder= (ViewHolder) view.getTag();
        }
        MineOrderInfo mineOrderInfo=getItem(i);
        butils.display(holder.item_icon,mineOrderInfo.getMarketIconPath());
        yutils.display(holder.iv_order,mineOrderInfo.getBookIconPath());
        holder.item_title.setText(mineOrderInfo.getMarketName());
        String[] splie=mineOrderInfo.getOrderDay().split("日");
        holder.tv_time.setText(splie[0]+"日");
        holder.item_rating.setRating((float) mineOrderInfo.getMarketHotLevel());
        holder.tv_money.setText("¥"+mineOrderInfo.getMarketPrice()+"/人");
        holder.item_name.setText(mineOrderInfo.getTypeName());
        holder.tv_distance.setText(mineOrderInfo.getMarketDistance()+"km");
        holder.tv_orderinfo.setText(mineOrderInfo.getOrderState());
        return view;
    }

    static class ViewHolder{
        public ImageView item_icon;
        public ImageView iv_order;
        public TextView item_title;
        public TextView tv_time;
        public RatingBar item_rating;
        public TextView tv_money;
        public TextView item_name;
        public TextView tv_distance;
        public TextView tv_orderinfo;
    }
}
