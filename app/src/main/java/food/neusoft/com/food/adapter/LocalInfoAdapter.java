package food.neusoft.com.food.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import food.neusoft.com.food.R;

/**
 * Created by 张宇翔 on 2016/11/22 11:45.
 * Email：1124751755@qq.com
 * 功能：地区的Adapter
 */

public class LocalInfoAdapter extends BaseAdapter {

    private List<String> info;
    private Context context;

    public LocalInfoAdapter(Context context,List<String> info){
        this.info=info;
        this.context=context;
    }


    @Override
    public int getCount() {
        return info.size();
    }

    @Override
    public String getItem(int i) {
        return info.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view==null){
            view=View.inflate(context, R.layout.local_item,null);
            holder=new ViewHolder();
            holder.tv_name= (TextView) view.findViewById(R.id.tv_name);
            view.setTag(holder);
        }else{
            holder= (ViewHolder) view.getTag();
        }
        holder.tv_name.setText(getItem(i));
        return view;
    }
    static class ViewHolder{
        public TextView tv_name;
    }
}
