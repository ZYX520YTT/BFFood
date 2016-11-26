package food.neusoft.com.food.domian;

/**
 * Created by 张宇翔 on 2016/11/24 22:35.
 * Email：1124751755@qq.com
 * 功能：
 */

public class DrinkInfo {
    private int imageid;
    private int PhotoLeftType;
    private int PhotoRightType;
    private String storename;
    private float rating;
    private String yh;//优惠

    public DrinkInfo(int imageid, String yh, String storename, float rating, int photoLeftType, int photoRightType) {
        this.imageid = imageid;
        this.yh = yh;
        this.storename = storename;
        this.rating = rating;
        PhotoLeftType = photoLeftType;
        PhotoRightType = photoRightType;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getPhotoRightType() {
        return PhotoRightType;
    }

    public void setPhotoRightType(int photoRightType) {
        PhotoRightType = photoRightType;
    }

    public int getPhotoLeftType() {
        return PhotoLeftType;
    }

    public void setPhotoLeftType(int photoLeftType) {
        PhotoLeftType = photoLeftType;
    }

    public int getImageid() {
        return imageid;
    }

    public void setImageid(int imageid) {
        this.imageid = imageid;
    }

    public String getStorename() {
        return storename;
    }

    public void setStorename(String storename) {
        this.storename = storename;
    }

    public String getYh() {
        return yh;
    }

    public void setYh(String yh) {
        this.yh = yh;
    }

    @Override
    public String toString() {
        return "DessertInfo{" +
                "imageid=" + imageid +
                ", PhotoLeftType=" + PhotoLeftType +
                ", PhotoRightType=" + PhotoRightType +
                ", storename='" + storename + '\'' +
                ", rating=" + rating +
                ", yh='" + yh + '\'' +
                '}';
    }
}
