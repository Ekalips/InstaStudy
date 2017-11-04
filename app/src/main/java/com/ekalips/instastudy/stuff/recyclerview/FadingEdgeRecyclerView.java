package com.ekalips.instastudy.stuff.recyclerview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.Px;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by Ekalips on 10/25/17.
 */

public class FadingEdgeRecyclerView extends RecyclerView {

    @Px
    private final int fadingEdgeRequiredOffset = 50;

    public FadingEdgeRecyclerView(Context context) {
        super(context);
    }

    public FadingEdgeRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FadingEdgeRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected float getTopFadingEdgeStrength() {
        int offset = computeVerticalScrollOffset();
        if (offset <= 0) {
            return 0;
        } else if (offset > fadingEdgeRequiredOffset) {
            return 1;
        } else {
            return (float) offset / (float) fadingEdgeRequiredOffset;
        }
    }

    @Override
    protected float getBottomFadingEdgeStrength() {
        int realH = computeVerticalScrollRange();
        int viewH = computeVerticalScrollExtent();
        int maxOffset = (realH - viewH);
        int bottomOffset = maxOffset - computeVerticalScrollOffset();

        if (realH == viewH) { // this means that view can't be scrolled yet, so we don't need fading edge at all
            return 0;
        } else if (bottomOffset >= fadingEdgeRequiredOffset) {
            return 1;
        } else {
            return (float) bottomOffset / (float) fadingEdgeRequiredOffset;
        }
    }
}
