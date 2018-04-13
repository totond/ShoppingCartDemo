package yanzhikai.shoppingcartdemo.base;


import yanzhikai.shoppingcartdemo.widget.EmptyLayout;

/**
 * author : yany
 * e-mail : yanzhikai_yjk@qq.com
 * time   : 2018/04/09
 * desc   : Fragment基类，目前主要是绑定各种状态的View，如加载中，网络错误等
 */

public interface BaseView {
     //显示加载动画
    void showLoading();

     //隐藏加载
    void hideLoading();

     //显示网络错误
    void showNetError(EmptyLayout.OnRetryListener onRetryListener);

}
