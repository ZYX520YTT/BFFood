package food.neusoft.com.food.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;

import java.util.ArrayList;
import java.util.List;

import food.neusoft.com.food.R;
import food.neusoft.com.food.domian.FoodInfo;

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
            return foodInfos.size()/3+1;
        }
        return foodInfos.size()/3;
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
        ViewHolder holder;
        FoodInfo foodInfo1=foodInfos.get(i*3);
        FoodInfo foodInfo2=null;
        FoodInfo foodInfo3=null;
        if(view==null){
            view=View.inflate(context, R.layout.item_store,null);
            holder=new ViewHolder();
            holder.iv_image_one= (ImageView) view.findViewById(R.id.iv_image_one);
            holder.tv_dynamic_title_one= (TextView) view.findViewById(R.id.tv_dynamic_title_one);
            holder.tv_money_one= (TextView) view.findViewById(R.id.tv_money_one);
            holder.iv_image_two= (ImageView) view.findViewById(R.id.iv_image_two);
            holder.tv_dynamic_title_two= (TextView) view.findViewById(R.id.tv_dynamic_title_two);
            holder.tv_money_two= (TextView) view.findViewById(R.id.tv_money_two);
            holder.iv_image_three= (ImageView) view.findViewById(R.id.iv_image_three);
            holder.tv_dynamic_title_three= (TextView) view.findViewById(R.id.tv_dynamic_title_three);
            holder.tv_money_three= (TextView) view.findViewById(R.id.tv_money_three);
            view.setTag(holder);
        }else{
            holder= (ViewHolder) view.getTag();
        }
        utils.display(holder.iv_image_one,foodInfo1.getFoodIconPath());
        holder.tv_dynamic_title_one.setText(foodInfo1.getFoodName());
        holder.tv_money_one.setText(foodInfo1.getFoodPrice()+"元");
        if(foodInfo2!=null){
            utils.display(holder.iv_image_two,foodInfo2.getFoodIconPath());
            holder.tv_dynamic_title_two.setText(foodInfo2.getFoodName());
            holder.tv_money_two.setText(foodInfo2.getFoodPrice()+"元");
        }
        if(foodInfo3!=null){
            utils.display(holder.iv_image_three,foodInfo3.getFoodIconPath());
            holder.tv_dynamic_title_three.setText(foodInfo3.getFoodName());
            holder.tv_money_three.setText(foodInfo3.getFoodPrice()+"元");
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
    }
}
