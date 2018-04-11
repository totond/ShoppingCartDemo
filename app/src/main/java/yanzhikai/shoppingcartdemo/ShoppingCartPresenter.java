package yanzhikai.shoppingcartdemo;

import android.support.annotation.NonNull;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

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
        mDataSource.getDataFromRemote()
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mShoppingCartView.showLoading();
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ShoppingCartEntity>() {
                    @Override
                    public void accept(ShoppingCartEntity entity) throws Exception {
                        mShoppingCartView.hideLoading();
                        mShoppingCartView.bindData(entity);
                    }
                });

    }

    @Override
    public void deleteCommodity(final int index) {
        mDataSource.requestDelay(mDataSource.deleteCommodity(index))
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mShoppingCartView.showLoading();
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ShoppingCartEntity>() {
                    @Override
                    public void accept(ShoppingCartEntity entity) throws Exception {
                        mShoppingCartView.hideLoading();
                        mShoppingCartView.deleteCommodity(index);
                        mShoppingCartView.updateBottomUI(entity.isIsChosenAll(),entity.getTotalPrice());
                    }
                });
    }

    @Override
    public void dataStateChanged() {
        mDataSource.requestDelay(mDataSource.handleDataChanged())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mShoppingCartView.showLoading();
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ShoppingCartEntity>() {
                    @Override
                    public void accept(ShoppingCartEntity entity) throws Exception {
                        mShoppingCartView.hideLoading();
                        mShoppingCartView.updateBottomUI(entity.isIsChosenAll(),entity.getTotalPrice());
                    }
                });
    }

    @Override
    public void chooseAllOrNone(boolean choose) {
        ShoppingCartEntity entity;
        if (choose){
            entity = mDataSource.chooseAll();
        }else {
            entity = mDataSource.chooseNone();
        }
        mShoppingCartView.updateData();
        mShoppingCartView.updateBottomUI(entity.isIsChosenAll(),entity.getTotalPrice());
    }

}
