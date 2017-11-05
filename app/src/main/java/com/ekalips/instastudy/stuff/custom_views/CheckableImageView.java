package com.ekalips.instastudy.stuff.custom_views;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.widget.Checkable;

/**
 * Created by Ekalips on 10/13/17.
 */

public class CheckableImageView extends AppCompatImageView implements Checkable {

    private boolean mChecked;

    private static final int[] CHECKED_STATE_SET = {android.R.attr.state_checked};

    public CheckableImageView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context, attrs);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        if (attrs != null) {

            int[] attrsArray = new int[]{
                    android.R.attr.checked,
            };

            TypedArray attributeArray = context.obtainStyledAttributes(
                    attrs,
                    attrsArray);

            boolean isChecked = attributeArray.getBoolean(0, false);
            setChecked(isChecked);
            attributeArray.recycle();
        }
    }

    @Override
    public int[] onCreateDrawableState(final int extraSpace) {
        final int[] drawableState = super.onCreateDrawableState(extraSpace + 1);
        if (isChecked())
            mergeDrawableStates(drawableState, CHECKED_STATE_SET);
        return drawableState;
    }

    @Override
    public void toggle() {
        setChecked(!mChecked);
    }

    @Override
    public boolean isChecked() {
        return mChecked;
    }

    @Override
    public void setChecked(final boolean checked) {
        if (mChecked == checked)
            return;
        mChecked = checked;
        refreshDrawableState();
    }
}
