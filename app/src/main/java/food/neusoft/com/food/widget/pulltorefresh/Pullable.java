package food.neusoft.com.food.widget.pulltorefresh;

/**
 * Created by 张宇翔 on 2016/11/22 08:38.
 * Email：1124751755@qq.com
 * 功能：接口
 */

public interface Pullable
{
    /**
     * 判断是否可以下拉，如果不需要下拉功能可以直接return false
     *
     * @return true如果可以下拉否则返回false
     */
    boolean canPullDown();

    /**
     * 判断是否可以上拉，如果不需要上拉功能可以直接return false
     *
     * @return true如果可以上拉否则返回false
     */
    boolean canPullUp();
}
