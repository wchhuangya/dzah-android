package com.ch.wchhuangya.dzah.android.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ch.wchhuangya.dzah.android.BR;
import com.ch.wchhuangya.dzah.android.R;
import com.ch.wchhuangya.dzah.android.model.Contact;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wchya on 2016-11-23 21:51
 */

public class ContactAdapter extends RecyclerView.Adapter<BindingViewHolder> {

    private List<Contact> mData = new ArrayList<>();

    public void setData(List<Contact> data, int start, int count) {
        mData.addAll(start, data);
        notifyItemRangeInserted(start, count);
    }

    @Override
    public BindingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BindingViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.recycler_contact_item, parent, false), null);
    }

    @Override
    public void onBindViewHolder(BindingViewHolder holder, int position) {
        Contact contact = mData.get(position);
        holder.getBinding().setVariable(BR.contact, contact);
        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
