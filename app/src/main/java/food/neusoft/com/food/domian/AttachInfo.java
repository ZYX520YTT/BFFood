package food.neusoft.com.food.domian;

/**
 * Created by 张宇翔 on 2016/11/26 10:06.
 * Email：1124751755@qq.com
 * 功能：附近的信息
 */

public class AttachInfo {

    /**
     * "bookIconPath": "view_yu.png",
     "discountIconPath": "view_hui.png",
     "marketAdress": "青羊区",
     "marketBigPicture": "SY_pic.png",
     "marketDiscount": 9.0,
     "marketDistance": 1.0,
     "marketHotLevel": 5,
     "marketIconPath": "HG_pic_one.png",
     "marketIntroduce": "来这儿，体验四川的美味~",
     "marketName": "重庆渝达老火锅店",
     "marketNo": 1,
     "marketPrice": 36.0,
     "newIconPath": "view_new.png",
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

    public AttachInfo(String bookIconPath, String newIconPath, String discountIconPath, String marketAdress, String marketBigPicture, double marketDiscount, double marketDistance, double marketHotLevel, String marketIntroduce, String marketIconPath, String marketName, long marketNo, double marketPrice, String typeName) {
        this.bookIconPath = bookIconPath;
        this.newIconPath = newIconPath;
        this.discountIconPath = discountIconPath;
        this.marketAdress = marketAdress;
        this.marketBigPicture = marketBigPicture;
        this.marketDiscount = marketDiscount;
        this.marketDistance = marketDistance;
        this.marketHotLevel = marketHotLevel;
        this.marketIntroduce = marketIntroduce;
        this.marketIconPath = marketIconPath;
        this.marketName = marketName;
        this.marketNo = marketNo;
        this.marketPrice = marketPrice;
        this.typeName = typeName;
    }

    public String getBookIconPath() {
        return bookIconPath;
    }

    public void setBookIconPath(String bookIconPath) {
        this.bookIconPath = bookIconPath;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getNewIconPath() {
        return newIconPath;
    }

    public void setNewIconPath(String newIconPath) {
        this.newIconPath = newIconPath;
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
