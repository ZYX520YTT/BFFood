package food.neusoft.com.food.domian;

/**
 * Created by 张宇翔 on 2016/11/23 19:23.
 * Email：1124751755@qq.com
 * 功能：西餐的信息
 */

public class WestFoodInfo {

    private int imageid;
    private String storename;
    private String foodtype;
    private String distance;
    private int phototype;

    public WestFoodInfo(String distance, int phototype, String foodtype, int imageid, String storename) {
        this.distance = distance;
        this.phototype = phototype;
        this.foodtype = foodtype;
        this.imageid = imageid;
        this.storename = storename;
    }

    public String getStorename() {
        return storename;
    }

    public void setStorename(String storename) {
        this.storename = storename;
    }

    public int getImageid() {
        return imageid;
    }

    public void setImageid(int imageid) {
        this.imageid = imageid;
    }

    public String getFoodtype() {
        return foodtype;
    }

    public void setFoodtype(String foodtype) {
        this.foodtype = foodtype;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public int getPhototype() {
        return phototype;
    }

    public void setPhototype(int phototype) {
        this.phototype = phototype;
    }

    @Override
    public String toString() {
        return "WestFoodInfo{" +
                "distance='" + distance + '\'' +
                ", imageid=" + imageid +
                ", storename='" + storename + '\'' +
                ", foodtype='" + foodtype + '\'' +
                ", phototype=" + phototype +
                '}';
    }
}
