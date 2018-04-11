package yanzhikai.shoppingcartdemo;

import android.util.Log;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import yanzhikai.shoppingcartdemo.ShoppingCartEntity.CommodityEntity;

/**
 * author : yany
 * e-mail : yanzhikai_yjk@qq.com
 * time   : 2018/04/09
 * desc   :
 */

public class ShoppingCartShoppingCartDataSource implements IShoppingCartDataSource {
    public static final String TAG = "Shopping";

    private ShoppingCartEntity mData = new ShoppingCartEntity();


    @Override
    public Observable<ShoppingCartEntity> getDataFromRemote() {
        Log.d(TAG, "getDataFromRemote: ");
        ArrayList<CommodityEntity> commodityEntities = new ArrayList<>();
        commodityEntities.add(new CommodityEntity("数据结构", 30.6f, false));
        commodityEntities.add(new CommodityEntity("C++程序基础", 35.0f, true));
        commodityEntities.add(new CommodityEntity("数据结构", 30.6f, false));
        commodityEntities.add(new CommodityEntity("高等数学", 30.6f, false));
        commodityEntities.add(new CommodityEntity("大学语文", 35.0f, true));
        mData.setCommodities(commodityEntities);
        return handleDataChanged();
    }

    @Override
    public Observable<ShoppingCartEntity> deleteCommodity(int index) {
        mData.getCommodities().remove(index);
        return handleDataChanged();
    }

    @Override
    public Observable<ShoppingCartEntity> handleDataChanged() {
        ArrayList<CommodityEntity> commodityEntities = mData.getCommodities();
        float totalPrice = 0;
        boolean chosenAll = true;
        for (CommodityEntity commodityEntity : commodityEntities) {
            if (commodityEntity.isChosen()) {
                totalPrice += commodityEntity.getPrice();
            }
            chosenAll &= commodityEntity.isChosen();
        }
        mData.setTotalPrice(totalPrice);
        mData.setIsChosenAll(chosenAll);
        Log.d(TAG, "handleDataChanged: " + totalPrice);
        return Observable.just(mData);
    }

    @Override
    public ShoppingCartEntity chooseAll() {
        ArrayList<CommodityEntity> commodityEntities = mData.getCommodities();
        float totalPrice = 0;
        for (CommodityEntity commodityEntity : commodityEntities) {
            commodityEntity.setChosen(true);
            totalPrice += commodityEntity.getPrice();
        }
        mData.setTotalPrice(totalPrice);
        mData.setIsChosenAll(true);
        return mData;
    }

    @Override
    public ShoppingCartEntity chooseNone() {
        ArrayList<CommodityEntity> commodityEntities = mData.getCommodities();
        for (CommodityEntity commodityEntity : commodityEntities) {
            commodityEntity.setChosen(false);
        }
        mData.setTotalPrice(0);
        mData.setIsChosenAll(false);
        return mData;
    }

    @Override
    public Observable<ShoppingCartEntity> requestDelay(final Observable<ShoppingCartEntity> dataObservable) {

        return Observable.just(true)
                .delay(Constant.DELAY_TIME, TimeUnit.MILLISECONDS)
                .flatMap(new Function<Boolean, ObservableSource<ShoppingCartEntity>>() {
                    @Override
                    public ObservableSource<ShoppingCartEntity> apply(Boolean aLong) throws Exception {
                        return dataObservable;
                    }
                });
    }


}
