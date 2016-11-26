package food.neusoft.com.food.domian;

/**
 * Created by 张宇翔 on 2016/11/23 10:42.
 * Email：1124751755@qq.com
 * 功能：发现美食的图片信息
 */

public class FoodPhotoInfo {

    private int imageid;
    private String imagedesc;

    public FoodPhotoInfo(int imageid, String imagedesc) {
        this.imageid = imageid;
        this.imagedesc = imagedesc;
    }

    public String getImagedesc() {
        return imagedesc;
    }

    public void setImagedesc(String imagedesc) {
        this.imagedesc = imagedesc;
    }

    public int getImageid() {
        return imageid;
    }

    public void setImageid(int imageid) {
        this.imageid = imageid;
    }

    @Override
    public String toString() {
        return "FoodPhotoInfo{" +
                "imagedesc='" + imagedesc + '\'' +
                ", imageid=" + imageid +
                '}';
    }
}
