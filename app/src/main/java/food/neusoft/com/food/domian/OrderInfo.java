package food.neusoft.com.food.domian;

/**
 * Created by 张宇翔 on 2016/11/26 10:06.
 * Email：1124751755@qq.com
 * 功能：
 */

public class OrderInfo {


    private int imageid;
    private String storename;
    private float rating;
    private String money;
    private String name;
    private String price;
    private String distance;

    public OrderInfo(int imageid, String storename, float rating, String price, String money, String name, String distance) {
        this.imageid = imageid;
        this.storename = storename;
        this.rating = rating;
        this.price = price;
        this.money = money;
        this.name = name;
        this.distance=distance;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getStorename() {
        return storename;
    }

    public void setStorename(String storename) {
        this.storename = storename;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public int getImageid() {
        return imageid;
    }

    public void setImageid(int imageid) {
        this.imageid = imageid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "AttachHotInfo{" +
                "distance='" + distance + '\'' +
                ", imageid=" + imageid +
                ", storename='" + storename + '\'' +
                ", rating=" + rating +
                ", money='" + money + '\'' +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
