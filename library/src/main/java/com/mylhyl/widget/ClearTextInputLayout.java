package com.mylhyl.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by hupei on 2017/5/19.
 */

public final class ClearTextInputLayout extends AbsClear {

    private TextInputLayout mTextInputLayout;

    public ClearTextInputLayout(Context context) {
        super(context);
    }

    public ClearTextInputLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ClearTextInputLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    void initView(AttributeSet attrs) {
        super.initView(attrs);
        mTextInputLayout = new TextInputLayout(mContext, attrs);
        mTextInputLayout.setHint("");
    }

    @Override
    protected void configInputParams() {
        LayoutParams textInputParams = new LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        textInputParams.addRule(RelativeLayout.CENTER_VERTICAL);

        mTextInputLayout.setLayoutParams(textInputParams);

        mInputView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
    }

    @Override
    protected void addViews() {
        addView(mTextInputLayout);
        mTextInputLayout.addView(mInputView);
        addView(mClearImage);
    }

    public final TextInputLayout getTextInputLayout() {
        return mTextInputLayout;
    }
}
