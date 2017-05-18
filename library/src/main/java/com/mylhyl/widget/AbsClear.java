package com.mylhyl.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.text.method.KeyListener;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;

/**
 * Created by hupei on 2017/5/16.
 */
abstract class AbsClear extends RelativeLayout implements TextWatcher, View.OnClickListener,
        View.OnFocusChangeListener {

    Context mContext;
    AutoCompleteTextView mInputView;
    ImageView mClearImage;

    private boolean mHasFocus;

    public AbsClear(Context context) {
        this(context, null);
    }

    public AbsClear(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AbsClear(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (!isInEditMode())
            init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        this.mContext = context;

        initView(attrs);
        configInputParams();
        initClearParams();
        addViews();

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

        if (a.hasValue(R.styleable.ClearEditText_input_marginLeft)) {
            int marginLeft = a.getDimensionPixelSize(
                    R.styleable.ClearEditText_input_marginLeft, -1);
            setInputMarginLeft(marginLeft);
        }

        if (a.hasValue(R.styleable.ClearEditText_input_marginRight)) {
            int marginRight = a.getDimensionPixelSize(
                    R.styleable.ClearEditText_input_marginRight, -1);
            setInputMarginRight(marginRight);
        }

        a.recycle();
    }

    protected abstract void configInputParams();

    protected abstract void addViews();

    void initView(AttributeSet attrs) {
        //输入框
        mInputView = new AutoCompleteTextView(mContext, attrs);

        mInputView.addTextChangedListener(this);
        mInputView.setOnFocusChangeListener(this);


        //清除图标
        mClearImage = new ImageView(mContext, attrs);

        mClearImage.setBackgroundColor(Color.TRANSPARENT);
        mClearImage.setVisibility(GONE);
        mClearImage.setOnClickListener(this);

    }

    private void initClearParams() {
        LayoutParams clearParams = new LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        clearParams.addRule(RelativeLayout.CENTER_VERTICAL);
        clearParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

        mClearImage.setLayoutParams(clearParams);
    }

    /**
     * 设置输入框背景
     *
     * @param background
     */
    public final void setInputBackground(Drawable background) {
        if (mInputView != null && background != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                mInputView.setBackground(background);
            } else {
                mInputView.setBackgroundDrawable(background);
            }
        }
    }


    public final void setInputPaddingLeft(int paddingLeft) {
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

    public final void setInputPaddingRight(int paddingRight) {
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

    public final void setInputMarginLeft(int marginLeft) {
        if (mInputView != null) {
            ViewGroup.LayoutParams layoutParams = mInputView.getLayoutParams();
            if (layoutParams instanceof RelativeLayout.LayoutParams) {
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) layoutParams;
                params.leftMargin = marginLeft;
                mInputView.setLayoutParams(params);
            } else if (layoutParams instanceof LinearLayout.LayoutParams) {
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) layoutParams;
                params.leftMargin = marginLeft;
                mInputView.setLayoutParams(params);
            }
        }
    }

    public final void setInputMarginRight(int marginRight) {
        if (mInputView != null) {
            ViewGroup.LayoutParams layoutParams = mInputView.getLayoutParams();
            if (layoutParams instanceof RelativeLayout.LayoutParams) {
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) layoutParams;
                params.rightMargin = marginRight;
                mInputView.setLayoutParams(params);
            } else if (layoutParams instanceof LinearLayout.LayoutParams) {
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) layoutParams;
                params.rightMargin = marginRight;
                mInputView.setLayoutParams(params);
            }
        }
    }

    /**
     * 设置输入框提示语
     *
     * @param hint
     */
    public final void setInputHint(String hint) {
        if (mInputView != null) mInputView.setHint(hint);
    }

    /**
     * 设置输入框提示语字体颜色
     *
     * @param colors
     */
    public final void setInputHintTextColor(ColorStateList colors) {
        if (mInputView != null) mInputView.setHintTextColor(colors);
    }

    /**
     * 设置输入框字体大小
     *
     * @param inputTextSize
     */
    public final void setInputTextSize(float inputTextSize) {
        if (mInputView != null) mInputView.setTextSize(TypedValue.COMPLEX_UNIT_PX, inputTextSize);
    }

    /**
     * 设置输入框字体颜色
     *
     * @param colors
     */
    public final void setInputTextColor(ColorStateList colors) {
        if (mInputView != null && colors != null) mInputView.setTextColor(colors);
    }

    /**
     * 设置输入框文本限制
     *
     * @param digits 如只能输入：0123456789qwertyuiopasdfghjklzxcvbnm
     */
    public final void setInputDigits(String digits) {
        if (mInputView != null && !TextUtils.isEmpty(digits)) {
            KeyListener keyListener = DigitsKeyListener.getInstance(digits.toString());
            mInputView.setKeyListener(keyListener);
        }
    }

    public final void setInputType(int type) {
        if (mInputView != null) mInputView.setInputType(type);
    }

    public final void setClearSrc(Drawable drawable) {
        if (mClearImage != null && drawable != null) mClearImage.setImageDrawable(drawable);
    }

    public final <T extends ListAdapter & Filterable> void setAdapter(T adapter) {
        mInputView.setAdapter(adapter);
    }

    public final String getInputText() {
        return mInputView.getText().toString();
    }

    public final AutoCompleteTextView getInputView() {
        return mInputView;
    }

    public final ImageView getClearView() {
        return mClearImage;
    }

    private void setClearImageVisible(boolean visible) {
        mClearImage.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    @Override
    public final void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public final void onTextChanged(CharSequence s, int start, int before, int count) {
        if (mHasFocus) setClearImageVisible(s.length() > 0);
    }

    @Override
    public final void afterTextChanged(Editable s) {

    }

    @Override
    public final void onFocusChange(View v, boolean hasFocus) {
        this.mHasFocus = hasFocus;
        setClearImageVisible(mHasFocus ? getInputText().length() > 0 : false);
    }

    @Override
    public final void onClick(View v) {
        if (v == mClearImage) {
            mInputView.setText("");
        }
    }

}