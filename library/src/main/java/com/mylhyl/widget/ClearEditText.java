package com.mylhyl.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.text.method.KeyListener;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;

/**
 * Created by hupei on 2017/5/16.
 */

public class ClearEditText extends RelativeLayout implements TextWatcher, View.OnClickListener,
        View.OnFocusChangeListener {

    private Context mContext;
    private AutoCompleteTextView mInputView;
    private ImageView mClearImage;
    private int inputType;
    private boolean mHasFocus;

    public ClearEditText(Context context) {
        this(context, null);
    }

    public ClearEditText(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ClearEditText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (!isInEditMode())
            init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        this.mContext = context;
        initView(attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ClearEditText);

        if (a.hasValue(R.styleable.ClearEditText_input_background)) {
            Drawable background = a.getDrawable(R.styleable.ClearEditText_input_background);
            setInputBackground(background);
        }

        if (a.hasValue(R.styleable.ClearEditText_input_paddingLeft)) {
            int paddingLeft = a.getDimensionPixelSize(
                    R.styleable.ClearEditText_input_paddingLeft, -1);
            setInputPaddingLeft(paddingLeft);
        }

        if (a.hasValue(R.styleable.ClearEditText_input_paddingRight)) {
            int paddingRight = a.getDimensionPixelSize(
                    R.styleable.ClearEditText_input_paddingRight, -1);
            setInputPaddingRight(paddingRight);
        }

        a.recycle();
    }


    private void initView(AttributeSet attrs) {
        //输入框
        mInputView = new AutoCompleteTextView(mContext, attrs);
        LayoutParams inputParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams
                .WRAP_CONTENT);
        inputParams.addRule(RelativeLayout.CENTER_VERTICAL);

        mInputView.setBackgroundColor(Color.TRANSPARENT);
        mInputView.addTextChangedListener(this);
        mInputView.setOnFocusChangeListener(this);

        addView(mInputView, inputParams);

        //清除图标
        mClearImage = new ImageView(mContext, attrs);
        LayoutParams clearParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams
                .WRAP_CONTENT);
        clearParams.addRule(RelativeLayout.CENTER_VERTICAL);
        clearParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

        mClearImage.setBackgroundColor(Color.TRANSPARENT);
        mClearImage.setVisibility(GONE);
        mClearImage.setOnClickListener(this);

        addView(mClearImage, clearParams);
    }

    /**
     * 设置输入框背景
     *
     * @param background
     */
    public void setInputBackground(Drawable background) {
        if (mInputView != null && background != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                mInputView.setBackground(background);
            } else {
                mInputView.setBackgroundDrawable(background);
            }
        }
    }


    public void setInputPaddingLeft(int paddingLeft) {
        if (mInputView != null) {
            int left = mInputView.getPaddingLeft();
            int top = mInputView.getPaddingTop();
            int right = mInputView.getPaddingRight();
            int bottom = mInputView.getPaddingBottom();
            if (left != paddingLeft) {
                left = paddingLeft;
            }
            mInputView.setPadding(left, top, right, bottom);
        }
    }

    public void setInputPaddingRight(int paddingRight) {
        if (mInputView != null) {
            int left = mInputView.getPaddingLeft();
            int top = mInputView.getPaddingTop();
            int right = mInputView.getPaddingRight();
            int bottom = mInputView.getPaddingBottom();
            if (right != paddingRight) {
                right = paddingRight;
            }
            mInputView.setPadding(left, top, right, bottom);
        }
    }

    /**
     * 设置输入框提示语
     *
     * @param hint
     */
    public void setInputHint(String hint) {
        if (mInputView != null) mInputView.setHint(hint);
    }

    /**
     * 设置输入框提示语字体颜色
     *
     * @param colors
     */
    public void setInputHintTextColor(ColorStateList colors) {
        if (mInputView != null) mInputView.setHintTextColor(colors);
    }

    /**
     * 设置输入框字体大小
     *
     * @param inputTextSize
     */
    public void setInputTextSize(float inputTextSize) {
        if (mInputView != null) mInputView.setTextSize(TypedValue.COMPLEX_UNIT_PX, inputTextSize);
    }

    /**
     * 设置输入框字体颜色
     *
     * @param colors
     */
    public void setInputTextColor(ColorStateList colors) {
        if (mInputView != null && colors != null) mInputView.setTextColor(colors);
    }

    /**
     * 设置输入框文本限制
     *
     * @param digits 如只能输入：0123456789qwertyuiopasdfghjklzxcvbnm
     */
    public void setInputDigits(String digits) {
        if (mInputView != null && !TextUtils.isEmpty(digits)) {
            KeyListener keyListener = DigitsKeyListener.getInstance(digits.toString());
            mInputView.setKeyListener(keyListener);
        }
    }

    public void setInputType(int type) {
        if (mInputView != null) mInputView.setInputType(type);
    }

    public void setClearSrc(Drawable drawable) {
        if (mClearImage != null && drawable != null) mClearImage.setImageDrawable(drawable);
    }

    public <T extends ListAdapter & Filterable> void setAdapter(T adapter) {
        mInputView.setAdapter(adapter);
    }

    public String getText() {
        return mInputView.getText().toString();
    }

    private void setClearImageVisible(boolean visible) {
        mClearImage.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (mHasFocus) setClearImageVisible(s.length() > 0);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        this.mHasFocus = hasFocus;
        setClearImageVisible(mHasFocus ? getText().length() > 0 : false);
    }

    @Override
    public void onClick(View v) {
        if (v == mClearImage) {
            mInputView.setText("");
        }
    }

}
