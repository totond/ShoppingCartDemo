package yanzhikai.shoppingcartdemo.shoppingcart;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import yanzhikai.shoppingcartdemo.R;
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
    private ArrayList<ShoppingCartEntity.CommodityEntity> mData;

    private static final int ITEM_TYPE_NONE = 0;
    private static final int ITEM_TYPE_CONTENT = 1;
    public int color1 = Color.WHITE, color2 = Color.WHITE;

    public ShoppingCartAdapter(Context context, @NonNull ShoppingCartEntity entity) {
        super();
        mData = entity.getCommodities();
        color1 = context.getResources().getColor(R.color.colorCartItemBackground1);
        color2 = context.getResources().getColor(R.color.colorCartItemBackground2);
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
        if (holder instanceof CartItemViewHolder) {

            CartItemViewHolder cartItemViewHolder = (CartItemViewHolder) holder;
            if (position % 2 != 0) {
                holder.itemView.setBackgroundColor(color1);
            } else {
                cartItemViewHolder.itemView.setBackgroundColor(color2);
            }
            cartItemViewHolder.itemView.setTag(position);
            cartItemViewHolder.sw_item.setTag(position);
            cartItemViewHolder.right_menu.setTag(position);

            cartItemViewHolder.tv_commodity_name.setText(mData.get(position).getName());
            cartItemViewHolder.tv_price_number.setText(String.valueOf(mData.get(position).getPrice()));

            cartItemViewHolder.sw_item.setOpened(mData.get(position).isChosen());
            cartItemViewHolder.sw_item.setOnStateChangedListener(new SwitchView.OnStateChangedListener() {
                @Override
                public void toggleToOn(SwitchView view) {
                    if (mDataChangedListener != null) {
                        mDataChangedListener.onChosenChanged((Integer) view.getTag());
                    }
                }

                @Override
                public void toggleToOff(SwitchView view) {
                    if (mDataChangedListener != null) {
                        mDataChangedListener.onChosenChanged((Integer) view.getTag());
                    }
                }
            });

            cartItemViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mClickListener != null) {
                        mClickListener.onItemClick((Integer) v.getTag());
                    }
                }
            });
            cartItemViewHolder.right_menu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
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
        int size = mData.size();
        if (size > 0) {
            //只在非空的第一次回调
            if (isDataNull && mDataChangedListener != null) {
                mDataChangedListener.onDataIsNull(false);
            }
            isDataNull = false;
        } else {
            isDataNull = true;
            size = 1;
            if (mDataChangedListener != null) {
                mDataChangedListener.onDataIsNull(true);
            }
        }
        return size;
    }

    @Override
    public int getItemViewType(int position) {
        if (mData.size() <= 0) {
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


    public interface OnItemClickListener {
        void onItemClick(int position);

        void onRightMenuClick(int position);
    }

    public interface OnDataChangedListener {
        void onChosenChanged(int index);

        void onDataIsNull(boolean isNull);
    }
}
