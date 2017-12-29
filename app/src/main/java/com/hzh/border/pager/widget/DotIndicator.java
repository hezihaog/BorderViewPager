package com.hzh.border.pager.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.hzh.border.pager.R;

/**
 * Package: com.hzh.borderviewpager.widget
 * FileName: DotIndicator
 * Date: on 2017/12/29  下午2:57
 * Auther: zihe
 * Descirbe:
 * Email: hezihao@linghit.com
 */

public class DotIndicator extends LinearLayout {
    private int mMaxCount;

    public DotIndicator(Context context, int maxCount) {
        super(context);
        init(maxCount);
    }

    public DotIndicator(Context context, @Nullable AttributeSet attrs, int maxCount) {
        super(context, attrs);
        init(maxCount);
    }

    public DotIndicator(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int maxCount) {
        super(context, attrs, defStyleAttr);
        init(maxCount);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        setGravity(Gravity.CENTER);
    }

    private void init(int maxCount) {
        this.mMaxCount = maxCount;
        for (int i = 0; i < maxCount; i++) {
            ImageView dotIv = new ImageView(getContext());
            int marginRight = 0;
            LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            //默认第一个check
            if (i == 0) {
                dotIv.setBackground(getContext().getResources().getDrawable(R.drawable.ic_dot_check_shape));
            } else {
                dotIv.setBackground(getContext().getResources().getDrawable(R.drawable.ic_dot_nomal_shape));
            }
            //最后一个不需要
            if (i != maxCount - 1) {
                marginRight = dip2px(getContext(), 15);
            }
            params.setMargins(0, 0, marginRight, 0);
            addView(dotIv, params);
        }
    }

    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static int px2dp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 选择位置
     */
    public void checkCurrentPosition(int position) {
        int childCount = getChildCount();
        Drawable momalDrawable = getContext().getResources().getDrawable(R.drawable.ic_dot_nomal_shape);
        for (int i = 0; i < childCount; i++) {
            getChildAt(i).setBackground(momalDrawable);
        }
        getChildAt(position).setBackground(getContext().getResources().getDrawable(R.drawable.ic_dot_check_shape));
    }
}