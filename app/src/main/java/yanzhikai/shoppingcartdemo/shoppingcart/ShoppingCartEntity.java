package yanzhikai.shoppingcartdemo.shoppingcart;

import java.util.ArrayList;

/**
 * author : yany
 * e-mail : yanzhikai_yjk@qq.com
 * time   : 2018/04/10
 * desc   : 购物车数据实体类，这里模拟成json实体
 */

public class ShoppingCartEntity {

    /**
     * isChosenAll : false
     * totalPrice : 120.5
     * commodities : [{"name":"数据结构","price":"35.0","chosen":"true"},{"name":"数据结构","price":"35.0","chosen":"true"}]
     */

    private boolean isChosenAll;
    private float totalPrice;
    private ArrayList<CommodityEntity> commodities;

    public boolean isIsChosenAll() {
        return isChosenAll;
    }

    public void setIsChosenAll(boolean isChosenAll) {
        this.isChosenAll = isChosenAll;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public ArrayList<CommodityEntity> getCommodities() {
        return commodities;
    }

    public void setCommodities(ArrayList<CommodityEntity> commodities) {
        this.commodities = commodities;
    }

    public static class CommodityEntity {
        /**
         * name : 数据结构
         * price : 35.0
         * chosen : true
         */

        private String name;
        private float price;
        private boolean chosen;

        public CommodityEntity(String name, float price, boolean chosen) {
            super();
            this.name = name;
            this.price = price;
            this.chosen = chosen;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public float getPrice() {
            return price;
        }

        public void setPrice(float price) {
            this.price = price;
        }

        public boolean isChosen() {
            return chosen;
        }

        public void setChosen(boolean chosen) {
            this.chosen = chosen;
        }
    }
}
