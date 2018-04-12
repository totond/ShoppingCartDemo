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

    ShoppingCartEntity getDataFromRemote();

    ShoppingCartEntity deleteCommodity(int index);

    ShoppingCartEntity handleDataChanged();

    ShoppingCartEntity chooseAll();

    ShoppingCartEntity chooseNone();

}
