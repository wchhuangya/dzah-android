package com.ch.wchhuangya.dzah.android.activity.customview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.ch.wchhuangya.dzah.android.BaseActivity;
import com.ch.wchhuangya.dzah.android.R;

/**
 * 自定义视图测量模式
 * Created by wchya on 16/8/29.
 */
public class MeasureModelActivity extends BaseActivity {
    /** 单选按钮组 */
    private RadioGroup mRadioGroup;
    /** EXACTLY--精确模式单选按钮 **/
    private RadioButton mExactlyRb;
    /** EXACTLY--match_parent 单选按钮 **/
    private RadioButton mMatchparentRb;
    /** AT_MOST模式　**/
    private RadioButton mAtmostRb;
    /** 显示提示信息的文本 */
    private TextView mTv;
    /** 走一个按钮 */
    private Button mBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.measure_model);

        activity = this;

        init();
    }

    private void init() {
        mRadioGroup = (RadioGroup) findViewById(R.id.measure_model_rg);
        mTv = (TextView) findViewById(R.id.measure_model_tv);
        mBtn = (Button) findViewById(R.id.measure_model_btn);

        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.measure_model_exactly_rb:
                        mTv.setText("该模式下,控件被设置了精确的宽或高。");
                        break;
                    case R.id.measure_model_match_parent_rb:
                        mTv.setText("该模式下,控件的宽和高都被设置为:match_parent。");
                        break;
                    case R.id.measure_model_atmost_rb:
                        mTv.setText("该模式下,控件的宽和高都被设置为:wrap_content。");
                        break;
                }
            }
        });

        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mRadioGroup.getCheckedRadioButtonId() == -1) {
                    showToast("请选择要查看的模式!");
                } else {
                    if (mRadioGroup.getCheckedRadioButtonId() == R.id.measure_model_exactly_rb)
                        intent = new Intent(activity, MeasureModelExactlyExactlyActivity.class);
                    else if (mRadioGroup.getCheckedRadioButtonId() == R.id.measure_model_match_parent_rb)
                        intent = new Intent(activity, MeasureModelExactlyMathparentActivity.class);
                    else if (mRadioGroup.getCheckedRadioButtonId() == R.id.measure_model_atmost_rb)
                        intent = new Intent(activity, MeasureModelAtmostActivity.class);

                    startActivity(intent);
                }
            }
        });
    }
}
