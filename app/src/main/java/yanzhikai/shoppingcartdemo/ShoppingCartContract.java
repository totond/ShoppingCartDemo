package yanzhikai.shoppingcartdemo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * author : yany
 * e-mail : yanzhikai_yjk@qq.com
 * time   : 2018/04/09
 * desc   :
 */

public interface ShoppingCartContract {

    interface IShoppingCartView{
        //绑定商品数据
        void bindData(ShoppingCartEntity entity);

        //刷新全部商品数据
        void updateData();

        //删除单个商品数据
        void deleteCommodity(int index);

        //刷新底部显示数据
        void updateBottomUI(boolean isChosenAll, float totalPrice);

        //提交购物车数据
//        void commitCartData();

        //错误信息显示
//        void showError();

    }

    interface IShoppingCarPresenter{
        void bindData();

        void deleteCommodity(int index);

        void dataStateChanged();

        //全选或者全不选
        void chooseAllOrNone(boolean choose);
    }
}
