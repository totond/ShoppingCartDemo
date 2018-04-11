package yanzhikai.shoppingcartdemo.base;


import yanzhikai.shoppingcartdemo.widget.EmptyLayout;

/**
 * @author dangxueyi
 * @description
 * @date 2017/12/23
 */

public interface IBaseView {
    /**
     * 显示加载动画
     */
    void showLoading();

    /**
     * 隐藏加载
     */
    void hideLoading();

    /**
     * 显示网络错误
     * @param onRetryListener 点击监听
     */
    void showNetError(EmptyLayout.OnRetryListener onRetryListener);


}
