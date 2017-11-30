package com.ekalips.instastudy.main.mvvm.view.group_chat.attachments;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.PermissionChecker;
import android.transition.TransitionManager;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import com.ekalips.instastudy.R;
import com.ekalips.instastudy.databinding.ChatAttachmentGalleryBinding;
import com.ekalips.instastudy.main.contract.chat.attachments.AttachmentGalleryContract;
import com.ekalips.instastudy.main.mvvm.model.GalleryRecyclerViewAdapter;
import com.wonderslab.base.BR;
import com.wonderslab.base.event_system.Event;
import com.wonderslab.base.event_system.EventNavigate;
import com.wonderslab.base.fragment.BaseBindingFragment;

/**
 * Created by ekalips on 11/30/17.
 */

public class AttachmentGalleryFragment extends BaseBindingFragment<ChatAttachmentGalleryBinding, AttachmentGalleryContract.View, AttachmentGalleryContract.ViewModel> implements AttachmentGalleryContract.View {
    @Override
    protected int layoutResId() {
        return R.layout.chat_attachment_gallery;
    }

    @Override
    public int getViewModelBRId() {
        return BR.vm;
    }

    @Override
    public AttachmentGalleryContract.View getViewInterface() {
        return this;
    }

    @Override
    public void handleEvent(Event event) {

    }

    @Override
    protected void handleNavigationEvent(EventNavigate eventNavigate) {

    }

    private int screenH;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        screenH = getResources().getDisplayMetrics().heightPixels;
    }

    @Override
    public void onBindingReady(ChatAttachmentGalleryBinding binding) {
        super.onBindingReady(binding);
        binding.getRoot().getViewTreeObserver().addOnGlobalLayoutListener(layoutListener);
        binding.recyclerView.setAdapter(new GalleryRecyclerViewAdapter(adapterCallbacks));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding.getRoot().getViewTreeObserver().removeOnGlobalLayoutListener(layoutListener);
    }

    @Override
    public void onResume() {
        super.onResume();

        boolean permissionsGranted = PermissionChecker.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PermissionChecker.PERMISSION_GRANTED;
        binding.setPermissionsGranted(permissionsGranted);
        if (permissionsGranted) {
            getViewModel().fetchImages();
        }
    }

    @Override
    public void requestPermissions() {
        requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
    }

    private final GalleryRecyclerViewAdapter.AdapterCallbacks adapterCallbacks = file -> getViewModel().selectFile(file);

    private final ViewTreeObserver.OnGlobalLayoutListener layoutListener = this::checkHeightAndDisplayToolbar;

    private void checkHeightAndDisplayToolbar() {
        boolean makeToolbarVisible = (float) binding.getRoot().getHeight() / (float) screenH > 0.8f;
        if (binding.getToolbarVisible() != makeToolbarVisible) {
            TransitionManager.beginDelayedTransition((ViewGroup) binding.getRoot());
            binding.setToolbarVisible(makeToolbarVisible);
        }
    }
}
