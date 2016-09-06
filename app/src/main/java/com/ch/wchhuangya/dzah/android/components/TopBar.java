package com.ch.wchhuangya.dzah.android.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ch.wchhuangya.dzah.android.R;
import com.ch.wchhuangya.dzah.android.util.LogHelper;

/**
 * 自定义顶部工具栏
 * Created by wchya on 16/9/3.
 */
public class TopBar extends LinearLayout {
    /*// 包含topbar上的元素：左按钮、右按钮、标题
    private Button mLeftButton, mRightButton;
    private TextView mTitleView;

    // 布局属性，用来控制组件元素在ViewGroup中的位置
    private RelativeLayout.LayoutParams mLeftParams, mTitlepParams, mRightParams;

    // 左按钮的属性值，即我们在atts.xml文件中定义的属性
    private int mLeftTextColor;
    private Drawable mLeftBackground;
    private String mLeftText;
    // 右按钮的属性值，即我们在atts.xml文件中定义的属性
    private int mRightTextColor;
    private Drawable mRightBackground;
    private String mRightText;
    // 标题的属性值，即我们在atts.xml文件中定义的属性
    private float mTitleTextSize;
    private int mTitleTextColor;
    private String mTitle;

    // 映射传入的接口对象
    private topbarClickListener mListener;

    public TopBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public TopBar(Context context) {
        super(context);
    }

    public TopBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 设置topbar的背景
        setBackgroundColor(0xFFF59563);
        // 通过这个方法，将你在atts.xml中定义的declare-styleable
        // 的所有属性的值存储到TypedArray中
        TypedArray ta = context.obtainStyledAttributes(attrs,
                R.styleable.TopBar);
        // 从TypedArray中取出对应的值来为要设置的属性赋值
        mLeftTextColor = ta.getColor(
                R.styleable.TopBar_leftTextColor, 0);
        mLeftBackground = ta.getDrawable(
                R.styleable.TopBar_leftBackground);
        mLeftText = ta.getString(R.styleable.TopBar_leftText);

        mRightTextColor = ta.getColor(
                R.styleable.TopBar_rightTextColor, 0);
        mRightBackground = ta.getDrawable(
                R.styleable.TopBar_rightBackground);
        mRightText = ta.getString(R.styleable.TopBar_rightText);

        mTitleTextSize = ta.getDimension(
                R.styleable.TopBar_titleTextSize, 10);
        mTitleTextColor = ta.getColor(
                R.styleable.TopBar_titleTextColor, 0);
        mTitle = ta.getString(R.styleable.TopBar_titleB);

        // 获取完TypedArray的值后，一般要调用
        // recyle方法来避免重新创建的时候的错误
        ta.recycle();

        mLeftButton = new Button(context);
        mRightButton = new Button(context);
        mTitleView = new TextView(context);

        // 为创建的组件元素赋值
        // 值就来源于我们在引用的xml文件中给对应属性的赋值
        mLeftButton.setTextColor(mLeftTextColor);
        mLeftButton.setBackground(mLeftBackground);
        mLeftButton.setText(mLeftText);

        mRightButton.setTextColor(mRightTextColor);
        mRightButton.setBackground(mRightBackground);
        mRightButton.setText(mRightText);

        mTitleView.setText(mTitle);
        mTitleView.setTextColor(mTitleTextColor);
        mTitleView.setTextSize(mTitleTextSize);
        mTitleView.setGravity(Gravity.CENTER);

        // 为组件元素设置相应的布局元素
        mLeftParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);
        mLeftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, TRUE);
        // 添加到ViewGroup
        addView(mLeftButton, mLeftParams);

        mRightParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);
        mRightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, TRUE);
        addView(mRightButton, mRightParams);

        mTitlepParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);
        mTitlepParams.addRule(RelativeLayout.CENTER_IN_PARENT, TRUE);
        addView(mTitleView, mTitlepParams);

        // 按钮的点击事件，不需要具体的实现，
        // 只需调用接口的方法，回调的时候，会有具体的实现
        mRightButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                mListener.rightClick();
            }
        });

        mLeftButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                mListener.leftClick();
            }
        });
    }

    // 暴露一个方法给调用者来注册接口回调
    // 通过接口来获得回调者对接口方法的实现
    public void setOnTopbarClickListener(topbarClickListener mListener) {
        this.mListener = mListener;
    }

    *//**
     * 设置按钮的显示与否 通过id区分按钮，flag区分是否显示
     *
     * @param id   id
     * @param flag 是否显示
     *//*
    public void setButtonVisable(int id, boolean flag) {
        if (flag) {
            if (id == 0) {
                mLeftButton.setVisibility(View.VISIBLE);
            } else {
                mRightButton.setVisibility(View.VISIBLE);
            }
        } else {
            if (id == 0) {
                mLeftButton.setVisibility(View.GONE);
            } else {
                mRightButton.setVisibility(View.GONE);
            }
        }
    }

    // 接口对象，实现回调机制，在回调方法中
    // 通过映射的接口对象调用接口中的方法
    // 而不用去考虑如何实现，具体的实现由调用者去创建
    public interface topbarClickListener {
        // 左按钮点击事件
        void leftClick();
        // 右按钮点击事件
        void rightClick();
    }*/
    private Drawable mLeftDa;
    private TextView mLeftTv;
    private String mLeftStr;
    private String mMidStr;
    private TextView mRightTv;
    private String mRightStr;
    private ImageView mRightIv;
    private boolean hasRightTv;
    private boolean hasRightIv;

