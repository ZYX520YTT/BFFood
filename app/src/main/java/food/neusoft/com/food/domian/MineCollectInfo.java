package food.neusoft.com.food.domian;

/**
 * Created by 张宇翔 on 2016/12/1 14:26.
 * Email：1124751755@qq.com
 * 功能：我的收藏店铺信息
 */

public class MineCollectInfo {
/**
 * "bookIconPath": "",
 "discountIconPath": "",
 "marketAdress": "青羊区",
 "marketBigPicture": "SY_pic.png",
 "marketDiscount": 9.9,
 "marketDistance": 6.4,
 "marketHotLevel": 5,
 "marketIconPath": "Re_pic_three.png.png",
 "marketIntroduce": "自助餐",
 "marketName": "自助汤锅",
 "marketNo": 935,
 "marketPrice": 50.0,
 "newIconPath": "",
 "typeName": "火锅"
 */
    private String bookIconPath;
    private String discountIconPath;
    private String marketAdress;
    private String marketBigPicture;
    private double marketDiscount;
    private double marketDistance;
    private double marketHotLevel;
    private String marketIconPath;
    private String marketIntroduce;
    private String marketName;
    private long marketNo;
    private double marketPrice;
    private String newIconPath;
    private String typeName;

    public MineCollectInfo(String bookIconPath, String discountIconPath, String marketAdress, String marketBigPicture, double marketDiscount, double marketDistance, double marketHotLevel, String marketIntroduce, String marketIconPath, long marketNo, double marketPrice, String newIconPath, String typeName, String marketName) {
        this.bookIconPath = bookIconPath;
        this.discountIconPath = discountIconPath;
        this.marketAdress = marketAdress;
        this.marketBigPicture = marketBigPicture;
        this.marketDiscount = marketDiscount;
        this.marketDistance = marketDistance;
        this.marketHotLevel = marketHotLevel;
        this.marketIntroduce = marketIntroduce;
        this.marketIconPath = marketIconPath;
        this.marketNo = marketNo;
        this.marketPrice = marketPrice;
        this.newIconPath = newIconPath;
        this.typeName = typeName;
        this.marketName = marketName;
    }

    public String getBookIconPath() {
        return bookIconPath;
    }

    public void setBookIconPath(String bookIconPath) {
        this.bookIconPath = bookIconPath;
    }

    public String getNewIconPath() {
        return newIconPath;
    }

    public void setNewIconPath(String newIconPath) {
        this.newIconPath = newIconPath;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public double getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(double marketPrice) {
        this.marketPrice = marketPrice;
    }

    public long getMarketNo() {
        return marketNo;
    }

    public void setMarketNo(long marketNo) {
        this.marketNo = marketNo;
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public String getMarketIntroduce() {
        return marketIntroduce;
    }

    public void setMarketIntroduce(String marketIntroduce) {
        this.marketIntroduce = marketIntroduce;
    }

    public String getMarketIconPath() {
        return marketIconPath;
    }

    public void setMarketIconPath(String marketIconPath) {
        this.marketIconPath = marketIconPath;
    }

    public double getMarketHotLevel() {
        return marketHotLevel;
    }

    public void setMarketHotLevel(double marketHotLevel) {
        this.marketHotLevel = marketHotLevel;
    }

    public double getMarketDistance() {
        return marketDistance;
    }

    public void setMarketDistance(double marketDistance) {
        this.marketDistance = marketDistance;
    }

    public double getMarketDiscount() {
        return marketDiscount;
    }

    public void setMarketDiscount(double marketDiscount) {
        this.marketDiscount = marketDiscount;
    }

    public String getMarketBigPicture() {
        return marketBigPicture;
    }

    public void setMarketBigPicture(String marketBigPicture) {
        this.marketBigPicture = marketBigPicture;
    }

    public String getMarketAdress() {
        return marketAdress;
    }

    public void setMarketAdress(String marketAdress) {
        this.marketAdress = marketAdress;
    }

    public String getDiscountIconPath() {
        return discountIconPath;
    }

    public void setDiscountIconPath(String discountIconPath) {
        this.discountIconPath = discountIconPath;
    }
}
