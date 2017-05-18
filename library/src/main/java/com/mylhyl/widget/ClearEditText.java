package com.mylhyl.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by hupei on 2017/5/16.
 */

public final class ClearEditText extends AbsClear {

    public ClearEditText(Context context) {
        this(context, null);
    }

    public ClearEditText(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ClearEditText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void configInputParams() {
        RelativeLayout.LayoutParams inputParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        inputParams.setMargins(0, 0, 0, 0);
        inputParams.addRule(RelativeLayout.CENTER_VERTICAL);
        mInputView.setLayoutParams(inputParams);
    }

    @Override
    protected void addViews() {
        addView(mInputView);

        addView(mClearImage);
    }

    public void addRuleInputView(int verb) {
        addRuleInputView(verb, TRUE);
    }

    @Override
    protected void addRuleInputView(int verb, int subject) {
        if (mInputView != null) {
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mInputView
                    .getLayoutParams();
            params.addRule(verb, subject);
            mInputView.setLayoutParams(params);
        }
    }
}
