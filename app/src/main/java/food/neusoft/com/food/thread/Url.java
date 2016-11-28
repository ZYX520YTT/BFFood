package food.neusoft.com.food.thread;

/**
 * Created by 张宇翔 on 2016/11/27 20:31.
 * Email：1124751755@qq.com
 * 功能：Url
 */

public class Url {

    //100.0.101.18
    public final  static String Url="http://100.0.101.18:8080/CDFood/";

    public final  static String ImgURL="http://100.0.101.18:8080/CDFood/Images/";

    //http://100.0.101.18:8080/CDFood/login?userId=21&userPassword=1
    //http://100.0.101.18:8080/CDFood/signIn?userId=21&userPassword=1
    //http://100.0.101.18:8080/CDFood/login?userId=67&userPassword=6
    //http://100.0.101.18:8080/CDFood/getHotMarket?count=5&marketAdress=温江区&firstIndex=0
    //http://100.0.101.18:8080/CDFood/Images/SY_pic.png
    //http://100.0.101.18:8080/CDFood/getHotFood?count=10&marketAdress=温江区&firstIndex=0
    //http://100.0.101.18:8080/CDFood/getHotMarket?count=10&marketAdress=青羊区&firstIndex=0
    //http://100.0.101.18:8080/CDFood/Images/SY_pic.png


    private static String getUrl(String activity){
        String url=null;
        url=Url+activity;
        return url;
    }

    public static String getImgURL(String activity) {
        String url = null;
        url =ImgURL+activity;
        return url;
    }


    /**用户注册**/
    public static String signIn =getUrl("signIn");

    /**用户登录**/
    public static String login=getUrl("login");

    /**获取热门美食**/
    public static String getHotFood=getUrl("getHotFood");


    /**获取热门商铺**/
    public static String getHotMarket=getUrl("getHotMarket");

    /**获取火锅的商铺**/
    public static String getHotpotMarket =getUrl("getHotpotMarket");

    /**获取西餐的商铺**/
    public static String getWesternMarket =getUrl("getWesternMarket");




    /**获取附近的商铺**/
    public static String getNearMarket =getUrl("getNearMarket");


}
