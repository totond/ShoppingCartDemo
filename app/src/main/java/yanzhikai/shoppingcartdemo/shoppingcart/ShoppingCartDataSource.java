package yanzhikai.shoppingcartdemo.shoppingcart;


/**
 * author : yany
 * e-mail : yanzhikai_yjk@qq.com
 * time   : 2018/04/09
 * desc   : model接口
 */

interface ShoppingCartDataSource {

    //模拟从服务器获取数据
    ShoppingCartEntity getDataFromRemote();

    //模拟让服务器删除商品数据
    ShoppingCartEntity deleteCommodity(int index);

    //模拟让服务器处理单个数据变化信息
    ShoppingCartEntity handleDataChanged(int index);

    //模拟让服务器处理数据变化信息
    ShoppingCartEntity handleDataChanged();

    //模拟让服务器全选
    ShoppingCartEntity chooseAll();

    //模拟让服务器全不选
    ShoppingCartEntity chooseNone();

}
