package com.ekalips.instastudy.profile.mvvm.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.TypedValue;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.ekalips.instastudy.R;
import com.ekalips.instastudy.databinding.DialogProfileBinding;
import com.ekalips.instastudy.profile.ProfileContract;
import com.wonderslab.base.BR;
import com.wonderslab.base.event_system.Event;
import com.wonderslab.base.event_system.EventNavigate;
import com.wonderslab.base.fragment.BaseBindingBottomSheetDialogFragment;

public class ProfileDialog extends BaseBindingBottomSheetDialogFragment<DialogProfileBinding, ProfileContract.View, ProfileContract.ViewModel> implements ProfileContract.View {

    private static final String EXTRA_USER_ID = "user_id";

    public static ProfileDialog newInstance(String userId) {
        ProfileDialog dialog = new ProfileDialog();
        Bundle args = new Bundle(1);
        args.putString(EXTRA_USER_ID, userId);
        dialog.setArguments(args);
        return dialog;
    }


    @Override
    protected int layoutResId() {
        return R.layout.dialog_profile;
    }

    @Override
    public int getViewModelBRId() {
        return BR.vm;
    }

    @Override
    public ProfileContract.View getViewInterface() {
        return this;
    }

    @Override
    public void handleEvent(Event event) {

    }

    @Override
    protected void handleNavigationEvent(EventNavigate eventNavigate) {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        extractAndInit();
    }

    private void extractAndInit() {
        String userId = getArguments().getString(EXTRA_USER_ID);
        getViewModel().init(userId);
    }

    @Override
    public void showEnterCodeDialog() {
        FrameLayout frameLayout = new FrameLayout(getContext());
        int padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16, getResources().getDisplayMetrics());
        frameLayout.setPadding(padding, padding, padding, padding);
        EditText text = new EditText(getContext());
        text.setHint(R.string.hint_access_code);
        frameLayout.addView(text);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(R.string.enter_code_dialog_title)
                .setView(frameLayout)
                .setPositiveButton(R.string.confirm, (dialog, which) -> getViewModel().sendRiseCode(text.getText().toString()))
                .setNegativeButton(android.R.string.cancel, null)
                .show();
    }
}
