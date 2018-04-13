package yanzhikai.shoppingcartdemo.shoppingcart;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import yanzhikai.shoppingcartdemo.Constant;

/**
 * author : yany
 * e-mail : yanzhikai_yjk@qq.com
 * time   : 2018/04/09
 * desc   : Presenter实现，管理V和P层
 */

public class ShoppingCartPresenterImpl implements ShoppingCartContract.IShoppingCarPresenter {
    public static final String TAG = "ShoppingCartPresenter";


    private final ShoppingCartSourceImpl mDataSource = new ShoppingCartSourceImpl();
    @NonNull
    private final ShoppingCartContract.ShoppingCartView mShoppingCartView;

    public ShoppingCartPresenterImpl(@NonNull ShoppingCartContract.ShoppingCartView iShoppingCartView) {
        mShoppingCartView = iShoppingCartView;
    }


    @Override
    public void bindData() {
        Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> e) throws Exception {
                mShoppingCartView.showLoading();
                e.onNext(true);
            }
        })
                .delay(Constant.DELAY_TIME, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean b) throws Exception {
                        mShoppingCartView.hideLoading();
                        mShoppingCartView.bindData(mDataSource.getDataFromRemote());
                    }
                });
    }

    @Override
    public void deleteCommodity(final int index) {
        Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> e) throws Exception {
                mShoppingCartView.showLoading();
                e.onNext(true);
            }
        })
                .delay(Constant.DELAY_TIME, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        ShoppingCartEntity entity = mDataSource.deleteCommodity(index);
                        mShoppingCartView.hideLoading();
                        mShoppingCartView.deleteCommodity(index);
                        mShoppingCartView.updateBottomUI(entity.isIsChosenAll(), entity.getTotalPrice());
                    }
                });
    }

    @Override
    public void changeData(final int index) {
        Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> e) throws Exception {
                mShoppingCartView.showLoading();
                e.onNext(true);
            }
        })
                .delay(Constant.DELAY_TIME, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        ShoppingCartEntity entity = mDataSource.handleDataChanged(index);
                        mShoppingCartView.hideLoading();
                        mShoppingCartView.updateCommodity(index);
                        mShoppingCartView.updateBottomUI(entity.isIsChosenAll(), entity.getTotalPrice());
                    }
                });
    }


    @Override
    public void chooseAllOrNone(final boolean choose) {
        Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> e) throws Exception {
                mShoppingCartView.showLoading();
                e.onNext(true);
            }
        })
                .delay(Constant.DELAY_TIME, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        ShoppingCartEntity entity;
                        if (choose) {
                            entity = mDataSource.chooseAll();
                        } else {
                            entity = mDataSource.chooseNone();
                        }
                        mShoppingCartView.hideLoading();
                        mShoppingCartView.updateData();
                        mShoppingCartView.updateBottomUI(entity.isIsChosenAll(), entity.getTotalPrice());
                    }
                });

    }

}
