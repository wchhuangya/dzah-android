package com.ch.wchhuangya.dzah.android.adapter;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

/**
 * Created by wchya on 2016-11-23 21:51
 */

public class BindingViewHolder extends RecyclerView.ViewHolder {

    private ViewDataBinding mBinding;

    public BindingViewHolder(ViewDataBinding binding, Object object) {
        super(binding.getRoot());

        mBinding = binding;
        mBinding.getRoot().setTag(object);
    }

    public ViewDataBinding getBinding() {
        return mBinding;
    }
}
