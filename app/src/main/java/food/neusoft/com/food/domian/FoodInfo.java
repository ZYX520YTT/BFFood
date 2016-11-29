package food.neusoft.com.food.domian;

/**
 * Created by 张宇翔 on 2016/11/29 21:30.
 * Email：1124751755@qq.com
 * 功能：
 */

public class FoodInfo {

    private double foodDiscount;
    private boolean foodHot;
    private String foodIconPath;
    private String foodIntroduce;
    private String foodName;
    private long foodNo;
    private double foodPrice;

    public FoodInfo(double foodDiscount, double foodPrice, long foodNo, String foodName, String foodIconPath, boolean foodHot, String foodIntroduce) {
        this.foodDiscount = foodDiscount;
        this.foodPrice = foodPrice;
        this.foodNo = foodNo;
        this.foodName = foodName;
        this.foodIconPath = foodIconPath;
        this.foodHot = foodHot;
        this.foodIntroduce = foodIntroduce;
    }

    public double getFoodDiscount() {
        return foodDiscount;
    }

    public void setFoodDiscount(double foodDiscount) {
        this.foodDiscount = foodDiscount;
    }

    public double getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(double foodPrice) {
        this.foodPrice = foodPrice;
    }

    public long getFoodNo() {
        return foodNo;
    }

    public void setFoodNo(long foodNo) {
        this.foodNo = foodNo;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodIntroduce() {
        return foodIntroduce;
    }

    public void setFoodIntroduce(String foodIntroduce) {
        this.foodIntroduce = foodIntroduce;
    }

    public boolean isFoodHot() {
        return foodHot;
    }

    public void setFoodHot(boolean foodHot) {
        this.foodHot = foodHot;
    }

    public String getFoodIconPath() {
        return foodIconPath;
    }

    public void setFoodIconPath(String foodIconPath) {
        this.foodIconPath = foodIconPath;
    }
}
