package food.neusoft.com.food.domian;

/**
 * Created by 张宇翔 on 2016/11/23 10:42.
 * Email：1124751755@qq.com
 * 功能：发现美食的图片信息
 */

public class FoodPhotoInfo {

    private double foodDiscount;//打折
    private boolean foodHot;//是否是热门
    private String foodIconPath;//图片
    private String foodIntroduce;//介绍
    private String foodName;//美食名字
    private long foodNo;//美食编号

    public FoodPhotoInfo(double foodDiscount, boolean foodHot, String foodIconPath, String foodName, String foodIntroduce, long foodNo) {
        this.foodDiscount = foodDiscount;
        this.foodHot = foodHot;
        this.foodIconPath = foodIconPath;
        this.foodName = foodName;
        this.foodIntroduce = foodIntroduce;
        this.foodNo = foodNo;
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

    public double getFoodDiscount() {
        return foodDiscount;
    }

    public void setFoodDiscount(float foodDiscount) {
        this.foodDiscount = foodDiscount;
    }

    public String getFoodIconPath() {
        return foodIconPath;
    }

    public void setFoodIconPath(String foodIconPath) {
        this.foodIconPath = foodIconPath;
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
}
