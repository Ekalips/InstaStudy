package com.ekalips.instastudy.stuff;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ekalips.instastudy.stuff.recyclerview.DataSetInterface;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.io.File;
import java.util.List;

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
    @SuppressWarnings("all")
    public static void setDataToRecyclerView(RecyclerView recyclerView, List data) {
        if (data != null && recyclerView.getAdapter() instanceof DataSetInterface) {
            ((DataSetInterface) recyclerView.getAdapter()).setData(data);
        }
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
                .buildRect(placeholderText, ColorGenerator.MATERIAL.getColor(text != null ? text : ""));
        Glide.with(imageView.getContext()).load(src).apply(RequestOptions.centerCropTransform().placeholder(placeholder)).into(imageView);
    }

    @BindingAdapter({"src", "text", "file"})
    public static void setImageWithLinkOrFile(ImageView imageView, String src, String text, File file) {
        if (file == null) {
            setImageSrcWithPlaceholderText(imageView, src, text);
        } else {
            Glide.with(imageView.getContext()).load(file).apply(RequestOptions.centerCropTransform()).into(imageView);
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

}
