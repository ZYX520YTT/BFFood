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
import food.neusoft.com.food.domian.DessertInfo;
import food.neusoft.com.food.utils.TimeChose;

/**
 * Created by 张宇翔 on 2016/11/24 21:02.
 * Email：1124751755@qq.com
 * 功能：
 */

public class DessertAdapter extends BaseAdapter {

    private Context context;
    private List<DessertInfo> dessertInfos;
    private BitmapUtils butils;
    private BitmapUtils yutils;

    public DessertAdapter(Context context, List<DessertInfo> dessertInfos) {
        this.context = context;
        this.dessertInfos = dessertInfos;
        butils=new BitmapUtils(context);
        butils.configDefaultLoadingImage(R.drawable.pic_dessert_one);
        yutils=new BitmapUtils(context);
        yutils.configDefaultLoadingImage(R.drawable.view_yu);
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
            holder.llyt_one= (RelativeLayout) view.findViewById(R.id.llyt_one);
            holder.llyt_two= (RelativeLayout) view.findViewById(R.id.llyt_two);
            view.setTag(holder);
        }else{
            holder= (ViewHolder) view.getTag();
        }
        butils.display(holder.iv_image_one,dessertinfo1.getMarketIconPath());
        yutils.display(holder.iv_photolefttype_one,dessertinfo1.getDiscountIconPath());
        yutils.display(holder.iv_photorighttype_one,dessertinfo1.getBookIconPath());
        holder.tv_storename_one.setText(dessertinfo1.getMarketName());
        holder.item_rating_one.setRating((float) dessertinfo1.getMarketHotLevel());
        holder.tv_youhui_one.setText(dessertinfo1.getMarketIntroduce());
        if(dessertinfo2!=null){
            butils.display(holder.iv_image_two,dessertinfo2.getMarketIconPath());
            yutils.display(holder.iv_photolefttype_two,dessertinfo2.getDiscountIconPath());
            yutils.display(holder.iv_photorighttype_two,dessertinfo2.getBookIconPath());
            holder.tv_storename_two.setText(dessertinfo2.getMarketName());
            holder.item_rating_two.setRating((float) dessertinfo2.getMarketHotLevel());
            holder.tv_youhui_two.setText(dessertinfo2.getMarketIntroduce());
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


    private void Oncleck(ViewHolder holder,final int position){
        holder.llyt_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int onclick =position*2;
                Intent intent=new Intent(context, StroeActivity.class);
                DessertInfo dessertInfo=dessertInfos.get(onclick);
                intent.putExtra("marketNo",dessertInfo.getMarketNo());
                intent.putExtra("type","甜品");
                intent.putExtra("storename",dessertInfo.getMarketName());
                intent.putExtra("introduce",dessertInfo.getMarketIntroduce());
                intent.putExtra("imagepath",dessertInfo.getMarketIconPath());
                context.startActivity(intent);
            }
        });

        holder.llyt_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int onclick =position*2+1;
                Intent intent=new Intent(context, StroeActivity.class);
                DessertInfo dessertInfo=dessertInfos.get(onclick);
                intent.putExtra("marketNo",dessertInfo.getMarketNo());
                intent.putExtra("type","甜品");
                intent.putExtra("storename",dessertInfo.getMarketName());
                intent.putExtra("introduce",dessertInfo.getMarketIntroduce());
                intent.putExtra("imagepath",dessertInfo.getMarketIconPath());
                context.startActivity(intent);
            }
        });

        holder.iv_photorighttype_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int onclick =position*2;
                DessertInfo dessertInfo=dessertInfos.get(onclick);
                TimeChose timeChose=new TimeChose(context, (Activity) context);
                timeChose.DiaLog(dessertInfo.getMarketNo());
            }
        });
        holder.iv_photorighttype_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int onclick =position*2+1;
                DessertInfo dessertInfo=dessertInfos.get(onclick);
                TimeChose timeChose=new TimeChose(context, (Activity) context);
                timeChose.DiaLog( dessertInfo.getMarketNo());
            }
        });



    }
}
