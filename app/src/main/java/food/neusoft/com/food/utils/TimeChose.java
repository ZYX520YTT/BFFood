package food.neusoft.com.food.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import food.neusoft.com.food.R;


/**
 * Created by 张宇翔 on 2016/11/25 22:13.
 * Email：1124751755@qq.com
 * 功能：点击预约弹出来的黑框框
 */

public class TimeChose {

    private List<String> date;
    private List<String> time;
    private List<String> people;
    private Context context;
    private Activity activity;

    public static String Currentdate = null;
    public static String Currenttime = null;
    public static String Currentpeople = null;
    private Dialog dialog;
    private TextView tv_time;
    private TextView tv_now;
    private TextView tv_date;
    private TextView tv_people;


    public TimeChose(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
    }


    private void InitData() {
        date = new ArrayList<>();
        time = new ArrayList<>();
        people = new ArrayList<>();
        getDate(date);
        for (int i = 0; i <= 24; i++) {
            time.add(i + ":00");
            time.add(i + ":30");
        }
        for (int i = 1; i <= 50; i++) {
            people.add(i + "人");
        }
    }

    private void getDate(List<String> date) {
//        for(int i=1;i<=30;i++){
//            Calendar calendar=new GregorianCalendar();
//            calendar.add(Calendar.DATE, i);
////			System.out.println(calendar.getTime());
//            Date time = calendar.getTime();
//            String format=new SimpleDateFormat("MM").format(time);
//            System.out.println(format);
        for (int i = 0; i <= 30; i++) {
            Calendar calendar = new GregorianCalendar();
            calendar.add(Calendar.DATE, i);
            Date time = calendar.getTime();
            String date1 = new SimpleDateFormat("dd").format(time);
            String moth1 = new SimpleDateFormat("MM").format(time);
            if (i == 0) {
                date.add(moth1 + "月" + date1 + "日" + "\n" + "今天");
            } else if (i == 1) {
                date.add(moth1 + "月" + date1 + "日" + "\n" + "明天");
            } else if (i == 2) {
                date.add(moth1 + "月" + date1 + "日" + "\n" + "后天");
            } else {
                date.add(moth1 + "月" + date1 + "日" + "\n" + getWeekOfDate(time));
            }
        }

    }

