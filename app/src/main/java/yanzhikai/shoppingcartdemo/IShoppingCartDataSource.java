package yanzhikai.shoppingcartdemo;

import java.util.ArrayList;

/**
 * author : yany
 * e-mail : yanzhikai_yjk@qq.com
 * time   : 2018/04/09
 * desc   :
 */

public interface IShoppingCartDataSource {
    ShoppingCartEntity getDataFromRemote();

    ShoppingCartEntity getDataFromLocal();

    void deleteCommodity(int index);

    ShoppingCartEntity handleDataChanged();
}
