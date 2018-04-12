package yanzhikai.shoppingcartdemo;

import android.support.annotation.NonNull;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
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

public class ShoppingCartPresenter implements ShoppingCartContract.IShoppingCarPresenter {
    public static final String TAG = "ShoppingCartPresenter";


    private final IShoppingCartDataSource mDataSource = new ShoppingCartShoppingCartDataSource();
    @NonNull
    private final ShoppingCartContract.IShoppingCartView mShoppingCartView;

    public ShoppingCartPresenter(@NonNull ShoppingCartContract.IShoppingCartView iShoppingCartView) {
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
//        mDataSource.getDataFromRemote()
//                .subscribeOn(Schedulers.io())
//                .doOnSubscribe(new Consumer<Disposable>() {
//                    @Override
//                    public void accept(Disposable disposable) throws Exception {
//                        mShoppingCartView.showLoading();
//                    }
//                })
//                .subscribeOn(AndroidSchedulers.mainThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<ShoppingCartEntity>() {
//                    @Override
//                    public void accept(ShoppingCartEntity entity) throws Exception {
//                        mShoppingCartView.hideLoading();
//                        mShoppingCartView.bindData(entity);
//                    }
//                });

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
//       mDataSource.deleteCommodity(index)
//                .subscribeOn(Schedulers.io())
//                .doOnSubscribe(new Consumer<Disposable>() {
//                    @Override
//                    public void accept(Disposable disposable) throws Exception {
//                        mShoppingCartView.showLoading();
//                    }
//                })
//                .subscribeOn(AndroidSchedulers.mainThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<ShoppingCartEntity>() {
//                    @Override
//                    public void accept(ShoppingCartEntity entity) throws Exception {
//                        mShoppingCartView.hideLoading();
//                        mShoppingCartView.deleteCommodity(index);
//                        mShoppingCartView.updateBottomUI(entity.isIsChosenAll(),entity.getTotalPrice());
//                    }
//                });
    }

        @Override
        public void dataStateChanged () {
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
                            ShoppingCartEntity entity = mDataSource.handleDataChanged();
                            mShoppingCartView.hideLoading();
                            mShoppingCartView.updateBottomUI(entity.isIsChosenAll(), entity.getTotalPrice());
                        }
                    });
//            mDataSource.handleDataChanged()
//                    .subscribeOn(Schedulers.io())
//                    .doOnSubscribe(new Consumer<Disposable>() {
//                        @Override
//                        public void accept(Disposable disposable) throws Exception {
//                            mShoppingCartView.showLoading();
//                        }
//                    })
//                    .subscribeOn(AndroidSchedulers.mainThread())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe(new Consumer<ShoppingCartEntity>() {
//                        @Override
//                        public void accept(ShoppingCartEntity entity) throws Exception {
//                            ShoppingCartEntity entity = mDataSource.handleDataChanged();
//                            mShoppingCartView.hideLoading();
//                            mShoppingCartView.updateBottomUI(entity.isIsChosenAll(), entity.getTotalPrice());
//                        }
//                    });
        }

        @Override
        public void chooseAllOrNone ( boolean choose){
            ShoppingCartEntity entity;
            if (choose) {
                entity = mDataSource.chooseAll();
            } else {
                entity = mDataSource.chooseNone();
            }
            mShoppingCartView.updateData();
            mShoppingCartView.updateBottomUI(entity.isIsChosenAll(), entity.getTotalPrice());
        }

    }
