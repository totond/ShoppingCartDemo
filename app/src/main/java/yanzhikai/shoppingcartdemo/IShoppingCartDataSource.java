package yanzhikai.shoppingcartdemo;

import java.util.ArrayList;

import io.reactivex.Observable;

/**
 * author : yany
 * e-mail : yanzhikai_yjk@qq.com
 * time   : 2018/04/09
 * desc   :
 */

public interface IShoppingCartDataSource {

    Observable<ShoppingCartEntity> getDataFromRemote();

    Observable<ShoppingCartEntity> deleteCommodity(int index);

    Observable<ShoppingCartEntity> handleDataChanged();

    ShoppingCartEntity chooseAll();

    ShoppingCartEntity chooseNone();

    Observable<ShoppingCartEntity> requestDelay(Observable<ShoppingCartEntity> dataObservable);
}
