package com.ekalips.instastudy.main.mvvm.view.files;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;

import com.ekalips.instastudy.BR;
import com.ekalips.instastudy.R;
import com.ekalips.instastudy.databinding.FragmentFilesBinding;
import com.ekalips.instastudy.main.contract.FilesScreenContract;
import com.ekalips.instastudy.main.mvvm.model.files.FilesRecyclerViewAdapter;
import com.wonderslab.base.event_system.Event;
import com.wonderslab.base.event_system.EventNavigate;
import com.wonderslab.base.fragment.BaseBindingFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class FilesFragment extends BaseBindingFragment<FragmentFilesBinding, FilesScreenContract.View, FilesScreenContract.ViewModel> implements FilesScreenContract.View {

    private static final String ARG_GROUP = "group_id";
    private static final String ARG_PATH = "path";


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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        extractAndInit();
    }

    @Override
    public void onBindingReady(FragmentFilesBinding binding) {
        super.onBindingReady(binding);
        binding.recyclerView.setAdapter(new FilesRecyclerViewAdapter());
        binding.recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
    }

    private void extractAndInit() {
        String groupId = getArguments().getString(ARG_GROUP);
        String path = getArguments().getString(ARG_PATH);
        getViewModel().init(groupId, path);
    }
}
