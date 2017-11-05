package com.ekalips.instastudy.stuff.custom_views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.ViewOutlineProvider;

import com.ekalips.instastudy.R;

/**
 * Created by Ekalips on 10/20/17.
 */

public class RoundableImageView extends AppCompatImageView {

    private boolean circleImage = false;
    private float cornerRadius = 0f;
    private int backgroundColor;

    private Path clipPath = new Path();
    private RectF roundedRect = new RectF();
    private PaintFlagsDrawFilter drawFilter;

    public RoundableImageView(Context context) {
        this(context, null);
    }

    public RoundableImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundableImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs);
        drawFilter = new PaintFlagsDrawFilter(Paint.ANTI_ALIAS_FLAG, Paint.DITHER_FLAG);
    }


    @Override
    public ViewOutlineProvider getOutlineProvider() {
        return super.getOutlineProvider();
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundableImageView);
        circleImage = typedArray.getBoolean(R.styleable.RoundableImageView_circle, false);
        cornerRadius = typedArray.getDimension(R.styleable.RoundableImageView_cornersRadius, 0f);
        backgroundColor = typedArray.getColor(R.styleable.RoundableImageView_backgroundColor, Color.TRANSPARENT);
        typedArray.recycle();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        canvas.setDrawFilter(drawFilter);
        clipPath.rewind();
        roundedRect.set(0, 0, getWidth(), getHeight());
        clipPath.addRoundRect(roundedRect, circleImage ? getWidth() / 2f : cornerRadius, circleImage ? getHeight() / 2f : cornerRadius, Path.Direction.CCW);
        clipPath.close();
        canvas.clipPath(clipPath);
        canvas.drawColor(backgroundColor);
        super.onDraw(canvas);

    }

    public boolean isCircle() {
        return circleImage;
    }

    public void setCircle(boolean circleImage) {
        this.circleImage = circleImage;
        invalidate();
    }

    public float getCornerRadius() {
        return cornerRadius;
    }

    public void setCornerRadius(float cornerRadius) {
        this.cornerRadius = cornerRadius;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    @Override
    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Path getClipPath() {
        return clipPath;
    }

    public void setClipPath(Path clipPath) {
        this.clipPath = clipPath;
    }
}
