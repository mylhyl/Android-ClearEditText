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
        super(context);
    }

    public ClearEditText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ClearEditText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void configInputParams() {

        LayoutParams inputParams = new LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        inputParams.addRule(RelativeLayout.CENTER_VERTICAL);
        mInputView.setLayoutParams(inputParams);
    }

    @Override
    protected void addViews() {
        addView(mInputView);

        addView(mClearImage);
    }
}
