package food.neusoft.com.food.domian;

/**
 * Created by 张宇翔 on 2016/11/29 21:30.
 * Email：1124751755@qq.com
 * 功能：
 */

public class FoodInfo {

    private String foodDiscount;
    private String foodHot;
    private String foodIconPath;
    private String foodIntroduce;
    private String foodName;
    private long foodNo;
    private double foodPrice;

    public FoodInfo(String foodName, String foodIntroduce, String foodHot, String foodIconPath, String foodDiscount, long foodNo, double foodPrice) {
        this.foodName = foodName;
        this.foodIntroduce = foodIntroduce;
        this.foodHot = foodHot;
        this.foodIconPath = foodIconPath;
        this.foodDiscount = foodDiscount;
        this.foodNo = foodNo;
        this.foodPrice = foodPrice;
    }

    public String getFoodDiscount() {
        return foodDiscount;
    }

    public void setFoodDiscount(String foodDiscount) {
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

    public String getFoodIconPath() {
        return foodIconPath;
    }

    public void setFoodIconPath(String foodIconPath) {
        this.foodIconPath = foodIconPath;
    }

    public String getFoodHot() {
        return foodHot;
    }

    public void setFoodHot(String foodHot) {
        this.foodHot = foodHot;
    }


    @Override
    public String toString() {
        return "FoodInfo{" +
                "foodDiscount='" + foodDiscount + '\'' +
                ", foodHot='" + foodHot + '\'' +
                ", foodIconPath='" + foodIconPath + '\'' +
                ", foodIntroduce='" + foodIntroduce + '\'' +
                ", foodName='" + foodName + '\'' +
                ", foodNo=" + foodNo +
                ", foodPrice=" + foodPrice +
                '}';
    }
}
