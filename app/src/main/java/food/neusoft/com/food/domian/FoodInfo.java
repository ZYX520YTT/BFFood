package food.neusoft.com.food.domian;

/**
 * Created by 张宇翔 on 2016/11/29 21:30.
 * Email：1124751755@qq.com
 * 功能：
 */

public class FoodInfo {

    private String foodDiscount;
    private boolean foodHot;
    private String foodIconPath;
    private String foodIntroduce;
    private String foodName;
    private long foodNo;
    private double foodPrice;

    public FoodInfo(String foodDiscount, boolean foodHot, String foodIconPath, String foodIntroduce, String foodName, long foodNo, double foodPrice) {
        this.foodDiscount = foodDiscount;
        this.foodHot = foodHot;
        this.foodIconPath = foodIconPath;
        this.foodIntroduce = foodIntroduce;
        this.foodName = foodName;
        this.foodNo = foodNo;
        this.foodPrice = foodPrice;
    }

    public String getFoodIconPath() {
        return foodIconPath;
    }

    public void setFoodIconPath(String foodIconPath) {
        this.foodIconPath = foodIconPath;
    }

    public boolean isFoodHot() {
        return foodHot;
    }

    public void setFoodHot(boolean foodHot) {
        this.foodHot = foodHot;
    }

    public String getFoodDiscount() {
        return foodDiscount;
    }

    public void setFoodDiscount(String foodDiscount) {
        this.foodDiscount = foodDiscount;
    }

    public String getFoodIntroduce() {
        return foodIntroduce;
    }

    public void setFoodIntroduce(String foodIntroduce) {
        this.foodIntroduce = foodIntroduce;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public long getFoodNo() {
        return foodNo;
    }

    public void setFoodNo(long foodNo) {
        this.foodNo = foodNo;
    }

    public double getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(double foodPrice) {
        this.foodPrice = foodPrice;
    }
}
