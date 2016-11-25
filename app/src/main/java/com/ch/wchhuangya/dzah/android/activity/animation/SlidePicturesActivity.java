package com.ch.wchhuangya.dzah.android.activity.animation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewAnimator;

import com.ch.wchhuangya.dzah.android.BaseActivity;
import com.ch.wchhuangya.dzah.android.R;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 使用 ViewAnimator 滑动查看图片
 * Created by wchya on 2016-11-25 16:44
 */

public class SlidePicturesActivity extends BaseActivity {

    private ViewAnimator mViewAnimator;
    private Integer[] img_res_id = {R.mipmap.pet_pic_1,R.mipmap.pet_pic_2,R.mipmap.pet_pic_3,R.mipmap.pet_pic_4,R.mipmap.pet_pic_5};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slide_pictures);

        mViewAnimator = (ViewAnimator) findViewById(R.id.slide_pictures_viewanimator);
        mViewAnimator.setInAnimation(AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left));
        mViewAnimator.setOutAnimation(AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right));
        Observable.from(img_res_id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::addViewAndListener, this::printError, ()->{
                    findViewById(R.id.slide_pictures_pre_btn).setOnClickListener(view -> {
                        mViewAnimator.showPrevious();
                    });
                    findViewById(R.id.slide_pictures_next_btn).setOnClickListener(view -> {
                        mViewAnimator.showNext();
                    });
                });
    }

    private void addViewAndListener(Integer integer) {
        ImageView imageView = createImageView();
        imageView.setBackgroundResource(integer);
        mViewAnimator.addView(imageView);
    }

    private ImageView createImageView() {
        ImageView imageView = new ImageView(this);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(320, 480);
        imageView.setLayoutParams(params);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return imageView;
    }
}
