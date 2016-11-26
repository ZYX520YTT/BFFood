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
import food.neusoft.com.food.domian.AttachInfo;

/**
 * Created by 张宇翔 on 2016/11/23 17:06.
 * Email：1124751755@qq.com
 * 功能：附近的Adapter
 */

public class AttachAdapter extends BaseAdapter {

    private Context context;
    private List<AttachInfo> attachInfos;

    public AttachAdapter(Context context, List<AttachInfo> attachInfos) {
        this.context=context;
        this.attachInfos=attachInfos;
    }

    @Override
    public int getCount() {
        return attachInfos.size();
    }

    @Override
    public AttachInfo getItem(int i) {
        return attachInfos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view==null){
            view=View.inflate(context, R.layout.itme_attach,null);
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
        AttachInfo attachInfo=getItem(i);
        holder.item_icon.setImageResource(attachInfo.getImageid());
        holder.item_title.setText(attachInfo.getStorename());
        holder.item_rating.setRating(attachInfo.getRating());
        holder.tv_money.setText(attachInfo.getMoney());
        holder.item_name.setText(attachInfo.getName());
        holder.tv_price.setText(attachInfo.getPrice());
        holder.tv_distance.setText(attachInfo.getDistance());
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
