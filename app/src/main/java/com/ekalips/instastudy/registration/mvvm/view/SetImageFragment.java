package com.ekalips.instastudy.registration.mvvm.view;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.ekalips.instastudy.R;
import com.ekalips.instastudy.databinding.FragmentSetImageBinding;
import com.ekalips.instastudy.registration.contract.SetImageScreenContract;
import com.ekalips.instastudy.stuff.GlideImageLoader;
import com.esafirm.imagepicker.features.ImagePicker;
import com.esafirm.imagepicker.features.camera.DefaultCameraModule;
import com.esafirm.imagepicker.model.Image;
import com.wonderslab.base.BR;
import com.wonderslab.base.event_system.Event;
import com.wonderslab.base.event_system.EventNavigate;
import com.wonderslab.base.fragment.BaseBindingFragment;

import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class SetImageFragment extends BaseBindingFragment<FragmentSetImageBinding, SetImageScreenContract.View, SetImageScreenContract.ViewModel> implements SetImageScreenContract.View {


    private static final int REQUEST_GALLERY = 0;
    private static final int REQUEST_CAMERA = 1;

    public static SetImageFragment newInstance() {
        Bundle args = new Bundle();
        SetImageFragment fragment = new SetImageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private final DefaultCameraModule defaultCameraModule = new DefaultCameraModule();

    @Override
    protected int layoutResId() {
        return R.layout.fragment_set_image;
    }

    @Override
    public int getViewModelBRId() {
        return BR.vm;
    }

    @Override
    public SetImageScreenContract.View getViewInterface() {
        return this;
    }

    @Override
    public void handleEvent(Event event) {

    }

    @Override
    protected void handleNavigationEvent(EventNavigate eventNavigate) {

    }

    @Override
    public void requestImageFromCamera() {
        startActivityForResult(defaultCameraModule.getCameraIntent(getContext()), REQUEST_CAMERA);
    }

    @Override
    public void requestImageFromGallery() {
        ImagePicker.create(this)
                .returnAfterFirst(true)
                .single()
                .showCamera(false)
                .imageLoader(new GlideImageLoader())
                .start(REQUEST_GALLERY);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != RESULT_OK || data == null) {
            return;
        }

        switch (requestCode) {
            case REQUEST_CAMERA: {
                handleCameraRequestResult(data);
                break;
            }
            case REQUEST_GALLERY: {
                handleGalleryRequestResult(data);
                break;
            }
        }
    }

    private void handleCameraRequestResult(Intent data) {
        defaultCameraModule.getImage(getContext(), data, this::onImagesPicked);
    }

    private void handleGalleryRequestResult(Intent data) {
        onImagesPicked(ImagePicker.getImages(data));
    }

    private void onImagesPicked(List<Image> images) {
        if (images != null && images.size() > 0) {
            getViewModel().onImageSelected(images.get(0));
        }
    }
}
