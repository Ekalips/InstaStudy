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

    private int borderColor;
    private float borderWidth;
    private boolean borderEnabled = false;

    private final Path clipPath = new Path();
    private final RectF roundedRect = new RectF();
    private final PaintFlagsDrawFilter drawFilter;

    private final Paint borderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public RoundableImageView(Context context) {
        this(context, null);
    }

    public RoundableImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundableImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs);
        invalidateBorderPaint();
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
        borderColor = typedArray.getColor(R.styleable.RoundableImageView_r_borderColor, Color.BLACK);
        borderWidth = typedArray.getDimension(R.styleable.RoundableImageView_r_borderWidth, 0f);
        borderEnabled = typedArray.getBoolean(R.styleable.RoundableImageView_r_border, false);
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
        if (borderEnabled && borderWidth > 0) {
            canvas.drawPath(clipPath, borderPaint);
        }
    }

    public boolean isCircleImage() {
        return circleImage;
    }

    public float getCornerRadius() {
        return cornerRadius;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public int getBorderColor() {
        return borderColor;
    }

    public float getBorderWidth() {
        return borderWidth;
    }

    public boolean isBorderEnabled() {
        return borderEnabled;
    }

    public void setCircleImage(boolean circleImage) {
        this.circleImage = circleImage;
        invalidate();
    }

    public void setCornerRadius(float cornerRadius) {
        this.cornerRadius = cornerRadius;
        invalidate();
    }

    @Override
    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
        invalidate();
    }

    public void setBorderColor(int borderColor) {
        this.borderColor = borderColor;
        invalidateBorderPaint();
        invalidate();
    }

    public void setBorderWidth(float borderWidth) {
        this.borderWidth = borderWidth;
        invalidateBorderPaint();
        invalidate();
    }

    public void setBorderEnabled(boolean borderEnabled) {
        this.borderEnabled = borderEnabled;
        invalidateBorderPaint();
        invalidate();
    }

    private void invalidateBorderPaint() {
        borderPaint.setColor(borderColor);
        borderPaint.setStyle(Paint.Style.STROKE);
        borderPaint.setStrokeWidth(borderWidth);
    }
}
