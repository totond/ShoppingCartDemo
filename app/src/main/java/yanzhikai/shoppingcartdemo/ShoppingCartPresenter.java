package yanzhikai.shoppingcartdemo;

import android.support.annotation.NonNull;

/**
 * author : yany
 * e-mail : yanzhikai_yjk@qq.com
 * time   : 2018/04/09
 * desc   :
 */

public class ShoppingCartPresenter implements ShoppingCartContract.IShoppingCarPresenter{
    public static final String TAG = "ShoppingCartPresenter";


    private final IShoppingCartDataSource mDataSource = new ShoppingCartShoppingCartDataSource();
    @NonNull
    private final ShoppingCartContract.IShoppingCartView mShoppingCartView;

    public ShoppingCartPresenter(@NonNull ShoppingCartContract.IShoppingCartView iShoppingCartView) {
        mShoppingCartView = iShoppingCartView;
    }


    @Override
    public void bindData() {
        mShoppingCartView.bindData(mDataSource.getDataFromLocal());
    }

    @Override
    public void deleteCommodity(int index) {
        mDataSource.deleteCommodity(index);
        //假设删除成功的话
        mShoppingCartView.deleteCommodity(index);
        dataStateChanged();
    }

    @Override
    public void dataStateChanged() {
        ShoppingCartEntity entity = mDataSource.handleDataChanged();
        mShoppingCartView.updateBottomUI(entity.isIsChosenAll(),entity.getTotalPrice());
    }



}
