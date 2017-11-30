package com.ekalips.instastudy.main.mvvm.vm.group_chat.attachments;

import android.databinding.ObservableField;
import android.util.Log;

import com.ekalips.instastudy.main.contract.chat.attachments.AttachmentGalleryContract;
import com.ekalips.instastudy.main.contract.chat.attachments.ChatAttachmentContract;
import com.ekalips.instastudy.main.mvvm.model.SelectableFile;
import com.ekalips.instastudy.providers.FilesProvider;
import com.wonderslab.base.rx.RxRequests;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by ekalips on 11/30/17.
 */

public class AttachmentGalleryViewModel extends AttachmentGalleryContract.ViewModel {

    private static final String TAG = AttachmentGalleryViewModel.class.getSimpleName();

    private final ObservableField<List<SelectableFile>> files = new ObservableField<>(new ArrayList<>());
    private final ObservableField<List<SelectableFile>> selectedFiles = new ObservableField<>(new ArrayList<>());

    private final FilesProvider filesProvider;
    private final ChatAttachmentContract.ViewModel parentDialogVM;

    @Inject
    public AttachmentGalleryViewModel(RxRequests rxRequests, FilesProvider filesProvider, ChatAttachmentContract.ViewModel parentDialogVM) {
        super(rxRequests);
        this.parentDialogVM = parentDialogVM;
        this.filesProvider = filesProvider;
    }

    @Override
    public ObservableField<List<SelectableFile>> getImages() {
        return files;
    }

    @Override
    public ObservableField<List<SelectableFile>> getSelectedFiles() {
        return selectedFiles;
    }

    @Override
    public void fetchImages() {
        if (files.get().size() == 0) {
            addDisposable(filesProvider.getImages().map(SelectableFile::new).subscribe(this::onNewFile, this::onFilesGetError, files::notifyChange));
        }
    }

    private void onNewFile(SelectableFile file) {
        files.get().remove(file);
        files.get().add(file);
    }

    private void onFilesGetError(Throwable throwable) {
        Log.e(TAG, "onFilesGetError: ", throwable);
    }

    @Override
    public void requestPermissions() {
        if (view != null) {
            view.requestPermissions();
        }
    }

    @Override
    public void selectFile(SelectableFile selectableFile) {
        boolean changed = false;
        if (selectableFile.isSelected()) {
            changed = selectedFiles.get().remove(selectableFile);
        } else {
            if (selectedFiles.get().size() < 9) {
                changed = selectedFiles.get().add(selectableFile);
            }
        }
        if (changed) {
            selectableFile.setSelected(!selectableFile.isSelected());
            selectedFiles.notifyChange();
        }
    }

    @Override
    public void goBack() {
        parentDialogVM.goBack();
    }

    @Override
    public void unselectAll() {
        for (SelectableFile file :
                selectedFiles.get()) {
            file.setSelected(false);
        }
        selectedFiles.get().clear();
        selectedFiles.notifyChange();
    }

    @Override
    public void sendSelected() {
        if (selectedFiles.get().size() == 0) {
            goBack();
        } else {
            // TODO: 12/1/17 Send files
        }
    }
}
