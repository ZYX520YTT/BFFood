package food.neusoft.com.food.domian;

/**
 * Created by 张宇翔 on 2016/11/30 20:27.
 * Email：1124751755@qq.com
 * 功能：
 */

public class MineOrderInfo {

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


    private String orderDay;
    private long orderNo;
    private int orderPeopleCount;
    private String orderState;
    private String orderTime;
    private double orderTotalPrice;


    private String userIconPath;
    private String userId;
    private long userNo;
    private String userPassword;

    public MineOrderInfo(String bookIconPath, double marketDistance, double marketHotLevel, String marketIconPath, String marketName, double marketPrice, String orderDay, String orderState, String orderTime, double orderTotalPrice, int orderPeopleCount, long orderNo) {
        this.bookIconPath = bookIconPath;
        this.marketDistance = marketDistance;
        this.marketHotLevel = marketHotLevel;
        this.marketIconPath = marketIconPath;
        this.marketName = marketName;
        this.marketPrice = marketPrice;
        this.orderDay = orderDay;
        this.orderState = orderState;
        this.orderTime = orderTime;
        this.orderTotalPrice = orderTotalPrice;
        this.orderPeopleCount = orderPeopleCount;
        this.orderNo = orderNo;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public long getUserNo() {
        return userNo;
    }

    public void setUserNo(long userNo) {
        this.userNo = userNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserIconPath() {
        return userIconPath;
    }

    public void setUserIconPath(String userIconPath) {
        this.userIconPath = userIconPath;
    }

    public double getOrderTotalPrice() {
        return orderTotalPrice;
    }

    public void setOrderTotalPrice(double orderTotalPrice) {
        this.orderTotalPrice = orderTotalPrice;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    public int getOrderPeopleCount() {
        return orderPeopleCount;
    }

    public void setOrderPeopleCount(int orderPeopleCount) {
        this.orderPeopleCount = orderPeopleCount;
    }

    public long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(long orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderDay() {
        return orderDay;
    }

    public void setOrderDay(String orderDay) {
        this.orderDay = orderDay;
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

    public String getBookIconPath() {
        return bookIconPath;
    }

    public void setBookIconPath(String bookIconPath) {
        this.bookIconPath = bookIconPath;
    }

    public String getMarketBigPicture() {
        return marketBigPicture;
    }

    public void setMarketBigPicture(String marketBigPicture) {
        this.marketBigPicture = marketBigPicture;
    }

    @Override
    public String toString() {
        return "MineOrderInfo{" +
                "bookIconPath='" + bookIconPath + '\'' +
                ", discountIconPath='" + discountIconPath + '\'' +
                ", marketAdress='" + marketAdress + '\'' +
                ", marketBigPicture='" + marketBigPicture + '\'' +
                ", marketDiscount=" + marketDiscount +
                ", marketDistance=" + marketDistance +
                ", marketHotLevel=" + marketHotLevel +
                ", marketIconPath='" + marketIconPath + '\'' +
                ", marketIntroduce='" + marketIntroduce + '\'' +
                ", marketName='" + marketName + '\'' +
                ", marketNo=" + marketNo +
                ", marketPrice=" + marketPrice +
                ", newIconPath='" + newIconPath + '\'' +
                ", orderDay='" + orderDay + '\'' +
                ", orderNo=" + orderNo +
                ", orderPeopleCount=" + orderPeopleCount +
                ", orderState='" + orderState + '\'' +
                ", orderTime='" + orderTime + '\'' +
                ", orderTotalPrice=" + orderTotalPrice +
                ", userIconPath='" + userIconPath + '\'' +
                ", userId='" + userId + '\'' +
                ", userNo=" + userNo +
                ", userPassword='" + userPassword + '\'' +
                '}';
    }
}
