![](https://i.imgur.com/r0CXBAV.gif)


# 简介
　　一个购物车模块的实现Demo，使用MVP架构，M层模拟从服务器获取和处理数据，P层模拟请求延时，View层展示数据，实现了下面的特点：

 - 选中列表项中的切换按钮，表示加入待购物。
 - 上面列表的切换按钮全部被选中时，下面的全选按钮也被选中。
 - 选中下面的全选按钮，列表项中切换按钮全部被选中；取消全选的时候，列表项中的按钮全部取消选择。
 - 左滑动出现删除选项，点击删除对应列表项
 - 列表为空的时候，下面的全选按钮不可选。
 - 最下方的价格合计为选中项目的价格的总和。

###[点击下载Demo](https://github.com/totond/ShoppingCartDemo/blob/master/apk/ShoppingCartDemo.apk)


## 接口简介
　　Demo采用MVP架构，把显示层和数据请求层分离出来，通过模拟延时可以更加清晰地看出这样的分离，把数据处理的逻辑放到服务器端（这里由M层模拟）。个人认为，这样做的话，如果发生请求错误，就可以不更新数据，或者直接进入错误提示界面，不会给用户错误的信息显示。

　　下面展示大概的接口，具体实现请看代码：
### View层

```
    interface ShoppingCartView extends BaseView {
        //绑定商品数据
        void bindData(ShoppingCartEntity entity);

        //刷新全部商品数据
        void updateData();

        //删除单个商品数据
        void deleteCommodity(int index);

        //刷新单个商品数据
        void updateCommodity(int index);

        //刷新底部显示数据
        void updateBottomUI(boolean isChosenAll, float totalPrice);

    }
```

### Presenter层

```
    interface IShoppingCarPresenter{
        //让M层获取数据,然后给View层绑定
        void bindData();

        //让M层删除数据,通知View层更新
        void deleteCommodity(int index);

        //让M层改变数据,通知View层更新
        void changeData(int index);

        //让M层设置数据全选或者全不选,通知View层更新
        void chooseAllOrNone(boolean choose);
    }
```

### Model层

```
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

```

## 第三方库使用
 > 具体使用和修改请看注释。

 - [Rxjava](https://github.com/ReactiveX/RxJava)
 - [ButterKnife](https://github.com/JakeWharton/butterknife)
 - [SwipeMenuRecyclerView](https://github.com/AItsuki/SwipeMenuRecyclerView)
 - [SwitchView](https://github.com/iielse/SwitchButton)
 - [EmptyLayout](https://github.com/dangxy/WanAndroid/blob/master/app/src/main/java/com/dangxy/wanandroid/base/EmptyLayout.java)
 - [LoadingView](https://github.com/szhangbiao/EmptyLayout/blob/master/library/src/main/java/com/custom/emptylayout/LoadingView.java)