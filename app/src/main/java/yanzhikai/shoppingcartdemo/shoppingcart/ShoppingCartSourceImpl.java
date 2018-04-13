package yanzhikai.shoppingcartdemo.shoppingcart;

import java.util.ArrayList;


/**
 * author : yany
 * e-mail : yanzhikai_yjk@qq.com
 * time   : 2018/04/09
 * desc   :
 */

public class ShoppingCartSourceImpl implements ShoppingCartDataSource {
    public static final String TAG = "Shopping";

    private ShoppingCartEntity mData = new ShoppingCartEntity();


    @Override
    public ShoppingCartEntity getDataFromRemote() {
        ArrayList<ShoppingCartEntity.CommodityEntity> commodityEntities = new ArrayList<>();
        commodityEntities.add(new ShoppingCartEntity.CommodityEntity("数据结构", 30.6f, false));
        commodityEntities.add(new ShoppingCartEntity.CommodityEntity("C++程序基础", 35.0f, true));
        commodityEntities.add(new ShoppingCartEntity.CommodityEntity("数据结构", 30.6f, false));
        commodityEntities.add(new ShoppingCartEntity.CommodityEntity("高等数学", 30.6f, true));
        commodityEntities.add(new ShoppingCartEntity.CommodityEntity("大学语文", 35.0f, false));
        commodityEntities.add(new ShoppingCartEntity.CommodityEntity("大学英语课程", 35.0f, true));
        commodityEntities.add(new ShoppingCartEntity.CommodityEntity("数据库技术", 30.6f, false));
        commodityEntities.add(new ShoppingCartEntity.CommodityEntity("设计模式", 35.0f, true));
        mData.setCommodities(commodityEntities);
        return handleDataChanged();
    }

    @Override
    public ShoppingCartEntity deleteCommodity(int index) {
        mData.getCommodities().remove(index);
        return handleDataChanged();
    }

    @Override
    public ShoppingCartEntity handleDataChanged(int index) {
        ShoppingCartEntity.CommodityEntity commodityEntity = mData.getCommodities().get(index);
        commodityEntity.setChosen(!commodityEntity.isChosen());
        return handleDataChanged();
    }

    @Override
    public ShoppingCartEntity handleDataChanged() {
        ArrayList<ShoppingCartEntity.CommodityEntity> commodityEntities = mData.getCommodities();
        float totalPrice = 0;
        boolean chosenAll = true;
        for (ShoppingCartEntity.CommodityEntity commodityEntity : commodityEntities) {
            if (commodityEntity.isChosen()) {
                totalPrice += commodityEntity.getPrice();
            }
            chosenAll &= commodityEntity.isChosen();
        }
        mData.setTotalPrice(totalPrice);
        mData.setIsChosenAll(chosenAll);
        return mData;
    }

    @Override
    public ShoppingCartEntity chooseAll() {
        ArrayList<ShoppingCartEntity.CommodityEntity> commodityEntities = mData.getCommodities();
        float totalPrice = 0;
        for (ShoppingCartEntity.CommodityEntity commodityEntity : commodityEntities) {
            commodityEntity.setChosen(true);
            totalPrice += commodityEntity.getPrice();
        }
        mData.setTotalPrice(totalPrice);
        mData.setIsChosenAll(true);
        return mData;
    }

    @Override
    public ShoppingCartEntity chooseNone() {
        ArrayList<ShoppingCartEntity.CommodityEntity> commodityEntities = mData.getCommodities();
        for (ShoppingCartEntity.CommodityEntity commodityEntity : commodityEntities) {
            commodityEntity.setChosen(false);
        }
        mData.setTotalPrice(0);
        mData.setIsChosenAll(false);
        return mData;
    }

}
