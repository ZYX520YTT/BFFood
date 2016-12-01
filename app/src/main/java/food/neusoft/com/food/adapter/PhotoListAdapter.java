package food.neusoft.com.food.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;

import java.util.ArrayList;
import java.util.List;

import food.neusoft.com.food.R;
import food.neusoft.com.food.domian.FoodPhotoInfo;

/**
 * Created by 张宇翔 on 2016/11/23 10:41.
 * Email：1124751755@qq.com
 * 功能：发现美食的Adapter
 */

public class PhotoListAdapter extends BaseAdapter {

    private Context context;
    private List<FoodPhotoInfo> foodPhotoInfos;


    private BitmapUtils utils;

    public PhotoListAdapter(Context context,List<FoodPhotoInfo> foodPhotoInfos){
        this.foodPhotoInfos=foodPhotoInfos;
        this.context=context;
        utils=new BitmapUtils(context);
        utils.configDefaultLoadingImage(R.drawable.fj_loading);
    }

    @Override
    public int getCount() {
        if(foodPhotoInfos.size()%3!=0){
            return foodPhotoInfos.size()/3+1;
        }
        return foodPhotoInfos.size()/3;
    }

    @Override
    public Object getItem(int i) {
        List<FoodPhotoInfo> relist=new ArrayList<>();
        try{
            relist.add(foodPhotoInfos.get(i*3));
            relist.add(foodPhotoInfos.get(i*3+1));
            relist.add(foodPhotoInfos.get(i*3+2));
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
        ViewHolder holder=null;
        FoodPhotoInfo foodPhotoInfo1=foodPhotoInfos.get(i*3);
        FoodPhotoInfo foodPhotoInfo2;
        FoodPhotoInfo foodPhotoInfo3;
        try{
            foodPhotoInfo2=foodPhotoInfos.get(i*3+1);
        }catch (Exception e){
            foodPhotoInfo2=null;
        }
        try{
            foodPhotoInfo3=foodPhotoInfos.get(i*3+2);
        }catch (Exception e){
            foodPhotoInfo3=null;
        }
        if(holder==null){
            holder=new ViewHolder();
            view=View.inflate(context, R.layout.item_hot_dynamic,null);
        }else{
            holder= (ViewHolder) view.getTag();
        }

        holder.iv_image_one= (ImageView) view.findViewById(R.id.iv_image_one);
        holder.iv_image_two= (ImageView) view.findViewById(R.id.iv_image_two);
        holder.iv_image_three= (ImageView) view.findViewById(R.id.iv_image_three);
        holder.tv_dynamic_title_one= (TextView) view.findViewById(R.id.tv_dynamic_title_one);
        holder.tv_dynamic_title_two= (TextView) view.findViewById(R.id.tv_dynamic_title_two);
        holder.tv_dynamic_title_three= (TextView) view.findViewById(R.id.tv_dynamic_title_three);
        holder.llyt_dynamic_one= (LinearLayout) view.findViewById(R.id.llyt_dynamic_one);
        holder.llyt_dynamic_two= (LinearLayout) view.findViewById(R.id.llyt_dynamic_two);
        holder.llyt_dynamic_three= (LinearLayout) view.findViewById(R.id.llyt_dynamic_three);


        utils.display(holder.iv_image_one,foodPhotoInfo1.getFoodIconPath());
        holder.tv_dynamic_title_one.setText(foodPhotoInfo1.getFoodName());
        if(foodPhotoInfo2!=null){
            utils.display(holder.iv_image_two,foodPhotoInfo2.getFoodIconPath());
            holder.tv_dynamic_title_two.setText(foodPhotoInfo2.getFoodName());
        }else{
            holder.llyt_dynamic_two.setVisibility(View.INVISIBLE);
        }
        if(foodPhotoInfo3!=null){
            utils.display(holder.iv_image_three,foodPhotoInfo3.getFoodIconPath());
            holder.tv_dynamic_title_three.setText(foodPhotoInfo3.getFoodName());
        }else{
            holder.llyt_dynamic_three.setVisibility(View.INVISIBLE);
        }
        return view;
    }


    static class ViewHolder{
        public ImageView iv_image_one;
        public TextView tv_dynamic_title_one;
        public ImageView iv_image_two;
        public TextView tv_dynamic_title_two;
        public ImageView iv_image_three;
        public TextView tv_dynamic_title_three;
        public LinearLayout llyt_dynamic_one;
        public LinearLayout llyt_dynamic_two;
        public LinearLayout llyt_dynamic_three;
    }
}