    private ClickListener mClickListener;

    public TopBar(Context context) {
        super(context);
    }

    public TopBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public TopBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TopBar);

        mLeftStr = typedArray.getString(R.styleable.TopBar_leftText);
        mMidStr = typedArray.getString(R.styleable.TopBar_midText);
        hasRightIv = typedArray.getBoolean(R.styleable.TopBar_hasRightImage, false);
        hasRightTv = typedArray.getBoolean(R.styleable.TopBar_hasRightText, false);
        mRightStr = typedArray.getString(R.styleable.TopBar_rightText);

        typedArray.recycle();
        LogHelper.d(TopBar.class, mLeftStr + "|" + mMidStr + "|" + hasRightIv + "|" + hasRightTv + "|" + mRightStr);

        setBackgroundColor(0xffffffff);
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);

        ImageView leftIv = new ImageView(context);
        if (mLeftDa == null)
            leftIv.setBackgroundResource(R.mipmap.top_bar_back_btn);
        else
            leftIv.setImageDrawable(mLeftDa);
        LayoutParams params = new LayoutParams(20, 25);
        params.setMargins(15, 2, 15, 0);
        leftIv.setLayoutParams(params);
        addView(leftIv);

        TextView leftTv = new TextView(context);
        leftTv.setText(mLeftStr);
        LayoutParams params1 = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        leftTv.setLayoutParams(params1);
        addView(leftTv);

        TextView midTv = new TextView(context);
        midTv.setText(mMidStr);
        midTv.setSingleLine(true);
        midTv.setEllipsize(TextUtils.TruncateAt.MIDDLE);
        LayoutParams params2 = new LayoutParams(0, LayoutParams.WRAP_CONTENT, 1);
        midTv.setLayoutParams(params2);
        midTv.setGravity(Gravity.CENTER);
        addView(midTv);

        TextView rightTv = new TextView(context);
        rightTv.setText(mRightStr);
        rightTv.setVisibility(hasRightTv ? VISIBLE : INVISIBLE);
        LayoutParams params3 = new LayoutParams(hasRightTv ? LayoutParams.WRAP_CONTENT : 60, LayoutParams.WRAP_CONTENT);
        params3.setMargins(15, 2, 15, 0);
        rightTv.setLayoutParams(params3);
        addView(rightTv);

        ImageView rightIv = new ImageView(context);
        LayoutParams params4 = new LayoutParams(hasRightTv ? LayoutParams.WRAP_CONTENT : 60, LayoutParams.WRAP_CONTENT);
        rightIv.setLayoutParams(params4);
        if (!hasRightIv)
            rightIv.setVisibility(GONE);
        addView(rightIv);


        leftTv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mClickListener.leftClickListener();
            }
        });

        rightTv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mClickListener.rightClickListener();
            }
        });

        rightIv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mClickListener.rightBtnClickListener();
            }
        });
    }

    public interface ClickListener {
        void leftClickListener();
        void rightClickListener();
        void rightBtnClickListener();
    }

    public void setClickListener(ClickListener clickListener) {
        this.mClickListener = clickListener;
    }
}
