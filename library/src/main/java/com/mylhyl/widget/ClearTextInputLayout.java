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
        this(context, null);
    }

    public ClearTextInputLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
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
        RelativeLayout.LayoutParams textInputParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        textInputParams.addRule(RelativeLayout.CENTER_VERTICAL);
        textInputParams.setMargins(0, 0, 0, 0);

        mTextInputLayout.setLayoutParams(textInputParams);

        LinearLayout.LayoutParams inputParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        inputParams.setMargins(0, 0, 0, 0);
        mInputView.setLayoutParams(inputParams);
    }

    @Override
    protected void addViews() {
        addView(mTextInputLayout);
        mTextInputLayout.addView(mInputView);
        addView(mClearImage);
    }

    @Override
    protected void addRuleInputView(int verb, int subject) {
        if (mTextInputLayout != null) {
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mInputView
                    .getLayoutParams();
            params.addRule(verb, subject);
            mTextInputLayout.setLayoutParams(params);
        }
    }

    public final TextInputLayout getTextInputLayout() {
        return mTextInputLayout;
    }
}
