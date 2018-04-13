package yanzhikai.shoppingcartdemo.shoppingcart;


import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.TextView;

import com.aitsuki.swipe.SwipeMenuRecyclerView;

import butterknife.BindView;
import yanzhikai.shoppingcartdemo.util.NumberUtil;
import yanzhikai.shoppingcartdemo.R;
import yanzhikai.shoppingcartdemo.base.BaseFragment;
import yanzhikai.shoppingcartdemo.widget.SwitchView;

/**
 * author : yany
 * e-mail : yanzhikai_yjk@qq.com
 * time   : 2018/04/09
 * desc   : View层实现的Fragment，与用户交互界面，使用RecyclerView展示数据
 */
public class ShoppingCartFragment extends BaseFragment implements ShoppingCartContract.ShoppingCartView {
    public static final String TAG = "ShoppingCartFragment";
//    public static final String DATA_LIST = "dataList";

    @BindView(R.id.rv_shopping_cart)
    SwipeMenuRecyclerView rvShoppingCart;
    @BindView(R.id.sw_choose_all)
    SwitchView swChooseAll;
    @BindView(R.id.tv_total_price)
    TextView tvTotalPrice;


    private ShoppingCartPresenterImpl mShoppingCartPresenterImpl;
    private ShoppingCartAdapter mShoppingCartAdapter;

    public ShoppingCartFragment() {

    }

    //预加载参数可使用
//    public static ShoppingCartFragment newInstance(ArrayList<CommodityEntity> param1) {
//        ShoppingCartFragment fragment = new ShoppingCartFragment();
//        Bundle args = new Bundle();
//        args.putParcelableArrayList(DATA_LIST, (ArrayList<? extends Parcelable>) param1);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    protected void loadData() {
        mShoppingCartPresenterImpl = new ShoppingCartPresenterImpl(this);
        mShoppingCartPresenterImpl.bindData();
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_shopping_cart;
    }

    @Override
    protected void initViews() {
        rvShoppingCart.setLayoutManager(new LinearLayoutManager(getContext()));
        //设置分界线
        DividerItemDecoration itemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        rvShoppingCart.addItemDecoration(itemDecoration);

        swChooseAll.setOnStateChangedListener(new SwitchView.OnStateChangedListener() {
            @Override
            public void toggleToOn(SwitchView view) {
                mShoppingCartPresenterImpl.chooseAllOrNone(true);
            }

            @Override
            public void toggleToOff(SwitchView view) {
                mShoppingCartPresenterImpl.chooseAllOrNone(false);
            }
        });
    }

    @Override
    public void bindData(ShoppingCartEntity entity) {
        mShoppingCartAdapter = new ShoppingCartAdapter(getActivity(),entity);
        mShoppingCartAdapter.setOnItemClickListener(new ShoppingCartAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }

            @Override
            public void onRightMenuClick(int position) {
                mShoppingCartPresenterImpl.deleteCommodity(position);
            }
        });

        mShoppingCartAdapter.setOnDataChangedListener(new ShoppingCartAdapter.OnDataChangedListener() {
            @Override
            public void onChosenChanged(int index) {
                mShoppingCartPresenterImpl.changeData(index);
            }

            @Override
            public void onDataIsNull(boolean isNull) {
                swChooseAll.setOpened(false);
                swChooseAll.setCanOpen(!isNull);
            }
        });
        rvShoppingCart.setAdapter(mShoppingCartAdapter);
        updateBottomUI(entity.isIsChosenAll(), entity.getTotalPrice());
    }

    @Override
    public void updateData() {
        mShoppingCartAdapter.notifyDataSetChanged();
    }

    @Override
    public void deleteCommodity(int index) {
        mShoppingCartAdapter.notifyItemRemoved(index);
        mShoppingCartAdapter.notifyItemRangeChanged(index, mShoppingCartAdapter.getItemCount() - index);
    }

    @Override
    public void updateCommodity(int index) {
        mShoppingCartAdapter.notifyItemChanged(index);
    }


    @Override
    public void updateBottomUI(boolean isChosenAll, float totalPrice) {
        swChooseAll.toggleSwitch(isChosenAll);
        tvTotalPrice.setText(String.format(getString(R.string.shopping_cart_price_total), NumberUtil.floatToStringWith1Bit(totalPrice)));
    }


}
