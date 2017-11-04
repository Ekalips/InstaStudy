package com.ekalips.instastudy.stuff;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.esafirm.imagepicker.features.imageloader.ImageLoader;
import com.esafirm.imagepicker.features.imageloader.ImageType;

/**
 * Created by Ekalips on 11/2/17.
 */

public class GlideImageLoader implements ImageLoader {
    @Override
    public void loadImage(String s, ImageView imageView, ImageType imageType) {
        Glide.with(imageView.getContext()).load(s).into(imageView);
    }
}