    public static String getWeekOfDate(Date date) {
        String[] weekOfDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
        }
        int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return weekOfDays[w];
    }


    public void DiaLog() {
        InitData();


        View view = LayoutInflater.from(context).inflate(R.layout.dialog, null);

        tv_time = (TextView) view.findViewById(R.id.tv_time);//时间
        tv_time.setText("0:00");

        //日期
        tv_date = (TextView) view.findViewById(R.id.tv_date);
        //今天
        tv_now = (TextView) view.findViewById(R.id.tv_now);

        String s=date.get(0);
        String[] splite=s.split("日");
        tv_date.setText(splite[0]+"日");
        tv_now.setText(splite[1]);




        tv_people = (TextView) view.findViewById(R.id.tv_people);
        tv_people.setText("0");



        Button bt_cancel = (Button) view.findViewById(R.id.bt_cancel);
        Button bt_config = (Button) view.findViewById(R.id.bt_config);

        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        bt_config.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Currentdate != null && Currenttime != null && Currentpeople != null) {
                    Toast.makeText(context, "日期：" + Currentdate + " ，时间: " + Currenttime + "  人数:  " + Currentpeople, Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                } else {
                    Toast.makeText(context, "选择没成功", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }
        });


        RecyclerView rv_date = (RecyclerView) view.findViewById(R.id.rv_date);
        RecyclerView rv_time = (RecyclerView) view.findViewById(R.id.rv_time);
        RecyclerView rv_people = (RecyclerView) view.findViewById(R.id.rv_people);
        rv_date.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        rv_date.setAdapter(new DateAdapter());
        rv_date.setItemAnimator(null);


        rv_time.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        rv_time.setAdapter(new TimeAdapter());
        rv_time.setItemAnimator(null);

        rv_people.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        rv_people.setAdapter(new PeopleAdapter());
        rv_people.setItemAnimator(null);

        dialog = new AlertDialog.Builder(context).create();
        dialog.show();
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();// 得到属性

        params.gravity = Gravity.CENTER;// 显示在中间
        params.width = (int) (activity.getWindowManager().getDefaultDisplay().getWidth());// 设置对话框的宽度为手机屏幕的0.8
        params.height = (int) (activity.getWindowManager().getDefaultDisplay()
                .getHeight());// 设置对话框的高度为手机屏幕的0.25
        dialog.getWindow().setAttributes(params);// 設置屬性
        dialog.getWindow().setContentView(view);// 把自定義view加上去
    }


    private class DateAdapter extends RecyclerView.Adapter<DateAdapter.MyDateViewHolder> {

        private int index=-1;

        @Override
        public void onBindViewHolder(MyDateViewHolder holder, int position) {
            holder.rb1.setText(date.get(position));
            holder.rb1.setBackgroundResource(index == position ? R.drawable.panel_one_light : R.drawable.panel_one);
            holder.rb1.setTextColor(index == position ? Color.WHITE : Color.WHITE);
        }

        @Override
        public MyDateViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyDateViewHolder holder = new MyDateViewHolder(LayoutInflater.from(context).inflate(R.layout.date_item, parent, false));

            return holder;
        }

        @Override
        public int getItemCount() {
            return date.size();
        }


        class MyDateViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            private TextView rb1;

            public MyDateViewHolder(View itemView) {
                super(itemView);
                rb1 = (TextView) itemView.findViewById(R.id.rb1);
                rb1.setOnClickListener(this);
            }

            @Override
            public void onClick(View view) {
                int position = index;
                index = getLayoutPosition();
                Currentdate = date.get(index);
                Toast.makeText(context, Currentdate, Toast.LENGTH_SHORT).show();
                String s=Currentdate;
                String[] splite=s.split("日");
                tv_date.setText(splite[0]+"日");
                tv_now.setText(splite[1]);
                notifyItemChanged(index);
                notifyItemChanged(position);
            }
        }
    }


    private class TimeAdapter extends RecyclerView.Adapter<TimeAdapter.MyTimeViewHolder> {

        private int index=-1;

        @Override
        public void onBindViewHolder(MyTimeViewHolder holder, int position) {
            holder.rb2.setText(time.get(position));
            holder.rb2.setBackgroundResource(index == position ? R.drawable.panel_two_light : R.drawable.panel_two);
            holder.rb2.setTextColor(index == position ? Color.WHITE : Color.WHITE);
        }

        @Override
        public MyTimeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyTimeViewHolder holder = new MyTimeViewHolder(LayoutInflater.from(context).inflate(R.layout.time_item, parent, false));

            return holder;
        }

        @Override
        public int getItemCount() {
            return time.size();
        }


        class MyTimeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            private RadioButton rb2;

            public MyTimeViewHolder(View itemView) {
                super(itemView);
                rb2 = (RadioButton) itemView.findViewById(R.id.rb2);
                rb2.setOnClickListener(this);
            }

            @Override
            public void onClick(View view) {
                int position = index;
                index = getLayoutPosition();
                Currenttime = time.get(index);
                Toast.makeText(context, Currenttime, Toast.LENGTH_SHORT).show();
                tv_time.setText(Currenttime);
                notifyItemChanged(index);
                notifyItemChanged(position);
            }
        }
    }


    private class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.MyPeopleViewHolder> {

        private int index=-1;

        @Override
        public void onBindViewHolder(MyPeopleViewHolder holder, int position) {
            holder.rb3.setText(people.get(position));
            holder.rb3.setBackgroundResource(index == position ? R.drawable.panel_three_light : R.drawable.panel_three);
            holder.rb3.setTextColor(index == position ? Color.WHITE : Color.WHITE);
        }

        @Override
        public MyPeopleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyPeopleViewHolder holder = new MyPeopleViewHolder(LayoutInflater.from(context).inflate(R.layout.people_item, parent, false));

            return holder;
        }

        @Override
        public int getItemCount() {
            return people.size();
        }


        class MyPeopleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            private RadioButton rb3;

            public MyPeopleViewHolder(View itemView) {
                super(itemView);
                rb3 = (RadioButton) itemView.findViewById(R.id.rb3);
                rb3.setOnClickListener(this);
            }

            @Override
            public void onClick(View view) {
                int position = index;
                index = getLayoutPosition();
                Currentpeople = people.get(index);
                Toast.makeText(context, Currentpeople, Toast.LENGTH_SHORT).show();
                tv_people.setText(Currentpeople);
                notifyItemChanged(index);
                notifyItemChanged(position);
            }
        }
    }

}
