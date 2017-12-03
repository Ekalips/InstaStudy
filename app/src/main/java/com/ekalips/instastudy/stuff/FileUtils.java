package com.ekalips.instastudy.stuff;

import android.graphics.drawable.Drawable;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;

/**
 * Created by djqrj on 12/3/2017.
 */

public class FileUtils {

    public static String getFileExt(String fileName) {
        if (StringUtils.isEmpty(fileName)) {
            return "";
        }
        int lastDot = fileName.lastIndexOf(".");
        if (lastDot != -1) {
            String fileExt = fileName.substring(lastDot + 1);
            if (fileExt.length() > 3) {
                fileExt = fileExt.substring(0, 2);
            }
            return fileExt;
        }
        return "";
    }

    public static Drawable getFilePlaceholder(String fileName) {
        String ext = getFileExt(fileName);

        return TextDrawable.builder()
                .beginConfig()
                .toUpperCase()
                .endConfig()
                .buildRoundRect(ext, ColorGenerator.MATERIAL.getColor(ext), 16);

    }

}
