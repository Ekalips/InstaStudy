package com.ekalips.instastudy.main.mvvm.view.group_chat.attachments;

import android.Manifest;
import android.support.v4.content.PermissionChecker;

import com.ekalips.instastudy.R;
import com.ekalips.instastudy.databinding.ChatAttachmentPhotoBinding;
import com.ekalips.instastudy.main.contract.chat.attachments.AttachmentTakePhotoContract;
import com.wonderslab.base.BR;
import com.wonderslab.base.event_system.Event;
import com.wonderslab.base.event_system.EventNavigate;
import com.wonderslab.base.fragment.BaseBindingFragment;

import java.io.File;

import io.fotoapparat.Fotoapparat;
import io.fotoapparat.parameter.ScaleType;
import io.fotoapparat.parameter.selector.FlashSelectors;
import io.fotoapparat.parameter.selector.FocusModeSelectors;
import io.fotoapparat.parameter.selector.LensPositionSelectors;
import io.fotoapparat.parameter.selector.Selectors;
import io.fotoapparat.parameter.selector.SizeSelectors;

/**
 * Created by ekalips on 11/29/17.
 */

public class AttachmentTakeImageFragment extends BaseBindingFragment<ChatAttachmentPhotoBinding, AttachmentTakePhotoContract.View, AttachmentTakePhotoContract.ViewModel>
        implements AttachmentTakePhotoContract.View {

    private Fotoapparat fotoapparat;
    private boolean started;

    @Override
    protected int layoutResId() {
        return R.layout.chat_attachment_photo;
    }

    @Override
    public int getViewModelBRId() {
        return BR.vm;
    }

    @Override
    public AttachmentTakePhotoContract.View getViewInterface() {
        return this;
    }

    @Override
    public void handleEvent(Event event) {

    }

    @Override
    protected void handleNavigationEvent(EventNavigate eventNavigate) {

    }

    @Override
    public void onBindingReady(ChatAttachmentPhotoBinding binding) {
        super.onBindingReady(binding);
        fotoapparat = Fotoapparat.with(getContext())
                .into(binding.cameraView)
                .previewScaleType(ScaleType.CENTER_CROP)
                .photoSize(SizeSelectors.biggestSize())
                .lensPosition(LensPositionSelectors.back())
                .focusMode(Selectors.firstAvailable(
                        FocusModeSelectors.continuousFocus(),
                        FocusModeSelectors.autoFocus(),
                        FocusModeSelectors.fixed()))
                .flash(Selectors.firstAvailable(      // (optional) similar to how it is done for focus mode, this time for flash
                        FlashSelectors.autoRedEye(),
                        FlashSelectors.autoFlash(),
                        FlashSelectors.torch(),
                        FlashSelectors.off()))
                .build();
    }

    @Override
    public void onResume() {
        super.onResume();

        boolean permissionsGranted = PermissionChecker.checkSelfPermission(getContext(), Manifest.permission.CAMERA) == PermissionChecker.PERMISSION_GRANTED;
        binding.setPermissionsGranted(permissionsGranted);
        startFotoaparatIfAvailable();
    }

    @Override
    public void onStart() {
        super.onStart();
        startFotoaparatIfAvailable();
    }

    @Override
    public void onStop() {
        super.onStop();
        stopFotoaparatIfAvailable();
    }

    private void startFotoaparatIfAvailable() {
        if (binding.getPermissionsGranted() && !started) {
            started = true;
            fotoapparat.start();
        }
    }

    private void stopFotoaparatIfAvailable() {
        if (started) {
            fotoapparat.stop();
        }
    }

    @Override
    public void requestPermissions() {
        requestPermissions(new String[]{Manifest.permission.CAMERA}, 0);
    }

    @Override
    public void takePicture(File imageFile) {
        fotoapparat.takePicture().saveToFile(imageFile).whenAvailable(aVoid -> getViewModel().onPictureTaken(imageFile));
    }
}
