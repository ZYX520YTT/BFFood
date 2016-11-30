package food.neusoft.com.food.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;

import java.util.ArrayList;
import java.util.List;

import food.neusoft.com.food.R;
import food.neusoft.com.food.activity.StroeActivity;
import food.neusoft.com.food.domian.DrinkInfo;
import food.neusoft.com.food.utils.TimeChose;

/**
 * Created by 张宇翔 on 2016/11/24 21:02.
 * Email：1124751755@qq.com
 * 功能：
 */

public class DrinkAdapter extends BaseAdapter {

    private Context context;
    private List<DrinkInfo> drinkInfos;

    private BitmapUtils butils;
    private BitmapUtils yutils;

    public DrinkAdapter(Context context, List<DrinkInfo> drinkInfos) {
        this.context = context;
        this.drinkInfos = drinkInfos;
        butils=new BitmapUtils(context);
        yutils=new BitmapUtils(context);
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
        ViewHolder holder=null;
        DrinkInfo drinkInfo1=drinkInfos.get(i*2);
        DrinkInfo drinkInfo2=null;
        try{
            drinkInfo2=drinkInfos.get(i*2+1);
        }catch (Exception e){
            drinkInfo2=null;
        }
        if(holder==null){
            holder=new ViewHolder();
            view=View.inflate(context, R.layout.item_drink,null);
        }else{
            holder= (ViewHolder) view.getTag();
        }

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
        holder.llyt_one= (RelativeLayout) view.findViewById(R.id.llyt_one);
        holder.llyt_two= (RelativeLayout) view.findViewById(R.id.llyt_two);


        butils.display(holder.iv_image_one,drinkInfo1.getMarketIconPath());
        yutils.display(holder.iv_photolefttype_one,drinkInfo1.getDiscountIconPath());
        yutils.display(holder.iv_photorighttype_one,drinkInfo1.getBookIconPath());
        holder.tv_storename_one.setText(drinkInfo1.getMarketName());
        holder.item_rating_one.setRating((float) drinkInfo1.getMarketHotLevel());
        holder.tv_youhui_one.setText(drinkInfo1.getMarketIntroduce());
        if(drinkInfo2!=null){
            butils.display(holder.iv_image_two,drinkInfo2.getMarketIconPath());
            yutils.display(holder.iv_photolefttype_two,drinkInfo2.getDiscountIconPath());
            yutils.display(holder.iv_photorighttype_two,drinkInfo2.getBookIconPath());
            holder.tv_storename_two.setText(drinkInfo2.getMarketName());
            holder.item_rating_two.setRating((float) drinkInfo2.getMarketHotLevel());
            holder.tv_youhui_two.setText(drinkInfo2.getMarketIntroduce());
        }else{
            holder.llyt_two.setVisibility(View.INVISIBLE);
        }
        Oncleck(holder,i);
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
        public RelativeLayout llyt_one;
        public RelativeLayout llyt_two;
    }


    private void Oncleck(ViewHolder holder,final  int position){
        holder.llyt_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int onclick =position*2;
                Intent intent=new Intent(context, StroeActivity.class);
                DrinkInfo drinkInfo=drinkInfos.get(onclick);
                intent.putExtra("marketNo",drinkInfo.getMarketNo());
                intent.putExtra("type","甜品");
                intent.putExtra("storename",drinkInfo.getMarketName());
                intent.putExtra("introduce",drinkInfo.getMarketIntroduce());
                intent.putExtra("imagepath",drinkInfo.getMarketIconPath());
                context.startActivity(intent);
            }
        });

        holder.llyt_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int onclick =position*2+1;
                Intent intent=new Intent(context, StroeActivity.class);
                DrinkInfo drinkInfo=drinkInfos.get(onclick);
                intent.putExtra("marketNo",drinkInfo.getMarketNo());
                intent.putExtra("type","甜品");
                intent.putExtra("storename",drinkInfo.getMarketName());
                intent.putExtra("introduce",drinkInfo.getMarketIntroduce());
                intent.putExtra("imagepath",drinkInfo.getMarketIconPath());
                context.startActivity(intent);
            }
        });

        holder.iv_photorighttype_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int onclick =position*2;
                DrinkInfo drinkInfo=drinkInfos.get(onclick);
                TimeChose timeChose=new TimeChose(context, (Activity) context);
                timeChose.DiaLog(drinkInfo.getMarketNo());
            }
        });
        holder.iv_photorighttype_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int onclick =position*2+1;
                DrinkInfo drinkInfo=drinkInfos.get(onclick);
                TimeChose timeChose=new TimeChose(context, (Activity) context);
                timeChose.DiaLog(drinkInfo.getMarketNo());
            }
        });
    }
}
