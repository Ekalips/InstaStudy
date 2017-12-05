package com.ekalips.instastudy.main.mvvm.view.group_chat.attachments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.FrameLayout;

import com.ekalips.instastudy.R;
import com.ekalips.instastudy.databinding.BottomDialogChatAttachmentBinding;
import com.ekalips.instastudy.main.contract.chat.attachments.ChatAttachmentContract;
import com.ekalips.instastudy.main.navigation.chat_navigation.AttachmentsLocalNavigator;
import com.wonderslab.base.BR;
import com.wonderslab.base.event_system.Event;
import com.wonderslab.base.event_system.EventNavigate;
import com.wonderslab.base.fragment.BaseBindingBottomSheetDialogFragment;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * Created by ekalips on 11/29/17.
 */

public class ChatAttachmentDialog extends BaseBindingBottomSheetDialogFragment<BottomDialogChatAttachmentBinding, ChatAttachmentContract.View, ChatAttachmentContract.ViewModel>
        implements ChatAttachmentContract.View, HasSupportFragmentInjector {

    private static final String TAG = ChatAttachmentDialog.class.getSimpleName();

    private static final String ARG_ID = "id";

    public static ChatAttachmentDialog newInstance(String id) {
        Bundle args = new Bundle();
        args.putString(ARG_ID, id);
        ChatAttachmentDialog fragment = new ChatAttachmentDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int layoutResId() {
        return R.layout.bottom_dialog_chat_attachment;
    }

    @Override
    public int getViewModelBRId() {
        return BR.vm;
    }

    @Override
    public ChatAttachmentContract.View getViewInterface() {
        return this;
    }

    @Override
    public void handleEvent(Event event) {

    }

    int startPeekH;
    int screenH;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        extractAndInit();
        startPeekH = (int) getResources().getDimension(R.dimen.bottom_sheet_init_height);
        screenH = getResources().getDisplayMetrics().heightPixels;
    }

    private void extractAndInit(){
        String groupId = getArguments().getString(ARG_ID);
        getViewModel().init(groupId);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.setOnShowListener(onShowListener);
        return dialog;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (getDialog() != null) {
            getDialog().setOnShowListener(null);
        }
    }

    @Inject
    AttachmentsLocalNavigator attachmentsLocalNavigator;
    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;

    @Override
    protected void handleNavigationEvent(EventNavigate eventNavigate) {
        switch ((ChatAttachmentContract.Pages) eventNavigate.getNavigationPlace()) {
            case TAKE_PHOTO: {
                attachmentsLocalNavigator.showTakeImageFragment();
                break;
            }
            case SELECT_IMAGE: {
                attachmentsLocalNavigator.showSelectImageFragment();
                break;
            }
        }
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }

    private final Dialog.OnShowListener onShowListener = new DialogInterface.OnShowListener() {
        @Override
        public void onShow(DialogInterface dialog) {
            BottomSheetDialog d = (BottomSheetDialog) dialog;
            FrameLayout bottomSheet = d.findViewById(android.support.design.R.id.design_bottom_sheet);
            if (bottomSheet != null) {
                BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
                bottomSheetBehavior.setPeekHeight(startPeekH);
                bottomSheetBehavior.setBottomSheetCallback(bottomSheetCallback);
                bottomSheetBehavior.setSkipCollapsed(true);
                bottomSheetBehavior.setHideable(false);
            }
        }
    };

    private final BottomSheetBehavior.BottomSheetCallback bottomSheetCallback = new BottomSheetBehavior.BottomSheetCallback() {
        @Override
        public void onStateChanged(@NonNull View bottomSheet, int newState) {
            switch (newState) {
                case BottomSheetBehavior.STATE_EXPANDED: {
                    hideControls();
                    break;
                }
                case BottomSheetBehavior.STATE_COLLAPSED: {
                    showControls();
                    break;
                }
            }

        }

        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {
            if (slideOffset >= 0) {
                binding.fragmentContainer.getLayoutParams().height = (int) (((screenH - startPeekH) * slideOffset) + startPeekH);
                binding.fragmentContainer.requestLayout();
            }
        }
    };

    private void hideControls() {
        binding.navigationContainer.animate().translationYBy(binding.navigationContainer.getHeight() * 0.75f).start();
    }

    private void showControls() {
        binding.navigationContainer.animate().translationY(0).start();
    }
}
