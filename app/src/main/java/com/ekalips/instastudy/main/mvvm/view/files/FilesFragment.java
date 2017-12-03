package com.ekalips.instastudy.main.mvvm.view.files;


import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.PermissionChecker;
import android.support.v7.widget.DividerItemDecoration;
import android.widget.Toast;

import com.ekalips.instastudy.BR;
import com.ekalips.instastudy.R;
import com.ekalips.instastudy.data.files.models.Directory;
import com.ekalips.instastudy.data.files.models.File;
import com.ekalips.instastudy.databinding.FragmentFilesBinding;
import com.ekalips.instastudy.main.contract.FilesScreenContract;
import com.ekalips.instastudy.main.contract.MainActivityContract;
import com.ekalips.instastudy.main.mvvm.model.files.FilesRecyclerViewAdapter;
import com.ekalips.instastudy.stuff.StringUtils;
import com.wonderslab.base.event_system.Event;
import com.wonderslab.base.event_system.EventNavigate;
import com.wonderslab.base.fragment.BaseBindingFragment;

import javax.inject.Inject;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class FilesFragment extends BaseBindingFragment<FragmentFilesBinding, FilesScreenContract.View, FilesScreenContract.ViewModel> implements FilesScreenContract.View {

    private static final String ARG_GROUP = "group_id";
    private static final String ARG_PATH = "path";

    private static final int FILE_SELECT_CODE = 0;


    public static FilesFragment newInstance(String groupId, String path) {
        Bundle args = new Bundle();
        args.putString(ARG_GROUP, groupId);
        args.putString(ARG_PATH, path);
        FilesFragment fragment = new FilesFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int layoutResId() {
        return R.layout.fragment_files;
    }

    @Override
    public int getViewModelBRId() {
        return BR.vm;
    }

    @Override
    public FilesScreenContract.View getViewInterface() {
        return this;
    }

    @Override
    public void handleEvent(Event event) {

    }

    @Override
    protected void handleNavigationEvent(EventNavigate eventNavigate) {

    }

    @Inject
    MainActivityContract.FlexibleMainToolbar flexibleMainToolbar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        flexibleMainToolbar.onChange(getString(R.string.files_tile), -1);

        extractAndInit();
    }

    @Override
    public void onBindingReady(FragmentFilesBinding binding) {
        super.onBindingReady(binding);
        binding.recyclerView.setAdapter(new FilesRecyclerViewAdapter(adapterCallbacks));
        binding.recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
    }

    private void extractAndInit() {
        String groupId = getArguments().getString(ARG_GROUP);
        String path = getArguments().getString(ARG_PATH);
        getViewModel().init(groupId, path);
    }

    private final FilesRecyclerViewAdapter.AdapterCallbacks adapterCallbacks = new FilesRecyclerViewAdapter.AdapterCallbacks() {
        @Override
        public void onDownloadFileClicked(File file) {
            getViewModel().onDownloadFile(file);
        }

        @Override
        public void onDirectoryClicked(Directory directory) {
            getViewModel().onOpenDirectory(directory);
        }
    };

    @Override
    public void showFileChooser() {
        if (PermissionChecker.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PermissionChecker.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
            return;
        }


        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        try {
            startActivityForResult(
                    Intent.createChooser(intent, getString(R.string.file_selector_title)),
                    FILE_SELECT_CODE);
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getContext(), R.string.error_no_file_manager,
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == FILE_SELECT_CODE) {
            Uri uri = data.getData();
            if (uri != null)
                getViewModel().onFileSelected(uri);
        }
    }

    @Override
    public void openUrl(String url) {
        if (!StringUtils.isEmpty(url)) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(Intent.createChooser(browserIntent, "Browser"));
        }
    }
}
