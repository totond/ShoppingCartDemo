package yanzhikai.shoppingcartdemo.shoppingcart;

import yanzhikai.shoppingcartdemo.base.BaseView;

/**
 * author : yany
 * e-mail : yanzhikai_yjk@qq.com
 * time   : 2018/04/09
 * desc   : View层和Presenter层的Contract类
 */

public interface ShoppingCartContract {

    interface ShoppingCartView extends BaseView {
        //绑定商品数据
        void bindData(ShoppingCartEntity entity);

        //刷新全部商品数据
        void updateData();

        //删除单个商品数据
        void deleteCommodity(int index);

        //刷新单个商品数据
        void updateCommodity(int index);

        //刷新底部显示数据
        void updateBottomUI(boolean isChosenAll, float totalPrice);

        //提交购物车数据
//        void commitCartData();

        //错误信息显示
//        void showError();

    }

    interface IShoppingCarPresenter{
        //让M层获取数据,然后给View层绑定
        void bindData();

        //让M层删除数据,通知View层更新
        void deleteCommodity(int index);

        //让M层改变数据,通知View层更新
        void changeData(int index);

        //让M层设置数据全选或者全不选,通知View层更新
        void chooseAllOrNone(boolean choose);
    }
}
