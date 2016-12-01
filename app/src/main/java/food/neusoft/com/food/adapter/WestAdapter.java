package food.neusoft.com.food.adapter;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;

import java.util.List;

import food.neusoft.com.food.R;
import food.neusoft.com.food.domian.WestFoodInfo;
import food.neusoft.com.food.utils.TimeChose;

/**
 * Created by 张宇翔 on 2016/11/23 19:22.
 * Email：1124751755@qq.com
 * 功能：西餐的Adapter
 */

public class WestAdapter extends BaseAdapter {

    private Context context;

    private List<WestFoodInfo> westFoodInfos;

    private BitmapUtils butils;//大图片
    private BitmapUtils yutils;//预的图片

    public WestAdapter(Context context,List<WestFoodInfo> westFoodInfos) {
        this.westFoodInfos = westFoodInfos;
        this.context = context;
        butils=new BitmapUtils(context);
        butils.configDefaultLoadingImage(R.drawable.xc_loading);
        yutils=new BitmapUtils(context);
    }

    @Override
    public int getCount() {
        return westFoodInfos.size();
    }

    @Override
    public WestFoodInfo getItem(int i) {
        return westFoodInfos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view==null){
            view=View.inflate(context, R.layout.item_west,null);
            holder=new ViewHolder();
            holder.iv_west= (ImageView) view.findViewById(R.id.iv_west);//
            holder.tv_storename= (TextView) view.findViewById(R.id.tv_storename);//
            holder.iv_phototype= (ImageView) view.findViewById(R.id.iv_phototype);
            holder.tv_type= (TextView) view.findViewById(R.id.tv_type);
            holder.tv_distance= (TextView) view.findViewById(R.id.tv_distance);//
            holder.iv_tiao= (ImageView) view.findViewById(R.id.iv_tiao);
            view.setTag(holder);
        }else{
            holder= (ViewHolder) view.getTag();
        }
        WestFoodInfo info=getItem(i);
        butils.display(holder.iv_west,info.getMarketIconPath());
        holder.tv_storename.setText(info.getMarketName());
        holder.tv_distance.setText(info.getMarketDistance()+"km");
        yutils.display(holder.iv_tiao,info.getErectLineIconPath());
        if(info.getBookIconPath()!=null&& !TextUtils.isEmpty(info.getBookIconPath())){
            yutils.display(holder.iv_phototype,info.getBookIconPath());
        }
        holder.tv_type.setText(info.getTypeName());
        Oncleck(holder,i);
        return view;
    }

    static class ViewHolder{
        public ImageView iv_west;
        public TextView tv_storename;
        public ImageView iv_phototype;
        public TextView tv_type;
        public TextView tv_distance;
        public ImageView iv_tiao;
    }

    private void Oncleck(ViewHolder holder,final  int position){
        holder.iv_phototype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WestFoodInfo westFoodInfo=westFoodInfos.get(position);
                TimeChose timeChose=new TimeChose(context, (Activity) context);
                timeChose.DiaLog(westFoodInfo.getMarketNo());
            }
        });
    }








}
