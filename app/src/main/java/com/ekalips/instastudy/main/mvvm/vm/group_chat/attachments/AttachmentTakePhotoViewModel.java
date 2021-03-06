package com.ekalips.instastudy.main.mvvm.vm.group_chat.attachments;

import android.databinding.ObservableField;

import com.ekalips.instastudy.main.contract.chat.attachments.AttachmentTakePhotoContract;
import com.ekalips.instastudy.main.contract.chat.attachments.ChatAttachmentContract;
import com.ekalips.instastudy.providers.FilesProvider;
import com.wonderslab.base.rx.RxRequests;

import java.io.File;

import javax.inject.Inject;

/**
 * Created by ekalips on 11/29/17.
 */

public class AttachmentTakePhotoViewModel extends AttachmentTakePhotoContract.ViewModel {

    private final FilesProvider filesProvider;
    private final ObservableField<File> takenFile = new ObservableField<>();
    private final ChatAttachmentContract.ViewModel parentVM;

    @Inject
    public AttachmentTakePhotoViewModel(RxRequests rxRequests, FilesProvider filesProvider, ChatAttachmentContract.ViewModel parentVM) {
        super(rxRequests);
        this.filesProvider = filesProvider;
        this.parentVM = parentVM;
    }

    @Override
    public void requestPermissions() {
        if (view != null) {
            view.requestPermissions();
        }
    }

    @Override
    public void takePicture() {
        if (view != null) {
            view.takePicture(filesProvider.createImageFile());
        }
    }

    @Override
    public void onPictureTaken(File imageFile) {
        takenFile.set(imageFile);
    }

    @Override
    public ObservableField<File> getTakenFile() {
        return takenFile;
    }

    @Override
    public void undo() {
        takenFile.set(null);
    }

    @Override
    public void send() {
        if (takenFile.get() != null) {
            parentVM.sendFiles(takenFile.get());
        }
    }

    @Override
    public void switchCamera() {
        if (view != null) {
            view.switchCamera();
        }
    }
}
