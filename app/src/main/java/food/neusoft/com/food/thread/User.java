package food.neusoft.com.food.thread;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * Created by 张宇翔 on 2016/11/21 22:45.
 * Email：1124751755@qq.com
 * 功能：对用户信息的操作
 */

public class User {
    private Context context;

    // 构造方法中传入上下文对象
    public User(Context context) {
        super();
        this.context = context;
    }

    public void savePhone(String userPhone) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                "UserPhone", Context.MODE_PRIVATE);
        Editor editor = sharedPreferences.edit();
        editor.putString("UserPhone", userPhone); // 目前是保存在内存中，还没有保存到文件中
        editor.commit(); // 数据提交到xml文件中
    }

    public void savePass(String pass) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                "savePass", Context.MODE_PRIVATE);
        Editor editor = sharedPreferences.edit();
        editor.putString("savePass", pass); // 目前是保存在内存中，还没有保存到文件中
        editor.commit(); // 数据提交到xml文件中
    }

    public void saveUserNumber(String UserNumber) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                "UserNumber", Context.MODE_PRIVATE);
        Editor editor = sharedPreferences.edit();
        editor.putString("UserNumber", UserNumber); // 目前是保存在内存中，还没有保存到文件中
        editor.commit(); // 数据提交到xml文件中
    }

    public void saveUserPower(String UserPower) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                "UserPower", Context.MODE_PRIVATE);
        Editor editor = sharedPreferences.edit();
        editor.putString("UserPower", UserPower); // 目前是保存在内存中，还没有保存到文件中
        editor.commit(); // 数据提交到xml文件中
    }

    public String getPhone() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                "UserPhone", Context.MODE_PRIVATE);
        return sharedPreferences.getString("UserPhone", "");
    }

    public String getPass() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                "savePass", Context.MODE_PRIVATE);
        return sharedPreferences.getString("savePass", "");
    }

    public String getUserNumber() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                "UserNumber", Context.MODE_PRIVATE);
        return sharedPreferences.getString("UserNumber", "");
    }

    public String getUserPower() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                "UserPower", Context.MODE_PRIVATE);
        return sharedPreferences.getString("UserPower", "");
    }

    public void removePass() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                "savePass", Context.MODE_PRIVATE);
        Editor editor = sharedPreferences.edit();
        editor.remove("savePass");
        editor.commit();
    }

    public void removeUserNumber() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                "UserNumber", Context.MODE_PRIVATE);
        Editor editor = sharedPreferences.edit();
        editor.remove("UserNumber");
        editor.commit();
    }

    public void removeUserPower() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                "UserPower", Context.MODE_PRIVATE);
        Editor editor = sharedPreferences.edit();
        editor.remove("UserPower");
        editor.commit();
    }
}
