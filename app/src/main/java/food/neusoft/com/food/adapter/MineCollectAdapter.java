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
import food.neusoft.com.food.domian.MineCollectInfo;

/**
 * Created by 张宇翔 on 2016/12/1 14:32.
 * Email：1124751755@qq.com
 * 功能：我的收藏的Adapter
 */

public class MineCollectAdapter extends BaseAdapter{

    private Context context;
    private List<MineCollectInfo> mineCollectInfos;
    private BitmapUtils butils;
    private BitmapUtils yutils;

    public MineCollectAdapter(Context context,List<MineCollectInfo> mineCollectInfos){
        this.context=context;
        this.mineCollectInfos=mineCollectInfos;
        butils=new BitmapUtils(context);
        butils.configDefaultLoadingImage(R.drawable.fj_loading);
        yutils=new BitmapUtils(context);
    }

    @Override
    public int getCount() {
        return mineCollectInfos.size();
    }

    @Override
    public MineCollectInfo getItem(int i) {
        return mineCollectInfos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view==null){
            view=View.inflate(context, R.layout.item_minecollect,null);
            holder=new ViewHolder();
            holder.item_icon= (ImageView) view.findViewById(R.id.item_icon);
            holder.iv_order= (ImageView) view.findViewById(R.id.iv_order);
            holder.item_title= (TextView) view.findViewById(R.id.item_title);
            holder.item_rating= (RatingBar) view.findViewById(R.id.item_rating);
            holder.tv_money= (TextView) view.findViewById(R.id.tv_money);
            holder.item_name= (TextView) view.findViewById(R.id.item_name);
            holder.tv_distance= (TextView) view.findViewById(R.id.tv_distance);
            holder.iv_new= (ImageView) view.findViewById(R.id.iv_new);
            holder.tv_new= (TextView) view.findViewById(R.id.tv_new);
            holder.iv_hui= (ImageView) view.findViewById(R.id.iv_hui);
            holder.tv_hui= (TextView) view.findViewById(R.id.tv_hui);
            holder.tv_time= (TextView) view.findViewById(R.id.tv_time);
            view.setTag(holder);
        }else{
            holder= (ViewHolder) view.getTag();
        }
        MineCollectInfo info=getItem(i);
        butils.display(holder.item_icon,info.getMarketIconPath());
        yutils.display(holder.iv_order,info.getBookIconPath());
        holder.item_title.setText(info.getMarketName());
        holder.item_rating.setRating((float) info.getMarketHotLevel());
        holder.tv_money.setText("¥"+info.getMarketPrice()+"/人");
        holder.item_name.setText(info.getTypeName());
        holder.tv_distance.setText(info.getMarketDistance()+"km");
        yutils.display(holder.iv_new,info.getNewIconPath());
        holder.tv_new.setText(info.getMarketIntroduce());
        yutils.display(holder.iv_hui,info.getDiscountIconPath());
        holder.tv_hui.setText(info.getMarketIntroduce());
        holder.tv_time.setText(info.getDate());
        return view;
    }


    static class ViewHolder{
        public ImageView item_icon;
        public ImageView iv_order;
        public TextView item_title;
        public RatingBar item_rating;
        public TextView tv_money;
        public TextView item_name;
        public TextView tv_distance;
        public ImageView iv_new;
        public TextView tv_new;
        public ImageView iv_hui;
        public TextView tv_hui;
        public TextView tv_time;
    }
}
