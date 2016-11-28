package food.neusoft.com.food.domian;

/**
 * Created by 张宇翔 on 2016/11/23 17:11.
 * Email：1124751755@qq.com
 * 功能：火锅的信息
 */

public class HotPotInfo {

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
    private String typeName;

    public HotPotInfo(String bookIconPath, String typeName, double marketPrice, long marketNo, String marketName, String marketIntroduce, String marketIconPath, double marketHotLevel, double marketDiscount, String marketBigPicture, String discountIconPath, String marketAdress, double marketDistance) {
        this.bookIconPath = bookIconPath;
        this.typeName = typeName;
        this.marketPrice = marketPrice;
        this.marketNo = marketNo;
        this.marketName = marketName;
        this.marketIntroduce = marketIntroduce;
        this.marketIconPath = marketIconPath;
        this.marketHotLevel = marketHotLevel;
        this.marketDiscount = marketDiscount;
        this.marketBigPicture = marketBigPicture;
        this.discountIconPath = discountIconPath;
        this.marketAdress = marketAdress;
        this.marketDistance = marketDistance;
    }

    public String getBookIconPath() {
        return bookIconPath;
    }

    public void setBookIconPath(String bookIconPath) {
        this.bookIconPath = bookIconPath;
    }

    public String getMarketIntroduce() {
        return marketIntroduce;
    }

    public void setMarketIntroduce(String marketIntroduce) {
        this.marketIntroduce = marketIntroduce;
    }

    public double getMarketHotLevel() {
        return marketHotLevel;
    }

    public void setMarketHotLevel(double marketHotLevel) {
        this.marketHotLevel = marketHotLevel;
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

    public double getMarketDiscount() {
        return marketDiscount;
    }

    public void setMarketDiscount(double marketDiscount) {
        this.marketDiscount = marketDiscount;
    }

    public double getMarketDistance() {
        return marketDistance;
    }

    public void setMarketDistance(double marketDistance) {
        this.marketDistance = marketDistance;
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

    public long getMarketNo() {
        return marketNo;
    }

    public void setMarketNo(long marketNo) {
        this.marketNo = marketNo;
    }

    public double getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(double marketPrice) {
        this.marketPrice = marketPrice;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
