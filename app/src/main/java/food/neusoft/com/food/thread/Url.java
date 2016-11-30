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
    //http://100.0.101.18:8080/CDFood/getHotpotMarket?count=10&firstIndex=0&marketAdress=温江区
    //http://100.0.101.18:8080/CDFood/Images/SY_pic.png
    //http://100.0.101.18:8080/CDFood/getHotFood?count=10&marketAdress=温江区&firstIndex=0
    //http://100.0.101.18:8080/CDFood/getDrinkMarket?count=10&firstIndex=0&marketAdress=青羊区
    //http://100.0.101.18:8080/CDFood/Images/Re_pic_three.png.png
    //http://100.0.101.18:8080/CDFood/removeCollect?userId=1&marketNo=10
    //http://100.0.101.18:8080/CDFood/saveCollect?userId=1&marketNo=10
    //http://100.0.101.18:8080/CDFood/getMyOrder?userId=1&count=10&firstIndex=0

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
    public static String getHotpotMarket=getUrl("getHotpotMarket");

    /**获取西餐的商铺**/
    public static String getWesternMarket=getUrl("getWesternMarket");

    /**获取甜品的商铺**/
    public static String getSweetMarket=getUrl("getSweetMarket");

    /**获取饮料的商铺**/
    public static String getDrinkMarket=getUrl("getDrinkMarket");


    /**获取附近的商铺**/
    public static String getNearMarket=getUrl("getNearMarket");

    /**获取某个店铺里的商品**/
    public static String getFoods=getUrl("getFoods");


    /**保存预约单号**/
    public static String saveFoodOrder=getUrl("saveFoodOrder");


    /**得到我的预约订单**/
    public static String getBookMarket=getUrl("getBookMarket");

    /**获取可预约的商铺**/
    public static String getMyOrder=getUrl("getMyOrder");


    /**收藏店铺（保存）**/
    public static String saveCollect=getUrl("saveCollect");

    /**取消收藏**/
    public static String removeCollect=getUrl("removeCollect");



    /**上传我的头像**/
    public static String uploadPhotos=getUrl("uploadPhotos");


    /**获取用户资料**/
    public static String getUserInfo=getUrl("getUserInfo");







}
