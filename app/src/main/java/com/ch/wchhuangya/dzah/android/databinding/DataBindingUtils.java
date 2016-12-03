package com.ch.wchhuangya.dzah.android.databinding;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by wchya on 2016-12-02 11:13
 */

public class DataBindingUtils {
    @BindingAdapter("bind:squareImg")
    public static void setSquareImg(ImageView imageView, String imgUrl) {
        ImageLoader.getInstance().displayImage(imgUrl, imageView);
    }
    @BindingAdapter("bind:localImg")
    public static void setLocalImg(ImageView imageView, int resId) {
        imageView.setBackgroundResource(resId);
    }
}
