package com.ekalips.instastudy.stuff;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ekalips.instastudy.data.groups.Group;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.io.File;

/**
 * Created by Ekalips on 10/3/17.
 */

public class Bindings {

    @BindingAdapter({"background"})
    public static void setBackground(View view, Drawable drawable) {
        view.setBackground(drawable);
    }

    @BindingAdapter({"android:visibility"})
    public static void setVisibility(View view, boolean visible) {
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    @BindingAdapter({"src"})
    public static void setImageToIv(ImageView imageView, String src) {
        Glide.with(imageView.getContext()).load(src).apply(RequestOptions.centerCropTransform()).into(imageView);
    }

    @BindingAdapter({"errorEnabled", "error"})
    public static void setError(TextInputLayout textInputLayout, boolean errorEnabled, String errorMessage) {
        textInputLayout.setErrorEnabled(errorEnabled);
        if (errorEnabled) {
            textInputLayout.setError(errorMessage);
        }
    }

    @BindingAdapter({"src", "placeholder"})
    public static void setImageWithPlaceholder(ImageView imageView, String src, Drawable placeholder) {
        Glide.with(imageView.getContext()).load(src).apply(RequestOptions.centerCropTransform().placeholder(placeholder)).into(imageView);
    }

    @BindingAdapter("tint")
    public static void setTintFromString(ImageView imageView, String text) {
        ColorGenerator colorGenerator = ColorGenerator.MATERIAL;
        int color = colorGenerator.getColor(text != null ? text : "");
        color = CommonUtils.adjustAlpha(color, 0.5f);
        imageView.setColorFilter(color);
    }

    @BindingAdapter({"src", "text"})
    public static void setImageSrcWithPlaceholderText(ImageView imageView, String src, String text) {
        String placeholderText = StringUtils.getFirstLetters(text);
        TextDrawable placeholder = TextDrawable.builder()
                .buildRound(placeholderText, ColorGenerator.MATERIAL.getColor(text != null ? text : ""));
        Glide.with(imageView.getContext()).load(src).apply(RequestOptions.centerCropTransform().placeholder(placeholder)).into(imageView);
    }

    @BindingAdapter({"src", "text", "file"})
    public static void setImageWithLinkOrFile(ImageView imageView, String src, String text, File file) {
        if (file == null) {
            setImageSrcWithPlaceholderText(imageView, src, text);
        } else {
            setFileSrc(imageView, file);
        }
    }

    @BindingAdapter({"file", "placeholder"})
    public static void setImageWithPlaceholder(ImageView imageView, File src, Drawable placeholder) {
        Glide.with(imageView.getContext()).load(src).apply(RequestOptions.centerCropTransform().placeholder(placeholder)).into(imageView);
    }

    @BindingAdapter(("shimmer"))
    public static void setShimmerEnabled(ShimmerFrameLayout view, boolean enabled) {
        if (enabled && !view.isAnimationStarted()) {
            view.startShimmerAnimation();
            return;
        }
        if (!enabled && view.isAnimationStarted()) {
            view.stopShimmerAnimation();
        }
    }

    @BindingAdapter(("android:text"))
    public static void setGroupText(TextView textView, Group group) {
        if (group != null) {
            textView.setText(group.getTitle());
        }
    }

    @BindingAdapter("android:layout_gravity")
    public static void setGravityToView(View view, int gravity) {
        if (view.getLayoutParams() instanceof FrameLayout.LayoutParams) {
            ((FrameLayout.LayoutParams) view.getLayoutParams()).gravity = gravity;
            view.requestLayout();
        }
    }

    @BindingAdapter("src")
    public static void setFileSrc(ImageView imageView, File file){
        Glide.with(imageView.getContext()).load(file).apply(RequestOptions.centerCropTransform()).into(imageView);
    }
}
