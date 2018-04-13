package yanzhikai.shoppingcartdemo.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import yanzhikai.shoppingcartdemo.R;
import yanzhikai.shoppingcartdemo.widget.EmptyLayout;

/**
 * author : yany
 * e-mail : yanzhikai_yjk@qq.com
 * time   : 2018/04/09
 * desc   : Fragment基类，目前主要是绑定各种状态的View，如加载中，网络错误等
 */

public abstract class BaseFragment extends Fragment implements BaseView {

    @BindView(R.id.empty_layout)
    EmptyLayout mEmptyLayout;

    Unbinder unbinder;
    protected View mRootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(attachLayoutRes(), container, false);
        unbinder = ButterKnife.bind(this, mRootView);
        initViews();
        loadData();
        return mRootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showLoading() {
        if (mEmptyLayout != null) {
            mEmptyLayout.setEmptyStatus(EmptyLayout.STATUS_LOADING);
        }
    }

    @Override
    public void hideLoading() {
        if (mEmptyLayout != null) {
            mEmptyLayout.hide();
        }
    }

    @Override
    public void showNetError(EmptyLayout.OnRetryListener onRetryListener) {
        if (mEmptyLayout != null) {
            mEmptyLayout.setEmptyStatus(EmptyLayout.STATUS_NO_NET);
            mEmptyLayout.setRetryListener(onRetryListener);
        }
    }


    /**
     * 加载数据
     */
    protected abstract void loadData();

    /**
     * 绑定布局文件
     * @return 布局文件ID
     */
    protected abstract int attachLayoutRes();

    /**
     * 初始化视图控件
     */
    protected abstract void initViews();

}
