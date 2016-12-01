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
import food.neusoft.com.food.domian.HotPotInfo;
import food.neusoft.com.food.utils.TimeChose;

/**
 * Created by 张宇翔 on 2016/11/23 17:06.
 * Email：1124751755@qq.com
 * 功能：火锅的Adapter
 */

public class HotPotAdapter extends BaseAdapter {

    private Context context;
    private List<HotPotInfo> hotPotInfos;

    private BitmapUtils butils;
    private BitmapUtils yutils;

    public HotPotAdapter(Context context,List<HotPotInfo> hotPotInfos) {
        this.context=context;
        this.hotPotInfos=hotPotInfos;
        butils=new BitmapUtils(context);
        butils.configDefaultLoadingImage(R.drawable.hg_loading);
        yutils=new BitmapUtils(context);
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
            holder.iv_hui= (ImageView) view.findViewById(R.id.iv_hui);
            view.setTag(holder);
        }else{
            holder= (ViewHolder) view.getTag();
        }
        HotPotInfo hotPotInfo=getItem(i);
        butils.display(holder.item_icon,hotPotInfo.getMarketIconPath());
        holder.item_title.setText(hotPotInfo.getMarketName());
        holder.item_rating.setRating((float) hotPotInfo.getMarketHotLevel());
        holder.tv_money.setText("¥"+hotPotInfo.getMarketPrice()+"/人");
        holder.item_name.setText(hotPotInfo.getTypeName());
        holder.tv_price.setText(hotPotInfo.getMarketDiscount()+"折(预约专享受)");
        yutils.display(holder.iv_hui,hotPotInfo.getBookIconPath());
        Oncleck(holder,i);
        return view;
    }


    static class ViewHolder{
        public ImageView item_icon;
        public TextView item_title;
        public RatingBar item_rating;
        public TextView tv_money;
        public TextView item_name;
        public TextView tv_price;
        public ImageView iv_hui;
    }

    private void Oncleck(ViewHolder holder,final  int position){
        holder.iv_hui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HotPotInfo hotPotInfo=hotPotInfos.get(position);
                TimeChose timeChose=new TimeChose(context, (Activity) context);
                timeChose.DiaLog(hotPotInfo.getMarketNo());
            }
        });
    }
}
