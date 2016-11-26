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
import food.neusoft.com.food.domian.DrinkInfo;

/**
 * Created by 张宇翔 on 2016/11/24 21:02.
 * Email：1124751755@qq.com
 * 功能：
 */

public class DrinkAdapter extends BaseAdapter {

    private Context context;
    private List<DrinkInfo> drinkInfos;

    public DrinkAdapter(Context context, List<DrinkInfo> drinkInfos) {
        this.context = context;
        this.drinkInfos = drinkInfos;
    }

    @Override
    public int getCount() {
        if(drinkInfos.size()%2!=0){
            return drinkInfos.size()/2+1;
        }
        return drinkInfos.size()/2;
    }

    @Override
    public Object getItem(int i) {
        List<DrinkInfo> relist=new ArrayList<>();
        try{
            relist.add(drinkInfos.get(i*2));
            relist.add(drinkInfos.get(i*2+1));
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
        DrinkInfo drinkInfo1=drinkInfos.get(i*2);
        DrinkInfo drinkInfo2=null;
        try{
            drinkInfo2=drinkInfos.get(i*2+1);
        }catch (Exception e){
            drinkInfo2=null;
        }
        if(view==null){
            view=View.inflate(context, R.layout.item_drink,null);
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
        holder.iv_image_one.setImageResource(drinkInfo1.getImageid());
        holder.iv_photolefttype_one.setImageResource(drinkInfo1.getPhotoLeftType());
        holder.iv_photorighttype_one.setImageResource(drinkInfo1.getPhotoRightType());
        holder.tv_storename_one.setText(drinkInfo1.getStorename());
        holder.item_rating_one.setRating(drinkInfo1.getRating());
        holder.tv_youhui_one.setText(drinkInfo1.getYh());
        if(drinkInfo2!=null){
            holder.iv_image_two.setImageResource(drinkInfo2.getImageid());
            holder.iv_photolefttype_two.setImageResource(drinkInfo2.getPhotoLeftType());
            holder.iv_photorighttype_two.setImageResource(drinkInfo2.getPhotoRightType());
            holder.tv_storename_two.setText(drinkInfo2.getStorename());
            holder.item_rating_two.setRating(drinkInfo2.getRating());
            holder.tv_youhui_two.setText(drinkInfo2.getYh());
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
