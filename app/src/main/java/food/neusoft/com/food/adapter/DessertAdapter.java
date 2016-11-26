package food.neusoft.com.food.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import food.neusoft.com.food.R;
import food.neusoft.com.food.domian.DessertInfo;

/**
 * Created by 张宇翔 on 2016/11/24 21:02.
 * Email：1124751755@qq.com
 * 功能：
 */

public class DessertAdapter extends BaseAdapter {

    private Context context;
    private List<DessertInfo> dessertInfos;

    public DessertAdapter(Context context, List<DessertInfo> dessertInfos) {
        this.context = context;
        this.dessertInfos = dessertInfos;
    }

    @Override
    public int getCount() {
        if(dessertInfos.size()%2!=0){
            return dessertInfos.size()/2+1;
        }
        return dessertInfos.size()/2;
    }

    @Override
    public Object getItem(int i) {
        List<DessertInfo> relist=new ArrayList<>();
        try{
            relist.add(dessertInfos.get(i*2));
            relist.add(dessertInfos.get(i*2+1));
        }catch (Exception e){
            return relist;
        }
        return relist;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        DessertInfo dessertinfo1=dessertInfos.get(i*2);
        DessertInfo dessertinfo2=null;
        try{
            dessertinfo2=dessertInfos.get(i*2+1);
        }catch (Exception e){
            dessertinfo2=null;
        }
        if(view==null){
            view=View.inflate(context, R.layout.item_dessert,null);
            holder=new ViewHolder();
            holder.iv_image_one= (ImageView) view.findViewById(R.id.iv_image_one);
            holder.iv_photolefttype_one= (ImageView) view.findViewById(R.id.iv_photolefttype_one);
            holder.iv_photorighttype_one= (ImageView) view.findViewById(R.id.iv_photorighttype_one);
            holder.tv_storename_one= (TextView) view.findViewById(R.id.tv_storename_one);
            holder.item_rating_one= (RatingBar) view.findViewById(R.id.item_rating_one);
            holder.tv_youhui_one= (TextView) view.findViewById(R.id.tv_youhui_one);
            holder.iv_image_two= (ImageView) view.findViewById(R.id.iv_image_two);
            holder.iv_photolefttype_two= (ImageView) view.findViewById(R.id.iv_photolefttype_two);
            holder.iv_photorighttype_two= (ImageView) view.findViewById(R.id.iv_photorighttype_two);
            holder.tv_storename_two= (TextView) view.findViewById(R.id.tv_storename_two);
            holder.item_rating_two= (RatingBar) view.findViewById(R.id.item_rating_two);
            holder.tv_youhui_two= (TextView) view.findViewById(R.id.tv_youhui_two);
            view.setTag(holder);
        }else{
            holder= (ViewHolder) view.getTag();
        }
        holder.iv_image_one.setImageResource(dessertinfo1.getImageid());
        holder.iv_photolefttype_one.setImageResource(dessertinfo1.getPhotoLeftType());
        holder.iv_photorighttype_one.setImageResource(dessertinfo1.getPhotoRightType());
        holder.tv_storename_one.setText(dessertinfo1.getStorename());
        holder.item_rating_one.setRating(dessertinfo1.getRating());
        holder.tv_youhui_one.setText(dessertinfo1.getYh());
        if(dessertinfo2!=null){
            holder.iv_image_two.setImageResource(dessertinfo2.getImageid());
            holder.iv_photolefttype_two.setImageResource(dessertinfo2.getPhotoLeftType());
            holder.iv_photorighttype_two.setImageResource(dessertinfo2.getPhotoRightType());
            holder.tv_storename_two.setText(dessertinfo2.getStorename());
            holder.item_rating_two.setRating(dessertinfo2.getRating());
            holder.tv_youhui_two.setText(dessertinfo2.getYh());
        }
        return view;
    }


    static class ViewHolder{
        public ImageView iv_image_one;
        public ImageView iv_photolefttype_one;
        public ImageView iv_photorighttype_one;
        public TextView tv_storename_one;
        public RatingBar item_rating_one;
        public TextView tv_youhui_one;
        public ImageView iv_image_two;
        public ImageView iv_photolefttype_two;
        public ImageView iv_photorighttype_two;
        public TextView tv_storename_two;
        public RatingBar item_rating_two;
        public TextView tv_youhui_two;
    }
}
