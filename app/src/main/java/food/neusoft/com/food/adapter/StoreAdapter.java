package food.neusoft.com.food.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;

import java.util.ArrayList;
import java.util.List;

import food.neusoft.com.food.R;
import food.neusoft.com.food.domian.FoodInfo;

import static food.neusoft.com.food.R.id.llyt_dynamic_one;

/**
 * Created by 张宇翔 on 2016/11/29 21:11.
 * Email：1124751755@qq.com
 * 功能：店铺里的商品的Adapter
 */

public class StoreAdapter extends BaseAdapter {

    private Context context;
    private List<FoodInfo> foodInfos;
    private BitmapUtils utils;

    public StoreAdapter(Context context,List<FoodInfo> foodInfos){
        this.context=context;
        this.foodInfos=foodInfos;
        utils=new BitmapUtils(context);
        utils.configDefaultLoadingImage(R.drawable.dp_loading);
    }


    @Override
    public int getCount() {
        if(foodInfos.size()%3!=0){
            return (foodInfos.size()+2)/3;
        }else{
            return foodInfos.size()/3;
        }
    }

    @Override
    public Object getItem(int i) {
        List<FoodInfo> relist=new ArrayList<>();
        try{
            relist.add(foodInfos.get(i*3));
            relist.add(foodInfos.get(i*3+1));
            relist.add(foodInfos.get(i*3+2));
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
        FoodInfo foodInfo1=foodInfos.get(i*3);
        FoodInfo foodInfo2;
        FoodInfo foodInfo3;
        try {
            foodInfo2=foodInfos.get(i*3+1);
        }catch (Exception e){
            foodInfo2=null;
        }
        try {
            foodInfo3=foodInfos.get(i*3+2);
        }catch (Exception e){
            foodInfo3=null;
        }
        if(holder==null){
            holder=new ViewHolder();
            view=View.inflate(context, R.layout.item_store,null);
        }else{
            holder= (ViewHolder) view.getTag();
        }
        holder.iv_image_one= (ImageView) view.findViewById(R.id.iv_image_one);
        holder.tv_dynamic_title_one= (TextView) view.findViewById(R.id.tv_dynamic_title_one);
        holder.tv_money_one= (TextView) view.findViewById(R.id.tv_money_one);
        holder.iv_image_two= (ImageView) view.findViewById(R.id.iv_image_two);
        holder.tv_dynamic_title_two= (TextView) view.findViewById(R.id.tv_dynamic_title_two);
        holder.tv_money_two= (TextView) view.findViewById(R.id.tv_money_two);
        holder.iv_image_three= (ImageView) view.findViewById(R.id.iv_image_three);
        holder.tv_dynamic_title_three= (TextView) view.findViewById(R.id.tv_dynamic_title_three);
        holder.tv_money_three= (TextView) view.findViewById(R.id.tv_money_three);
        holder.llyt_dynamic_one= (RelativeLayout) view.findViewById(llyt_dynamic_one);
        holder.llyt_dynamic_two= (RelativeLayout) view.findViewById(R.id.llyt_dynamic_two);
        holder.llyt_dynamic_three= (RelativeLayout) view.findViewById(R.id.llyt_dynamic_three);

        utils.display(holder.iv_image_one,foodInfo1.getFoodIconPath());
        holder.tv_dynamic_title_one.setText(foodInfo1.getFoodName());
        holder.tv_money_one.setText(foodInfo1.getFoodPrice()+"元");
        if(foodInfo2!=null){
            utils.display(holder.iv_image_two,foodInfo2.getFoodIconPath());
            holder.tv_dynamic_title_two.setText(foodInfo2.getFoodName());
            holder.tv_money_two.setText(foodInfo2.getFoodPrice()+"元");
        }else{
            holder.llyt_dynamic_two.setVisibility(View.INVISIBLE);
        }
        if(foodInfo3!=null){
            utils.display(holder.iv_image_three,foodInfo3.getFoodIconPath());
            holder.tv_dynamic_title_three.setText(foodInfo3.getFoodName());
            holder.tv_money_three.setText(foodInfo3.getFoodPrice()+"元");
        }else{
            holder.llyt_dynamic_three.setVisibility(View.INVISIBLE);
        }
        return view;
    }

    static class ViewHolder{
        public ImageView iv_image_one;
        public TextView tv_dynamic_title_one;
        public TextView tv_money_one;
        public ImageView iv_image_two;
        public TextView tv_dynamic_title_two;
        public TextView tv_money_two;
        public ImageView iv_image_three;
        public TextView tv_dynamic_title_three;
        public TextView tv_money_three;
        public RelativeLayout llyt_dynamic_one;
        public RelativeLayout llyt_dynamic_two;
        public RelativeLayout llyt_dynamic_three;
    }
}
