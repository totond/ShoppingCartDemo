package yanzhikai.shoppingcartdemo;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import yanzhikai.shoppingcartdemo.widget.SwitchView;

/**
 * author : yany
 * e-mail : yanzhikai_yjk@qq.com
 * time   : 2018/04/09
 * desc   :
 */

public class ShoppingCartAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final String TAG = "ShoppingCartAdapter";

    private OnItemClickListener mClickListener;
    private OnDataChangedListener mDataChangedListener;

    private boolean isDataNull = false;

    @NonNull
    private ShoppingCartEntity mData;

    private static final int ITEM_TYPE_NONE = 0;
    private static final int ITEM_TYPE_CONTENT = 1;

    public ShoppingCartAdapter(@NonNull ShoppingCartEntity entity) {
        super();
        mData = entity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE_NONE) {
            return new NoContentViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart_no_content, parent, false));
        } else {
            return new CartItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_right_swipe_cart, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: " + position);
        if (holder instanceof CartItemViewHolder) {
            CartItemViewHolder cartItemViewHolder = (CartItemViewHolder) holder;
            cartItemViewHolder.itemView.setTag(position);
            cartItemViewHolder.sw_item.setTag(position);
            cartItemViewHolder.right_menu.setTag(position);

            cartItemViewHolder.tv_commodity_name.setText(mData.getCommodities().get(position).getName());
            cartItemViewHolder.tv_price_number.setText(String.valueOf(mData.getCommodities().get(position).getPrice()));

            cartItemViewHolder.sw_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "onClick: sw_item");
                    ShoppingCartEntity.CommodityEntity commodityEntity = mData.getCommodities().get((Integer) v.getTag());
                    commodityEntity.setChosen(!commodityEntity.isChosen());
                    if (mDataChangedListener != null){
                        mDataChangedListener.onChosenChanged();
                    }
                }
            });
            cartItemViewHolder.sw_item.setOpened(mData.getCommodities().get(position).isChosen());
            cartItemViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        Log.d(TAG, "onItemClick: ");
                    if (mClickListener != null) {
                        mClickListener.onItemClick((Integer) v.getTag());
                    }
                }
            });
            cartItemViewHolder.right_menu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        Log.d(TAG, "onRightMenuClick: " + mClickListener);
                    if (mClickListener != null) {
                        mClickListener.onRightMenuClick((Integer) v.getTag());
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        //支持数据为空时显示空Item
        int size = mData.getCommodities().size();
        if (size > 0){
            //只在非空的第一次回调
            if (isDataNull && mDataChangedListener != null){
                mDataChangedListener.onDataIsNull(false);
            }
            isDataNull = false;
        }else {
            isDataNull = true;
            size = 1;
            if (mDataChangedListener != null){
                mDataChangedListener.onDataIsNull(true);
            }
        }
        return size;
    }

    @Override
    public int getItemViewType(int position) {
        if (mData.getCommodities().size() <= 0) {
            return ITEM_TYPE_NONE;
        } else {
            return ITEM_TYPE_CONTENT;
        }
    }

    public void setOnItemClickListener(OnItemClickListener clickListener) {
        this.mClickListener = clickListener;
    }

    public void setOnDataChangedListener(OnDataChangedListener dataChangedListener) {
        this.mDataChangedListener = dataChangedListener;
    }

    public class CartItemViewHolder extends RecyclerView.ViewHolder {
        SwitchView sw_item;
        TextView tv_commodity_name, tv_price_number;
        View right_menu;

        public CartItemViewHolder(View itemView) {
            super(itemView);
            sw_item = itemView.findViewById(R.id.sw_item);
            tv_commodity_name = itemView.findViewById(R.id.tv_commodity_name);
            tv_price_number = itemView.findViewById(R.id.tv_price_number);
            right_menu = itemView.findViewById(R.id.right_menu);
        }
    }

    public class NoContentViewHolder extends RecyclerView.ViewHolder {

        public NoContentViewHolder(View itemView) {
            super(itemView);
        }
    }

    /**
     * author : yany
     * e-mail : yanzhikai_yjk@qq.com
     * time   : 2018/04/09
     * desc   :
     */

    public interface OnItemClickListener {
        void onItemClick(int position);

        void onRightMenuClick(int position);
    }

    public interface OnDataChangedListener {
        void onChosenChanged();

        void onDataIsNull(boolean isNull);
    }
}
