package food.neusoft.com.food.domian;

/**
 * Created by 张宇翔 on 2016/11/23 19:23.
 * Email：1124751755@qq.com
 * 功能：西餐的信息
 */

public class WestFoodInfo {

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
    private String bookIconPath;
    private String typeName;
    private String erectLineIconPath;
    private String discountIconPath;

    public WestFoodInfo(String bookIconPath, String typeName, double marketPrice, long marketNo, String marketName, String marketIconPath, double marketHotLevel, double marketDistance, double marketDiscount, String marketBigPicture, String marketAdress, String erectLineIconPath, String discountIconPath, String marketIntroduce) {
        this.bookIconPath = bookIconPath;
        this.typeName = typeName;
        this.marketPrice = marketPrice;
        this.marketNo = marketNo;
        this.marketName = marketName;
        this.marketIconPath = marketIconPath;
        this.marketHotLevel = marketHotLevel;
        this.marketDistance = marketDistance;
        this.marketDiscount = marketDiscount;
        this.marketBigPicture = marketBigPicture;
        this.marketAdress = marketAdress;
        this.erectLineIconPath = erectLineIconPath;
        this.discountIconPath = discountIconPath;
        this.marketIntroduce = marketIntroduce;
    }

    public String getBookIconPath() {
        return bookIconPath;
    }

    public void setBookIconPath(String bookIconPath) {
        this.bookIconPath = bookIconPath;
    }

    public String getDiscountIconPath() {
        return discountIconPath;
    }

    public void setDiscountIconPath(String discountIconPath) {
        this.discountIconPath = discountIconPath;
    }

    public String getErectLineIconPath() {
        return erectLineIconPath;
    }

    public void setErectLineIconPath(String erectLineIconPath) {
        this.erectLineIconPath = erectLineIconPath;
    }

    public String getMarketAdress() {
        return marketAdress;
    }

    public void setMarketAdress(String marketAdress) {
        this.marketAdress = marketAdress;
    }

    public String getMarketBigPicture() {
        return marketBigPicture;
    }

    public void setMarketBigPicture(String marketBigPicture) {
        this.marketBigPicture = marketBigPicture;
    }

    public double getMarketDiscount() {
        return marketDiscount;
    }

    public void setMarketDiscount(double marketDiscount) {
        this.marketDiscount = marketDiscount;
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

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
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
}
