package food.neusoft.com.food.thread;

/**
 * Created by 张宇翔 on 2016/11/27 20:31.
 * Email：1124751755@qq.com
 * 功能：Url
 */

public class Url {

    //100.0.101.18
    public final  static String Url="http://100.0.101.18:8080/CDFood/";


    //http://100.0.101.18:8080/CDFood/login?userId=21&userPassword=1
    //http://100.0.101.18:8080/CDFood/signIn?userId=21&userPassword=1

    private static String getUrl(String activity){
        String url=null;
        url=Url+activity;
        return url;
    }

    /**用户注册**/
    public static String signIn =getUrl("signIn");

    /**用户登录**/
    public static String login=getUrl("login");


}
